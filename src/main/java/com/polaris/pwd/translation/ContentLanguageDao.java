package com.polaris.pwd.translation;

import org.springframework.stereotype.Repository;

import com.polaris.pwf.dao.AbstractPolarisDealersCommonDao;

/*
 * @author pceder
 */
@Repository
public class ContentLanguageDao extends AbstractPolarisDealersCommonDao<ContentLanguage> {

	public ContentLanguageDao() {
		super(ContentLanguage.class);
		// TODO Auto-generated constructor stub
	}
	public ContentLanguageDao(Class<ContentLanguage> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
