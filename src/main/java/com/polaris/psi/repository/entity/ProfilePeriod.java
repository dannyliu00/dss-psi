/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT070F_ProfilePeriod")
public class ProfilePeriod {
	
	@Id
	@Column(name = "N0PPID")
	private int id;
	
	@Column(name = "N0CODE")
	private String periodCode;
	
	@Column(name = "N0DESC")
	private String name;
	
	@Column(name = "N0SDAT")
	private String startDate;
	
	@Column(name = "N0EDAT")
	private String endDate;
	
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
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
