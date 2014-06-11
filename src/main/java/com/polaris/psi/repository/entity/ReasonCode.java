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
@Table(name = "Profile.OT080F_ReasonCode")
public class ReasonCode implements Serializable {

	private static final long serialVersionUID = 3070851503046452010L;

	@Id
	@Column(name = "N9RCODE")
	private int id;
	
	@Column(name = "N9CODE")
	private String descsription;
	
	@Column(name = "N9ROLE")
	private int role;

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
	 * @return the descsription
	 */
	public String getDescsription() {
		return descsription;
	}

	/**
	 * @param descsription the descsription to set
	 */
	public void setDescsription(String descsription) {
		this.descsription = descsription;
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
