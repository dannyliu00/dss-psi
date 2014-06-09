/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerAndDsmDao extends AbstractPolarisDealersExtensionDao<DealerAndDsm> {
	
	private static final Logger log = Logger.getLogger(DealerAndDsmDao.class);
	
	public DealerAndDsmDao() {
		super(DealerAndDsm.class);
	}

    /*
     * SELECT *
     *   FROM Enterprise.RsmDsmDealer
     *  WHERE DealerId=?
     *
     * @param dealerId
     * @return DealerAndDsm
     */
	public DealerAndDsm selectByDealerId(Object dealerId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("dealerId", dealerId);
        
        List<DealerAndDsm> dsms = selectByMap(keyMap, null);
        
        try {
            if(dsms.size() > 1) throw new Exception();
        } catch (Exception e) {
        	log.error("We expected to get a List with a single item. The List contained " + dsms.size() + " instead."
        			+ " Returning the first entry.");
        }
        
        return dsms.get(0);
	}
	
}
