/**
 * 
 */
package com.polaris.psi.model;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "<TBL_ORDR_SEG>")
public class OrderSegment implements Serializable {

	private static final long serialVersionUID = -4692650680058882881L;
	
//	@Id
//	@Column(name = "<COL_NAME1>")
	private int orderSegmentId;
	
//	@Column(name = "<COL_NAME2>")
	private String name;
	
//	@Column(name = "<COL_NAME3>")
	private int actualQty;
	
//	@Column(name = "<COL_NAME4>")
	private int recommendedQty;
	
	private Segment segment;

	public OrderSegment(int orderSegmentId, String name, int recommendedQty, int actualQty, Segment segment) {
		this.orderSegmentId = orderSegmentId;
	}
	
	/**
	 * @return the orderSegmentId
	 */
	public int getOrderSegmentId() {
		return orderSegmentId;
	}

	/**
	 * @param orderSegmentId the orderSegmentId to set
	 */
	public void setOrderSegmentId(int orderSegmentId) {
		this.orderSegmentId = orderSegmentId;
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
	 * @return the segment
	 */
	public Segment getSegment() {
		return segment;
	}

	/**
	 * @param segment the segment to set
	 */
	public void setSegment(Segment segment) {
		this.segment = segment;
	}

}
