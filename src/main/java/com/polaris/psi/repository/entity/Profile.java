/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT071F_Profile")
public class Profile implements Serializable {

	private static final long serialVersionUID = -3733982811045554835L;

	@Id
	@Column(name = "N1IPID")
	private int id;
	
	@Column(name = "N1TDAT")
	private String targetCompleteDate;
	
	@Column(name = "N1DESC")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "N1STID")
	private ProfileStatus status;
	
	@Column(name = "N1LGLT")
	private String legalText;
	
	@OneToMany
	private List<ProfileAndPeriod> periods;
	
	@OneToMany
	private List<OrderSegment> orderSegments;
	
	@OneToMany
	private List<ProfileOrderSegmentCompliance> osComplianceValues;
	
	@OneToMany(mappedBy = "profile")
	private List<ProfileSegmentCompliance> segmentComplianceValues;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<DealerProfileHeader> profileHeaders;

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
	public String getTargetCompleteDate() {
		return targetCompleteDate;
	}

	/**
	 * @param targetCompleteDate the targetCompleteDate to set
	 */
	public void setTargetCompleteDate(String targetCompleteDate) {
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

	/**
	 * @return the profileHeaders
	 */
	public List<DealerProfileHeader> getProfileHeaders() {
		return profileHeaders;
	}

	/**
	 * @param profileHeaders the profileHeaders to set
	 */
	public void setProfileHeaders(List<DealerProfileHeader> profileHeaders) {
		this.profileHeaders = profileHeaders;
	}

	/**
	 * @return the orderSegments
	 */
	public List<OrderSegment> getOrderSegments() {
		return orderSegments;
	}

	/**
	 * @param orderSegments the orderSegments to set
	 */
	public void setOrderSegments(List<OrderSegment> orderSegments) {
		this.orderSegments = orderSegments;
	}

	/**
	 * @return the osComplianceValues
	 */
	public List<ProfileOrderSegmentCompliance> getOsComplianceValues() {
		return osComplianceValues;
	}

	/**
	 * @param osComplianceValues the osComplianceValues to set
	 */
	public void setOsComplianceValues(
			List<ProfileOrderSegmentCompliance> osComplianceValues) {
		this.osComplianceValues = osComplianceValues;
	}

	/**
	 * @return the segmentComplianceValues
	 */
	public List<ProfileSegmentCompliance> getSegmentComplianceValues() {
		return segmentComplianceValues;
	}

	/**
	 * @param segmentComplianceValues the segmentComplianceValues to set
	 */
	public void setSegmentComplianceValues(
			List<ProfileSegmentCompliance> segmentComplianceValues) {
		this.segmentComplianceValues = segmentComplianceValues;
	}

	/**
	 * @return the periods
	 */
	public List<ProfileAndPeriod> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(List<ProfileAndPeriod> periods) {
		this.periods = periods;
	}
	
}
