/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.SegmentStockingProfileOrder;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;
import com.polaris.pwf.dao.SegmentStockingProfileDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileOrderDao extends AbstractPolarisMinneapolisDao<SegmentStockingProfileOrder> {

	private static final SplunkLogger LOG = new SplunkLogger(SegmentStockingProfileDao.class);
	
	public DealerProfileOrderDao() {
		super(SegmentStockingProfileOrder.class);
	}
	
	public SegmentStockingProfileOrder retrieve(String profile, String segment, int period) {
        Map<String, Object> keyMap = new HashMap<String, Object>(3);
        keyMap.put("profileName", profile);
        keyMap.put("detailToken", segment);
        keyMap.put("profilePeriod", period);
        
        List<SegmentStockingProfileOrder> profiles = selectByMap(keyMap, null);
        
        try {
            if(profiles.size() > 1) throw new Exception();
        } catch (Exception e) {
        	LOG.error(PolarisIdentity.get(), "retrieve", "We expected to get a List with a single item. The List contained " + profiles.size() + " instead.");
        	return null;
        }
        
        return profiles.get(0);
	}
	
}
