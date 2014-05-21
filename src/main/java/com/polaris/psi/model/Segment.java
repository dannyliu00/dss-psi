/**
 * 
 */
package com.polaris.psi.model;

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
@Entity
@Table(name = "<TABLE_NAME>")
public class Segment implements Serializable {

	private static final long serialVersionUID = -4350198463764538656L;
	
	@Id
	@Column(name = "<COL_NAME>")
	private int segmentId;
	
	@Column(name = "<COL_NAME>")
	private String name;
	
	@Column(name = "<COL_NAME>")
	private int actualQty;
	
	@Column(name = "<COL_NAME>")
	private int recommendedQty;
	
	private List<OrderSegment> orderSegments;

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
	public List<OrderSegment> getOrderSegments() {
		return orderSegments;
	}

	/**
	 * @param orderSegments the orderSegments to set
	 */
	public void setOrderSegments(List<OrderSegment> orderSegments) {
		this.orderSegments = orderSegments;
	}

}
