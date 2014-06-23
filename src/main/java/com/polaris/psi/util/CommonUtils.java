package com.polaris.psi.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

public class CommonUtils {
	
	private static Logger log = Logger.getLogger(CommonUtils.class);
	
	public static boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	public static Date convertDate(String s) {
		if(s == null) return null;
		
		final String[] formats = {"MM-dd-yyyy", "MM/dd/yyyy", "MM-dd-yy", "MM/dd/yy"};
		try {
			return DateUtils.parseDate(s, formats);
		} catch (ParseException pe) {
			log.error(pe.getMessage());
			log.error("Date format of string passed in is not supported.  Supported formates are: ");
			for (int i = 0; i < formats.length; i++) {
				log.error("    " + formats[i]);
			}
			log.error("String value passed in was: " + s);
		}
		
		return null;
	}

	public static Integer convertToInteger(BigDecimal value) {
		if(value == null) return null;
		
		return value.intValueExact();
	}
	
	public static  String trimString(Character value) {
		if(value == null) return null;
		
		return trimString(value.toString());
	}
	
	public static String trimString(String value) {
		if(value == null) return null;
		
		return value.trim();
	}
}
