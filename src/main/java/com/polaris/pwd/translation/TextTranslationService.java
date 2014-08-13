package com.polaris.pwd.translation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polaris.psi.Constants;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.repository.CommonRepositoryConstants;
import com.polaris.pwf.repository.dealercommon.entity.Application;
import com.polaris.pwf.repository.dealercommon.entity.ApplicationDao;
import com.polaris.pwf.repository.dealercommon.entity.Content;
import com.polaris.pwf.repository.dealercommon.entity.ContentDao;
import com.polaris.pwf.repository.dealercommon.entity.ContentLanguage;
import com.polaris.pwf.repository.dealercommon.entity.ContentLanguageDao;


@Service
@ComponentScan(basePackages = {"com.polaris.pwd.translation"})
public class TextTranslationService {
	
	@Autowired
	ResourceStringDao resourceStringDao;

	@Autowired
	ContentDao contentDao;
	
	@Autowired
	ContentLanguageDao contentLanguageDao;

	@Autowired
	ApplicationDao applicationDao;
	

	
	private static final SplunkLogger LOG = new SplunkLogger(TextTranslationService.class);
	
	private final  Map<String, ResourceContainer> resources = new HashMap<String,ResourceContainer>();
	private Integer applicationId=null;
	
	private final Object myLock = new Object();
	
	
	// Returns all resource strings for a application.
	public Map<String, String> getResourceStrings(String applicationId, String languageCode) {
		
		LOG.methodStart(PolarisIdentity.get(), "getResourceStrings");
		
		if(resources.get(languageCode)!=null && resources.get(languageCode).isExpired()==false) {
			return resources.get(languageCode).getLanguageStrings();
		}
		
		LOG.info(PolarisIdentity.get(), "getResourceStrings", "Resource strings not found in cache for: " + languageCode);
		
		// Found nothing, or it is expired. We're going to have to synchronize
		synchronized(myLock) {

			// Check again
			if(resources.get(languageCode)!=null && resources.get(languageCode).isExpired()==false) {
				return resources.get(languageCode).getLanguageStrings();
			}
			
			ResourceContainer newContainer = new ResourceContainer();
			newContainer.setLanguage(languageCode);
			
			for(ResourceStringDto dto:resourceStringDao.getResourceStrings(applicationId, languageCode) ) {
				newContainer.getLanguageStrings().put(dto.getTerm(), dto.getValue());	
			}
			resources.put(languageCode, newContainer);
		}

		LOG.methodEnd(PolarisIdentity.get(), "getResourceStrings");
		
		return resources.get(languageCode).getLanguageStrings();
	}
	
	/*
	 * Adds a new term to the database and to the static collection.
	 */
    @Transactional(CommonRepositoryConstants.TX_MANAGER_POLARIS_DEALERS_COMMON)
	public synchronized void addTerm(String applicationGuid, String languageId, String term) {
		
		// Do some null checks
		if(languageId==null || languageId.isEmpty()) {throw new IllegalArgumentException("languageId cannot be null");}
		if(term==null || term.isEmpty()) {throw new IllegalArgumentException("term cannot be null");}
		if(applicationGuid==null || applicationGuid.isEmpty()) {throw new IllegalArgumentException("applicationGuid cannot be null");}
		
		// Make sure we don't already have it.
		if(getResourceStrings(Constants.APPLICATION_GUID,languageId).containsKey(term)) {
			LOG.warn(PolarisIdentity.get(), "addTerm", String.format("Attempt to add term:[%s] that already exist for language:[%s]. Action was aborted. ",term,languageId));
			
			// leave
			return;
		}
		
		// check if we need to go and get the applicationID
		if(this.applicationId==null) {
			Application app = applicationDao.selectByAppGuid(applicationGuid);
			if(app!=null) {
				this.applicationId=app.getId();
			} else {
				throw new IllegalArgumentException("Unable to find an ApplicationID for AppGuid: " + applicationGuid);
			}
		}
		
		// Create the term
		Content newContent = createNewContent(term);
		contentDao.insert(newContent);
		
		// Create the default English entry
		ContentLanguage newContentLanguage = createNewContentLanguage(1, term); //TODO: this should not be hard coded
		newContentLanguage.setContentId(newContent.getId());
		contentLanguageDao.insert(newContentLanguage);
		
		// Add it to the static structure
		getResourceStrings(Constants.APPLICATION_GUID,languageId).put(term, term);
		
	}
    
    private Content createNewContent(String term) {
		// Create the term
		Content newContent = new Content();
		newContent.setActive(true);
		newContent.setApplicationId(this.applicationId);
		newContent.setContent(term);
		newContent.setCreatedBy(PolarisIdentity.get().getUserId());
		newContent.setCreatedDate(new Date());
		newContent.setDefaultLcid(409);
		newContent.setUpdatedBy(PolarisIdentity.get().getUserId());
		newContent.setUpdatedDate(new Date());
		
		return newContent;
    	
    }
    
    private ContentLanguage createNewContentLanguage(Integer languageId, String term) {
		// Create the term
    	ContentLanguage newContent = new ContentLanguage();
    	newContent.setValue(term);
    	newContent.setLanguageId(languageId);
		newContent.setCreatedBy(PolarisIdentity.get().getUserId());
		newContent.setCreatedDate(new Date());
		newContent.setUpdatedBy(PolarisIdentity.get().getUserId());
		newContent.setUpdatedDate(new Date());
		
		return newContent;
    	
    }

}
