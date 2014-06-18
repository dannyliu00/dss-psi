/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT075F_ProfileSegmentRecommendation")
public class OrderSegmentCompliance implements Serializable {
	
	private static final long serialVersionUID = 5400103451840365533L;

	@Id
	@Column(name = "N5ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "N5IPID")
	private Profile profile;
	
	@OneToOne
	@JoinColumn(name = "N5CODE", referencedColumnName = "N0CODE")
	private ProfilePeriod profilePeriod;

	@Column(name = "N5OSEG")
	private String profileAndOrderSegment;
	
	@Column(name = "N5RMIN")
	private Integer minimum;
	
	@Column(name = "N5REC")
	private Integer recommended;
	
	@Column(name = "N5RMAX")
	private Integer maximum;

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
	 * @return the profileAndOrderSegment
	 */
	public String getProfileAndOrderSegment() {
		return profileAndOrderSegment;
	}

	/**
	 * @param profileAndOrderSegment the profileAndOrderSegment to set
	 */
	public void setProfileAndOrderSegment(String profileAndOrderSegment) {
		this.profileAndOrderSegment = profileAndOrderSegment;
	}

	/**
	 * @return the minimum
	 */
	public Integer getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the recommended
	 */
	public Integer getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the maximum
	 */
	public Integer getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	
}
