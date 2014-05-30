/**
 * 
 */
package com.polaris.psi.repository.entity;


/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "<TBL_DEALER>")
public class Dealer {

//	@Id
//	@Column(name = "<COL_NAME1>")
	private Integer id;

//	@Column(name = "<COL_NAME2>")
	private String name;

//	@Column(name = "<COL_NAME3>")
	private Integer company;
	
//	@Column(name = "<COL_NAME4>")
	private String city;

//	@Column(name = "<COL_NAME5>")
	private String state;

//	@Column(name = "<COL_NAME6>")
	private String zip;
	
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

}
