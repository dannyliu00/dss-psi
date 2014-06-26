/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "OT077F")
public class DealerProfileHeader implements Serializable {

	private static final long serialVersionUID = -8042124328347338586L;

	@Id
	@Column(name = "N7DHID")
	private int id;

	@Column(name = "N7IPID")
	private int profileId;
	
	@Column(name = "N7DLR")
	private int dealerId;
	
	@ManyToOne
	@JoinColumn(name = "N7STID")
	private DealerProfileHeaderStatus status;
	
	@OneToMany(mappedBy = "header", fetch=FetchType.EAGER)
	private List<DealerProfileDetail> details;

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
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
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

	/**
	 * @return the details
	 */
	public List<DealerProfileDetail> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<DealerProfileDetail> details) {
		this.details = details;
	}
	
}
