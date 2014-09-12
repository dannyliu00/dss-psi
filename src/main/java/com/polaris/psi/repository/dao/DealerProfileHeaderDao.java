/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileHeaderDao extends AbstractPolarisMinneapolisDao<DealerProfileHeader> {

	public DealerProfileHeaderDao() {
		super(DealerProfileHeader.class);
	}
	
	public List<DealerProfileHeader> getByDealerAndProfile(int dealerId, int profileId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("dealerId", dealerId);
        keyMap.put("profileId", profileId);
        
        List<DealerProfileHeader> headers = selectByMap(keyMap, null);
        
        return headers;
	}
	
}
