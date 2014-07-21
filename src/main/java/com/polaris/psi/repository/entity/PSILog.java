/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "OT083F")
public class PSILog implements Serializable {

	private static final long serialVersionUID = 2982531330926708965L;

	@Id
	@Column(name = "P3LID")
	private Integer id;
	
	@Column(name = "P3LROW")
	private Integer rowNumber;
	
	@Column(name = "P3LTM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name = "P3DHID")
	private Integer headerId;
	
	@Column(name = "P3DDID")
	private Integer detailId;
	
	@Column(name = "P3OSEG")
	private String orderSegment;
	
	@Column(name = "P3DQTY")
	private Integer quantity;
	
	@Column(name = "P3DCOD")
	private Integer dealerReasonCode;
	
	@Column(name = "P3DCOM")
	private String dealerComments;
	
	@Column(name = "P3DUS")
	private String dealerLoggedInId;
	
	@Column(name = "P3DDT")
	@Temporal(TemporalType.DATE)
	private Date detailChangeDate;
	
	@Column(name = "P3DTM")
	@Temporal(TemporalType.TIME)
	private Date detailChangeTime;
	
	@Column(name = "P3SQTY")
	private Integer dsmQty;
	
	@Column(name = "P3SCOD")
	private Integer dsmReasonCode;
	
	@Column(name = "P3SCOM")
	private String dsmComments;
	
	@Column(name = "P3SUS")
	private String dsmLoggedInId;
	
	@Column(name = "P3SDT")
	@Temporal(TemporalType.DATE)
	private Date dsmChangeDate;
	
	@Column(name = "P3STM")
	@Temporal(TemporalType.TIME)
	private Date dsmChangeTime;
	
	@Column(name = "P3AQTY")
	private Integer adminQty;
	
	@Column(name = "P3ACOD")
	private Integer adminReasonCode;
	
	@Column(name = "P3ACOM")
	private String adminComments;
	
	@Column(name = "P3QTY")
	private Integer finalQty;
	
	@Column(name = "P3AUS")
	private String adminLoggedInId;
	
	@Column(name = "P3ADT")
	@Temporal(TemporalType.DATE)
	private Date adminChangedDate;
	
	@Column(name = "P3ATM")
	@Temporal(TemporalType.TIME)
	private Date adminChangeTime;
	
	@Column(name = "P3SBDT")
	@Temporal(TemporalType.DATE)
	private Date submittedDate;
	
	@Column(name = "P3SBTM")
	@Temporal(TemporalType.TIME)
	private Date submittedTime;
	
	@Column(name = "P3APDT")
	@Temporal(TemporalType.DATE)
	private Date approvedDate;
	
	@Column(name = "P3APTM")
	@Temporal(TemporalType.TIME)
	private Date approvedTime;	

	@Column(name = "P3CRDT")
    @Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "P3CRTM")
    @Temporal(TemporalType.TIME)
	private Date createdTime;
	
	@Column(name = "P3CRUS")
	private String createdUser;
	
	@Column(name = "P3CRPG")
	private String createdProgram;
	
	@Column(name = "P3CHDT")
    @Temporal(TemporalType.DATE)
	private Date changedDate;
	
	@Column(name = "P3CHTM")
    @Temporal(TemporalType.TIME)
	private Date changedTime;
	
	@Column(name = "P3CHUS")
	private String changedUser;
	
	@Column(name = "P3CHPG")
	private String changedProgram;

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
	 * @return the rowNumber
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * @param rowNumber the rowNumber to set
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * @return the logTimestamp
	 */
	public Date getLogTimestamp() {
		return logTimestamp;
	}

	/**
	 * @param logTimestamp the logTimestamp to set
	 */
	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
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
	 * @return the detailId
	 */
	public Integer getDetailId() {
		return detailId;
	}

	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	/**
	 * @return the orderSegment
	 */
	public String getOrderSegment() {
		return orderSegment;
	}

