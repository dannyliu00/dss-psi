/**
 * 
 */
package com.polaris.psi.resource.dto;

/**
 * @author bericks
 *
 */
public class SegmentQuantityDto implements IBaseDto {

	private int orderSegmentId;
	private int profilePeriodId;
	private int dealerId;
	
	private int recMinimum;
	private int recMaximum;
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

	@Override
	public int getRecMinimum() {
		return this.recMinimum;
	}

	@Override
	public void setRecMinimum(int min) {
		this.recMinimum = min;
	}

	@Override
	public int getRecMaximum() {
		return this.recMaximum;
	}

	@Override
	public void setRecMaximum(int max) {
		this.recMaximum = max;
	}

	@Override
	public int getRecommended() {
		return this.recommended;
	}

	@Override
	public void setRecommended(int rec) {
		this.recommended = rec;
	}

}
