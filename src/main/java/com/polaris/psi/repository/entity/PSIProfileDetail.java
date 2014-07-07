/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
public class PSIProfileDetail implements Serializable {

	private static final long serialVersionUID = -3527950266934264332L;
	
	private Integer id;
	private Integer headerId;
	private Integer profileOrderSegmentId;
	private String periodCode;
	private Integer requestedQty;
	private Integer reasonCode;
	private String dealerComments;
	private Integer dsmQty;
	private Integer dsmReasonCode;
	private String dsmComments;
	private Integer adminQty;
	private Integer adminReasonCode;
	private String adminComments;
	private Integer finalQty;

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
	 * @return the profileOrderSegmentId
	 */
	public Integer getProfileOrderSegmentId() {
		return profileOrderSegmentId;
	}

	/**
	 * @param profileOrderSegmentId the profileOrderSegmentId to set
	 */
	public void setProfileOrderSegmentId(Integer profileOrderSegmentId) {
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
	 * @return the requestedQty
	 */
	public Integer getRequestedQty() {
		return requestedQty;
	}

	/**
	 * @param requestedQty the requestedQty to set
	 */
	public void setRequestedQty(Integer requestedQty) {
		this.requestedQty = requestedQty;
	}

	/**
	 * @return the reasonCode
	 */
	public Integer getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((finalQty == null) ? 0 : finalQty.hashCode());
		result = prime * result
				+ ((headerId == null) ? 0 : headerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((periodCode == null) ? 0 : periodCode.hashCode());
		result = prime
				* result
				+ ((profileOrderSegmentId == null) ? 0 : profileOrderSegmentId
						.hashCode());
		result = prime * result
				+ ((requestedQty == null) ? 0 : requestedQty.hashCode());
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
		PSIProfileDetail other = (PSIProfileDetail) obj;
		if (finalQty == null) {
			if (other.finalQty != null)
				return false;
		} else if (!finalQty.equals(other.finalQty))
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
		if (periodCode == null) {
			if (other.periodCode != null)
				return false;
		} else if (!periodCode.equals(other.periodCode))
			return false;
		if (profileOrderSegmentId == null) {
			if (other.profileOrderSegmentId != null)
				return false;
		} else if (!profileOrderSegmentId.equals(other.profileOrderSegmentId))
			return false;
		if (requestedQty == null) {
			if (other.requestedQty != null)
				return false;
		} else if (!requestedQty.equals(other.requestedQty))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSIProfileDetail [id=" + id + ", headerId=" + headerId
				+ ", profileOrderSegmentId=" + profileOrderSegmentId
				+ ", periodCode=" + periodCode + ", requestedQty="
				+ requestedQty + ", finalQty=" + finalQty + "]";
	}

}
