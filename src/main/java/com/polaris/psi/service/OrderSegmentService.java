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
import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;
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
	DealerProfileHeaderStatusDao statusDao;
	
	@Autowired
	DealerProfileHeaderDao headerDao;
	
	@Autowired
	DealerProfileDetailDao detailDao;
	
	@Autowired
	HeaderDataMapper headerDataMapper;
	
	@Autowired
	DetailDataMapper detailDataMapper;

	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public List<OrderSegmentDto> saveOrderSegmentQuantities(List<OrderSegmentDto> records) {
		assert(records.size() > 0);
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			updateOrderSegmentQty(records);
			return records;
		}
		
		List<DealerProfileHeaderStatus> statii = statusDao.selectAll();
		DealerProfileHeaderStatus status = getDefaultStatus(statii);
		DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(testRecord, status);
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto orderSegment : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
			saved.add(returnedSegment);
		}

		return saved;
	}
	
	@Transactional(CommonRepositoryConstants.TX_MANAGER_POLMPLS)
	public List<OrderSegmentDto> submitOrderSegmentQuantities(List<OrderSegmentDto> records) {
		assert(records.size() > 0);
		List<OrderSegmentDto> submitted = new ArrayList<OrderSegmentDto>();
		DealerProfileHeaderStatus status = statusDao.getPendingStatus();

		OrderSegmentDto testRecord = records.get(0);
		if(testRecord.getHeaderId() != null) {
			updateOrderSegmentQty(records);
			DealerProfileHeader header = headerDao.select(testRecord.getHeaderId());
			headerDataMapper.updateExistingSubmittedHeader(header, status);
			headerDao.update(header);
			
			for (OrderSegmentDto dto : records) {
				dto.setSubmittedDate(header.getSubmittedDate());
			}
			return records;
		}
		
		DealerProfileHeader header = headerDataMapper.createNewSubmittedHeader(testRecord, status);
		DealerProfileHeader returnedHeader = headerDao.insert(header);
		for (OrderSegmentDto orderSegment : records) {
			OrderSegmentDto returnedSegment = createOrderSegmentQty(returnedHeader, orderSegment);
			submitted.add(returnedSegment);
		}

		return submitted;
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

	protected DealerProfileHeaderStatus getDefaultStatus(List<DealerProfileHeaderStatus> statii) {
		for (DealerProfileHeaderStatus status : statii) {
			if(status.getDescription().trim().equals(Constants.IN_PROGRESS_STATUS)) return status;
		}
		
		return null;
	}
}
