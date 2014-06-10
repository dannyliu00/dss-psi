package com.polaris.psi.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Profile.OT074F_ProfileAndOrderSegments")
public class OrderSegment {

	@Id
	@Column(name = "N4PSID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "N4IPID")
	private Profile profile;
	
	@OneToOne
	@JoinColumn(name = "N4OSEG")
	private ProfileOrderSegmentCompliance compliance;
	
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
	 * @return the compliance
	 */
	public ProfileOrderSegmentCompliance getCompliance() {
		return compliance;
	}

	/**
	 * @param compliance the compliance to set
	 */
	public void setCompliance(ProfileOrderSegmentCompliance compliance) {
		this.compliance = compliance;
	}

}
