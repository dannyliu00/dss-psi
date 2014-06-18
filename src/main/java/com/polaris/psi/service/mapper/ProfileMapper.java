/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class ProfileMapper implements IMapper<Profile, ProfileDto> {
	
	@Autowired
	ProfilePeriodMapper periodMapper;
	
	@Autowired
	OrderSegmentMapper osMapper;
	
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
		dto.setStatus(profile.getStatus().getDescription());
		
		return dto;
	}
	
	protected int getMaxValues(List<SegmentCompliance> segments) {
		int total = 0;
		
		for (SegmentCompliance segment : segments) {
			Integer max = segment.getMaximum();
			total += max != null ? max : 0 ;
		}
		
		return total;
	}
	
	protected int getMinValues(List<SegmentCompliance> segments) {
		int total = 0;
		
		for (SegmentCompliance segment : segments) {
			Integer min = segment.getMinimum();
			total += segment.getMinimum() != null ? min : 0;
		}
		
		return total;
	}
	
}
