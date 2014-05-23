package com.polaris.psi;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Constants {
	
    // initialized in InitServlet
	public static final Calendar DEFAULT_DATE = new GregorianCalendar(1900, 0, 1, 0, 0, 0);
	
	//Enterprise attributes specific to this application
	public final static String ENTERPRISE_ATTRIBUTE_TRANSLATION_URL = "getTranslation";

    public static final String PROGRAM_NAME = "Inventory Profile Sell-In";
    public static String APPLICATION_GUID = "02E0E350-DA3B-4C84-BB26-9B1E8F5A9862";

	//Caches
	public final static String VALID_VALUES_CACHE = "app-ValidValues";
	public final static String VALID_VALUES_MAP_CACHE = "app-ValidValuesMap";
	public final static String VALID_VALUES_MAP_ALL_CACHE = "app-ValidValuesMapAll";
}

