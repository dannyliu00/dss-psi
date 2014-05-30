package com.polaris.psi.repository.entity;


//@Entity
//@Table(name = "<TBL_ORDR_SEG>")
public class OrderSegment {

//	@Id
//	@Column(name = "<COL_NAME1>")
	private int id;
	
//	@Column(name = "<COL_NAME2>")
	private String name;
	
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

}
