/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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

	private static Logger LOG = Logger.getLogger(OrderSegmentService.class);

	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto saveOrderSegmentQuantities(ProfileDetailsDto profileDetailsDto) {
		List<OrderSegmentDto> records = profileDetailsDto.getOrderSegments();
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getInProgressStatus();
		
		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			if(LOG.isTraceEnabled()) {
				LOG.trace("Header record does exist for the detail records passed in. System will update existing "
						+ "header and detail records for saving the profile.");
			}

			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateChangedAttributes(header, status, testRecord.getModifiedUserName(), profileDetailsDto.isNonCompliant());
			headerDao.update(header);
			
			profileDetailsDto.setOrderSegments(records);
			profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
			profileDetailsDto.setSuccessful(true);

			return profileDetailsDto;
		}
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("Header record does not exist for the detail records passed in. System will create new "
					+ "header and detail records for saving the profile.");
		}
		
		DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(testRecord, status, profileDetailsDto.isNonCompliant());
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto orderSegment : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
			saved.add(returnedSegment);
		}

		profileDetailsDto.setOrderSegments(saved);
		profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
		profileDetailsDto.setSuccessful(true);
		return profileDetailsDto;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto submitOrderSegmentQuantities(ProfileDetailsDto profileDetailsDto) {
		List<OrderSegmentDto> records = profileDetailsDto.getOrderSegments();
		List<OrderSegmentDto> submitted = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getPendingStatus();

		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			if(LOG.isTraceEnabled()) {
				LOG.trace("Header record does exist for the detail records passed in. System will update existing "
						+ "header and detail records for submitting the profile.");
			}

			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateExistingSubmittedHeader(header, status, profileDetailsDto.isNonCompliant());
			headerDao.update(header);
			
			for (OrderSegmentDto dto : records) {
				dto.setSubmittedDate(header.getSubmittedDate());
				logService.writeDealerChangesToLog(header, dto);
			}
			
			profileDetailsDto.setOrderSegments(records);
			profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
			profileDetailsDto.setSuccessful(true);
			return profileDetailsDto;
		}
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("Header record does not exist for the detail records passed in. System will create new "
					+ "header and detail records for submitting the profile.");
		}
		
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

		return profileDetailsDto;
	}
	
	public ProfileDetailsDto dsmApproveWithChanges(ProfileDetailsDto profileDetailsDto, String userName) {
		DealerProfileHeaderStatus status = statusService.getApprovedWithChangesStatus();
		
		return updateDataFromDsm(profileDetailsDto, status, userName);
	}
	
	public ProfileDetailsDto dsmSendToDealer(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getSendToDealerStatus();
		
		return updateDataFromDsm(profile, status, userName);
	}
	
	public ProfileDetailsDto dsmApproveAsRequested(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getApprovedAsRequestedStatus();
		
		return updateDataFromDsm(profile, status, userName);
	}
	
	public ProfileDetailsDto dsmSubmitForException(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getExceptionRequestedStatus();
		
		return updateDataFromDsm(profile, status, userName);
	}
	
	public ProfileDetailsDto dsmSaveChanges(ProfileDetailsDto profile, String userName) {
		DealerProfileHeaderStatus status = statusService.getPendingStatus();
		
		return updateDataFromDsm(profile, status, userName);
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	protected ProfileDetailsDto updateDataFromDsm(ProfileDetailsDto profile, DealerProfileHeaderStatus status, String userName) {
		List<OrderSegmentDto> orderSegments = profile.getOrderSegments();
		if(orderSegments.size() == 0) {
			LOG.error("No records passed in to do any work.  System will not make any changes.");
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return profile;
		}
		
		OrderSegmentDto testRecord = orderSegments.get(0);
		Integer headerId = testRecord.getHeaderId();
		if(headerId == null) {
			LOG.error("Profile detail records passed in did not contain an associated header record.  System will not "
					+ "make any changes.");
			
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return profile;
		}
		
		DealerProfileHeader header = headerDao.select(headerId);
		headerDataMapper.updateChangedAttributes(header, status, userName, isNonCompliant(profile));
		headerDao.update(header);
		
		for (OrderSegmentDto dto : orderSegments) {
			DealerProfileDetail detail = detailDao.select(dto.getId());
			detailDataMapper.updateDsmEnteredDetails(detail, dto, userName);
			detailDao.update(detail);
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
}
