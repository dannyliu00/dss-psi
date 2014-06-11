/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileHeaderDao extends AbstractPolarisDealersExtensionDao<DealerProfileHeader> {

	public DealerProfileHeaderDao() {
		super(DealerProfileHeader.class);
	}
	
	public List<DealerProfileHeader> getDealerHeaders(Integer dealerId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("dealerId", 2350400);
        
        return selectByMap(keyMap, null);
	}

}
