package com.polaris.psi.util;

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

}
