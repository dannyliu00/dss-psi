/**
 * 
 */
package com.polaris.psi.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.mapper.ProfileMapper;
import com.polaris.pwf.repository.CommonRepositoryConstants;

/**
 * @author bericks
 *
 */
@Service
public class ProfileService {

	@Autowired
	DealerProfileHeaderDao headerDao;
	
	@Autowired
	ProfileMapper mapper;
	
    @Transactional(CommonRepositoryConstants.TX_MANAGER_POLARIS_DEALERS_EXTENSION)
	public List<ProfileDto> getDealerProfiles(int dealerId) {
		List<DealerProfileHeader> profileHeaders = headerDao.getDealerHeaders(dealerId);
		List<Profile> profiles = new LinkedList<Profile>();
		
		for (DealerProfileHeader header : profileHeaders) {
			Profile profile = header.getProfile();
			profile.getSegmentComplianceValues();
			profiles.add(profile);
		}
		
		return mapper.mapToDto(profiles);
	}
}
