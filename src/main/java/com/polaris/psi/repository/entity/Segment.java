/**
 * 
 */
package com.polaris.psi.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "FG004F")
public class Segment {

//	@Id
//	@Column(name = "MIMOD#")
	private String id;
	
//	@Column(name = "MIDESC")
	private String name;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
