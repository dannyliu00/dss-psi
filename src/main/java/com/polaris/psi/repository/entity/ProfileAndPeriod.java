/**
 * 
 */
package com.polaris.psi.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "OT073F_ProfileAndPeriodIDs")
public class ProfileAndPeriod {

//	@Id
//	@Column(name = "N3ID")
	private int id;
	
//	@JoinColumn(name = "N3IPID")
	private Profile profile;
	
//	@JoinColumn(name = "N3PPID")
	private ProfilePeriod period;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the period
	 */
	public ProfilePeriod getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(ProfilePeriod period) {
		this.period = period;
	}
	
}
