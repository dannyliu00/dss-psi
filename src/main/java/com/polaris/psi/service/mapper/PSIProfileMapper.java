/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.ProfileDto;

/**
 * @author bericks
 *
 */
@Component
public class PSIProfileMapper implements IMapper<PSIProfile, ProfileDto> {

	@Autowired
	ProfileTypeMapper typeMapper;
	
	@Override
	public List<ProfileDto> mapToDto(List<PSIProfile> entities) {
		List<ProfileDto> profiles = new ArrayList<ProfileDto>();
		
		for (PSIProfile entity : entities) {
			profiles.add(mapToDto(entity));
		}

		return profiles;
	}

	@Override
	public ProfileDto mapToDto(PSIProfile entity) {
		ProfileDto dto = new ProfileDto();
		dto.setName(entity.getName());
		dto.setModifiedDate(entity.getTargetCompleteDate());
		dto.setProfileId(entity.getId());
		dto.setStatus(entity.getStatus());
		
		typeMapper.mapTypeToProfile(entity.getName(), dto);
		
		return dto;
	}

}
