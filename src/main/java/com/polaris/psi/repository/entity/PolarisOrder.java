/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "OT003F")
public class PolarisOrder implements Serializable {

	private static final long serialVersionUID = -7711636386970268594L;

	@Column(name = "A3PRFN")
	private String profileName;
	
	@Column(name = "A3DTKN")
	private String detailToken;
	
	@Column(name = "A3ETKN")
	private String extensionToken;
	
	@Column(name = "A3RQST")
	private boolean requiredToStock;
	
	@Column(name = "A3RQQY")
	private int requiredToStockQty;
	
	@Column(name = "A3RCMQ")
	private int recommendedMinQty;
	
	@Column(name = "A3EFDT")
    @Temporal(TemporalType.DATE)
	private Date effectiveFromDate;
	
	@Column(name = "A3ETDT")
    @Temporal(TemporalType.DATE)
	private Date effectiveToDate;
	
	@Column(name = "A3CRDT")
    @Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Column(name = "A3CRTM")
    @Temporal(TemporalType.DATE)
	private Date createTime;
	
	@Column(name = "A3CRUS")
	private String createUser;
	
	@Column(name = "A3CRPG")
	private String createProgram;
	
	@Column(name = "A3CHDT")
    @Temporal(TemporalType.DATE)
	private Date changeDate;
	
	@Column(name = "A3CHTM")
    @Temporal(TemporalType.DATE)
	private Date changeTime;

	@Column(name = "A3CHUS")
	private String changeUser;
	
	@Column(name = "A3CHPG")
	private String changeProgram;
	
	@Column(name = "A3USR01")
	private String userDef1;
	
	@Column(name = "A3USR02")
	private String userDef2;
	
	@Column(name = "A3USR03")
	private String userDef3;
	
	@Column(name = "A3USR04")
	private String userDef4;
	
	@Column(name = "A3USR05")
	private String userDef5;
	
	@Column(name = "A3USR06")
	private String userDef6;
	
	@Column(name = "A3USR07")
	private String userDef7;
	
	@Column(name = "A3USR08")
	private String userDef8;
	
	@Column(name = "A3USR09")
	private String userDef9;
	
	@Column(name = "A3USR10")
	private String userDef10;

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the detailToken
	 */
	public String getDetailToken() {
		return detailToken;
	}

	/**
	 * @param detailToken the detailToken to set
	 */
	public void setDetailToken(String detailToken) {
		this.detailToken = detailToken;
	}

	/**
	 * @return the extensionToken
	 */
	public String getExtensionToken() {
		return extensionToken;
	}

	/**
	 * @param extensionToken the extensionToken to set
	 */
	public void setExtensionToken(String extensionToken) {
		this.extensionToken = extensionToken;
	}

	/**
	 * @return the requiredToStock
	 */
	public boolean isRequiredToStock() {
		return requiredToStock;
	}

	/**
	 * @param requiredToStock the requiredToStock to set
	 */
	public void setRequiredToStock(boolean requiredToStock) {
		this.requiredToStock = requiredToStock;
	}

	/**
	 * @return the requiredToStockQty
	 */
	public int getRequiredToStockQty() {
		return requiredToStockQty;
	}

	/**
	 * @param requiredToStockQty the requiredToStockQty to set
	 */
	public void setRequiredToStockQty(int requiredToStockQty) {
		this.requiredToStockQty = requiredToStockQty;
	}

	/**
	 * @return the recommendedMinQty
	 */
	public int getRecommendedMinQty() {
		return recommendedMinQty;
	}

	/**
	 * @param recommendedMinQty the recommendedMinQty to set
	 */
	public void setRecommendedMinQty(int recommendedMinQty) {
		this.recommendedMinQty = recommendedMinQty;
	}

	/**
	 * @return the effectiveFromDate
	 */
	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

