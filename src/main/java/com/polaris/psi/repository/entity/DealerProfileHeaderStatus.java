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
@Table(name = "OT079F")
public class DealerProfileHeaderStatus implements Serializable {

	private static final long serialVersionUID = 2110848633815105165L;
	
	@Id
	@Column(name = "N9STID")
	private int id;
	
	@Column(name = "N9DESC")
	private String description;
	
	@Column(name = "N9LOG")
	private Character isLog;
	
	@Column(name = "N9FINL")
	private Character isFinalState;

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
	public Character getIsLog() {
		return isLog;
	}

	/**
	 * @param isLog the isLog to set
	 */
	public void setIsLog(Character isLog) {
		this.isLog = isLog;
	}

	/**
	 * @return the isFinalState
	 */
	public Character getIsFinalState() {
		return isFinalState;
	}

	/**
	 * @param isFinalState the isFinalState to set
	 */
	public void setIsFinalState(Character isFinalState) {
		this.isFinalState = isFinalState;
	}

}
