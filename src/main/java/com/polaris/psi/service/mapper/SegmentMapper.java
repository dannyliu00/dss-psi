/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Segment;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.resource.dto.SegmentDto;

/**
 * @author bericks
 *
 */
@Component
public class SegmentMapper implements IMapper<SegmentCompliance, SegmentDto> {

	@Override
	public List<SegmentDto> mapToDto(List<SegmentCompliance> entities) {
		List<SegmentDto> dtos = new LinkedList<SegmentDto>();
		
		for (SegmentCompliance profileSegmentCompliance : entities) {
			dtos.add(mapToDto(profileSegmentCompliance));
		}

		return dtos;
	}

	@Override
	public SegmentDto mapToDto(SegmentCompliance entity) {
		SegmentDto dto = new SegmentDto();
		dto.setName(entity.getName());
		dto.setRecMaximum(entity.getMaximum() == null ? 0 : entity.getMaximum());
		dto.setRecMinimum(entity.getMinimum() == null ? 0 : entity.getMinimum());
		dto.setRecommendedOSCount(entity.getRecommendedOSCount() == null ? 0 : entity.getRecommendedOSCount());
		dto.setSegmentId(entity.getId());

		return dto;
	}
	
	public SegmentDto mapToDto(Segment entity) {
		SegmentDto dto = new SegmentDto();
		dto.setName(entity.getName());
		dto.setType(entity.getType());

		return dto;
	}

}
