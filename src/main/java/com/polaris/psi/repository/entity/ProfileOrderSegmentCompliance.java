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
//@Table(name = "OT075F_ProfileSegmentRecommendation")
public class ProfileOrderSegmentCompliance {
	
//	@Id
//	@Column(name = "N5ID")
	private int id;
	
//	@JoinColumn(name = "N5IPID")
	private Profile profile;
	
//	@JoinColumn(name = "N5CODE")
	private ProfilePeriod profilePeriod;
	
//	@JoinColumn(name = "N5OSEG")
	private OrderSegment orderSegment;
	
//	@Column(name = "N5RMIN")
	private int minimum;
	
//	@Column(name = "N5REC")
	private int recommended;
	
//	@Column(name = "N5RMAX")
	private int maximum;

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
	 * @return the profilePeriod
	 */
	public ProfilePeriod getProfilePeriod() {
		return profilePeriod;
	}

	/**
	 * @param profilePeriod the profilePeriod to set
	 */
	public void setProfilePeriod(ProfilePeriod profilePeriod) {
		this.profilePeriod = profilePeriod;
	}

	/**
	 * @return the orderSegment
	 */
	public OrderSegment getOrderSegment() {
		return orderSegment;
	}

	/**
	 * @param orderSegment the orderSegment to set
	 */
	public void setOrderSegment(OrderSegment orderSegment) {
		this.orderSegment = orderSegment;
	}

	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the recommended
	 */
	public int getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
