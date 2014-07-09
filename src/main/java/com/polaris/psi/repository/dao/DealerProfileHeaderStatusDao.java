/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileHeaderStatusDao extends AbstractPolarisMinneapolisDao<DealerProfileHeaderStatus> {

	private static final Logger log = Logger.getLogger(DealerProfileHeaderStatusDao.class);
	
	public DealerProfileHeaderStatusDao() {
		super(DealerProfileHeaderStatus.class);
	}
	
	public DealerProfileHeaderStatus getStatus(String status) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("description", status);
        
        List<DealerProfileHeaderStatus> statii = selectByMap(keyMap, null);
        
        try {
            if(statii.size() > 1) throw new Exception();
        } catch (Exception e) {
        	log.error("We expected to get a List with a single item. The List contained " + statii.size() + " instead."
        			+ " Returning the first entry.");
        }
        
        return statii.get(0);
	}
	
}
