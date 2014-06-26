/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
public class PSIOrderSegment implements Serializable {

	private static final long serialVersionUID = -2134013853617686230L;
	
	private Integer id;
	private Integer profileId;
	private String name;
	private Integer sort;
	private String subSegment;
	private Integer complianceId;
	private String periodCode;
	private Integer dealerId;
	private Integer recMinimum;
	private Integer recommended;
	private Integer recMaximum;
	
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
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
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
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the subSegment
	 */
	public String getSubSegment() {
		return subSegment;
	}

	/**
	 * @param subSegment the subSegment to set
	 */
	public void setSubSegment(String subSegment) {
		this.subSegment = subSegment;
	}

	/**
	 * @return the complianceId
	 */
	public Integer getComplianceId() {
		return complianceId;
	}

	/**
	 * @param complianceId the complianceId to set
	 */
	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
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
	 * @return the dealerId
	 */
	public Integer getDealerId() {
		return dealerId;
	}

	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	/**
	 * @return the recMinimum
	 */
	public Integer getRecMinimum() {
		return recMinimum;
	}

	/**
	 * @param recMinimum the recMinimum to set
	 */
	public void setRecMinimum(Integer recMinimum) {
		this.recMinimum = recMinimum;
	}

	/**
	 * @return the recommended
	 */
	public Integer getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the recMaximum
	 */
	public Integer getRecMaximum() {
		return recMaximum;
	}

	/**
	 * @param recMaximum the recMaximum to set
	 */
	public void setRecMaximum(Integer recMaximum) {
		this.recMaximum = recMaximum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((complianceId == null) ? 0 : complianceId.hashCode());
		result = prime * result
				+ ((dealerId == null) ? 0 : dealerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((periodCode == null) ? 0 : periodCode.hashCode());
		result = prime * result
				+ ((recommended == null) ? 0 : recommended.hashCode());
		result = prime * result
				+ ((subSegment == null) ? 0 : subSegment.hashCode());
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
		PSIOrderSegment other = (PSIOrderSegment) obj;
		if (complianceId == null) {
			if (other.complianceId != null)
				return false;
		} else if (!complianceId.equals(other.complianceId))
			return false;
		if (dealerId == null) {
			if (other.dealerId != null)
				return false;
		} else if (!dealerId.equals(other.dealerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (periodCode == null) {
			if (other.periodCode != null)
				return false;
		} else if (!periodCode.equals(other.periodCode))
			return false;
		if (recommended == null) {
			if (other.recommended != null)
				return false;
		} else if (!recommended.equals(other.recommended))
			return false;
		if (subSegment == null) {
			if (other.subSegment != null)
				return false;
		} else if (!subSegment.equals(other.subSegment))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSIOrderSegmentDao [id=" + id + ", profileId=" + profileId + ", name=" + name + ", subSegment="
				+ subSegment + ", complianceId=" + complianceId
				+ ", periodCode=" + periodCode + ", dealerId=" + dealerId
				+ ", recommended=" + recommended + "]";
	}

}
