/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "<TBL_SEG>")
public class SegmentDto implements Serializable {

	private static final long serialVersionUID = -4350198463764538656L;
	
//	@Id
//	@Column(name = "<COL_NAME1>")
	private int segmentId;
	
//	@Column(name = "<COL_NAME2>")
	private String name;
	
//	@Column(name = "<COL_NAME3>")
	private int actualQty;
	
//	@Column(name = "<COL_NAME4>")
	private int recommendedQty;
	
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
