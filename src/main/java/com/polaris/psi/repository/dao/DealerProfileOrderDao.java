/**
 * 
 */
package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PolarisOrder;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileOrderDao extends AbstractPolarisMinneapolisDao<PolarisOrder> {

	public DealerProfileOrderDao() {
		super(PolarisOrder.class);
	}
	
}
