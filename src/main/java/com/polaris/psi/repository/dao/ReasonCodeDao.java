/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class ReasonCodeDao extends AbstractPolarisMinneapolisDao<ReasonCode> {

	public ReasonCodeDao() {
		super(ReasonCode.class);
	}
	
	public List<ReasonCode> getReasonCodes(int roleId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("role", roleId);
		
		return selectByMap(keyMap, null);
	}	
	
}
