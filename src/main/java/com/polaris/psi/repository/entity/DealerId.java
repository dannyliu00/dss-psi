/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.CM006F")
@IdClass(DealerIdPK.class)
public class DealerId implements Serializable {

	private static final long serialVersionUID = -1633207196955817379L;
	
	@Id
	@Column(name = "PTCUST")
	private String id;

	@Id
	@Column(name = "PTSFAM")
	private String family;
	
	@Column(name = "PTCANDT")
	private Integer canceled;

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
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	/**
	 * @return the canceled
	 */
	public Integer getCanceled() {
		return canceled;
	}

	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(Integer canceled) {
		this.canceled = canceled;
	}

}
