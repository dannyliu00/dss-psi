/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
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
		dto.setTargetCompletionDate(entity.getTargetCompleteDate());
		dto.setModifiedDate(entity.getLastModifiedDate());
		dto.setProfileId(entity.getId());
		dto.setStatus(getStatus(entity.getStatus()));
		dto.setTypeCode(entity.getType());
		dto.setDealerEmail(entity.getEmail());
		
		typeMapper.mapTypeToProfile(entity.getType(), dto);
		
		return dto;
	}
	
	protected String getStatus(String s) {
		if (s == null) s = "";
		
		switch (s) {
			case Constants.PENDING_STATUS:
				break;
	
			case Constants.RETURNED_TO_DEALER:
				break;
	
			case Constants.RETURNED_TO_DSM:
				s = Constants.PENDING_STATUS;
				break;
	
			case Constants.EXCEPTION_REQUESTED:
				s = Constants.PENDING_STATUS;
				break;
	
			case Constants.APPROVED_AS_REQUESTED:
				s = Constants.APPROVED;
				break;
	
			case Constants.APPROVED_W_CHANGES:
				s = Constants.APPROVED;
				break;
	
			case Constants.APPROVED_COMPLIANT:
				s = Constants.APPROVED;
				break;
	
			case Constants.APPROVED_NONCOMPLIANT:
				break;
	
			default:
				s = Constants.DEFAULT_PROFILE_STATUS;
				break;
		}

		return s;
	}

}
