/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.dao.ProfileDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.Segment;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.ProfileMapper;
import com.polaris.psi.service.mapper.ProfileTypeMapper;

/**
 * @author bericks
 *
 */
@Service
public class ProfileService {

	@Autowired
	DealerProfileHeaderDao headerDao;
	
	@Autowired
	ProfileDao profileDao;
	
	@Autowired
	SegmentService segmentService;
	
	@Autowired
	OrderSegmentService orderSegmentService;
	
	@Autowired
	ProfilePeriodService profilePeriodService;
	
	@Autowired
	ProfileMapper mapper;
	
	@Autowired
	ProfileTypeMapper typeMapper;
	
	public List<ProfileDto> getDealerProfiles(int dealerId) {
		List<DealerProfileHeader> profileHeaders = headerDao.getDealerHeaders(dealerId);
		List<Profile> profiles = new ArrayList<Profile>();
		List<ProfileDto> dtos = new ArrayList<ProfileDto>();

		for (DealerProfileHeader header : profileHeaders) {
			Profile profile = header.getProfile();
			profiles.add(profile);
		}

		for (Profile profile : profiles) {
			ProfileDto profileDto = mapper.mapToDto(profile);

			List<OrderSegmentDto> orderSegments = orderSegmentService.retrieveByProfile(profile);
			if(orderSegments.size() > 0) {
				List<SegmentDto> segmentDtos = segmentService.retrieveBySubSegment(orderSegments.get(0).getSubSegment());
				typeMapper.mapTypeToProfile(segmentDtos.get(0).getType(), profileDto);
			}

			List<SegmentDto> segments = segmentService.retrieveByProfile(profile);
			if(segments.size() > 0) {
				profileDto.setSegments(segments);
			}

			dtos.add(profileDto);
		}

		return dtos;
	}

	public ProfileDto getDealerProfile(int profileId) {
    	Profile profile = profileDao.retrieveProfileById(profileId);
    	ProfileDto dto = mapper.mapToDto(profile);
    	
		List<SegmentDto> segments = segmentService.retrieveByProfile(profile);
    	if(segments.size() > 0) {
	    	dto.setSegments(segments);
	    	dto.setType(segments.get(0).getType());
    	}

		List<OrderSegmentDto> orderSegments = orderSegmentService.retrieveByProfile(profile);
    	dto.setOrderSegments(orderSegments);
    	
    	List<SegmentDto> segmentDtos = segmentService.retrieveBySubSegment(orderSegments.get(0).getSubSegment());
		typeMapper.mapTypeToProfile(segmentDtos.get(0).getType(), dto);

    	List<ProfilePeriodDto> periods = profilePeriodService.getPeriodsByProfile(profile);
    	dto.setPeriods(periods);
    	
    	return dto;
	}
	
	protected String getSegmentType(List<Segment> segments) {
		if(segments.equals(null)) return "";
		
		if(segments.size() < 1 ) return "";
		
		return segments.get(0).getType();
	}
	
	protected String getSegmentName(List<SegmentCompliance> segments) {
		if(segments.equals(null)) return "";
		
		if(segments.size() < 1 ) return "";
		
		return segments.get(0).getName();
	}
	
	protected boolean hasSegments(Profile profile) {
		List<SegmentCompliance> compliance = profile.getSegmentComplianceValues();
		
		if(compliance.equals(null)) return false;
		
		if(compliance.size() < 1) return false;
		
		return true;
		
	}

}
