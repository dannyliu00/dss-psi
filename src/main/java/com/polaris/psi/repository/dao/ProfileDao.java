/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class ProfileDao extends AbstractPolarisDealersExtensionDao<Profile> {

	public ProfileDao() {
		super(Profile.class);
	}
	
	public List<Profile> getDealerProfiles(Integer dealerId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("dealer", dealerId);
        
        return selectByMap(keyMap, null);
	}
}
