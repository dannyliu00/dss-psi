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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((isFinalState == null) ? 0 : isFinalState.hashCode());
		result = prime * result + ((isLog == null) ? 0 : isLog.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DealerProfileHeaderStatus other = (DealerProfileHeaderStatus) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (isFinalState == null) {
			if (other.isFinalState != null)
				return false;
		} else if (!isFinalState.equals(other.isFinalState))
			return false;
		if (isLog == null) {
			if (other.isLog != null)
				return false;
		} else if (!isLog.equals(other.isLog))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealerProfileHeaderStatus [id=" + id + ", description="
				+ description + ", isLog=" + isLog + ", isFinalState="
				+ isFinalState + "]";
	}

}
