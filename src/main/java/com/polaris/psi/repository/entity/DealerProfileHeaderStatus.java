/**
 * 
 */
package com.polaris.psi.repository.entity;

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
@Table(name = "Profile.OT079F_DealerProfileHeaderStatus")
public class DealerProfileHeaderStatus implements Serializable {

	private static final long serialVersionUID = 2110848633815105165L;
	
	@Id
	@Column(name = "N9STID")
	private int id;
	
	@Column(name = "N9DESC")
	private String description;
	
	@Column(name = "N9LOG")
	private boolean isLog;
	
	@Column(name = "N9FINAL")
	private boolean isFinalState;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isLog
	 */
	public boolean isLog() {
		return isLog;
	}

	/**
	 * @param isLog the isLog to set
	 */
	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}

	/**
	 * @return the isFinalState
	 */
	public boolean isFinalState() {
		return isFinalState;
	}

	/**
	 * @param isFinalState the isFinalState to set
	 */
	public void setFinalState(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	
}
