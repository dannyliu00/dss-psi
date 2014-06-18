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
@Table(name = "Profile.OT025F")
public class OrderSegment implements Serializable {
	
	private static final long serialVersionUID = -6602907903969618969L;
	
	@Id
	@Column(name = "C7OSEG")
	private String name;
	
	@Id
	@Column(name = "C7SBSG")
	private String subSegment;

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
	 * @return the subSegment
	 */
	public String getSubSegment() {
		return subSegment;
	}

	/**
	 * @param subSegment the subSegment to set
	 */
	public void setSubSegment(String subSegment) {
		this.subSegment = subSegment;
	}

}
