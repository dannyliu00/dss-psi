/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.OT078F_DealerProfileDetail")
public class DealerProfileDetail implements Serializable {

	private static final long serialVersionUID = 8268329969553727531L;

	@Id
	@Column(name = "N8DDID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "N8DHID")
	private DealerProfileHeader header;
	
	@OneToOne
	@JoinColumn(name = "N8PSID")
	private ProfileAndOrderSegment profileOrderSegment;
	
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
	private Integer adminReadonCode;
	
	@Column(name = "N8ACOM", nullable=true)
	private String adminComments;
	
	@Column(name = "N8QTY", nullable=true)
	private Integer finalQty;

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
	 * @return the profileOrderSegment
	 */
	public ProfileAndOrderSegment getProfileOrderSegment() {
		return profileOrderSegment;
	}

	/**
	 * @param profileOrderSegment the profileOrderSegment to set
	 */
	public void setProfileOrderSegment(ProfileAndOrderSegment profileOrderSegment) {
		this.profileOrderSegment = profileOrderSegment;
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
	 * @return the adminReadonCode
	 */
	public Integer getAdminReadonCode() {
		return adminReadonCode;
	}

	/**
	 * @param adminReadonCode the adminReadonCode to set
	 */
	public void setAdminReadonCode(Integer adminReadonCode) {
		this.adminReadonCode = adminReadonCode;
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
	
}
