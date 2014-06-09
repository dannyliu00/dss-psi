/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerAndDsmDao extends AbstractPolarisDealersExtensionDao<DealerAndDsm> {
	
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
        
        return selectOneByMap(keyMap);
	}
	
}
