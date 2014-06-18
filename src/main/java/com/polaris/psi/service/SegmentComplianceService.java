/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.SegmentComplianceDao;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.SegmentMapper;

/**
 * @author bericks
 *
 */
@Service
public class SegmentComplianceService {

	@Autowired
	SegmentComplianceDao dao;
	
	@Autowired
	SegmentMapper mapper;
	
	public List<SegmentDto> getListByProfile(Profile profile) {
		
		return mapper.mapToDto(dao.retrieveListByProfile(profile));
	}
}
