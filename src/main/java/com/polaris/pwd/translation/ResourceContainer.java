package com.polaris.pwd.translation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;



/*
 * Wrapper class to hold the language resource strings
 * 
 * @author pceder
 */
public class ResourceContainer {

	private String language;
	private final Map<String, String> languageStrings = new HashMap<String,String>();
	
	private Date createDate;
	
	public ResourceContainer() {
		createDate = new Date();
	}
	
	// A mechanism to determine for how long to hang on to the cached resource strings.
	public boolean isExpired() {
		return DateUtils.addHours(createDate, 1).after(new Date());
	}

	/**
	 * @return the languageStrings
	 */
	public Map<String, String> getLanguageStrings() {
		return languageStrings;
	}


	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
