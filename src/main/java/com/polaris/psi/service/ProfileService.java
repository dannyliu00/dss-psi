/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.OrderSegmentComparator;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.mapper.PSIOrderSegmentMapper;
import com.polaris.psi.service.mapper.PSIProfileMapper;
import com.polaris.psi.service.mapper.PSIProfilePeriodMapper;
import com.polaris.psi.service.mapper.PSISegmentMapper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.PSIOrderSegmentDao;
import com.polaris.pwf.dao.PSIProfileDao;
import com.polaris.pwf.dao.PSIProfileDetailDao;
import com.polaris.pwf.dao.PSIProfilePeriodDao;
import com.polaris.pwf.dao.PSISegmentDao;

/**
 * @author bericks
 *
 */
@Service
public class ProfileService {

	private static final SplunkLogger LOG = new SplunkLogger(ProfileService.class);

	@Autowired
	PSIProfileDao psiProfileDao;
	
	@Autowired
	PSIProfileDetailDao psiDetailDao;
	
	@Autowired
	PSIProfilePeriodDao profilePeriodDao;
	
	@Autowired
	PSISegmentDao psiSegmentDao;
	
	@Autowired
	PSIOrderSegmentDao psiOsDao;
	
	@Autowired
	PSIProfileMapper profileMapper;
	
	@Autowired
	PSIProfilePeriodMapper periodMapper;
	
	@Autowired
	PSISegmentMapper segmentMapper;
	
	@Autowired
	PSIOrderSegmentMapper osMapper;
	
	@Autowired
	OrderSegmentComparator osComparator;
	
	/**
	 * @param dealerId
	 * @return
	 */
	public List<ProfileDto> getCurrentDealerProfiles(int dealerId) {

		LOG.methodStart(PolarisIdentity.get(), "getCurrentDealerProfiles");
		
		List<PSIProfile> psiProfiles = psiProfileDao.retrieveDealerCurrentProfileListByDealerId(dealerId);
		List<ProfileDto> psiDtos = profileMapper.mapToDto(psiProfiles);
		
		LOG.methodEnd(PolarisIdentity.get(), "getCurrentDealerProfiles");

		return psiDtos;
	}

	/**
	 * @param dealerId
	 * @return
	 */
	public List<ProfileDto> getHistoricalDealerProfiles(int dealerId) {

		LOG.methodStart(PolarisIdentity.get(), "getHistoricalDealerProfiles");

		List<PSIProfile> psiProfiles = psiProfileDao.retrieveDealerHistoryProfileListByDealerId(dealerId);
		List<ProfileDto> psiDtos = profileMapper.mapToDto(psiProfiles);
		
		LOG.methodEnd(PolarisIdentity.get(), "getHistoricalDealerProfiles");

		return psiDtos;
	}

	/**
	 * @param profileId
	 * @param dealerId
	 * @return
	 */
	public ProfileDto getDealerProfile(int profileId, int dealerId) {
		
		LOG.methodStart(PolarisIdentity.get(), "getDealerProfile");

		PSIProfile psiProfile = psiProfileDao.retrieveProfileById(profileId, dealerId);

		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		if(psiProfile.getHeaderId() != null) details = psiDetailDao.retrieveByHeaderId(psiProfile.getHeaderId());
		
		List<PSIOrderSegment> psiOSes = psiOsDao.retrieveByProfileAndDealer(profileId, dealerId);
		List<PSISegment> psiSegments = psiSegmentDao.retrieveByProfileDealerAndType(profileId, dealerId, psiProfile.getType());
		
    	ProfileDto dto = profileMapper.mapToDto(psiProfile);
    	dto.setSegments(segmentMapper.mapToDto(psiSegments));
    	dto.setOrderSegments(osMapper.mapToDto(psiOSes, details, psiProfile.getEmail()));
    	
    	List<PSIProfilePeriod> periods = profilePeriodDao.retrieveByProfileId(psiProfile.getId());
    	dto.setPeriods(periodMapper.mapToDto(periods));
    	
		LOG.methodEnd(PolarisIdentity.get(), "getDealerProfile");

		return dto;
	}
	
	/**
	 * @param profileId
	 * @param dealerId
	 * @return
	 */
	public ProfileDto getDealerProfile(int profileId, int dealerId, boolean isDealer) {
		
		LOG.methodStart(PolarisIdentity.get(), "getDealerProfile");

		PSIProfile psiProfile = psiProfileDao.retrieveProfileById(profileId, dealerId);

		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		if(psiProfile.getHeaderId() != null) details = psiDetailDao.retrieveByHeaderId(psiProfile.getHeaderId());
		
		List<PSIOrderSegment> psiOSes = psiOsDao.retrieveByProfileAndDealer(profileId, dealerId);
		List<PSISegment> psiSegments = psiSegmentDao.retrieveByProfileDealerAndType(profileId, dealerId, psiProfile.getType());
		
    	ProfileDto dto = profileMapper.mapToDto(psiProfile, isDealer);
    	dto.setSegments(segmentMapper.mapToDto(psiSegments));
    	dto.setOrderSegments(osMapper.mapToDto(psiOSes, details, psiProfile.getEmail()));
    	
    	List<PSIProfilePeriod> periods = profilePeriodDao.retrieveByProfileId(psiProfile.getId());
    	dto.setPeriods(periodMapper.mapToDto(periods));
    	
		LOG.methodEnd(PolarisIdentity.get(), "getDealerProfile");

		return dto;
	}
	
}
