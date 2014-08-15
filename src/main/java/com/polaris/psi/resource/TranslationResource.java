package com.polaris.psi.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwd.translation.TextTranslationService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

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
					translationService.getResourceStrings(languagePreference,Constants.APPLICATION_GUID);
	        
			
			LOG.methodEnd(PolarisIdentity.get(),"getResourceStrings");
			
			return langMap;
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "getResourceStrings", e);
			throw e;
		}
	}
	
	@Path("/{addResourceString}")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addResourceString(String newTerm) {
		try {
			LOG.methodStart(PolarisIdentity.get(),"addResourceString");
			
			if(newTerm==null || newTerm.isEmpty()) {
				// this is not expected.
				LOG.warn(PolarisIdentity.get(),"addResourceString", "Attempt was made to add a null or empty string.");
				return null;
			}
			
			LOG.info(PolarisIdentity.get(),"addResourceString", "Adding term: " + newTerm );
			
			String languagePreference = sessionHelper.getUserData().getSessionDetail().get(Constants.LANGUAGE_PREFERENCE);
			//Locale locale;
			
			translationService.addTerm(Constants.APPLICATION_GUID, languagePreference, newTerm);

			LOG.methodEnd(PolarisIdentity.get(),"addResourceString");
			
			return null;
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "addResourceString", e);
			throw e;
		}
		
	}	
}
