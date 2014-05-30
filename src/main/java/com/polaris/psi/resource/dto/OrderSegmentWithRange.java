/**
 * 
 */
package com.polaris.psi.resource.dto;

/**
 * @author bericks
 *
 */
public class OrderSegmentWithRange extends OrderSegmentDto {

	private static final long serialVersionUID = -7875018740992473600L;
	
	private int minRecommend;
	private int maxRecommend;

	/**
	 * @return the minRecommend
	 */
	public int getMinRecommend() {
		return minRecommend;
	}

	/**
	 * @param minRecommend the minRecommend to set
	 */
	public void setMinRecommend(int minRecommend) {
		this.minRecommend = minRecommend;
	}

	/**
	 * @return the maxRecommend
	 */
	public int getMaxRecommend() {
		return maxRecommend;
	}

	/**
	 * @param maxRecommend the maxRecommend to set
	 */
	public void setMaxRecommend(int maxRecommend) {
		this.maxRecommend = maxRecommend;
	}
	
}
