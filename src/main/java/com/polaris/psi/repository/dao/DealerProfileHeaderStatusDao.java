/**
 * 
 */
package com.polaris.psi.repository.dao;

import java.util.List;

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
	
	public DealerProfileHeaderStatus retrieveById(Integer id) {
		return select(id);
	}
	
	public List<DealerProfileHeaderStatus> retrieveAll() {
		return retrieveAll();
	}
}
