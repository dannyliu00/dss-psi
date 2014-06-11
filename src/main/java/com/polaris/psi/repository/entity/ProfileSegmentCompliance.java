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
@Table(name = "Profile.OT076F_ProfileSuperSegmentCompliance")
public class ProfileSegmentCompliance implements Serializable {

	private static final long serialVersionUID = 4992914637184597272L;

	@Id
	@Column(name = "N6ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "N6IPID")
	private Profile profile;
	
	@Column(name = "N6CODE")
	private String profileCode;
	
	@Column(name = "N6DLR")
	private int dealerId;
	
	@OneToOne
	@JoinColumn(name = "N6SPSEG", referencedColumnName="MISPSG")
	private Segment segment;
	
	@Column(name = "N6SMIN")
	private Integer minimum;
	
	@Column(name = "N6SMAX", nullable=true)
	private Integer maximum;
	
	@Column(name = "N6OREQ", nullable=true)
	private Integer recommendedOSCount;

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
	 * @return the profileCode
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 * @param profileCode the profileCode to set
	 */
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * @return the dealerId
	 */
	public int getDealerId() {
		return dealerId;
	}

	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
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

	/**
	 * @return the recommendedOSCount
	 */
	public Integer getRecommendedOSCount() {
		return recommendedOSCount;
	}

	/**
	 * @param recommendedOSCount the recommendedOSCount to set
	 */
	public void setRecommendedOSCount(Integer recommendedOSCount) {
		this.recommendedOSCount = recommendedOSCount;
	}

}
