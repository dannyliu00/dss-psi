/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bericks
 *
 */
public class DsmDealerProfilesDto implements Serializable {

	private static final long serialVersionUID = -284675998986409993L;

	private Integer dsmId;
	private String dsmName;
	private Integer dealerid;
	private String dealerName;
	private Integer profileId;
	private Date modifiedDate;
	private Integer rsmId;
	private String rsmName;
	private String status;
	private String type;
	private boolean nonCompliant;

	/**
	 * @return the dsmId
	 */
	public Integer getDsmId() {
		return dsmId;
	}

	/**
	 * @param dsmId the dsmId to set
	 */
	public void setDsmId(Integer dsmId) {
		this.dsmId = dsmId;
	}

	/**
	 * @return the dsmName
	 */
	public String getDsmName() {
		return dsmName;
	}

	/**
	 * @param dsmName the dsmName to set
	 */
	public void setDsmName(String dsmName) {
		this.dsmName = dsmName;
	}

	/**
	 * @return the dealerid
	 */
	public Integer getDealerid() {
		return dealerid;
	}

	/**
	 * @param dealerid the dealerid to set
	 */
	public void setDealerid(Integer dealerid) {
		this.dealerid = dealerid;
	}

	/**
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * @param dealerName the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the rsmId
	 */
	public Integer getRsmId() {
		return rsmId;
	}

	/**
	 * @param rsmId the rsmId to set
	 */
	public void setRsmId(Integer rsmId) {
		this.rsmId = rsmId;
	}

	/**
	 * @return the rsmName
	 */
	public String getRsmName() {
		return rsmName;
	}

	/**
	 * @param rsmName the rsmName to set
	 */
	public void setRsmName(String rsmName) {
		this.rsmName = rsmName;
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
	 * @return the nonCompliant
	 */
	public boolean isNonCompliant() {
		return nonCompliant;
	}

	/**
	 * @param nonCompliant the nonCompliant to set
	 */
	public void setNonCompliant(boolean nonCompliant) {
		this.nonCompliant = nonCompliant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dealerName == null) ? 0 : dealerName.hashCode());
		result = prime * result
				+ ((dealerid == null) ? 0 : dealerid.hashCode());
		result = prime * result + ((dsmId == null) ? 0 : dsmId.hashCode());
		result = prime * result + ((dsmName == null) ? 0 : dsmName.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + ((rsmId == null) ? 0 : rsmId.hashCode());
		result = prime * result + ((rsmName == null) ? 0 : rsmName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		DsmDealerProfilesDto other = (DsmDealerProfilesDto) obj;
		if (dealerName == null) {
			if (other.dealerName != null)
				return false;
		} else if (!dealerName.equals(other.dealerName))
			return false;
		if (dealerid == null) {
			if (other.dealerid != null)
				return false;
		} else if (!dealerid.equals(other.dealerid))
			return false;
		if (dsmId == null) {
			if (other.dsmId != null)
				return false;
		} else if (!dsmId.equals(other.dsmId))
			return false;
		if (dsmName == null) {
			if (other.dsmName != null)
				return false;
		} else if (!dsmName.equals(other.dsmName))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (rsmId == null) {
			if (other.rsmId != null)
				return false;
		} else if (!rsmId.equals(other.rsmId))
			return false;
		if (rsmName == null) {
			if (other.rsmName != null)
				return false;
		} else if (!rsmName.equals(other.rsmName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "DsmDealerProfilesDto [dsmId=" + dsmId + ", dsmName=" + dsmName
				+ ", dealerid=" + dealerid + ", dealerName=" + dealerName
				+ ", profileId=" + profileId + ", modifiedDate=" + modifiedDate
				+ ", rsmId=" + rsmId + ", rsmName=" + rsmName + ", status="
				+ status + ", type=" + type + "]";
	}

}
