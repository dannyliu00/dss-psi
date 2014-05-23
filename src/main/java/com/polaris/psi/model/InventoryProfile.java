/**
 * 
 */
package com.polaris.psi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "<TBL_INV_PROF>")
public class InventoryProfile implements Serializable {

	private static final long serialVersionUID = -159439629357551617L;

//	@Id
//	@Column(name = "<COL_NAME1>")
	private int profileId;
	
//	@Column(name = "<COL_NAME2>")
	private String name;
	
//	@Column(name = "<COL_NAME3>")
	private Date modifiedDate;
	
//	@Column(name = "<COL_NAME4>")
	private String status;
	
	private List<Segment> segments;

	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
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
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the segments
	 */
	public List<Segment> getSegments() {
		return segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

}
