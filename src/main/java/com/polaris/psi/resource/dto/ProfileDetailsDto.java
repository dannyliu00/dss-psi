/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author bericks
 *
 */
public class ProfileDetailsDto implements Serializable {

	private static final long serialVersionUID = 7299486776175540163L;

	private boolean nonCompliant;
	private boolean isSuccessful;
	private String message;
	private List<OrderSegmentDto> orderSegments;

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

	/**
	 * @return the isSuccessful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}

	/**
	 * @param isSuccessful the isSuccessful to set
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the orderSegments
	 */
	public List<OrderSegmentDto> getOrderSegments() {
		return orderSegments;
	}

	/**
	 * @param orderSegments the orderSegments to set
	 */
	public void setOrderSegments(List<OrderSegmentDto> orderSegments) {
		this.orderSegments = orderSegments;
	}
	
}