	/**
	 * @param orderSegment the orderSegment to set
	 */
	public void setOrderSegment(String orderSegment) {
		this.orderSegment = orderSegment;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	 * @return the dealerLoggedInId
	 */
	public String getDealerLoggedInId() {
		return dealerLoggedInId;
	}

	/**
	 * @param dealerLoggedInId the dealerLoggedInId to set
	 */
	public void setDealerLoggedInId(String dealerLoggedInId) {
		this.dealerLoggedInId = dealerLoggedInId;
	}

	/**
	 * @return the detailChangeDate
	 */
	public Date getDetailChangeDate() {
		return detailChangeDate;
	}

	/**
	 * @param detailChangeDate the detailChangeDate to set
	 */
	public void setDetailChangeDate(Date detailChangeDate) {
		this.detailChangeDate = detailChangeDate;
	}

	/**
	 * @return the detailChangeTime
	 */
	public Date getDetailChangeTime() {
		return detailChangeTime;
	}

	/**
	 * @param detailChangeTime the detailChangeTime to set
	 */
	public void setDetailChangeTime(Date detailChangeTime) {
		this.detailChangeTime = detailChangeTime;
	}

	/**
	 * @return the dsmQty
	 */
	public Integer getDsmQty() {
		return dsmQty;
	}

	/**
	 * @param dsmQty the dsmQty to set
	 */
	public void setDsmQty(Integer dsmQty) {
		this.dsmQty = dsmQty;
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
	 * @return the dsmLoggedInId
	 */
	public String getDsmLoggedInId() {
		return dsmLoggedInId;
	}

	/**
	 * @param dsmLoggedInId the dsmLoggedInId to set
	 */
	public void setDsmLoggedInId(String dsmLoggedInId) {
		this.dsmLoggedInId = dsmLoggedInId;
	}

	/**
	 * @return the dsmChangeDate
	 */
	public Date getDsmChangeDate() {
		return dsmChangeDate;
	}

	/**
	 * @param dsmChangeDate the dsmChangeDate to set
	 */
	public void setDsmChangeDate(Date dsmChangeDate) {
		this.dsmChangeDate = dsmChangeDate;
	}

	/**
	 * @return the dsmChangeTime
	 */
	public Date getDsmChangeTime() {
		return dsmChangeTime;
	}

	/**
	 * @param dsmChangeTime the dsmChangeTime to set
	 */
	public void setDsmChangeTime(Date dsmChangeTime) {
		this.dsmChangeTime = dsmChangeTime;
	}

	/**
	 * @return the adminQty
	 */
	public Integer getAdminQty() {
		return adminQty;
	}

	/**
	 * @param adminQty the adminQty to set
	 */
	public void setAdminQty(Integer adminQty) {
		this.adminQty = adminQty;
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
	 * @return the adminLoggedInId
	 */
	public String getAdminLoggedInId() {
		return adminLoggedInId;
	}

	/**
	 * @param adminLoggedInId the adminLoggedInId to set
	 */
	public void setAdminLoggedInId(String adminLoggedInId) {
		this.adminLoggedInId = adminLoggedInId;
	}

	/**
	 * @return the adminChangedDate
	 */
	public Date getAdminChangedDate() {
		return adminChangedDate;
	}

	/**
	 * @param adminChangedDate the adminChangedDate to set
	 */
	public void setAdminChangedDate(Date adminChangedDate) {
		this.adminChangedDate = adminChangedDate;
	}

	/**
	 * @return the adminChangeTime
	 */
	public Date getAdminChangeTime() {
		return adminChangeTime;
	}

	/**
	 * @param adminChangeTime the adminChangeTime to set
	 */
	public void setAdminChangeTime(Date adminChangeTime) {
		this.adminChangeTime = adminChangeTime;
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
				+ ((detailId == null) ? 0 : detailId.hashCode());
		result = prime * result
				+ ((headerId == null) ? 0 : headerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((logTimestamp == null) ? 0 : logTimestamp.hashCode());
		result = prime * result
				+ ((orderSegment == null) ? 0 : orderSegment.hashCode());
		result = prime * result
				+ ((rowNumber == null) ? 0 : rowNumber.hashCode());
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
		PSILog other = (PSILog) obj;
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
		if (detailId == null) {
			if (other.detailId != null)
				return false;
		} else if (!detailId.equals(other.detailId))
			return false;
		if (headerId == null) {
			if (other.headerId != null)
				return false;
		} else if (!headerId.equals(other.headerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logTimestamp == null) {
			if (other.logTimestamp != null)
				return false;
		} else if (!logTimestamp.equals(other.logTimestamp))
			return false;
		if (orderSegment == null) {
			if (other.orderSegment != null)
				return false;
		} else if (!orderSegment.equals(other.orderSegment))
			return false;
		if (rowNumber == null) {
			if (other.rowNumber != null)
				return false;
		} else if (!rowNumber.equals(other.rowNumber))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSILog [id=" + id + ", rowNumber=" + rowNumber
				+ ", logTimestamp=" + logTimestamp + ", headerId=" + headerId
				+ ", detailId=" + detailId + ", orderSegment=" + orderSegment
				+ ", createdDate=" + createdDate + ", createdTime="
				+ createdTime + ", createdUser=" + createdUser
				+ ", createdProgram=" + createdProgram + ", changedDate="
				+ changedDate + ", changedTime=" + changedTime
				+ ", changedUser=" + changedUser + ", changedProgram="
				+ changedProgram + "]";
	}

}
