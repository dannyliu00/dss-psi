/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT073F_ProfileAndPeriodIDs")
public class ProfileAndPeriod implements Serializable {

	private static final long serialVersionUID = -2341524121167528365L;

	@Id
	@Column(name = "N3ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "N3IPID")
	private Profile profile;

	@OneToMany
	@JoinColumn(name = "N3PPID")
	private List<ProfilePeriod> periods;

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
	 * @return the periods
	 */
	public List<ProfilePeriod> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(List<ProfilePeriod> periods) {
		this.periods = periods;
	}

}
