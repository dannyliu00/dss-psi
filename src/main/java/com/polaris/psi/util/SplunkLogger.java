package com.polaris.psi.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class SplunkLogger {

	private final Logger log;
	
	public SplunkLogger(Class<?> type) {
		
		log = Logger.getLogger(type);
	}
	
	public void methodStart(PolarisIdentity identity, String method) {
		trace(identity,method,"Start");
	}
	
	public void methodEnd(PolarisIdentity identity,String method) {
		trace(identity,method,"End");
	}

	public void trace(PolarisIdentity identity,String method, String message) {
		log.trace(addAttributes(identity,method,message,null));
		
	}

	public void trace(PolarisIdentity identity,String method, String message, final HashMap<String,Object> values) {
		log.trace(addAttributes(identity,method,message,values));
	}

	public void debug(PolarisIdentity identity,String method, String message) {
		log.debug(addAttributes(identity,method,message,null));
	}

	public void debug(PolarisIdentity identity,String method, String message, final HashMap<String,Object> values) {
		log.debug(addAttributes(identity,method,message,values));
	}

	public void debug(PolarisIdentity identity,String method, final HashMap<String,Object> values) {
		log.debug(addAttributes(identity,method,null,values));	}

	public void info(PolarisIdentity identity,String method, String message) {
		log.info(addAttributes(identity,method,message,null));
	}

	public void info(PolarisIdentity identity,String method, String message, final HashMap<String,Object> values) {
		log.info(addAttributes(identity,method,message,values));
	}

	public void warn(PolarisIdentity identity,String method, String message) {
		log.warn(addAttributes(identity,method,message,null));
	}

	public void warn(PolarisIdentity identity,String method, String message, final HashMap<String,Object> values) {
		log.warn(addAttributes(identity,method,message,values));
	}

	public void error(PolarisIdentity identity,final String method, final Throwable exception ) {
		log.error(addAttributes(identity,method,null,null),exception);
		
	}

	public void error(PolarisIdentity identity,final String method, String message ) {
		log.error(addAttributes(identity,method,message,null));
		
	}


	/*
	 * Writes the error with the required properties and the common fields.
	 */
	private String addAttributes(PolarisIdentity identity,String method, String message, final Map<String,Object> values) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("UserId=").append("\"").append(identity.getUserId()).append("\"");
		sb.append(",DealerId=").append("\"").append(identity.getDealerId()).append("\"");
		sb.append(",Method=").append("\"").append(method.replace('"', '\'')).append("\"");
	
		if(message!=null && message.length()>0) {
			sb.append(",Message=").append("\"").append(message.replace('"', '\'')).append("\"");
		}
		if(values!=null) {
			for (Map.Entry<String, Object> entry : values.entrySet()) {
			    String key = entry.getKey();
			    Object value = entry.getValue();
			    sb.append("," + key + "=").append("\"").append(value.toString().replace('"', '\'')).append("\"");
			}
		}
		
		return sb.toString();
	}
}
