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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author bericks
 *
 */
@Entity
@Table(name = "OT077F")
public class DealerProfileHeader implements Serializable {

	private static final long serialVersionUID = -8042124328347338586L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N7DHID")
	private int id;

	@Column(name = "N7IPID")
	private int profileId;
	
	@Column(name = "N7DLR")
	private int dealerId;
	
	@Column(name = "N7MAIL")
	private String emailAddress;
	
	@Column(name = "N7SBDT")
    @Temporal(TemporalType.DATE)
	private Date submittedDate;
	
	@Column(name = "N7SBTM")
    @Temporal(TemporalType.TIME)
	private Date submittedTime;
	
	@Column(name = "N7APDT")
    @Temporal(TemporalType.DATE)
	private Date approvedDate;
	
	@Column(name = "N7APTM")
    @Temporal(TemporalType.TIME)
	private Date approvedTime;
	
	@Column(name = "N7CRDT")
    @Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "N7CRTM")
    @Temporal(TemporalType.TIME)
	private Date createdTime;
	
	@Column(name = "N7CRUS")
	private String createdUser;
	
	@Column(name = "N7CRPG")
	private String createdProgram;
	
	@Column(name = "N7CHDT")
    @Temporal(TemporalType.DATE)
	private Date changedDate;
	
	@Column(name = "N7CHTM")
    @Temporal(TemporalType.TIME)
	private Date changedTime;
	
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
	 * @return the submittedTime
	 */
	public Date getSubmittedTime() {
		return submittedTime;
	}

	/**
	 * @param submittedTime the submittedTime to set
	 */
	public void setSubmittedTime(Date submittedTime) {
		this.submittedTime = submittedTime;
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
	 * @return the approvedTime
	 */
	public Date getApprovedTime() {
		return approvedTime;
	}

	/**
	 * @param approvedTime the approvedTime to set
	 */
	public void setApprovedTime(Date approvedTime) {
		this.approvedTime = approvedTime;
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
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	 * @return the changedTime
	 */
	public Date getChangedTime() {
		return changedTime;
	}

	/**
	 * @param changedTime the changedTime to set
	 */
	public void setChangedTime(Date changedTime) {
		this.changedTime = changedTime;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((changeUser == null) ? 0 : changeUser.hashCode());
		result = prime * result
				+ ((changedDate == null) ? 0 : changedDate.hashCode());
		result = prime * result
				+ ((changedProgram == null) ? 0 : changedProgram.hashCode());
		result = prime * result
				+ ((changedTime == null) ? 0 : changedTime.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdProgram == null) ? 0 : createdProgram.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result
				+ ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + dealerId;
		result = prime * result + id;
		result = prime * result + profileId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DealerProfileHeader other = (DealerProfileHeader) obj;
		if (changeUser == null) {
			if (other.changeUser != null)
				return false;
		} else if (!changeUser.equals(other.changeUser))
			return false;
		if (changedDate == null) {
			if (other.changedDate != null)
				return false;
		} else if (!changedDate.equals(other.changedDate))
			return false;
		if (changedProgram == null) {
			if (other.changedProgram != null)
				return false;
		} else if (!changedProgram.equals(other.changedProgram))
			return false;
		if (changedTime == null) {
			if (other.changedTime != null)
				return false;
		} else if (!changedTime.equals(other.changedTime))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdProgram == null) {
			if (other.createdProgram != null)
				return false;
		} else if (!createdProgram.equals(other.createdProgram))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (dealerId != other.dealerId)
			return false;
		if (id != other.id)
			return false;
		if (profileId != other.profileId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealerProfileHeader [id=" + id + ", profileId=" + profileId
				+ ", dealerId=" + dealerId + ", createdDate=" + createdDate
				+ ", createdTime=" + createdTime + ", createdUser="
				+ createdUser + ", createdProgram=" + createdProgram
				+ ", changedDate=" + changedDate + ", changedTime="
				+ changedTime + ", changeUser=" + changeUser
				+ ", changedProgram=" + changedProgram + ", status=" + status
				+ "]";
	}
	
}
