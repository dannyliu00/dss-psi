/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class SegmentComplianceDao extends AbstractPolarisDealersExtensionDao<SegmentCompliance> {

	public SegmentComplianceDao() {
		super(SegmentCompliance.class);
	}
	
	public List<SegmentCompliance> retrieveListByProfile(Profile profile) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("profile", profile);
        
        return selectByMap(keyMap, null);
	}
}
