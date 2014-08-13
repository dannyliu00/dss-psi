package com.polaris.pwf.repository.dealercommon.entity;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.DealerId;
import com.polaris.pwf.dao.AbstractPolarisDealersCommonDao;

/*
 * @author pceder
 */
@Repository
public class ContentDao extends AbstractPolarisDealersCommonDao<Content> {

	public ContentDao() {
		super(Content.class);
	}
	
	public ContentDao(Class<Content> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
