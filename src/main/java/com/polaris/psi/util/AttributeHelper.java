package com.polaris.psi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.Constants;
import com.polaris.pwf.repository.dealercommon.dao.EnterpriseApplicationDao;
import com.polaris.pwf.repository.dealercommon.dao.EnterpriseAttributeDao;
import com.polaris.pwf.repository.dealercommon.entity.EnterpriseApplication;
import com.polaris.pwf.repository.dealercommon.entity.EnterpriseAttribute;

/*
 *  Class is used to obtain Attributes (settings) from the attributes tables for this application.
 *  
 *  @author pceder
 */

@Service
public class AttributeHelper {
	
	@Autowired
	EnterpriseAttributeDao enterpriseAttributeDao;
	
    @Autowired
    EnterpriseApplicationDao enterpriseApplicationDao;
    
    private static final SplunkLogger LOG = new SplunkLogger(AttributeHelper.class);
	
    /*
     * Retrieves the attribute value for the attributeName
     * 
     * @author pceder
     */
	public String getAttribute(String attributeName) {
		
		if(attributeName==null) {
			LOG.warn(PolarisIdentity.get(), "getAttribute", "Parameter 'attributeName' is NULL");
			throw new IllegalArgumentException("AttributeName cannot be null");
		}
		
		EnterpriseApplication ea = enterpriseApplicationDao.getEnterpriseApplication(Constants.APPLICATION_GUID);
	    EnterpriseAttribute value = enterpriseAttributeDao.getEnterpriseAttribute(ea.getApplicationId(), "PSI", attributeName);
	    
	    if(value==null) {
	    	// Try the global application. Need to try all modules we know of. Yes, a bit of a hack
	    	String[] modules = new String[]{"Java Core Service","Extranet Header","DSM Header","DIS Header"};
	    	
	    	for(String module:modules) {
				ea = enterpriseApplicationDao.getEnterpriseApplication(Constants.GLOBAL_APPLICATION_GUID);
			    value = enterpriseAttributeDao.getEnterpriseAttribute(ea.getApplicationId(), module, attributeName);
			    
			    if(value!=null) {
			    	break;
			    }
	    	}
	    }
	    if(value!=null) {
	    	return value.getAttributeValue();
	    } else {
	    	LOG.warn(PolarisIdentity.get(), "getAttribute", "Missing attribute value:" + attributeName);
		    return "";
	    }
	}
}
