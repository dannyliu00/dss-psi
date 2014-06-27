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

	private String message;
	private List<OrderSegmentDto> orderSegments;

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
