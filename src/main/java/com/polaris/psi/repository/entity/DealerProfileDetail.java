/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "OT078F")
public class DealerProfileDetail implements Serializable {

	private static final long serialVersionUID = 8268329969553727531L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N8DDID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "N8DHID")
	private DealerProfileHeader header;
	
	@Column(name = "N8PSID")
	private int profileOrderSegmentId;
	
	@Column(name = "N8CODE")
	private String periodCode;
	
	@Column(name = "N8DQTY", nullable=true)
	private Integer actual;
	
	@Column(name = "N8DCOD", nullable=true)
	private Integer dealerReasonCode;
	
	@Column(name = "N8DCOM", nullable=true)
	private String dealerComments;
	
	@Column(name = "N8SQTY", nullable=true)
	private Integer dsmRecommendedQty;
	
	@Column(name = "N8SCOD", nullable=true)
	private Integer dsmReasonCode;
	
	@Column(name = "N8SCOM", nullable=true)
	private String dsmComments;
	
	@Column(name = "N8AQTY", nullable=true)
	private Integer adminApprovedQty;
	
	@Column(name = "N8ACOD", nullable=true)
	private Integer adminReasonCode;
	
	@Column(name = "N8ACOM", nullable=true)
	private String adminComments;
	
	@Column(name = "N8QTY", nullable=true)
	private Integer finalQty;

	@Column(name = "N8CRDT")
    @Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "N8CRTM")
    @Temporal(TemporalType.TIME)
	private Date createdTime;
	
	@Column(name = "N8CRUS")
	private String createdUser;
	
	@Column(name = "N8CRPG")
	private String createdProgram;
	
	@Column(name = "N8CHDT")
    @Temporal(TemporalType.DATE)
	private Date changedDate;
	
	@Column(name = "N8CHTM")
    @Temporal(TemporalType.TIME)
	private Date changedTime;
	
	@Column(name = "N8CHUS")
	private String changedUser;
	
	@Column(name = "N8CHPG")
	private String changedProgram;

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
	 * @return the header
	 */
	public DealerProfileHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(DealerProfileHeader header) {
		this.header = header;
	}

	/**
	 * @return the profileOrderSegmentId
	 */
	public int getProfileOrderSegmentId() {
		return profileOrderSegmentId;
	}

	/**
	 * @param profileOrderSegmentId the profileOrderSegmentId to set
	 */
	public void setProfileOrderSegmentId(int profileOrderSegmentId) {
		this.profileOrderSegmentId = profileOrderSegmentId;
	}

	/**
	 * @return the periodCode
	 */
	public String getPeriodCode() {
		return periodCode;
	}

	/**
	 * @param periodCode the periodCode to set
	 */
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}

	/**
	 * @return the actual
	 */
	public Integer getActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	public void setActual(Integer actual) {
		this.actual = actual;
	}

	/**
	 * @return the dealerReasonCode
	 */
	public Integer getDealerReasonCode() {
		return dealerReasonCode;
	}

	/**
	 * @param dealerReasonCode the dealerReasonCode to set
	 */
	public void setDealerReasonCode(Integer dealerReasonCode) {
		this.dealerReasonCode = dealerReasonCode;
	}

	/**
	 * @return the dealerComments
	 */
	public String getDealerComments() {
		return dealerComments;
	}

	/**
	 * @param dealerComments the dealerComments to set
	 */
	public void setDealerComments(String dealerComments) {
		this.dealerComments = dealerComments;
	}

	/**
	 * @return the dsmRecommendedQty
	 */
	public Integer getDsmRecommendedQty() {
		return dsmRecommendedQty;
	}

	/**
	 * @param dsmRecommendedQty the dsmRecommendedQty to set
	 */
	public void setDsmRecommendedQty(Integer dsmRecommendedQty) {
		this.dsmRecommendedQty = dsmRecommendedQty;
	}

	/**
	 * @return the dsmReasonCode
	 */
	public Integer getDsmReasonCode() {
		return dsmReasonCode;
	}

	/**
	 * @param dsmReasonCode the dsmReasonCode to set
	 */
	public void setDsmReasonCode(Integer dsmReasonCode) {
		this.dsmReasonCode = dsmReasonCode;
	}

	/**
	 * @return the dsmComments
	 */
	public String getDsmComments() {
		return dsmComments;
	}

	/**
	 * @param dsmComments the dsmComments to set
	 */
	public void setDsmComments(String dsmComments) {
		this.dsmComments = dsmComments;
	}

	/**
	 * @return the adminApprovedQty
	 */
	public Integer getAdminApprovedQty() {
		return adminApprovedQty;
	}

	/**
	 * @param adminApprovedQty the adminApprovedQty to set
	 */
	public void setAdminApprovedQty(Integer adminApprovedQty) {
		this.adminApprovedQty = adminApprovedQty;
	}

	/**
	 * @return the adminReasonCode
	 */
	public Integer getAdminReasonCode() {
		return adminReasonCode;
	}

	/**
	 * @param adminReasonCode the adminReasonCode to set
	 */
	public void setAdminReasonCode(Integer adminReasonCode) {
		this.adminReasonCode = adminReasonCode;
	}

	/**
	 * @return the adminComments
	 */
	public String getAdminComments() {
		return adminComments;
	}

	/**
	 * @param adminComments the adminComments to set
	 */
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	/**
	 * @return the finalQty
	 */
	public Integer getFinalQty() {
		return finalQty;
	}

	/**
	 * @param finalQty the finalQty to set
	 */
	public void setFinalQty(Integer finalQty) {
		this.finalQty = finalQty;
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
	 * @return the changedUser
	 */
	public String getChangedUser() {
		return changedUser;
	}

	/**
	 * @param changedUser the changedUser to set
	 */
	public void setChangedUser(String changedUser) {
		this.changedUser = changedUser;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actual == null) ? 0 : actual.hashCode());
		result = prime * result
				+ ((changedDate == null) ? 0 : changedDate.hashCode());
		result = prime * result
				+ ((changedProgram == null) ? 0 : changedProgram.hashCode());
		result = prime * result
				+ ((changedTime == null) ? 0 : changedTime.hashCode());
		result = prime * result
				+ ((changedUser == null) ? 0 : changedUser.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdProgram == null) ? 0 : createdProgram.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result
				+ ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result
				+ ((finalQty == null) ? 0 : finalQty.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((periodCode == null) ? 0 : periodCode.hashCode());
		result = prime * result + profileOrderSegmentId;
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
		DealerProfileDetail other = (DealerProfileDetail) obj;
		if (actual == null) {
			if (other.actual != null)
				return false;
		} else if (!actual.equals(other.actual))
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
		if (changedUser == null) {
			if (other.changedUser != null)
				return false;
		} else if (!changedUser.equals(other.changedUser))
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
		if (finalQty == null) {
			if (other.finalQty != null)
				return false;
		} else if (!finalQty.equals(other.finalQty))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (id != other.id)
			return false;
		if (periodCode == null) {
			if (other.periodCode != null)
				return false;
		} else if (!periodCode.equals(other.periodCode))
			return false;
		if (profileOrderSegmentId != other.profileOrderSegmentId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealerProfileDetail [id=" + id + ", header=" + header
				+ ", profileOrderSegmentId=" + profileOrderSegmentId
				+ ", periodCode=" + periodCode + ", actual=" + actual
				+ ", finalQty=" + finalQty + ", createdDate=" + createdDate
				+ ", createdTime=" + createdTime + ", createdUser="
				+ createdUser + ", createdProgram=" + createdProgram
				+ ", changedDate=" + changedDate + ", changedTime="
				+ changedTime + ", changedUser=" + changedUser
				+ ", changedProgram=" + changedProgram + "]";
	}

}
