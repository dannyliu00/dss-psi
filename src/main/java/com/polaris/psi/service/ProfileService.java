/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.dao.ProfileDao;
import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.Segment;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.PSIProfileMapper;
import com.polaris.psi.service.mapper.ProfileMapper;
import com.polaris.psi.service.mapper.ProfileTypeMapper;
import com.polaris.pwf.dao.PSIOrderSegmentDao;
import com.polaris.pwf.dao.PSIProfileDao;
import com.polaris.pwf.dao.PSIProfileDetailDao;
import com.polaris.pwf.dao.PSISegmentDao;

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
	PSIProfileDao psiProfileDao;
	
	@Autowired
	PSIProfileDetailDao psiDetailDao;
	
	@Autowired
	PSISegmentDao psiSegmentDao;
	
	@Autowired
	PSIOrderSegmentDao psiOsDao;
	
	@Autowired
	SegmentService segmentService;
	
	@Autowired
	OrderSegmentService orderSegmentService;
	
	@Autowired
	ProfilePeriodService profilePeriodService;
	
	@Autowired
	ProfileMapper mapper;
	
	@Autowired
	PSIProfileMapper profileMapper;
	
	@Autowired
	ProfileTypeMapper typeMapper;
	
	public List<ProfileDto> getDealerProfiles(int dealerId) {
		
		List<PSIProfile> psiProfiles = psiProfileDao.retrieveListByDealerId(dealerId);
		List<ProfileDto> psiDtos = profileMapper.mapToDto(psiProfiles);
		
		return psiDtos;
	}

	public ProfileDto getDealerProfile(int profileId, int dealerId) {
		
		PSIProfile psiProfile = psiProfileDao.retrieveProfileById(profileId);
		List<PSIProfileDetail> details = psiDetailDao.retrieveByHeaderId(psiProfile.getHeaderId());
		List<PSIOrderSegment> psiOSes = psiOsDao.retrieveByProfileAndDealer(psiProfile.getId(), dealerId);
		List<PSISegment> psiSegments = psiSegmentDao.retrieveByProfileDealerAndType(
				psiProfile.getId(), dealerId, psiProfile.getType());
		
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
