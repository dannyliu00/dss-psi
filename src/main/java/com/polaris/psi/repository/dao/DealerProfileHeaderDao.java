/**
 * 
 */
package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileHeaderDao extends AbstractPolarisMinneapolisDao<DealerProfileHeader> {

	public DealerProfileHeaderDao() {
		super(DealerProfileHeader.class);
	}
	
	public DealerProfileHeader create(DealerProfileHeader header) {
		return insert(header);
	}

}
