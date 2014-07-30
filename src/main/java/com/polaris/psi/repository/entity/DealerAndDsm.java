package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DealerAndDsm
 *
 */
@Entity
@Table(name = "Enterprise.RsmDsmDealer")
public class DealerAndDsm implements Serializable {
	
	private static final long serialVersionUID = -6439087784808583526L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="DealerId")
	private Integer dealerId;
	
	@Column(name = "DealerProductLine")
	private String productLine;
	
	@Column(name = "DealerName")
	private String dealerName;
	
	@Column(name = "DsmId")
	private Integer dsmId;
	
	@Column(name="DsmName")
	private String dsmName;
	
	@Column(name = "RsmId")
	private Integer rsmId;
	
	@Column(name="RsmName")
	private String rsmName;
	
	@Column(name="DealerEmail")
	private String dealerEmail;

	@Column(name="DsmEmailAddress")
	private String dsmEmailAddress;

	@Column(name="RsmEmailAddress")
	private String rsmEmailAddress;

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
	 * @return the productLine
	 */
	public String getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine the productLine to set
	 */
	public void setProductLine(String productLine) {
		this.productLine = productLine;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dealerId == null) ? 0 : dealerId.hashCode());
		result = prime * result
				+ ((dealerName == null) ? 0 : dealerName.hashCode());
		result = prime * result + ((dsmId == null) ? 0 : dsmId.hashCode());
		result = prime * result + ((dsmName == null) ? 0 : dsmName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((productLine == null) ? 0 : productLine.hashCode());
		result = prime * result + ((rsmId == null) ? 0 : rsmId.hashCode());
		result = prime * result + ((rsmName == null) ? 0 : rsmName.hashCode());
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
		DealerAndDsm other = (DealerAndDsm) obj;
		if (dealerId == null) {
			if (other.dealerId != null)
				return false;
		} else if (!dealerId.equals(other.dealerId))
			return false;
		if (dealerName == null) {
			if (other.dealerName != null)
				return false;
		} else if (!dealerName.equals(other.dealerName))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productLine == null) {
			if (other.productLine != null)
				return false;
		} else if (!productLine.equals(other.productLine))
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
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealerAndDsm [id=" + id + ", dealerId=" + dealerId
				+ ", productLine=" + productLine + ", dealerName=" + dealerName
				+ ", dsmId=" + dsmId + ", dsmName=" + dsmName + ", rsmId="
				+ rsmId + ", rsmName=" + rsmName + "]";
	}

	/**
	 * @return the dealerEmail
	 */
	public String getDealerEmail() {
		return dealerEmail;
	}

	/**
	 * @param dealerEmail the dealerEmail to set
	 */
	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}

	/**
	 * @return the dsmEmailAddress
	 */
	public String getDsmEmailAddress() {
		return dsmEmailAddress;
	}

	/**
	 * @param dsmEmailAddress the dsmEmailAddress to set
	 */
	public void setDsmEmailAddress(String dsmEmailAddress) {
		this.dsmEmailAddress = dsmEmailAddress;
	}

	/**
	 * @return the rsmEmailAddress
	 */
	public String getRsmEmailAddress() {
		return rsmEmailAddress;
	}

	/**
	 * @param rsmEmailAddress the rsmEmailAddress to set
	 */
	public void setRsmEmailAddress(String rsmEmailAddress) {
		this.rsmEmailAddress = rsmEmailAddress;
	}

}
