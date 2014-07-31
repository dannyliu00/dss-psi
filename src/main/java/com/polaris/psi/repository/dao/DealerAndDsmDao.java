/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerAndDsmDao extends AbstractPolarisDealersExtensionDao<DealerAndDsm> {
	
	private static final SplunkLogger LOG = new SplunkLogger(DealerAndDsmDao.class);
	
	public DealerAndDsmDao() {
		super(DealerAndDsm.class);
	}

	public DealerAndDsm selectByDealerId(Object dealerId, String productLine) {

		LOG.methodStart(PolarisIdentity.get(), "selectByDealerId");

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("dealerId", dealerId);
        keyMap.put("productLine", productLine);
        
        List<DealerAndDsm> dsms = selectByMap(keyMap, null);
        
        try {
            if(dsms.size() > 1) throw new Exception();
        } catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "getAttributes", "We expected to get a List with a single item. "
					+ "The List contained " + dsms.size() + " instead. Returning the first entry.");
        }
        
		LOG.methodEnd(PolarisIdentity.get(), "selectByDealerId");

		return dsms.get(0);
	}
	
	public List<DealerAndDsm> selectByDsmId(Object dsmId, String type) {

		LOG.methodStart(PolarisIdentity.get(), "selectByDsmId");

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("dsmId", dsmId);
        keyMap.put("productLine", type);
        
        List<DealerAndDsm> dsms = selectByMap(keyMap, null);
        
		LOG.methodEnd(PolarisIdentity.get(), "selectByDsmId");

        return dsms;
	}
	
	public List<DealerAndDsm> selectByRsmId(Object rsmId, String type) {

		LOG.methodStart(PolarisIdentity.get(), "selectByRsmId");

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("rsmId", rsmId);
        keyMap.put("productLine", type);
        
        List<DealerAndDsm> dsms = selectByMap(keyMap, null);
        
		LOG.methodEnd(PolarisIdentity.get(), "selectByRsmId");

        return dsms;
	}
	
}
