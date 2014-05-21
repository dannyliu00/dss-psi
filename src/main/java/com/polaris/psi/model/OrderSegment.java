/**
 * 
 */
package com.polaris.psi.model;

import java.io.Serializable;

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
public class OrderSegment implements Serializable {

	private static final long serialVersionUID = -4692650680058882881L;
	
	@Id
	@Column(name = "<COL_NAME>")
	private int orderSegmentId;
	
	@Column(name = "<COL_NAME>")
	private String name;
	
	@Column(name = "<COL_NAME>")
	private int actualQty;
	
	@Column(name = "<COL_NAME>")
	private int recommendedQty;

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

}
