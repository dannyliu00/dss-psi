/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
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

	public List<OrderSegmentDto> saveOrderSegmentQuantities(List<OrderSegmentDto> records) {
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		
		for (OrderSegmentDto orderSegment : records) {
			if(orderSegment.getHeaderId() != null) {
				DealerProfileHeader header = headerDao.select(orderSegment.getHeaderId());
				updateOrderSegmentQty(header, orderSegment);
				saved.add(orderSegment);
			} else {
				List<DealerProfileHeaderStatus> statii = statusDao.selectAll();
				DealerProfileHeaderStatus status = getDefaultStatus(statii);
				DealerProfileHeader header = new DealerProfileHeader();
				header.setCreatedProgram(Constants.PROGRAM_CODE);
				header.setCreatedDate(DateUtils.truncate(new Date(), Calendar.YEAR));
				header.setCreatedUser(orderSegment.getModifiedUserName());
				header.setDealerId(orderSegment.getDealerId());
				header.setEmailAddress(orderSegment.getDealerEmail());
				header.setProfileId(orderSegment.getProfileId());
				header.setStatus(status);
				header.setSubmittedDate(orderSegment.getSubmittedDate() != null ? orderSegment.getSubmittedDate() : null);
				
				DealerProfileHeader returnedHeader = headerDao.insert(header);
				
				saved.add(createOrderSegmentQty(returnedHeader, orderSegment));
			}
		}
		
		return saved;
	}
	
	public OrderSegmentDto createOrderSegmentQty(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = new DealerProfileDetail();
		detail.setActual(orderSegment.getActual());
		detail.setDealerComments(orderSegment.getDealerComments());
		detail.setDealerReasonCode(orderSegment.getReasonCode());
		detail.setCreatedProgram(Constants.PROGRAM_CODE);
		detail.setCreatedDate(DateUtils.truncate(new Date(), Calendar.YEAR));
		detail.setCreatedUser(orderSegment.getModifiedUserName());
		detail.setHeader(header);
		detail.setProfileOrderSegmentId(orderSegment.getProfileOrderSegmentId());
		
		DealerProfileDetail returnedDetail = detailDao.insert(detail);
		
		orderSegment.setId(returnedDetail.getId());
		orderSegment.setHeaderId(header.getId());
		
		return orderSegment;
	}
	
	public void updateOrderSegmentQty(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		DealerProfileDetail detail = detailDao.select(orderSegment.getId());
		detail.setActual(orderSegment.getActual());
		detail.setDealerReasonCode(orderSegment.getReasonCode());
		detail.setDealerComments(orderSegment.getDealerComments());
		detail.setChangedUser(orderSegment.getModifiedUserName());
		detailDao.update(detail);
	}
	
	protected DealerProfileHeaderStatus getDefaultStatus(List<DealerProfileHeaderStatus> statii) {
		for (DealerProfileHeaderStatus status : statii) {
			if(status.getDescription().equals(Constants.IN_PROGRESS_STATUS)) return status;
		}
		
		return null;
	}
}
