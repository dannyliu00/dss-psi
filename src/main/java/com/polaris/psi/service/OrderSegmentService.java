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
		
		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			if(LOG.isTraceEnabled()) {
				LOG.trace("Header record does exist for the detail records passed in. System will update existing "
						+ "header and detail records for saving the profile.");
			}

			updateOrderSegmentQty(records);
			profileDetailsDto.setOrderSegments(records);
			profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
			profileDetailsDto.setSuccessful(true);
			return profileDetailsDto;
		}
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("Header record does not exist for the detail records passed in. System will create new "
					+ "header and detail records for saving the profile.");
		}
		
		List<DealerProfileHeaderStatus> statii = statusService.getAllStatus();
		DealerProfileHeaderStatus status = getDefaultStatus(statii);
		DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(testRecord, status);
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
			headerDataMapper.updateExistingSubmittedHeader(header, status, testRecord.isNonCompliant());
			headerDao.update(header);
			
			for (OrderSegmentDto dto : records) {
				dto.setSubmittedDate(header.getSubmittedDate());
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
		
		DealerProfileHeader header = headerDataMapper.createNewSubmittedHeader(testRecord, status, testRecord.isNonCompliant());
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto orderSegment : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
			submitted.add(returnedSegment);
		}
		profileDetailsDto.setOrderSegments(submitted);
		profileDetailsDto.setMessage(Constants.SAVE_SUCCESSFUL);
		profileDetailsDto.setSuccessful(true);

		return profileDetailsDto;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public List<OrderSegmentDto> dsmApproveWithChanges(List<OrderSegmentDto> records) {
		boolean isNonCompliant = areDetailsNonCompliant(records);
		DealerProfileHeaderStatus status = statusService.getApprovedWithChangesStatus();
		
		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			if(LOG.isTraceEnabled()) {
				LOG.trace("Updating header and detail records for approving the profile with entered changes.");
			}
			
			updateDetailWithDsmData(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateApprovedHeader(header, status, testRecord.getModifiedUserName());
			header.setNonCompliant(isNonCompliant);
			headerDao.update(header);
			
			for (OrderSegmentDto dto : records) {
				dto.setApprovedDate(header.getApprovedDate());
			}

		} else {
			LOG.error("Profile detail records passed in did not contain an associated header record.  System will not "
					+ "do any of the 'approve with changes' work.");
		}
		
		return records;
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
	
	protected void updateDetailWithDsmData(List<OrderSegmentDto> orderSegments) {
		for (OrderSegmentDto dto : orderSegments) {
			updateDetailWithDsmData(dto);
		}
	}

	protected void updateDetailWithDsmData(OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = detailDao.select(orderSegment.getId());
		detailDataMapper.updateDsmEnteredDetails(detail, orderSegment);
		detail.setFinalQty(orderSegment.getDsmQty());
		detailDao.update(detail);
	}

	protected DealerProfileHeaderStatus getDefaultStatus(List<DealerProfileHeaderStatus> statii) {
		for (DealerProfileHeaderStatus status : statii) {
			if(status.getDescription().trim().equals(Constants.IN_PROGRESS_STATUS)) return status;
		}
		
		return null;
	}
	
	protected boolean areDetailsNonCompliant(List<OrderSegmentDto> orderSegments) {
		for (OrderSegmentDto dto : orderSegments) {
			if(dto.isNonCompliant()) return true;
		}
		
		return false;
	}
}
