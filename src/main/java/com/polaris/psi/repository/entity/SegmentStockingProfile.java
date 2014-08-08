/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bericks
 *
 */
public class SegmentStockingProfile implements Serializable {

	private static final long serialVersionUID = -4635527588835851256L;
	
	private int dealerId;
	private String productLine;
	private String profileCode;
	private String segmentCode;
	private String orderSegmentCode;
	private int recommendedQty;
	private String periodCode;
	private Date periodStartDate;
	private Date periodEndDate;
	private String stockingProfileProfileCode;
	private String stockingProfileSegmentCode;
	private int stockingProfilePeriodId;

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
	 * @return the productLine
	 */
	public String getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine the productLine to set
	 */
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	/**
	 * @return the profileCode
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 * @param profileCode the profileCode to set
	 */
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * @return the segmentCode
	 */
	public String getSegmentCode() {
		return segmentCode;
	}

	/**
	 * @param segmentCode the segmentCode to set
	 */
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}

	/**
	 * @return the orderSegmentCode
	 */
	public String getOrderSegmentCode() {
		return orderSegmentCode;
	}

	/**
	 * @param orderSegmentCode the orderSegmentCode to set
	 */
	public void setOrderSegmentCode(String orderSegmentCode) {
		this.orderSegmentCode = orderSegmentCode;
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
	 * @return the periodCode
	 */
	public String getPeriodCode() {
		return periodCode;
	}

	/**
	 * @param periodCode the periodCode to set
	 */
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}

	/**
	 * @return the periodStartDate
	 */
	public Date getPeriodStartDate() {
		return periodStartDate;
	}

	/**
	 * @param periodStartDate the periodStartDate to set
	 */
	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	/**
	 * @return the periodEndDate
	 */
	public Date getPeriodEndDate() {
		return periodEndDate;
	}

	/**
	 * @param periodEndDate the periodEndDate to set
	 */
	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	/**
	 * @return the stockingProfileProfileCode
	 */
	public String getStockingProfileProfileCode() {
		return stockingProfileProfileCode;
	}

	/**
	 * @param stockingProfileProfileCode the stockingProfileProfileCode to set
	 */
	public void setStockingProfileProfileCode(String stockingProfileProfileCode) {
		this.stockingProfileProfileCode = stockingProfileProfileCode;
	}

	/**
	 * @return the stockingProfileSegmentCode
	 */
	public String getStockingProfileSegmentCode() {
		return stockingProfileSegmentCode;
	}

	/**
	 * @param stockingProfileSegmentCode the stockingProfileSegmentCode to set
	 */
	public void setStockingProfileSegmentCode(String stockingProfileSegmentCode) {
		this.stockingProfileSegmentCode = stockingProfileSegmentCode;
	}

	/**
	 * @return the stockingProfilePeriodId
	 */
	public int getStockingProfilePeriodId() {
		return stockingProfilePeriodId;
	}

	/**
	 * @param stockingProfilePeriodId the stockingProfilePeriodId to set
	 */
	public void setStockingProfilePeriodId(int stockingProfilePeriodId) {
		this.stockingProfilePeriodId = stockingProfilePeriodId;
	}

}
