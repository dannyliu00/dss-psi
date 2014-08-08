/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
public class SegmentStockingProfileOrderPK implements Serializable {

	private static final long serialVersionUID = 834331705556974500L;

	private String profileName;
	
	private String detailToken;
	
	private int profilePeriod;

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the detailToken
	 */
	public String getDetailToken() {
		return detailToken;
	}

	/**
	 * @param detailToken the detailToken to set
	 */
	public void setDetailToken(String detailToken) {
		this.detailToken = detailToken;
	}

	/**
	 * @return the profilePeriod
	 */
	public int getProfilePeriod() {
		return profilePeriod;
	}

	/**
	 * @param profilePeriod the profilePeriod to set
	 */
	public void setProfilePeriod(int profilePeriod) {
		this.profilePeriod = profilePeriod;
	}
	
}
