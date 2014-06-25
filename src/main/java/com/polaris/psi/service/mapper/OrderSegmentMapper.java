package com.polaris.psi.service.mapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.OrderSegmentCompliance;
import com.polaris.psi.resource.dto.OrderSegmentComparator;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class OrderSegmentMapper implements IMapper<OrderSegmentCompliance, OrderSegmentDto> {

	@Autowired
	OrderSegmentComparator comparator;
	
	@Override
	public List<OrderSegmentDto> mapToDto(List<OrderSegmentCompliance> entities) {
		List<OrderSegmentDto> dtos = new LinkedList<OrderSegmentDto>();
		
		for (OrderSegmentCompliance orderSegment : entities) {
			dtos.add(mapToDto(orderSegment));
		}
		
		Collections.sort(dtos, comparator);
		
		return dtos;
	}

	@Override
	public OrderSegmentDto mapToDto(OrderSegmentCompliance entity) {
		OrderSegmentDto dto = new OrderSegmentDto();
		
		dto.setId(entity.getId());
		dto.setName(entity.getProfileAndOrderSegment());
		dto.setRecMaximum(entity.getMaximum() == null ? 0 : entity.getMaximum());
		dto.setRecMinimum(entity.getMinimum() == null ? 0 : entity.getMinimum());
		dto.setRecommended(entity.getRecommended() == null ? 0 : entity.getRecommended());
		dto.setPeriodCode(entity.getProfilePeriod().getPeriodCode());
		dto.setPeriodId(entity.getProfilePeriod().getId());
		dto.setPeriodStartDate(CommonUtils.convertDate(entity.getProfilePeriod().getStartDate()));
		
		return dto;
	}

}
