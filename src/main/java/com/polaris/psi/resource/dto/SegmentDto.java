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
public class SegmentDto implements IBaseDto, Serializable {

	private static final long serialVersionUID = -4350198463764538656L;
	
	private int segmentId;
	private String name;
	private int actualQty;
	private int recommended;
	private int recMinimum;
	private int recMaximum;
	private int actual;
	private int recommendedOSCount;
	
	private List<IBaseDto> orderSegments;

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
	 * @return the recommended
	 */
	@Override
	public int getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	@Override
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the recMinimum
	 */
	@Override
	public int getRecMinimum() {
		return recMinimum;
	}

	/**
	 * @param recMinimum the recMinimum to set
	 */
	@Override
	public void setRecMinimum(int recMinimum) {
		this.recMinimum = recMinimum;
	}

	/**
	 * @return the recMaximum
	 */
	@Override
	public int getRecMaximum() {
		return recMaximum;
	}

	/**
	 * @param recMaximum the recMaximum to set
	 */
	@Override
	public void setRecMaximum(int recMaximum) {
		this.recMaximum = recMaximum;
	}

	/**
	 * @return the actual
	 */
	@Override
	public int getActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	@Override
	public void setActual(int actual) {
		this.actual = actual;
	}

	/**
	 * @return the recommendedOSCount
	 */
	public int getRecommendedOSCount() {
		return recommendedOSCount;
	}

	/**
	 * @param recommendedOSCount the recommendedOSCount to set
	 */
	public void setRecommendedOSCount(int recommendedOSCount) {
		this.recommendedOSCount = recommendedOSCount;
	}

	/**
	 * @return the orderSegments
	 */
	public List<IBaseDto> getOrderSegments() {
		return orderSegments;
	}

	/**
	 * @param orderSegments the orderSegments to set
	 */
	public void setOrderSegments(List<IBaseDto> orderSegments) {
		this.orderSegments = orderSegments;
	}

}
