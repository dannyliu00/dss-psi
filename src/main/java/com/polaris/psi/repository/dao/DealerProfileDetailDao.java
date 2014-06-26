package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.pwf.dao.AbstractPolarisMinneapolisDao;

/**
 * @author bericks
 *
 */
@Repository
public class DealerProfileDetailDao extends AbstractPolarisMinneapolisDao<DealerProfileDetail> {

	public DealerProfileDetailDao() {
		super(DealerProfileDetail.class);
	}
	
	public DealerProfileDetail create(DealerProfileDetail detail) {
		return insert(detail);
	}
}
