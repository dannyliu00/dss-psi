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
//@Table(name = "OT076F_ProfileSuperSegmentCompliance")
public class ProfileSegmentCompliance {

//	@Id
//	@Column(name = "N6ID")
	private int id;
	
//	@JoinColumn(name = "N6IPID")
	private Profile profile;
	
//	@JoinColumn(name = "N6CODE")
	private ProfilePeriod profilePeriod;
	
//	@JoinColumn(name = "N6DLR")
	private Dealer dealer;
	
//	@JoinColumn(name = "N6SPSEG")
	private Segment segment;
	
//	@Column(name = "C6SMIN")
	private int minimum;
	
//	@Column(name = "C6SMAX")
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
	 * @return the dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	/**
	 * @return the segment
	 */
	public Segment getSegment() {
		return segment;
	}

	/**
	 * @param segment the segment to set
	 */
	public void setSegment(Segment segment) {
		this.segment = segment;
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
