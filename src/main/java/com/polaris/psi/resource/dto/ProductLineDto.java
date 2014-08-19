/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;

/**
 * @author pceder
 *
 */
public class ProductLineDto implements Serializable {

	private static final long serialVersionUID = 8391382100485071545L;

	private String productLineId;
	private String shortDescription;
	private String description;
	
	
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
