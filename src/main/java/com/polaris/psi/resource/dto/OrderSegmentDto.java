/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bericks
 *
 */
public class OrderSegmentDto implements IBaseDto, Serializable {

	private static final long serialVersionUID = -4692650680058882881L;
	
	private int id;
	private String osCode;
	private String osName;
	private int recMinimum;
	private int recommended;
	private int recMaximum;
	private String periodCode;
	private int periodId;
	private Date periodStartDate;
	private String dealerEmail;
	private Date submittedDate;
	private Date approvedDate;

	private Integer profileId;
	private Integer complianceId;
	private Integer dealerId;
	private Integer sort;
	
	private String adminComments;
	private String dealerComments;
	private String dsmComments;
	
	private int actual;
	private Integer adminQty;
	private Integer dsmQty;
	private Integer finalQty;
	
	private Integer adminReasonCode;
	private Integer dsmReasonCode;
	private Integer reasonCode;
	
	private Integer headerId;
	private Integer profileOrderSegmentId;
	
	private String modifiedUserName;
	
	private boolean nonCompliant;
	
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
	 * @return the osCode
	 */
	public String getOsCode() {
		return osCode;
	}

	/**
	 * @param osCode the osCode to set
	 */
	public void setOsCode(String osCode) {
		this.osCode = osCode;
	}

	/**
	 * @return the osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @param osName the osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
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

	/**
	 * @return the actual
	 */
	@Override
	public int getActual() {
		return this.actual;
	}

	/**
	 * @param actual the actual to set
	 */
	@Override
	public void setActual(int actual) {
		this.actual = actual;
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
	 * @return the periodId
	 */
	public int getPeriodId() {
		return periodId;
	}

	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	/**
	 * @return the periodStartDate
	 */
	public Date getPeriodStartDate() {
		return periodStartDate;
	}

	/**
	 * @param periodStartDate the periodStartDate to set
	 */
	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	/**
	 * @return the dealerEmail
	 */
	public String getDealerEmail() {
		return dealerEmail;
	}

	/**
	 * @param dealerEmail the dealerEmail to set
	 */
	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}

	/**
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the complianceId
	 */
	public Integer getComplianceId() {
		return complianceId;
	}

	/**
	 * @param complianceId the complianceId to set
	 */
	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}

	/**
	 * @return the dealerId
	 */
	public Integer getDealerId() {
		return dealerId;
	}

	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the adminComments
	 */
	public String getAdminComments() {
		return adminComments;
	}

	/**
	 * @param adminComments the adminComments to set
	 */
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	/**
	 * @return the dealerComments
	 */
	public String getDealerComments() {
		return dealerComments;
	}

	/**
	 * @param dealerComments the dealerComments to set
	 */
	public void setDealerComments(String dealerComments) {
		this.dealerComments = dealerComments;
	}

	/**
	 * @return the dsmComments
	 */
	public String getDsmComments() {
		return dsmComments;
	}

	/**
	 * @param dsmComments the dsmComments to set
	 */
	public void setDsmComments(String dsmComments) {
		this.dsmComments = dsmComments;
	}

	/**
	 * @return the adminQty
	 */
	public Integer getAdminQty() {
		return adminQty;
	}

	/**
	 * @param adminQty the adminQty to set
	 */
	public void setAdminQty(Integer adminQty) {
		this.adminQty = adminQty;
	}

	/**
	 * @return the dsmQty
	 */
	public Integer getDsmQty() {
		return dsmQty;
	}

	/**
	 * @param dsmQty the dsmQty to set
	 */
	public void setDsmQty(Integer dsmQty) {
		this.dsmQty = dsmQty;
	}

	/**
	 * @return the finalQty
	 */
	public Integer getFinalQty() {
		return finalQty;
	}

	/**
	 * @param finalQty the finalQty to set
	 */
	public void setFinalQty(Integer finalQty) {
		this.finalQty = finalQty;
	}

	/**
	 * @return the adminReasonCode
	 */
	public Integer getAdminReasonCode() {
		return adminReasonCode;
	}

	/**
	 * @param adminReasonCode the adminReasonCode to set
	 */
	public void setAdminReasonCode(Integer adminReasonCode) {
		this.adminReasonCode = adminReasonCode;
	}

	/**
	 * @return the dsmReasonCode
	 */
	public Integer getDsmReasonCode() {
		return dsmReasonCode;
	}

	/**
	 * @param dsmReasonCode the dsmReasonCode to set
	 */
	public void setDsmReasonCode(Integer dsmReasonCode) {
		this.dsmReasonCode = dsmReasonCode;
	}

	/**
	 * @return the reasonCode
	 */
	public Integer getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the headerId
	 */
	public Integer getHeaderId() {
		return headerId;
	}

	/**
	 * @param headerId the headerId to set
	 */
	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	/**
	 * @return the profileOrderSegmentId
	 */
	public Integer getProfileOrderSegmentId() {
		return profileOrderSegmentId;
	}

	/**
	 * @param profileOrderSegmentId the profileOrderSegmentId to set
	 */
	public void setProfileOrderSegmentId(Integer profileOrderSegmentId) {
		this.profileOrderSegmentId = profileOrderSegmentId;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the modifiedUserName
	 */
	public String getModifiedUserName() {
		return modifiedUserName;
	}

	/**
	 * @param modifiedUserName the modifiedUserName to set
	 */
	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	/**
	 * @return the nonCompliant
	 */
	public boolean isNonCompliant() {
		return nonCompliant;
	}

	/**
	 * @param nonCompliant the nonCompliant to set
	 */
	public void setNonCompliant(boolean nonCompliant) {
		this.nonCompliant = nonCompliant;
	}

}
