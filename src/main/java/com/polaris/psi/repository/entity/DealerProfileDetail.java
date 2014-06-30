/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "OT078F")
public class DealerProfileDetail implements Serializable {

	private static final long serialVersionUID = 8268329969553727531L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N8DDID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "N8DHID")
	private DealerProfileHeader header;
	
	@Column(name = "N8PSID")
	private int profileOrderSegmentId;
	
	@Column(name = "N8DQTY", nullable=true)
	private Integer actual;
	
	@Column(name = "N8DCOD", nullable=true)
	private Integer dealerReasonCode;
	
	@Column(name = "N8DCOM", nullable=true)
	private String dealerComments;
	
	@Column(name = "N8SQTY", nullable=true)
	private Integer dsmRecommendedQty;
	
	@Column(name = "N8SCOD", nullable=true)
	private Integer dsmReasonCode;
	
	@Column(name = "N8SCOM", nullable=true)
	private String dsmComments;
	
	@Column(name = "N8AQTY", nullable=true)
	private Integer adminApprovedQty;
	
	@Column(name = "N8ACOD", nullable=true)
	private Integer adminReasonCode;
	
	@Column(name = "N8ACOM", nullable=true)
	private String adminComments;
	
	@Column(name = "N8QTY", nullable=true)
	private Integer finalQty;

	@Column(name = "N8CRDT")
    @Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "N8CRTM")
    @Temporal(TemporalType.TIME)
	private Date createdTime;
	
	@Column(name = "N8CRUS")
	private String createdUser;
	
	@Column(name = "N8CRPG")
	private String createdProgram;
	
	@Column(name = "N8CHDT")
    @Temporal(TemporalType.DATE)
	private Date changedDate;
	
	@Column(name = "N8CHTM")
    @Temporal(TemporalType.TIME)
	private Date changedTime;
	
	@Column(name = "N8CHUS")
	private String changedUser;
	
	@Column(name = "N8CHPG")
	private String changedProgram;

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
	 * @return the header
	 */
	public DealerProfileHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(DealerProfileHeader header) {
		this.header = header;
	}

	/**
	 * @return the profileOrderSegmentId
	 */
	public int getProfileOrderSegmentId() {
		return profileOrderSegmentId;
	}

	/**
	 * @param profileOrderSegmentId the profileOrderSegmentId to set
	 */
	public void setProfileOrderSegmentId(int profileOrderSegmentId) {
		this.profileOrderSegmentId = profileOrderSegmentId;
	}

	/**
	 * @return the actual
	 */
	public Integer getActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	public void setActual(Integer actual) {
		this.actual = actual;
	}

	/**
	 * @return the dealerReasonCode
	 */
	public Integer getDealerReasonCode() {
		return dealerReasonCode;
	}

	/**
	 * @param dealerReasonCode the dealerReasonCode to set
	 */
	public void setDealerReasonCode(Integer dealerReasonCode) {
		this.dealerReasonCode = dealerReasonCode;
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
	 * @return the dsmRecommendedQty
	 */
	public Integer getDsmRecommendedQty() {
		return dsmRecommendedQty;
	}

	/**
	 * @param dsmRecommendedQty the dsmRecommendedQty to set
	 */
	public void setDsmRecommendedQty(Integer dsmRecommendedQty) {
		this.dsmRecommendedQty = dsmRecommendedQty;
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
	 * @return the adminApprovedQty
	 */
	public Integer getAdminApprovedQty() {
		return adminApprovedQty;
	}

	/**
	 * @param adminApprovedQty the adminApprovedQty to set
	 */
	public void setAdminApprovedQty(Integer adminApprovedQty) {
		this.adminApprovedQty = adminApprovedQty;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the createdProgram
	 */
	public String getCreatedProgram() {
		return createdProgram;
	}

	/**
	 * @param createdProgram the createdProgram to set
	 */
	public void setCreatedProgram(String createdProgram) {
		this.createdProgram = createdProgram;
	}

	/**
	 * @return the changedDate
	 */
	public Date getChangedDate() {
		return changedDate;
	}

	/**
	 * @param changedDate the changedDate to set
	 */
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	/**
	 * @return the changedTime
	 */
	public Date getChangedTime() {
		return changedTime;
	}

	/**
	 * @param changedTime the changedTime to set
	 */
	public void setChangedTime(Date changedTime) {
		this.changedTime = changedTime;
	}

	/**
	 * @return the changedUser
	 */
	public String getChangedUser() {
		return changedUser;
	}

	/**
	 * @param changedUser the changedUser to set
	 */
	public void setChangedUser(String changedUser) {
		this.changedUser = changedUser;
	}

	/**
	 * @return the changedProgram
	 */
	public String getChangedProgram() {
		return changedProgram;
	}

	/**
	 * @param changedProgram the changedProgram to set
	 */
	public void setChangedProgram(String changedProgram) {
		this.changedProgram = changedProgram;
	}

}
