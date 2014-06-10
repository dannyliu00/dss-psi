package com.polaris.psi.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class CommonUtils {
	
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
		String[] formats = {"MM-dd-yyyy", "ddd-MM-yyyy", "MM-dd-yy"};
		try {
			return DateUtils.parseDate(s, formats);
		} catch (ParseException pe) {
			
		}
		
		return null;
	}

}
