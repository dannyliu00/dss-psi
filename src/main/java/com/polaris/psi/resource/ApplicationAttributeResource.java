package com.polaris.psi.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.util.AttributeHelper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * Provides application settings to the UI
 * 
 * @author pceder
 *
 */
@Component
@Path("/attributes")
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationAttributeResource {

	private static final SplunkLogger LOG = new SplunkLogger(ApplicationAttributeResource.class);
	
	@Autowired
	AttributeHelper attributeHelper;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getAttributes() {
		
		LOG.methodStart(PolarisIdentity.get(), "getAttributes");
		
		try {
			Map<String, String> attributes = new HashMap<String, String>();
			
			attributes.put(Constants.ATTR_ASKPOLARISURL,attributeHelper.getAttribute(Constants.ATTR_ASKPOLARISURL));
			attributes.put(Constants.ATTR_ECHATURL,attributeHelper.getAttribute(Constants.ATTR_ECHATURL));
			attributes.put(Constants.ATTR_DSMHOME,attributeHelper.getAttribute(Constants.ATTR_DSMHOME));
			attributes.put(Constants.ATTR_DEALERHOME,attributeHelper.getAttribute(Constants.ATTR_DEALERHOME));
			attributes.put(Constants.ATTR_INTRANETHOME,attributeHelper.getAttribute(Constants.ATTR_INTRANETHOME));
			attributes.put(Constants.ATTR_DEALERCASES,attributeHelper.getAttribute(Constants.ATTR_DEALERCASES));
			attributes.put(Constants.ATTR_CHANGEDEALER,attributeHelper.getAttribute(Constants.ATTR_CHANGEDEALER));
			attributes.put(Constants.ATTR_ADMINHOMEURL,attributeHelper.getAttribute(Constants.ATTR_ADMINHOMEURL));
			attributes.put(Constants.ATTR_DEXROOT,attributeHelper.getAttribute(Constants.ATTR_DEXROOT));
			attributes.put(Constants.ATTR_ADMINHOME,attributeHelper.getAttribute(Constants.ATTR_ADMINHOME));
			
			LOG.methodEnd(PolarisIdentity.get(), "getAttributes");
			
			return attributes;
			
		} catch (Exception e) {
			// Log whatever this is and re throw
			LOG.error(PolarisIdentity.get(), "getAttributes", e);
			throw e;
		}
	}
}
