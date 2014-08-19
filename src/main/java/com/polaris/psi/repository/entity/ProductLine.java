package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DealerAndDsm
 *
 */
@Entity
@Table(name = "Enterprise.ProductLineCode")
public class ProductLine implements Serializable {
	
	private static final long serialVersionUID = -6439087784808583526L;

	@Id
	@Column(name = "Code")
	private String productLineId;
	
	@Column(name="ShortDescription")
	private String shortDescription;
	
	@Column(name = "Description")
	private String description;


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductLine [Code=" + productLineId + ", ShortDescription=" + shortDescription
				+ ", Description=" + description + "]";
	}


	/**
	 * @return the productLineId
	 */
	public String getProductLineId() {
		return productLineId;
	}


	/**
	 * @param productLineId the productLineId to set
	 */
	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}


	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}


	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}
