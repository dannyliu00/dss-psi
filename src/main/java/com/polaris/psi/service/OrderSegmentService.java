/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileDetailDao;
import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.mapper.DetailDataMapper;
import com.polaris.psi.service.mapper.HeaderDataMapper;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.repository.CommonRepositoryConstants;

/**
 * @author bericks
 *
 */
@Service
public class OrderSegmentService {
	
	@Autowired
	LogService logService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	DealerProfileHeaderDao headerDao;
	
	@Autowired
	DealerProfileDetailDao detailDao;
	
	@Autowired
	HeaderDataMapper headerDataMapper;
	
	@Autowired
	DetailDataMapper detailDataMapper;
	
	@Autowired
	EmailService emailService;

	private static final SplunkLogger LOG = new SplunkLogger(OrderSegmentService.class);

	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto saveOrderSegmentQuantities(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "saveOrderSegmentQuantities");
		
		List<OrderSegmentDto> records = profileDetailsDto.getOrderSegments();
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getInProgressStatus();
		
		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			
			LOG.debug(PolarisIdentity.get(), "saveOrderSegmentQuantities", "Header record does exist for the detail records passed in. System will update existing "
					+ "header and detail records for saving the profile.");

			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateChangedAttributes(header, status, testRecord.getModifiedUserName(), profileDetailsDto.isNonCompliant());
			headerDao.update(header);
			
			profileDetailsDto.setOrderSegments(records);
			profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
			profileDetailsDto.setSuccessful(true);

