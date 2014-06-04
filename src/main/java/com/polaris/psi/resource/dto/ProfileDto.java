/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author bericks
 *
 */
public class ProfileDto implements IBaseDto, Serializable {

	private static final long serialVersionUID = -159439629357551617L;

	private int profileId;
	private String type;
	private String name;
	private Date modifiedDate;
	private String status;
	private int recMinimum;
	private int recommended;
	private int recMaximum;
	private int actual;

	/**
	 * @return the actual
	 */
	@Override
	public int getActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	@Override
	public void setActual(int actual) {
		this.actual = actual;
	}

	private List<IBaseDto> periods;
	private List<IBaseDto> segments;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	public List<IBaseDto> getSegments() {
		return segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(List<IBaseDto> segments) {
		this.segments = segments;
	}

	/**
	 * @return the periods
	 */
	public List<IBaseDto> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(List<IBaseDto> periods) {
		this.periods = periods;
	}

	/**
	 * @return the recMinimum
	 */
	@Override
	public int getRecMinimum() {
		return recMinimum;
	}

	/**
	 * @param recMinimum the recMinimum to set
	 */
	@Override
	public void setRecMinimum(int recMinimum) {
		this.recMinimum = recMinimum;
	}

	/**
	 * @return the recommended
	 */
	@Override
	public int getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	@Override
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the recMaximum
	 */
	@Override
	public int getRecMaximum() {
		return recMaximum;
	}

	/**
	 * @param recMaximum the recMaximum to set
	 */
	@Override
	public void setRecMaximum(int recMaximum) {
		this.recMaximum = recMaximum;
	}

}
