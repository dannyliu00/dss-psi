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
import com.polaris.psi.exception.UnknownHeaderException;
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
	
	@Autowired
	SegmentStockingProfileOrderService stockingProfileService;

	private static final SplunkLogger LOG = new SplunkLogger(OrderSegmentService.class);

	@Transactional(value = CommonRepositoryConstants.TX_MANAGER_POLMPLS, rollbackFor={
			UnknownHeaderException.class, 
			RuntimeException.class, 
			Exception.class})
	public ProfileDetailsDto saveOrderSegmentQuantities(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "saveOrderSegmentQuantities");
		
		List<OrderSegmentDto> records = profile.getOrderSegments();
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getInProgressStatus();
		
		OrderSegmentDto representative = records.get(0);
		
		// Act accordingly on whether or not a header record already exists.
		if(representative.getHeaderId() != null) {
			// Update existing header and detail records
			LOG.debug(PolarisIdentity.get(), "saveOrderSegmentQuantities", "Header record does exist for the detail records passed in. System will update existing "
					+ "header and detail records for saving the profile.");
			
			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(representative.getHeaderId());
			
			if(!isStatusHigherPriority(status, header)) {
				UnknownHeaderException e = new UnknownHeaderException("Unknown header record already exists for this Dealer (" + representative.getDealerId() + 
						") and Profile (" + representative.getProfileId() + "). Failing this create attempt.");
				throw e;
			}

			headerDataMapper.updateChangedAttributes(header, status, representative.getModifiedUserName(), profile.isNonCompliant());
			headerDao.update(header);
			
			profile.setOrderSegments(records);
			profile.setMessage(Constants.SAVE_SUCCESSFUL);
			profile.setSuccessful(true);

		} else {
			// Create new header and detail records
			LOG.debug(PolarisIdentity.get(), "saveOrderSegmentQuantities", "Header record does not exist for the detail records passed in. System will create new "
					+ "header and detail records for saving the profile.");
			
			// Fix for use case when user has multiple browser windows/tabs open editing the same dealer profile
			if(doesRecordExist(profile)) {
				UnknownHeaderException e = new UnknownHeaderException("Unknown header record already exists for this Dealer (" + representative.getDealerId() + 
						") and Profile (" + representative.getProfileId() + "). Failing this create attempt.");
				throw e;
			}
			
			DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(representative, status, profile.isNonCompliant());
			DealerProfileHeader returnedHeader = headerDao.insert(header);
			for (OrderSegmentDto orderSegment : records) {
				OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
				saved.add(returnedSegment);
			}

			profile.setOrderSegments(saved);
			profile.setMessage(Constants.SAVE_SUCCESSFUL);
			profile.setSuccessful(true);
			
		}

		LOG.methodEnd(PolarisIdentity.get(), "saveOrderSegmentQuantities");
		
		return profile;
	}
	
	@Transactional(value = CommonRepositoryConstants.TX_MANAGER_POLMPLS, rollbackFor={
			UnknownHeaderException.class, 
			RuntimeException.class, 
			Exception.class})
	public ProfileDetailsDto submitOrderSegmentQuantities(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "submitOrderSegmentQuantities");
	
		List<OrderSegmentDto> records = profile.getOrderSegments();
		List<OrderSegmentDto> submitted = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusService.getPendingStatus();

		// Act accordingly on whether or not a header record already exists.
		OrderSegmentDto representative = records.get(0);
		if(representative.getHeaderId() != null) {
			// Update existing header and detail records
			LOG.debug(PolarisIdentity.get(), "submitOrderSegmentQuantities", "Header record does exist for the detail records passed in. System will update existing "
					+ "header and detail records for saving the profile.");

			DealerProfileHeader header = headerDao.select(representative.getHeaderId());
			headerDataMapper.updateExistingSubmittedHeader(header, status, representative.getDealerEmail(), profile.isNonCompliant());
			headerDao.update(header);
			
			updateOrderSegmentQty(records);
			for (OrderSegmentDto dto : records) {
				dto.setSubmittedDate(header.getSubmittedDate());
				logService.writeDealerChangesToLog(header, dto);
			}
			
			// Send email.
			emailService.sendProfileSubmissionEmail(profile);
				 
			profile.setOrderSegments(records);
			profile.setMessage(Constants.SAVE_SUCCESSFUL);
			profile.setSuccessful(true);
				 
		} else {
			// Create new header and detail records
			LOG.debug(PolarisIdentity.get(), "submitOrderSegmentQuantities", "Header record does not exist for the detail records passed in. System will create new "
					+ "header and detail records for saving the profile.");
			
			// Fix for use case when user has multiple browser windows/tabs open editing the same dealer profile
			if(doesRecordExist(profile)) {
				UnknownHeaderException e = new UnknownHeaderException("Unknown header record already exists for this Dealer (" + representative.getDealerId() + 
						") and Profile (" + representative.getProfileId() + "). Failing this create attempt.");
				throw e;
			}
			
			DealerProfileHeader header = headerDataMapper.createNewSubmittedHeader(representative, status, profile.isNonCompliant());
			DealerProfileHeader returnedHeader = headerDao.insert(header);
			for (OrderSegmentDto dto : records) {
				OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, dto);
				submitted.add(returnedSegment);
				logService.writeDealerChangesToLog(header, dto);
			}
			
			profile.setOrderSegments(submitted);
			profile.setMessage(Constants.SAVE_SUCCESSFUL);
			profile.setSuccessful(true);
			
			// Send email.
			emailService.sendProfileSubmissionEmail(profile);
		}

		LOG.methodEnd(PolarisIdentity.get(), "submitOrderSegmentQuantities");

		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto dsmApproveWithChanges(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "dsmApproveWithChanges");
		
		DealerProfileHeaderStatus status = statusService.getApprovedWithChangesStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) {
			stockingProfileService.saveStockingProfiles(profile, userName);
			emailService.sendApproveWithChangesEmail(profile);
		}
		
		if(!profile.isSuccessful()) {
			LOG.error(PolarisIdentity.get(), "dsmApproveWithChanges", Constants.COULD_NOT_UPDATE_DSM_VALUES);
			profile.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
		}

		LOG.methodEnd(PolarisIdentity.get(), "dsmApproveWithChanges");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto dsmSendToDealer(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "dsmSendToDealer");
		
		DealerProfileHeaderStatus status = statusService.getSendToDealerStatus();
		
		updateDataFromDsm(profile, status, userName);
		
		if(profile.isSuccessful()) emailService.sendReturnToDealerEmail(profile);

		LOG.methodEnd(PolarisIdentity.get(), "dsmSendToDealer");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto dsmApproveAsRequested(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "dsmApproveAsRequested");
		
		DealerProfileHeaderStatus status = statusService.getApprovedAsRequestedStatus();
		
		updateDataFromDsm(profile, status, userName);

		if(profile.isSuccessful()) {
			stockingProfileService.saveStockingProfiles(profile, userName);
			emailService.sendApproveAsRequestedEmail(profile);
		}

		if(!profile.isSuccessful()) {
			LOG.error(PolarisIdentity.get(), "dsmApprovedAsRequested", Constants.COULD_NOT_UPDATE_DSM_VALUES);
			profile.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "dsmApproveAsRequested");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto dsmSubmitForException(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "dsmSubmitForException");
		
		DealerProfileHeaderStatus status = statusService.getExceptionRequestedStatus();
		
		updateDataFromDsm(profile, status, userName);
		if(profile.isSuccessful()) {
			emailService.sendSubmitForExceptionEmail(profile);
		}
		
		if(!profile.isSuccessful()) {
			LOG.error(PolarisIdentity.get(), "dsmSubmitForException", Constants.COULD_NOT_UPDATE_DSM_VALUES);
			profile.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
		}

		LOG.methodEnd(PolarisIdentity.get(), "dsmSubmitForException");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto dsmSaveChanges(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "dsmSaveChanges");
		
		DealerProfileHeaderStatus status = statusService.getPendingStatus();
		
		updateDataFromDsm(profile, status, userName);

		LOG.methodEnd(PolarisIdentity.get(), "dsmSaveChanges");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto rsmSaveChanges(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "rsmSaveChanges");
		
		DealerProfileHeaderStatus status = statusService.getExceptionRequestedStatus();

		LOG.methodEnd(PolarisIdentity.get(), "rsmSaveChanges");
		
		return updateDataFromRsm(profile, status, userName);
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto rsmSendToDsm(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "rsmSendToDsm");
		
		DealerProfileHeaderStatus status = statusService.getSendToDsmStatus();
		
		updateDataFromRsm(profile, status, userName);
		//if(profile.isSuccessful()) emailService.sendReturnToDsmEmail(profile);

		LOG.methodEnd(PolarisIdentity.get(), "rsmSendToDsm");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto rsmApproveAsCompliant(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "rsmApproveAsCompliant");
		
		DealerProfileHeaderStatus status = statusService.getApproveAsCompliantStatus();

		try {
			updateDataFromRsm(profile, status, userName);
			if(profile.isSuccessful()) {
				stockingProfileService.saveStockingProfiles(profile, userName);
				emailService.sendApproveAsCompliantEmail(profile);
			}

			if(!profile.isSuccessful()) {
				LOG.error(PolarisIdentity.get(), "rsmApproveAsCompliant", Constants.COULD_NOT_UPDATE_RSM_VALUES);
				profile.setMessage(Constants.COULD_NOT_UPDATE_RSM_VALUES);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "rsmApproveAsCompliant", e);
			profile.setSuccessful(false);
			profile.setMessage(Constants.COULD_NOT_UPDATE_RSM_VALUES);
		}

		LOG.methodEnd(PolarisIdentity.get(), "rsmApproveAsCompliant");
		
		return profile;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public ProfileDetailsDto rsmApproveAsNonCompliant(ProfileDetailsDto profile, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "rsmApproveAsNonCompliant");
		
		DealerProfileHeaderStatus status = statusService.getApproveAsNonCompliantStatus();
		
		try {
			updateDataFromRsm(profile, status, userName);

			if(profile.isSuccessful()) {
				stockingProfileService.saveStockingProfiles(profile, userName);
				emailService.sendApproveAsNonCompliantEmail(profile);
			}

			if(!profile.isSuccessful()) {
				LOG.error(PolarisIdentity.get(), "rsmApproveAsNonCompliant", Constants.COULD_NOT_UPDATE_RSM_VALUES);
				profile.setMessage(Constants.COULD_NOT_UPDATE_RSM_VALUES);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "rsmApproveAsCompliant", e);
			profile.setSuccessful(false);
			profile.setMessage(Constants.COULD_NOT_UPDATE_RSM_VALUES);
		}

		LOG.methodEnd(PolarisIdentity.get(), "rsmApproveAsNonCompliant");
		
		return profile;
	}
	
	protected ProfileDetailsDto updateDataFromDsm(ProfileDetailsDto profile, DealerProfileHeaderStatus status, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "updateDataFromDsm");
		
		if(areRecordsEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromDsm", "No records passed in to do any work.  System will not make any changes.");
			return profile;
		}
		
		if(isHeaderEmpty(profile)) {
			LOG.warn(PolarisIdentity.get(),"updateDataFromDsm", "Profile detail records passed in did not contain an "
					+ "associated header record.  System will not make any changes.");
			return profile;
		}
		
		OrderSegmentDto representative = profile.getOrderSegments().get(0);
		Integer headerId = representative.getHeaderId();
		DealerProfileHeader header = headerDao.select(headerId);
		String statusDescription = status.getDescription();
		
		// if DSM is approving the dealer profile then we need to set the approved values on the header
		if(statusDescription.equals(Constants.APPROVED_AS_REQUESTED) || statusDescription.equals(Constants.APPROVED_W_CHANGES)) {
			headerDataMapper.updateApprovedHeader(header, status, userName, isNonCompliant(profile));
		} else {
			headerDataMapper.updateChangedAttributes(header, status, userName, isNonCompliant(profile));
		}
		headerDao.update(header);
		
		for (OrderSegmentDto dto : profile.getOrderSegments()) {
			DealerProfileDetail detail = detailDao.select(dto.getId());
			detailDataMapper.updateDsmEnteredDetails(detail, dto, userName);
			
			// reset DSM quantities to defaults when sending back to dealer
			if(statusDescription.equals(Constants.RETURNED_TO_DEALER)) {
				detail.setDsmRecommendedQty(CommonUtils.setIntegerValue(null));
			}
			
			// if DSM is approving the record then we need to set final quantity value
			if(statusDescription.equals(Constants.APPROVED_AS_REQUESTED) || statusDescription.equals(Constants.APPROVED_W_CHANGES)) {
				detailDataMapper.updateApprovedDetails(detail, dto, statusDescription);
			}
			detailDao.update(detail);
			
			// log the approval step for audit purposes
			if(!statusDescription.equals(Constants.PENDING_STATUS)) {
				logService.writeDsmChangesToLog(header, dto);
			}
		}
		
		profile.setMessage(Constants.SAVE_SUCCESSFUL);
		profile.setSuccessful(true);

		LOG.methodEnd(PolarisIdentity.get(), "updateDataFromDsm");
		
		return profile;
	}

	protected ProfileDetailsDto updateDataFromRsm(ProfileDetailsDto profile, DealerProfileHeaderStatus status, String userName) {

		LOG.methodStart(PolarisIdentity.get(), "updateDataFromRsm");
		
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
		String statusDescription = status.getDescription();
		
		// if RSM is approving the dealer profile then we need to set the approved values on the header
		if(statusDescription.equals(Constants.APPROVED_COMPLIANT) || statusDescription.equals(Constants.APPROVED_NONCOMPLIANT)) {
			headerDataMapper.updateApprovedHeader(header, status, userName, isNonCompliant(profile));
		} else {
			headerDataMapper.updateChangedAttributes(header, status, userName, isNonCompliant(profile));
		}
		headerDao.update(header);
		
		for (OrderSegmentDto dto : profile.getOrderSegments()) {
			DealerProfileDetail detail = detailDao.select(dto.getId());
			detailDataMapper.updateRsmEnteredDetails(detail, dto, userName);
			
			// reset RSM quantities to defaults when sending back to DSM
			if(statusDescription.equals(Constants.RETURNED_TO_DSM)) {
				detail.setAdminApprovedQty(CommonUtils.setIntegerValue(null));
			}
			
			// if RSM is approving the record then we need to set final quantity value
			if(statusDescription.equals(Constants.APPROVED_COMPLIANT) || statusDescription.equals(Constants.APPROVED_NONCOMPLIANT)) {
				detailDataMapper.updateApprovedDetails(detail, dto, statusDescription);
			}
			detailDao.update(detail);

			// log the approval step for audit purposes
			if(!statusDescription.equals(Constants.EXCEPTION_REQUESTED)) {
				logService.writeRsmChangesToLog(header, dto);
			}
		}
		
		profile.setMessage(Constants.SAVE_SUCCESSFUL);
		profile.setSuccessful(true);

		LOG.methodEnd(PolarisIdentity.get(), "updateDataFromRsm");
		
		return profile;
	}

	protected OrderSegmentDto createOrderSegmentQty(DealerProfileHeader header, OrderSegmentDto orderSegment) {

		LOG.methodStart(PolarisIdentity.get(), "createOrderSegmentQty");
		
		DealerProfileDetail detail = detailDataMapper.createInitialDetail(orderSegment, header);
		DealerProfileDetail returnedDetail = detailDao.insert(detail);
		
		orderSegment.setId(returnedDetail.getId());
		orderSegment.setHeaderId(header.getId());

		LOG.methodEnd(PolarisIdentity.get(), "createOrderSegmentQty");
		
		return orderSegment;
	}

	protected void updateOrderSegmentQty(List<OrderSegmentDto> orderSegments) {

		LOG.methodStart(PolarisIdentity.get(), "updateOrderSegmentQty");
		
		for (OrderSegmentDto dto : orderSegments) {
			updateOrderSegmentQty(dto);
		}

		LOG.methodEnd(PolarisIdentity.get(), "updateOrderSegmentQty");
	}
	
	protected void updateOrderSegmentQty(OrderSegmentDto orderSegment) {

		LOG.methodStart(PolarisIdentity.get(), "updateOrderSegmentQty");
		
		DealerProfileDetail detail = detailDao.select(orderSegment.getId());
		detailDataMapper.updateDealerEnteredDetails(detail, orderSegment);
		detailDao.update(detail);

		LOG.methodEnd(PolarisIdentity.get(), "updateOrderSegmentQty");
	}
	
	protected boolean isNonCompliant(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "isNonCompliant");
		
		if(areDetailsNonCompliant(profile.getOrderSegments())) return true;
		
		LOG.methodEnd(PolarisIdentity.get(), "isNonCompliant");
		
		return profile.isNonCompliant();
	}
	
	protected boolean areDetailsNonCompliant(List<OrderSegmentDto> orderSegments) {

		LOG.methodStart(PolarisIdentity.get(), "areDetailsNonCompliant");
		
		for (OrderSegmentDto dto : orderSegments) {
			if(dto.isNonCompliant()) return true;
		}

		LOG.methodEnd(PolarisIdentity.get(), "areDetailsNonCompliant");
		
		return false;
	}
	
	protected DealerProfileDetail getDetailsFromOrderSegment(List<DealerProfileDetail> details, OrderSegmentDto orderSegment) {

		LOG.methodStart(PolarisIdentity.get(), "getDetailsFromOrderSegment");
		
		for (DealerProfileDetail detail : details) {
			if(orderSegment.getHeaderId() == detail.getHeader().getId()
					&& orderSegment.getProfileOrderSegmentId() == detail.getProfileOrderSegmentId()
					&& orderSegment.getPeriodCode().equals(detail.getPeriodCode()))
				return detail;
		}

		LOG.methodEnd(PolarisIdentity.get(), "getDetailsFromOrderSegment");
		
		return null;
	}
	
	protected boolean areRecordsEmpty(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "areRecordsEmpty");
		
		if(profile.getOrderSegments().size() == 0) {
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return true;
		}

		LOG.methodEnd(PolarisIdentity.get(), "areRecordsEmpty");
		
		return false;
	}
	
	protected boolean isHeaderEmpty(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "isHeaderEmpty");
		
		OrderSegmentDto testRecord = profile.getOrderSegments().get(0);
		if(testRecord.getHeaderId() == null) {
			profile.setMessage(Constants.NO_RECORDS);
			profile.setSuccessful(false);
			return true;
		}

		LOG.methodEnd(PolarisIdentity.get(), "isHeaderEmpty");
		
		return false;
	}
	
	// Check to see if header records already exist for the profile passed in
	protected boolean doesRecordExist(ProfileDetailsDto profile) {

		LOG.methodStart(PolarisIdentity.get(), "doesRecordExist");
		
		OrderSegmentDto representative = profile.getOrderSegments().get(0);
		
		LOG.methodEnd(PolarisIdentity.get(), "doesRecordExist");
		
		return headerDao.getByDealerAndProfile(representative.getDealerId(), representative.getProfileId()).size() > 0;
	}
	
	// Check to see if existing status is higher than what user is trying to submit
	protected boolean isStatusHigherPriority(DealerProfileHeaderStatus status, DealerProfileHeader header) {

		LOG.methodStart(PolarisIdentity.get(), "isStatusHigherPriority");
		LOG.methodEnd(PolarisIdentity.get(), "isStatusHigherPriority");
		
		return status.getCode() >= header.getStatus().getCode();
	}
}
