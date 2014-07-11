/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.ReasonCode;
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
        keyMap.put("role", getReasonCodeRole(roleId));
		
		return selectByMap(keyMap, null);
	}	
	
	protected String getReasonCodeRole(int role) {
		String roleDescription = "";
		String dealer = Constants.DEALER_REASON_ROLE_DESC;
		String dsm = Constants.DSM_REASON_ROLE_DESC;
		String admin = Constants.ADMIN_REASON_ROLE_DESC;
		
		switch (role) {
		case 1:
			roleDescription = admin;
			break;

		case 5:
			roleDescription = dsm;
			break;
			
		default: // dealer = 4
			roleDescription = dealer;
			break;
		}
		
		return roleDescription;
	}
	
}
