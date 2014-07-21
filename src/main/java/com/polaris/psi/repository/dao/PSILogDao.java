/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSILog;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class PSILogDao extends AbstractPolarisMinneapolisDao<PSILog> {

	public PSILogDao() {
		super(PSILog.class);
	}
	
	public int getLogEntryCount(int headerId, int detailId) {
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("headerId", headerId);
        keyMap.put("detailId", detailId);
        
        return selectByMap(keyMap, null).size();
	}

}
