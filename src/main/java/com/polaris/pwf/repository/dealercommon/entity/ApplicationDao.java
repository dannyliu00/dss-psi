package com.polaris.pwf.repository.dealercommon.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.dao.DealerAndDsmDao;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.AbstractPolarisDealersCommonDao;

/*
 * @author pceder
 */
@Repository
public class ApplicationDao extends AbstractPolarisDealersCommonDao<Application> {

	private static final SplunkLogger LOG = new SplunkLogger(AbstractPolarisDealersCommonDao.class);
	
	public ApplicationDao() {
		super(Application.class);
	}
	
	public ApplicationDao(Class<Application> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
	public Application selectByAppGuid(String appGuid) {

		LOG.methodStart(PolarisIdentity.get(), "selectByAppGuid");

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("appGuid", appGuid);
        
        List<Application> apps = selectByMap(keyMap, null);
        
        if(apps==null || apps.size()!=1) {
        	LOG.warn(PolarisIdentity.get(), "selectByAppGuid", "Expected exactly 1 match for AppGuid:" + appGuid);
        	return null;
        };
        
		LOG.methodEnd(PolarisIdentity.get(), "selectByAppGuid");

		return apps.get(0);
	}

}
