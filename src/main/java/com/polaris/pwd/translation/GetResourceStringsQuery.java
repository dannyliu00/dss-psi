package com.polaris.pwd.translation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

/*
 * Returns all resource strings for an application
 */
public class GetResourceStringsQuery extends MappingSqlQuery<ResourceStringDto> {
	
	private static final String RESOURCE_QUERY = "SELECT c.Content, lang.Language,cl.Value" +
					" FROM" +
					" Translate.ContentLanguage cl INNER JOIN [Translate].[Language] lang ON cl.LanguageId=lang.LanguageId" +
					" INNER JOIN Translate.Content c on cl.ContentId=c.ContentId" + 
					" INNER JOIN Enterprise.Application a on c.ApplicationID=a.applicationid" +
					" WHERE AppGuid=? and lang.LanguageCode=?";
	
	
    public GetResourceStringsQuery(DataSource dataSource) {
    	super(dataSource, RESOURCE_QUERY);
    	
    	// Setup the parameters
		super.declareParameter(new SqlParameter("AppGuid", Types.VARCHAR,255));
		super.declareParameter(new SqlParameter("Language", Types.VARCHAR,50));	
        
        compile();
    }

    public ResourceStringDto mapRow(ResultSet rs, int rowNumber) throws SQLException {
    	
    	ResourceStringDto item = new ResourceStringDto();
    	
    	item.setTerm((String) rs.getObject("Content"));
    	item.setLanguage((String) rs.getObject("Language"));
    	item.setValue((String) rs.getObject("Value"));
    	
    	return item;
    } 
}