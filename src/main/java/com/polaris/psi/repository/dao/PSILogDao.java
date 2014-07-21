/**
 * 
 */
package com.polaris.psi.repository.dao;

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

}
