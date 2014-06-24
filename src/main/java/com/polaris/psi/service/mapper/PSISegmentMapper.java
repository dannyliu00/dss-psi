/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.SegmentDto;

/**
 * @author bericks
 *
 */
public class PSISegmentMapper implements IMapper<PSISegment, SegmentDto> {

	@Override
	public List<SegmentDto> mapToDto(List<PSISegment> entities) {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		
		for (PSISegment entity : entities) {
			segments.add(mapToDto(entity));
		}

		return segments;
	}

	@Override
	public SegmentDto mapToDto(PSISegment entity) {
		SegmentDto segment = new SegmentDto();
		segment.setName(entity.getName());
		segment.setSegmentId(entity.getId());
		segment.setSubSegment(entity.getSubSegment());
		return segment;
	}

}
