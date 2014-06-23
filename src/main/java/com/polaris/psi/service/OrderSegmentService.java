/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.OrderSegmentComplianceDao;
import com.polaris.psi.repository.dao.OrderSegmentDao;
import com.polaris.psi.repository.dao.ProfileOrderSegmentDao;
import com.polaris.psi.repository.entity.OrderSegment;
import com.polaris.psi.repository.entity.OrderSegmentCompliance;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.ProfileOrderSegment;
import com.polaris.psi.repository.entity.Segment;
import com.polaris.psi.resource.dto.OrderSegmentComparator;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.service.mapper.OrderSegmentMapper;

/**
 * @author bericks
 *
 */
@Service
public class OrderSegmentService {

	@Autowired
	OrderSegmentComplianceDao complianceDao;
	
	@Autowired
	ProfileOrderSegmentDao profileOrderSegmentDao;
	
	@Autowired
	OrderSegmentDao orderSegmentDao;
	
	@Autowired
	OrderSegmentMapper mapper;
	
	@Autowired
	OrderSegmentComparator comparator;
	
	public List<OrderSegmentDto> retrieveByProfile(Profile profile) {
		List<OrderSegmentCompliance> complianceValues = complianceDao.retrieveByProfile(profile);
		List<OrderSegmentDto> orderSegments = new ArrayList<OrderSegmentDto>();
		
		for (OrderSegmentCompliance compliance : complianceValues) {
			OrderSegmentDto dto = mapper.mapToDto(compliance);

			ProfileOrderSegment profileOrderSegment = profileOrderSegmentDao.retrieveByNameAndProfile(compliance.getProfileAndOrderSegment(), profile);
			if(profileOrderSegment != null) {
				OrderSegment os = orderSegmentDao.retrieveByName(profileOrderSegment.getName());
				if(os != null) dto.setSubSegment(os.getSubSegment());
			}
			orderSegments.add(dto);
		}
		
		Collections.sort(filterBySubsegment(orderSegments), comparator);

		return orderSegments;
	}
	
	
	protected List<OrderSegmentDto> filterBySubsegment(List<OrderSegmentDto> orderSegments) {
		List<OrderSegmentDto> finalOSes = new ArrayList<OrderSegmentDto>();
		
		for (OrderSegmentDto orderSegment : orderSegments) {
			String subSegment = orderSegment.getSubSegment();
			if(subSegment != null && subSegment.length() > 0) finalOSes.add(orderSegment);
		}
		
		return finalOSes;
	}
}
