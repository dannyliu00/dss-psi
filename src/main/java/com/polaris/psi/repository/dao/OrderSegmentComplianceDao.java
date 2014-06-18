/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.OrderSegmentCompliance;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class OrderSegmentComplianceDao extends AbstractPolarisDealersExtensionDao<OrderSegmentCompliance> {

	public OrderSegmentComplianceDao() {
		super(OrderSegmentCompliance.class);
	}
	
	public List<OrderSegmentCompliance> retrieveByProfile(Profile profile) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("profile", profile);
        
        return selectByMap(keyMap, null);
	}

}
