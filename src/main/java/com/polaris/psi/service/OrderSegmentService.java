/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<OrderSegmentDto> saveOrderSegmentQuantities(List<OrderSegmentDto> records) {
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		
		for (OrderSegmentDto orderSegment : records) {
			if(orderSegment.getHeaderId() != null) {
				updateOrderSegmentQty(orderSegment);
				saved.add(orderSegment);
			} else {
				List<DealerProfileHeaderStatus> statii = statusDao.selectAll();
				DealerProfileHeaderStatus status = getDefaultStatus(statii);
				DealerProfileHeader header = headerDataMapper.createNewNonSubmittedNonApprovedHeader(orderSegment, status);
				DealerProfileHeader returnedHeader = headerDao.insert(header);
				saved.add(createOrderSegmentQty(returnedHeader, orderSegment));
			}
		}
		
		return saved;
	}
	
	public OrderSegmentDto createOrderSegmentQty(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = detailDataMapper.createInitialDetail(orderSegment, header);
		DealerProfileDetail returnedDetail = detailDao.insert(detail);
		
		orderSegment.setId(returnedDetail.getId());
		orderSegment.setHeaderId(header.getId());
		
		return orderSegment;
	}
	
	public void updateOrderSegmentQty(OrderSegmentDto orderSegment) {
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
