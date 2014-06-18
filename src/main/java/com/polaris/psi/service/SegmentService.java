/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.SegmentComplianceDao;
import com.polaris.psi.repository.dao.SegmentDao;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.Segment;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.ProfileTypeMapper;
import com.polaris.psi.service.mapper.SegmentMapper;

/**
 * @author bericks
 *
 */
@Service
public class SegmentService {
	
	@Autowired
	SegmentComplianceDao complianceDao;
	
	@Autowired
	SegmentDao segmentDao;
	
	@Autowired
	SegmentMapper mapper;
	
	@Autowired
	ProfileTypeMapper typeMapper;
	
	public List<SegmentDto> retrieveByProfile(Profile profile) {
		List<SegmentCompliance> complianceValues = complianceDao.retrieveListByProfile(profile);
		if(complianceValues.size() < 1) return new ArrayList<SegmentDto>();
		
		List<Segment> segments = new ArrayList<Segment>();
		for (SegmentCompliance compliance : complianceValues) {
			List<Segment> segs = segmentDao.retrieveListByName(compliance.getName());
			for (Segment segment : segs) {
				segments.add(segment);
			}
		}
		
		List<SegmentDto> dtos = mapper.mapToDto(complianceValues);
		
		Segment jpaSegment = segments.get(0);
		if(jpaSegment != null) {
			for (SegmentDto dto : dtos) {
				dto.setType(getProductType(jpaSegment.getType()));
			}
		}
		
		return dtos;
	}
	
	public List<SegmentDto> retrieveBySubSegment(String subSegment) {
		List<Segment> segments = segmentDao.retrieveListBySubSegment(subSegment);
		List<SegmentDto> dtos = new ArrayList<SegmentDto>();
		
		for (Segment segment : segments) {
			SegmentDto dto = mapper.mapToDto(segment);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	protected String getProductType(String type) {
		String mc = "motorcycle";
		String atv = "atv";
		String productType;
		
		if(type == null) type = "";
		
		switch (type) {
		case "2":
			productType = atv;
			break;

		case "5":
			productType = mc;
			break;
			
		case "6":
			productType = atv;
			break;
			
		case "9":
			productType = atv;
			break;
			
		case "D":
			productType = atv;
			break;
			
		case "F":
			productType = mc;
			break;
			
		case "Z":
			productType = atv;
			break;
			
		default:
			productType = atv;
			break;
		}
		
		return productType;
	}
	
}
