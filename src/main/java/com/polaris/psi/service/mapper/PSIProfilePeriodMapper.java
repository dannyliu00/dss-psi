/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.resource.dto.ProfilePeriodDto;

/**
 * @author bericks
 *
 */
@Component
public class PSIProfilePeriodMapper implements IMapper<PSIProfilePeriod, ProfilePeriodDto> {

	@Override
	public List<ProfilePeriodDto> mapToDto(List<PSIProfilePeriod> entities) {
		List<ProfilePeriodDto> periods = new ArrayList<ProfilePeriodDto>();
		
		for (PSIProfilePeriod period : entities) {
			periods.add(mapToDto(period));
		}
		
		return periods;
	}

	@Override
	public ProfilePeriodDto mapToDto(PSIProfilePeriod entity) {
		ProfilePeriodDto dto = new ProfilePeriodDto();
		
		dto.setCode(entity.getPeriodCode());
		dto.setEndDate(entity.getEndDate());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSort(entity.getSort());
		dto.setStartDate(entity.getStartDate());
		
		return dto;
	}

}
