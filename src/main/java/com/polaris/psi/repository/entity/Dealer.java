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
@Table(name = "tblDealers")
public class Dealer implements Serializable {

	private static final long serialVersionUID = -5050795937517499091L;

	@Id
	@Column(name = "DealerID")
	private Integer id;

	@Column(name = "DealerName")
	private String name;

	@Column(name = "Company")
	private Integer company;
	
	@Column(name = "City")
	private String city;

	@Column(name = "State")
	private String state;

	@Column(name = "Zip")
	private String zip;
	
	@Column(name = "CustClass")
	private String classification;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

}
