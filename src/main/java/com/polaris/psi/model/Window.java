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
@Entity
@Table(name = "<TABLE_NAME>")
public class Window implements Serializable {

	private static final long serialVersionUID = -359239441726861126L;
	
	@Id
	@Column(name = "<COL_NAME>")
	private int windowId;
	
	@Column(name = "<COL_NAME>")
	private String name;

}
