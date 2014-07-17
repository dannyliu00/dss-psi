package com.polaris.psi.util;

import com.polaris.pwf.session.SessionThreadLocal;
import com.polaris.pwf.session.UserData;

public class PolarisIdentity {
	private String userId;
	private Integer dealerId;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the dealerId
	 */
	public Integer getDealerId() {
		return dealerId;
	}
	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	
	public static PolarisIdentity get() {
    	
    	UserData userData = SessionThreadLocal.getUserData();
    	
    	PolarisIdentity identity = new PolarisIdentity();

    	if(userData!=null) {
        	identity.setDealerId(userData.getDealerId());
        	identity.setUserId(userData.getUserName());
    	} else {
        	identity.setDealerId(0);
        	identity.setUserId("Unknown");
    	}
        
   	
    	
    	return identity;
    }	
}
