/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author bericks
 *
 */
public class DealerDto implements Serializable {

	private static final long serialVersionUID = 8391382100485071545L;

	private Integer dealerId;
	private String name;
	private Integer company;
	private String city;
	private String state;
	private String zip;
	private String dsmName;
	private String emailAddress;
	private String dsmEmailAddress;
	private String rsmEmailAddress;
	
	private List<ProfileDto> profiles;

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
	 * @return the company
	 */
	public Integer getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Integer company) {
		this.company = company;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the dsmName
	 */
	public String getDsmName() {
		return dsmName;
	}

	/**
	 * @param dsmName the dsmName to set
	 */
	public void setDsmName(String dsmName) {
		this.dsmName = dsmName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the profiles
	 */
	public List<ProfileDto> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(List<ProfileDto> profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the dsmEmailAddress
	 */
	public String getDsmEmailAddress() {
		return dsmEmailAddress;
	}

	/**
	 * @param dsmEmailAddress the dsmEmailAddress to set
	 */
	public void setDsmEmailAddress(String dsmEmailAddress) {
		this.dsmEmailAddress = dsmEmailAddress;
	}

	/**
	 * @return the rsmEmailAddress
	 */
	public String getRsmEmailAddress() {
		return rsmEmailAddress;
	}

	/**
	 * @param rsmEmailAddress the rsmEmailAddress to set
	 */
	public void setRsmEmailAddress(String rsmEmailAddress) {
		this.rsmEmailAddress = rsmEmailAddress;
	}

}
