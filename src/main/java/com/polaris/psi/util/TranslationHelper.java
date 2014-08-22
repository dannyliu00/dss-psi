package com.polaris.psi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.pwd.translation.TextTranslationService;
import com.polaris.pwf.session.SessionHelper;


@Component
public class TranslationHelper {

	@Autowired
	TextTranslationService translationService;
	
	@Autowired
	SessionHelper sessionHelper;

	private static final SplunkLogger LOG = new SplunkLogger(TranslationHelper.class);

	/*
	 * Get all strings for this application
	 * @author pceder
	 */
	public Map<String, String> getStrings() {

		try {
		
			String languageCode = sessionHelper.getUserData().getSessionDetail().get(Constants.LANGUAGE_PREFERENCE);
			String applicationGuid = Constants.APPLICATION_GUID;
			
			return translationService.getResourceStrings(languageCode, applicationGuid);

		} catch (Exception e) {
			// Exceptions here should not affect the application
			LOG.error(PolarisIdentity.get(),"getStrings", e );
			return new HashMap<String,String>();
		}
	}
    /*
     * Returns a translated string 
     */
	public String getString(String term) {
		
		// This can happen since it is user entered data that gets translated.
		if(term==null || term.isEmpty()) {
			return term;
		}
		
		LOG.methodStart(PolarisIdentity.get(),"getString");
		
		try {
			String languageCode = sessionHelper.getUserData().getSessionDetail().get(Constants.LANGUAGE_PREFERENCE);
			
			return getString(term,languageCode);
			
		} catch (Exception e) {
			// Exceptions here should not affect the application
			LOG.error(PolarisIdentity.get(),"getString", e );
			return term;
		}
		
	}
	
	/*
     * Returns a translated string 
	 * @author pceder
     */
	public String getString(String term, String languageCode) {
		
		// This can happen since it is user entered data that gets translated.
		if(term==null || term.isEmpty()) {
			return term;
		}
		
		LOG.methodStart(PolarisIdentity.get(),"getString");
		
		try {
			String applicationGuid = Constants.APPLICATION_GUID;
			
			Map<String, String> resourceStrings = translationService.getResourceStrings(languageCode, applicationGuid);
			
			if(resourceStrings.containsKey(term)) {
				return resourceStrings.get(term);
			}
			
			// String does not exists.
			LOG.info(PolarisIdentity.get(),"getString", "Term not found: " + term );
			
			addString(term);
		
			LOG.methodEnd(PolarisIdentity.get(),"getString");
			
			return term;
			
		} catch (Exception e) {
			// Exceptions here should not affect the application
			LOG.error(PolarisIdentity.get(),"getString", e );
			return term;
		}
		
	}	
	
	public void addString(String newTerm) {
		try {
			LOG.methodStart(PolarisIdentity.get(),"addString");
			
			if(newTerm==null || newTerm.isEmpty()) {
				// this is not expected.
				LOG.warn(PolarisIdentity.get(),"addString", "Attempt was made to add a null or empty string.");
				return;
			}
			
			LOG.info(PolarisIdentity.get(),"addString", "Adding term: " + newTerm );
			
			String languagePreference = sessionHelper.getUserData().getSessionDetail().get(Constants.LANGUAGE_PREFERENCE);
			//Locale locale;
			
			translationService.addTerm(Constants.APPLICATION_GUID, languagePreference, newTerm);

			LOG.methodEnd(PolarisIdentity.get(),"addString");
			
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "addString", e);
		}
	}
	
	
}
