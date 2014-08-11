package com.polaris.psi.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.webservice.client.MenuDataWebServiceClient;

/**
 * @author pceder
 *
 */
@Component
@Path("/translation")
@Produces(MediaType.APPLICATION_JSON)
public class TranslationResource {
	
	@Autowired
	SessionHelper sessionHelper;	
	
	@Autowired
	MenuDataWebServiceClient menuDataWebServiceClient;
	
	private static final SplunkLogger LOG = new SplunkLogger(TranslationResource.class);
	
	@GET
    @Path("/{getResourceStrings}")
    @Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getResourceStrings() {
		
		try {
			LOG.methodStart(PolarisIdentity.get(),"getResourceStrings");
			
	        Map<String, String> langMap = new HashMap<String, String>();
	        
	        langMap.put("Inventory Profile Summary","Min inventering summering");
	        langMap.put("Arne","Berit");
			
			LOG.methodEnd(PolarisIdentity.get(),"getResourceStrings");
			
			return langMap;
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "getResourceStrings", e);
			throw e;
		}
	}
}
