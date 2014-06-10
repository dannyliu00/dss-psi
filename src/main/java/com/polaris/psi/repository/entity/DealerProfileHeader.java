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
import javax.persistence.Table;


/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT077F_DealerProfileHeader")
public class DealerProfileHeader implements Serializable {

	private static final long serialVersionUID = -8042124328347338586L;

	@Id
	@Column(name = "N7DHID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "N7IPID")
	private Profile profile;
	
	@Column(name = "N7DLR")
	private int dealerId;
	
	@ManyToOne
	@JoinColumn(name = "N7STID")
	private DealerProfileHeaderStatus status;

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
	 * @return the status
	 */
	public DealerProfileHeaderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(DealerProfileHeaderStatus status) {
		this.status = status;
	}
	
	
	
}
