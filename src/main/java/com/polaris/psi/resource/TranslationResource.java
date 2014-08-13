package com.polaris.psi.resource;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwd.translation.TextTranslationService;
import com.polaris.pwf.session.SessionHelper;

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
	TextTranslationService translationService;
	
	private static final SplunkLogger LOG = new SplunkLogger(TranslationResource.class);
	
	@GET
    @Path("/{getResourceStrings}")
    @Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getResourceStrings() {
		
		try {
			LOG.methodStart(PolarisIdentity.get(),"getResourceStrings");
			
			String languagePreference = sessionHelper.getUserData().getSessionDetail().get(Constants.LANGUAGE_PREFERENCE);
			//Locale locale;

			Map<String, String> langMap = 
					translationService.getResourceStrings(Constants.APPLICATION_GUID, languagePreference);
	        
			
			LOG.methodEnd(PolarisIdentity.get(),"getResourceStrings");
			
			return langMap;
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "getResourceStrings", e);
			throw e;
		}
	}
}
