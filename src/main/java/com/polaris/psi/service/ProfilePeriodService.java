/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.ProfileAndPeriodDao;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.ProfileAndPeriod;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.service.mapper.ProfilePeriodMapper;

/**
 * @author bericks
 *
 */
@Service
public class ProfilePeriodService {

	@Autowired
	ProfileAndPeriodDao dao;
	
	@Autowired
	ProfilePeriodMapper mapper;
	
	public List<ProfilePeriodDto> getPeriodsByProfile(Profile profile) {
		List<ProfileAndPeriod> profilePeriods = dao.retrieveListByProfile(profile);
		List<ProfilePeriodDto> dtos = new ArrayList<ProfilePeriodDto>();
		
		dtos = mapper.mapToDto(profilePeriods);
		
		return dtos;
	}
}