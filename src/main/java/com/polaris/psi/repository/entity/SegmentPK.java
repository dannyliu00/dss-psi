/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
public class SegmentPK implements Serializable {

	private static final long serialVersionUID = -1358225220493787351L;

	private String name;

	private String type;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
