/**
 * 
 */
package com.polaris.psi.resource.dto;

/**
 * @author bericks
 *
 */
public class SegmentQuantityDto {

	private int orderSegmentId;
	private int profilePeriodId;
	private int dealerId;
	
	private int minimum;
	private int maximum;
	private int recommended;
	private int actual;
	
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
	 * @return the profilePeriodId
	 */
	public int getProfilePeriodId() {
		return profilePeriodId;
	}
	
	/**
	 * @param profilePeriodId the profilePeriodId to set
	 */
	public void setProfilePeriodId(int profilePeriodId) {
		this.profilePeriodId = profilePeriodId;
	}
	
	/**
	 * @return the dealerId
	 */
	public int getDealerId() {
		return dealerId;
	}
	
	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	
	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}
	
	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}
	
	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	/**
	 * @return the recommended
	 */
	public int getRecommended() {
		return recommended;
	}
	
	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}
	
	/**
	 * @return the actual
	 */
	public int getActual() {
		return actual;
	}
	
	/**
	 * @param actual the actual to set
	 */
	public void setActual(int actual) {
		this.actual = actual;
	}

}
