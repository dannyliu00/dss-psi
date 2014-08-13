package com.polaris.pwd.translation;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.polaris.pwf.config.spring.datasource.PolarisDealersCommonDataSource;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;
import com.polaris.pwf.repository.dealercommon.entity.GetResourceStringsQuery;

/*
 * DAO for retrieving resource strings
 */
@Repository
public class ResourceStringDao extends AbstractPolarisDealersExtensionDao<ResourceStringDto> {

	private GetResourceStringsQuery query;
	
    public ResourceStringDao() {
        super(ResourceStringDto.class);
    }

    @Autowired
	@Qualifier(PolarisDealersCommonDataSource.DATASOURCE_BEAN_NAME)
	public void setDataSource(DataSource dataSource){
    	
    	this.query = new GetResourceStringsQuery(dataSource);
	}
	
	public List<ResourceStringDto> getResourceStrings(String appGuid, String languageId) {
    	
    	List<ResourceStringDto> items = this.query.execute(appGuid, languageId);
	    
	    return items;
	}
}
