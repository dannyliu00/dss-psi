/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.util.Date;
import java.util.List;

/**
 * @author bericks
 *
 */
public class ProfilePeriodDto {

	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	
	private List<SegmentQuantityDto> quantities;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the quantities
	 */
	public List<SegmentQuantityDto> getQuantities() {
		return quantities;
	}

	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(List<SegmentQuantityDto> quantities) {
		this.quantities = quantities;
	}
	
}
