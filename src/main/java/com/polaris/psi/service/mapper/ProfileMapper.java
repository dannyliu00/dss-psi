/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.ProfileSegmentCompliance;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class ProfileMapper {
	
	public List<ProfileDto> mapToDto(List<Profile> profiles) {
		List<ProfileDto> dtos = new LinkedList<ProfileDto>();
		
		for (Profile profile : profiles) {
			dtos.add(mapToDto(profile));
		}
		
		return dtos;
	}
	
	public ProfileDto mapToDto(Profile profile) {
		ProfileDto dto = new ProfileDto();
		dto.setModifiedDate(CommonUtils.convertDate(profile.getTargetCompleteDate()));
		dto.setName(profile.getName());
		dto.setProfileId(profile.getId());
		dto.setRecMaximum(getMaxValues(profile.getSegmentComplianceValues()));
		dto.setRecMinimum(getMinValues(profile.getSegmentComplianceValues()));
		dto.setStatus(profile.getStatus().getDescription());
		
		return dto;
	}
	
	protected int getMaxValues(List<ProfileSegmentCompliance> segments) {
		int total = 0;
		
		for (ProfileSegmentCompliance segment : segments) {
			Integer max = segment.getMaximum();
			total += max != null ? max : 0 ;
		}
		
		return total;
	}
	
	protected int getMinValues(List<ProfileSegmentCompliance> segments) {
		int total = 0;
		
		for (ProfileSegmentCompliance segment : segments) {
			Integer min = segment.getMinimum();
			total += segment.getMinimum() != null ? min : 0;
		}
		
		return total;
	}
	
}
