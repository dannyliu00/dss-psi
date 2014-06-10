/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class ProfileMapper {
	
	public ProfileDto mapToDto(Profile profile) {
		ProfileDto dto = new ProfileDto();
		dto.setModifiedDate(CommonUtils.convertDate(profile.getTargetCompleteDate()));
		dto.setName(profile.getName());
		dto.setProfileId(profile.getId());
//		dto.setRecMaximum(profile.getSegmentComplianceValues().get(0).getMaximum());
//		dto.setRecMinimum(profile.getSegmentComplianceValues().get(0).getMinimum());
//		dto.setRecommended(profile.getSegmentComplianceValues().get(0).get);
		dto.setStatus(profile.getStatus().getDescription());
		
		return dto;
	}
	
}
