/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bericks
 *
 */
public class PSIProfile implements Serializable {

	private static final long serialVersionUID = 8073663052640811578L;
	
	private Integer id;
	private Date targetCompleteDate;
	private String name;
	private String profileStatus;
	private String status;
	private String type;
	private String legalText;
	private Integer headerId;
	private Integer dealer;
	private String email;
	private Date submittedDate;
	private Date approvedDate;
	private Date lastModifiedDate;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the targetCompleteDate
	 */
	public Date getTargetCompleteDate() {
		return targetCompleteDate;
	}
	
	/**
	 * @param targetCompleteDate the targetCompleteDate to set
	 */
	public void setTargetCompleteDate(Date targetCompleteDate) {
		this.targetCompleteDate = targetCompleteDate;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the profileStatus
	 */
	public String getProfileStatus() {
		return profileStatus;
	}

	/**
	 * @param profileStatus the profileStatus to set
	 */
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the legalText
	 */
	public String getLegalText() {
		return legalText;
	}
	
	/**
	 * @param legalText the legalText to set
	 */
	public void setLegalText(String legalText) {
		this.legalText = legalText;
	}
	
	/**
	 * @return the headerId
	 */
	public Integer getHeaderId() {
		return headerId;
	}

	/**
	 * @param headerId the headerId to set
	 */
	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	/**
	 * @return the dealer
	 */
	public Integer getDealer() {
		return dealer;
	}
	
	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(Integer dealer) {
		this.dealer = dealer;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dealer;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((profileStatus == null) ? 0 : profileStatus.hashCode());
		result = prime
				* result
				+ ((targetCompleteDate == null) ? 0 : targetCompleteDate
						.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PSIProfile other = (PSIProfile) obj;
		if (dealer != other.dealer)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profileStatus == null) {
			if (other.profileStatus != null)
				return false;
		} else if (!profileStatus.equals(other.profileStatus))
			return false;
		if (targetCompleteDate == null) {
			if (other.targetCompleteDate != null)
				return false;
		} else if (!targetCompleteDate.equals(other.targetCompleteDate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSIProfile [id=" + id + ", targetCompleteDate="
				+ targetCompleteDate + ", name=" + name + ", profileStatus="
				+ profileStatus + ", type=" + type + ", dealer=" + dealer + "]";
	}

}
