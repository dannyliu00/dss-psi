/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "OT071F_Profile")
public class Profile {

//	@Id
//	@Column(name = "N1IPID")
	private int id;
	
//	@Column(name = "N1TDAT")
	private Date targetCompleteDate;
	
//	@Column(name = "N1DESC")
	private String name;
	
//	@JoinColumn(name = "N1STID")
	private ProfileStatus status;
	
//	@Column(name = "N1LGLT")
	private String legalText;

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
	 * @return the targetCompleteDate
	 */
	public Date getTargetCompleteDate() {
		return targetCompleteDate;
	}

	/**
	 * @param targetCompleteDate the targetCompleteDate to set
	 */
	public void setTargetCompleteDate(Date targetCompleteDate) {
		this.targetCompleteDate = targetCompleteDate;
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
	 * @return the status
	 */
	public ProfileStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ProfileStatus status) {
		this.status = status;
	}

	/**
	 * @return the legalText
	 */
	public String getLegalText() {
		return legalText;
	}

	/**
	 * @param legalText the legalText to set
	 */
	public void setLegalText(String legalText) {
		this.legalText = legalText;
	}
	
	
}
