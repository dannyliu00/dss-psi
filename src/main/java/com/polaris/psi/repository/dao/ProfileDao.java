/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;
import com.polaris.pwf.repository.CommonRepositoryConstants;

/**
 * @author bericks
 *
 */
@Repository
public class ProfileDao extends AbstractPolarisDealersExtensionDao<Profile> {

	public ProfileDao() {
		super(Profile.class);
	}
	
	public List<Profile> retrieveListByDealerId(Integer dealerId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("dealer", dealerId);
        
        List<Profile> profiles = selectByMap(keyMap, null);
        
        return profiles;
	}
	
    @Transactional(CommonRepositoryConstants.TX_MANAGER_POLARIS_DEALERS_EXTENSION)
	public Profile retrieveProfileById(Integer profileId) {
		Profile profile = select(profileId);
		
//		Hibernate.initialize(profile.getOsComplianceValues());
//		Hibernate.initialize(profile.getPeriods());
//		Hibernate.initialize(profile.getProfileAndOrderSegments());
//		Hibernate.initialize(profile.getProfileHeaders());
//		Hibernate.initialize(profile.getSegmentComplianceValues());
		
		return profile;
	}
}
