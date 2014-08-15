/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerId;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerDao extends AbstractPolarisDealersExtensionDao<DealerId> {

	private static final SplunkLogger LOG = new SplunkLogger(DealerDao.class);
	
	public DealerDao() {
		super(DealerId.class);
	}
	
	public List<DealerId> retrieveListById(Integer dealerId) {
		LOG.methodEnd(PolarisIdentity.get(), "retrieveListById");
		
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("id", dealerId + "");
        keyMap.put("canceled", 0);
        
        List<DealerId> dealers = selectByMap(keyMap, null);
        
        LOG.methodEnd(PolarisIdentity.get(), "retrieveListById");
        
        return dealers;
	}
	
	public DealerId retrieveByIdAndType(Integer dealerId, String type) {
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByIdAndType");
		
        Map<String, Object> keyMap = new HashMap<String, Object>(3);
        keyMap.put("id", dealerId);
        keyMap.put("family", type);
        keyMap.put("canceled", 0);
        
        List<DealerId> dealers = selectByMap(keyMap, null);
        
        try {
            if(dealers.size() > 1) throw new Exception();
        } catch (Exception e) {
        	LOG.error(PolarisIdentity.get(), "retrieveByIdAndType", "We expected to get a List with a single item. The List contained " + dealers.size() + " instead.");
            LOG.methodEnd(PolarisIdentity.get(), "retrieveByIdAndType");
        	return null;
        }
        
        LOG.methodEnd(PolarisIdentity.get(), "retrieveByIdAndType");
        
        return dealers.get(0);
	}
	
}
