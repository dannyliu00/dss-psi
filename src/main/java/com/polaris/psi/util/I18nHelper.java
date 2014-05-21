package com.polaris.psi.util;

import java.util.Locale;

public class I18nHelper {
	/**
	 * Takes parameter like en, en_US, fr, fr_CA etc. and returns an actual Locale object
	 * Doesn't handle variants like en_US_xx (fake example)
	 * @param locale
	 */
	public final static Locale getLocale(String locale) {
		if(locale == null) {
			return null;
		}
		
		String trimmedLocale = locale.trim();
		String language;
		String country;
		
		if(trimmedLocale.indexOf('_') != -1) {
			String[] pieces = trimmedLocale.split("_");
			language = pieces[0];
			country = pieces[1];
		} else {
			language = trimmedLocale;
			country = "";
		}
		
		return new Locale(language,country);
	}
}
