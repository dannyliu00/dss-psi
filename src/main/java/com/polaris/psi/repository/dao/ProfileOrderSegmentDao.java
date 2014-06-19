/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.ProfileOrderSegment;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class ProfileOrderSegmentDao extends AbstractPolarisDealersExtensionDao<ProfileOrderSegment> {

	public ProfileOrderSegmentDao() {
		super(ProfileOrderSegment.class);
	}
	
	public ProfileOrderSegment retrieveByNameAndProfile(String name, Profile profile) {
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("name", name);
        keyMap.put("profile", profile);
        
        List<ProfileOrderSegment> orderSegments = selectByMap(keyMap, null);
        if(orderSegments.size() > 0) return orderSegments.get(0);
        
        return null;
	}
}