/**
 * 
 */
package com.polaris.psi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
//@Entity
//@Table(name = "<TBL_WIN>")
public class Window implements Serializable {

	private static final long serialVersionUID = -359239441726861126L;
	
//	@Id
//	@Column(name = "<COL_NAME1>")
	private int windowId;
	
//	@Column(name = "<COL_NAME2>")
	private String name;

	/**
	 * @return the windowId
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * @param windowId the windowId to set
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
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
