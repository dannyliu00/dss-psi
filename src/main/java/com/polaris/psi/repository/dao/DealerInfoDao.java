/**
 * 
 */
package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Dealer;
import com.polaris.pwf.dao.AbstractPolarisDealersDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerInfoDao extends AbstractPolarisDealersDao<Dealer> {

	public DealerInfoDao() {
		super(Dealer.class);
	}
	
}