			return profileDetailsDto;
		}
		
		LOG.debug(PolarisIdentity.get(), "saveOrderSegmentQuantities", "Header record does not exist for the detail records passed in. System will create new "
				+ "header and detail records for saving the profile.");
		
		DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(testRecord, status, profileDetailsDto.isNonCompliant());
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto orderSegment : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
			saved.add(returnedSegment);
		}

		profileDetailsDto.setOrderSegments(saved);
		profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
		profileDetailsDto.setSuccessful(true);
		
		LOG.methodEnd(PolarisIdentity.get(), "saveOrderSegmentQuantities");
		
		return profileDetailsDto;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto submitOrderSegmentQuantities(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "submitOrderSegmentQuantities");
		
		List<OrderSegmentDto> records = profileDetailsDto.getOrderSegments();
		List<OrderSegmentDto> submitted = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getPendingStatus();

		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			LOG.debug(PolarisIdentity.get(), "submitOrderSegmentQuantities", "Header record does exist for the detail records passed in. System will update existing "
					+ "header and detail records for saving the profile.");

			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateExistingSubmittedHeader(header, status, testRecord.getDealerEmail(), profileDetailsDto.isNonCompliant());
			headerDao.update(header);
			
			for (OrderSegmentDto dto : records) {
				dto.setSubmittedDate(header.getSubmittedDate());
				logService.writeDealerChangesToLog(header, dto);
			}
			
			// Send email.
			try {
				emailService.sendProfileSubmissionEmail(profileDetailsDto);
			} catch (Exception e) {
				LOG.error(PolarisIdentity.get(), "submitOrderSegmentQuantities", e);
			}
				 
			profileDetailsDto.setOrderSegments(records);
			profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
			profileDetailsDto.setSuccessful(true);
				 
			LOG.methodEnd(PolarisIdentity.get(), "submitOrderSegmentQuantities");

			return profileDetailsDto;
		}
		
		LOG.debug(PolarisIdentity.get(), "submitOrderSegmentQuantities", "Header record does not exist for the detail records passed in. System will create new "
				+ "header and detail records for saving the profile.");
		
		DealerProfileHeader header = headerDataMapper.createNewSubmittedHeader(testRecord, status, profileDetailsDto.isNonCompliant());
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto dto : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, dto);
			submitted.add(returnedSegment);
			logService.writeDealerChangesToLog(header, dto);
		}
		
		profileDetailsDto.setOrderSegments(submitted);
		profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
		profileDetailsDto.setSuccessful(true);
		
		// Send email.
		try {
			emailService.sendProfileSubmissionEmail(profileDetailsDto);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "submitOrderSegmentQuantities", e);
		}
 			 
		LOG.methodEnd(PolarisIdentity.get(), "submitOrderSegmentQuantities");

		return profileDetailsDto;
	}
	
	public ProfileDetailsDto dsmApproveWithChanges(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getApprovedWithChangesStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendApproveWithChangesEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto dsmSendToDealer(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getSendToDealerStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendReturnToDealerEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto dsmApproveAsRequested(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getApprovedAsRequestedStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendApproveAsRequestedEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto dsmSubmitForException(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getExceptionRequestedStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendSubmitForExceptionEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto dsmSaveChanges(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getPendingStatus();
		
		return updateDataFromDsm(profile, status, userName);
	}
	
	public ProfileDetailsDto rsmSaveChanges(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getExceptionRequestedStatus();
		
		return updateDataFromRsm(profile, status, userName);
	}
	
	public ProfileDetailsDto rsmSendToDsm(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getSendToDsmStatus();
		
		updateDataFromRsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendReturnToDsmEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto rsmApproveAsCompliant(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getApproveAsCompliantStatus();
		
		updateDataFromRsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendApproveAsCompliantEmail(profile);
		return profile;
	}
	
	public ProfileDetailsDto rsmApproveAsNonCompliant(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getApproveAsNonCompliantStatus();
		
		updateDataFromRsm(profile, status, userName);
		if(profile.isSuccessful()) emailService.sendApproveAsNonCompliantEmail(profile);
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	protected ProfileDetailsDto updateDataFromDsm(ProfileDetailsDto profile, DealerProfileHeaderStatus status, String userName) {
		if(areRecordsEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromDsm", "No records passed in to do any work.  System will not make any changes.");
			return profile;
		}
		
		if(isHeaderEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromDsm", "Profile detail records passed in did not contain an "
					+ "associated header record.  System will not make any changes.");
			return profile;
		}
		
		OrderSegmentDto testRecord = profile.getOrderSegments().get(0);
		Integer headerId = testRecord.getHeaderId();
		DealerProfileHeader header = headerDao.select(headerId);
		headerDataMapper.updateChangedAttributes(header, status, userName, isNonCompliant(profile));
		headerDao.update(header);
		
		for (OrderSegmentDto dto : profile.getOrderSegments()) {
			DealerProfileDetail detail = detailDao.select(dto.getId());
			detailDataMapper.updateDsmEnteredDetails(detail, dto, userName);
			if(status.getDescription().equals(Constants.RETURNED_TO_DEALER)) {
				detail.setDsmRecommendedQty(CommonUtils.setIntegerValue(null));
			}
			detailDao.update(detail);
			if(!status.getDescription().equals(Constants.PENDING_STATUS)) {
				logService.writeDsmChangesToLog(header, dto);
			}
		}
		
		profile.setMessage(Constants.SAVE_SUCCESSFUL);
		profile.setSuccessful(true);
		return profile;
	}

	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	protected ProfileDetailsDto updateDataFromRsm(ProfileDetailsDto profile, DealerProfileHeaderStatus status, String userName) {
		if(areRecordsEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromRsm", "No records passed in to do any work.  System will not make any changes.");
			return profile;
		}
		
		if(isHeaderEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromRsm", "Profile detail records passed in did not contain an "
					+ "associated header record.  System will not make any changes.");
			return profile;
		}
		
		OrderSegmentDto testRecord = profile.getOrderSegments().get(0);
		Integer headerId = testRecord.getHeaderId();
		DealerProfileHeader header = headerDao.select(headerId);
		headerDataMapper.updateChangedAttributes(header, status, userName, isNonCompliant(profile));
		headerDao.update(header);
		
		String description = status.getDescription();
		for (OrderSegmentDto dto : profile.getOrderSegments()) {
			DealerProfileDetail detail = detailDao.select(dto.getId());
			detailDataMapper.updateRsmEnteredDetails(detail, dto, userName);
			if(description.equals(Constants.RETURNED_TO_DSM)) {
				detail.setAdminApprovedQty(CommonUtils.setIntegerValue(null));
			}
			detailDao.update(detail);
			if(!description.equals(Constants.EXCEPTION_REQUESTED)) {
				logService.writeRsmChangesToLog(header, dto);
			}
		}
		
		profile.setMessage(Constants.SAVE_SUCCESSFUL);
		profile.setSuccessful(true);
		return profile;
	}

	protected OrderSegmentDto createOrderSegmentQty(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = detailDataMapper.createInitialDetail(orderSegment, header);
		DealerProfileDetail returnedDetail = detailDao.insert(detail);
		
		orderSegment.setId(returnedDetail.getId());
		orderSegment.setHeaderId(header.getId());
		
		return orderSegment;
	}

	protected void updateOrderSegmentQty(List<OrderSegmentDto> orderSegments) {
		for (OrderSegmentDto dto : orderSegments) {
			updateOrderSegmentQty(dto);
		}
	}
	
	protected void updateOrderSegmentQty(OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = detailDao.select(orderSegment.getId());
		detailDataMapper.updateDealerEnteredDetails(detail, orderSegment);
		detailDao.update(detail);
	}
	
	protected boolean isNonCompliant(ProfileDetailsDto profile) {
		if(areDetailsNonCompliant(profile.getOrderSegments())) return true;
		
		return profile.isNonCompliant();
	}
	
	protected boolean areDetailsNonCompliant(List<OrderSegmentDto> orderSegments) {
		for (OrderSegmentDto dto : orderSegments) {
			if(dto.isNonCompliant()) return true;
		}
		
		return false;
	}
	
	protected DealerProfileDetail getDetailsFromOrderSegment(List<DealerProfileDetail> details, OrderSegmentDto orderSegment) {
		for (DealerProfileDetail detail : details) {
			if(orderSegment.getHeaderId() == detail.getHeader().getId()
					&& orderSegment.getProfileOrderSegmentId() == detail.getProfileOrderSegmentId()
					&& orderSegment.getPeriodCode().equals(detail.getPeriodCode()))
				return detail;
		}
		
		return null;
	}
	
	protected boolean areRecordsEmpty(ProfileDetailsDto profile) {
		if(profile.getOrderSegments().size() == 0) {
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return true;
		}
		
		return false;
	}
	
	protected boolean isHeaderEmpty(ProfileDetailsDto profile) {
		OrderSegmentDto testRecord = profile.getOrderSegments().get(0);
		if(testRecord.getHeaderId() == null) {
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return true;
		}
		
		return false;
	}
}
