/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;
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
	
	@Column(name = "N7MAIL")
	private String emailAddress;
	
	@Column(name = "N7SBDT")
	private Date submittedDate;
	
	@Column(name = "N7APDT")
	private Date approvedDate;
	
	@Column(name = "N7CRDT")
	private Date createdDate;
	
	@Column(name = "N7CRUS")
	private String createdUser;
	
	@Column(name = "N7CRPG")
	private String createdProgram;
	
	@Column(name = "N7CHDT")
	private Date changedDate;
	
	@Column(name = "N7CHUS")
	private String changeUser;
	
	@Column(name = "N7CHPG")
	private String changedProgram;
	
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
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the createdProgram
	 */
	public String getCreatedProgram() {
		return createdProgram;
	}

	/**
	 * @param createdProgram the createdProgram to set
	 */
	public void setCreatedProgram(String createdProgram) {
		this.createdProgram = createdProgram;
	}

	/**
	 * @return the changedDate
	 */
	public Date getChangedDate() {
		return changedDate;
	}

	/**
	 * @param changedDate the changedDate to set
	 */
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	/**
	 * @return the changeUser
	 */
	public String getChangeUser() {
		return changeUser;
	}

	/**
	 * @param changeUser the changeUser to set
	 */
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	/**
	 * @return the changedProgram
	 */
	public String getChangedProgram() {
		return changedProgram;
	}

	/**
	 * @param changedProgram the changedProgram to set
	 */
	public void setChangedProgram(String changedProgram) {
		this.changedProgram = changedProgram;
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
