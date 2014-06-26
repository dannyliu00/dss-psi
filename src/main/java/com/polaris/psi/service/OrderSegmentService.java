/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
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

	public void saveOrderSegments(List<OrderSegmentDto> records) {
		List<OrderSegmentDto> saved = new ArrayList<OrderSegmentDto>();
		
		for (OrderSegmentDto orderSegment : records) {
			if(orderSegment.getHeaderId() != null) {
				// update records
			} else {
				saved.add(createOrderSegment(orderSegment));
			}
		}
	}
	
	public OrderSegmentDto createOrderSegment(OrderSegmentDto orderSegment) {
		List<DealerProfileHeaderStatus> statii = statusDao.retrieveAll();
		
		return null;
	}
}
