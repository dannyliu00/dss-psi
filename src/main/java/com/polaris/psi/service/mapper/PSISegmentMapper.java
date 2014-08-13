/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * PSISegmentMapper maps a PSISegment JPA entity to a SegmentDto object more fit for
 * use in PSI web requests.
 * 
 * @author bericks
 *
 */
@Component
public class PSISegmentMapper implements IMapper<PSISegment, SegmentDto> {

	private static final SplunkLogger LOG = new SplunkLogger(PSISegmentMapper.class);
	
	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.util.List)
	 */
	@Override
	public List<SegmentDto> mapToDto(List<PSISegment> entities) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		
		for (PSISegment entity : entities) {
			SegmentDto segment = mapToDto(entity);
			if(doesListContain(segments, segment))
				mapSubSegmentToSegment(segments, entity);
			else
				segments.add(mapToDto(entity));
		}

		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		return segments;
	}

	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	@Override
	public SegmentDto mapToDto(PSISegment entity) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		SegmentDto segment = new SegmentDto();
		segment.setName(entity.getName());
		segment.setRecMaximum(entity.getRecMaximum());
		segment.setRecMinimum(entity.getRecMinimum());
		segment.setRecommendedOSCount(entity.getRecOsCount());
		segment.setSegmentId(entity.getId());
		segment.addSubSegment(entity.getSubSegment());

		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return segment;
	}
	
	protected boolean doesListContain(List<SegmentDto> segments, SegmentDto segment) {
		LOG.methodStart(PolarisIdentity.get(), "doesListContain");

		for (SegmentDto dto : segments) {
			if(dto.getName().equals(segment.getName())) {
				LOG.methodEnd(PolarisIdentity.get(), "doesListContain");
				return true;
			}
		}

		LOG.methodEnd(PolarisIdentity.get(), "doesListContain");
		
		return false;
	}
	
	protected void mapSubSegmentToSegment(List<SegmentDto> segments, PSISegment segment) {
		LOG.methodStart(PolarisIdentity.get(), "mapSubSegmentToSegment");

		for (SegmentDto dto : segments) {
			if(dto.getName().equals(segment.getName())) {
				dto.addSubSegment(segment.getSubSegment());
				LOG.methodEnd(PolarisIdentity.get(), "mapSubSegmentToSegment");
				return;
			}
		}

		LOG.methodEnd(PolarisIdentity.get(), "mapSubSegmentToSegment");
	}

}
