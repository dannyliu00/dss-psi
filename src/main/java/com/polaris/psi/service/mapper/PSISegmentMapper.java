/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.SegmentDto;

/**
 * @author bericks
 *
 */
@Component
public class PSISegmentMapper implements IMapper<PSISegment, SegmentDto> {

	@Override
	public List<SegmentDto> mapToDto(List<PSISegment> entities) {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		
		for (PSISegment entity : entities) {
			SegmentDto segment = mapToDto(entity);
			if(doesListContain(segments, segment))
				mapSubSegmentToSegment(segments, entity);
			else
				segments.add(mapToDto(entity));
		}

		return segments;
	}

	@Override
	public SegmentDto mapToDto(PSISegment entity) {
		SegmentDto segment = new SegmentDto();
		segment.setName(entity.getName());
		segment.setRecMaximum(entity.getRecMaximum());
		segment.setRecMinimum(entity.getRecMinimum());
		segment.setRecommendedOSCount(entity.getRecOsCount());
		segment.setSegmentId(entity.getId());
		segment.addSubSegment(entity.getSubSegment());
		return segment;
	}
	
	protected boolean doesListContain(List<SegmentDto> segments, SegmentDto segment) {
		for (SegmentDto dto : segments) {
			if(dto.getName().equals(segment.getName())) return true;
		}
		return false;
	}
	
	protected void mapSubSegmentToSegment(List<SegmentDto> segments, PSISegment segment) {
		for (SegmentDto dto : segments) {
			if(dto.getName().equals(segment.getName())) {
				dto.addSubSegment(segment.getSubSegment());
				return;
			}
		}
	}

}