	/**
	 * @param effectiveFromDate the effectiveFromDate to set
	 */
	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	/**
	 * @return the effectiveToDate
	 */
	public Date getEffectiveToDate() {
		return effectiveToDate;
	}

	/**
	 * @param effectiveToDate the effectiveToDate to set
	 */
	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the createProgram
	 */
	public String getCreateProgram() {
		return createProgram;
	}

	/**
	 * @param createProgram the createProgram to set
	 */
	public void setCreateProgram(String createProgram) {
		this.createProgram = createProgram;
	}

	/**
	 * @return the changeDate
	 */
	public Date getChangeDate() {
		return changeDate;
	}

	/**
	 * @param changeDate the changeDate to set
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * @return the changeTime
	 */
	public Date getChangeTime() {
		return changeTime;
	}

	/**
	 * @param changeTime the changeTime to set
	 */
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * @return the changeUser
	 */
	public String getChangeUser() {
		return changeUser;
	}

	/**
	 * @param changeUser the changeUser to set
	 */
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	/**
	 * @return the changeProgram
	 */
	public String getChangeProgram() {
		return changeProgram;
	}

	/**
	 * @param changeProgram the changeProgram to set
	 */
	public void setChangeProgram(String changeProgram) {
		this.changeProgram = changeProgram;
	}

	/**
	 * @return the userDef1
	 */
	public String getUserDef1() {
		return userDef1;
	}

	/**
	 * @param userDef1 the userDef1 to set
	 */
	public void setUserDef1(String userDef1) {
		this.userDef1 = userDef1;
	}

	/**
	 * @return the userDef2
	 */
	public String getUserDef2() {
		return userDef2;
	}

	/**
	 * @param userDef2 the userDef2 to set
	 */
	public void setUserDef2(String userDef2) {
		this.userDef2 = userDef2;
	}

	/**
	 * @return the userDef3
	 */
	public String getUserDef3() {
		return userDef3;
	}

	/**
	 * @param userDef3 the userDef3 to set
	 */
	public void setUserDef3(String userDef3) {
		this.userDef3 = userDef3;
	}

	/**
	 * @return the userDef4
	 */
	public String getUserDef4() {
		return userDef4;
	}

	/**
	 * @param userDef4 the userDef4 to set
	 */
	public void setUserDef4(String userDef4) {
		this.userDef4 = userDef4;
	}

	/**
	 * @return the userDef5
	 */
	public String getUserDef5() {
		return userDef5;
	}

	/**
	 * @param userDef5 the userDef5 to set
	 */
	public void setUserDef5(String userDef5) {
		this.userDef5 = userDef5;
	}

	/**
	 * @return the userDef6
	 */
	public String getUserDef6() {
		return userDef6;
	}

	/**
	 * @param userDef6 the userDef6 to set
	 */
	public void setUserDef6(String userDef6) {
		this.userDef6 = userDef6;
	}

	/**
	 * @return the userDef7
	 */
	public String getUserDef7() {
		return userDef7;
	}

	/**
	 * @param userDef7 the userDef7 to set
	 */
	public void setUserDef7(String userDef7) {
		this.userDef7 = userDef7;
	}

	/**
	 * @return the userDef8
	 */
	public String getUserDef8() {
		return userDef8;
	}

	/**
	 * @param userDef8 the userDef8 to set
	 */
	public void setUserDef8(String userDef8) {
		this.userDef8 = userDef8;
	}

	/**
	 * @return the userDef9
	 */
	public String getUserDef9() {
		return userDef9;
	}

	/**
	 * @param userDef9 the userDef9 to set
	 */
	public void setUserDef9(String userDef9) {
		this.userDef9 = userDef9;
	}

	/**
	 * @return the userDef10
	 */
	public String getUserDef10() {
		return userDef10;
	}

	/**
	 * @param userDef10 the userDef10 to set
	 */
	public void setUserDef10(String userDef10) {
		this.userDef10 = userDef10;
	}
	
}
