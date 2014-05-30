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
//@Table(name = "OT074F_ProfileAndOrderSegments")
public class ProfileAndOrderSegment {

//	@Id
//	@Column(name = "N4PSID")
	private int id;
	
//	@JoinColumn(name = "N4IPID")
	private Profile profile;
	
//	@JoinColumn(name = "N4OSEG")
	private OrderSegment orderSegment;

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
	
	
}
