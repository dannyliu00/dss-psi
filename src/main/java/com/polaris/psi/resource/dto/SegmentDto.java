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
public class SegmentDto implements Serializable {

	private static final long serialVersionUID = -4350198463764538656L;
	
	private int segmentId;
	private String name;
	private int actualQty;
	private int recommendedQty;
	private int minimumQty;
	
	private List<OrderSegmentDto> orderSegments;

	/**
	 * @return the segmentId
	 */
	public int getSegmentId() {
		return segmentId;
	}

	/**
	 * @param segmentId the segmentId to set
	 */
	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
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
	 * @return the actualQty
	 */
	public int getActualQty() {
		return actualQty;
	}

	/**
	 * @param actualQty the actualQty to set
	 */
	public void setActualQty(int actualQty) {
		this.actualQty = actualQty;
	}

	/**
	 * @return the recommendedQty
	 */
	public int getRecommendedQty() {
		return recommendedQty;
	}

	/**
	 * @param recommendedQty the recommendedQty to set
	 */
	public void setRecommendedQty(int recommendedQty) {
		this.recommendedQty = recommendedQty;
	}

	/**
	 * @return the minimumQty
	 */
	public int getMinimumQty() {
		return minimumQty;
	}

	/**
	 * @param minimumQty the minimumQty to set
	 */
	public void setMinimumQty(int minimumQty) {
		this.minimumQty = minimumQty;
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
