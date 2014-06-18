/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.ProfileAndPeriod;
import com.polaris.psi.repository.entity.ProfilePeriod;
import com.polaris.psi.resource.dto.IProfilePeriodComparator;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class ProfilePeriodMapper implements IMapper<ProfileAndPeriod, ProfilePeriodDto> {

	@Autowired
	IProfilePeriodComparator comparator;
	
	@Override
	public List<ProfilePeriodDto> mapToDto(List<ProfileAndPeriod> entities) {
		List<ProfilePeriodDto> dtos = new LinkedList<ProfilePeriodDto>();
		
		for (ProfileAndPeriod profilePeriod : entities) {
			dtos.add(mapToDto(profilePeriod));
		}
		
		Collections.sort(dtos, comparator);

		return dtos;
	}

	@Override
	public ProfilePeriodDto mapToDto(ProfileAndPeriod entity) {
		ProfilePeriodDto dto = new ProfilePeriodDto();
		ProfilePeriod period = entity.getPeriod();
		dto.setEndDate(CommonUtils.convertDate(period.getEndDate()));
		dto.setId(period.getId());
		dto.setName(period.getName());
		dto.setStartDate(CommonUtils.convertDate(period.getStartDate()));

		return dto;
	}

}
