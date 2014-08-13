package com.polaris.pwd.translation;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;



/*
 * Wrapper class to hold the language resource strings
 * 
 * @author pceder
 */
public class ResourceContainer {

	private String language;
	private Map<String, String> languageStrings;
	
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
	 * @param languageStrings the languageStrings to set
	 */
	public void setLanguageStrings(Map<String, String> languageStrings) {
		this.languageStrings = languageStrings;
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
