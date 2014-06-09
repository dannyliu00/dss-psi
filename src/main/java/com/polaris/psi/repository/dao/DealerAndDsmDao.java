/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerAndDsmDao extends AbstractPolarisDealersExtensionDao<DealerAndDsm> {
	
	private Log log = LogFactory.getLog(this.getClass());
	
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
        	
        }
        
        return dsms.get(0);
	}
	
}
