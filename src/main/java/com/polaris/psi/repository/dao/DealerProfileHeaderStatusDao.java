/**
 * 
 */
package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileHeaderStatusDao extends AbstractPolarisMinneapolisDao<DealerProfileHeaderStatus> {

	public DealerProfileHeaderStatusDao() {
		super(DealerProfileHeaderStatus.class);
	}
	
}
