/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.polaris.psi.repository.entity.DealerId;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;
import com.polaris.pwf.repository.CommonRepositoryConstants;

/**
 * @author bericks
 *
 */
@Repository
public class DealerDao extends AbstractPolarisDealersExtensionDao<DealerId> {

	public DealerDao() {
		super(DealerId.class);
	}
	
    @Transactional(CommonRepositoryConstants.TX_MANAGER_POLARIS_DEALERS_EXTENSION)
	public List<DealerId> retrieveListById(Integer dealerId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("id", dealerId + "");
        keyMap.put("canceled", 0);
        
        List<DealerId> dealers = selectByMap(keyMap, null);
        
//        for (DealerId id : dealers) {
//    		Hibernate.initialize(id.getProfiles());
//		}
        
        return dealers;
	}
	
}
