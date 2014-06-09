package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DealerAndDsm
 *
 */
@Entity
@Table(name = "Enterprise.RsmDsmDealer")
public class DealerAndDsm implements Serializable {
	
	private static final long serialVersionUID = -6439087784808583526L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="DealerId")
	private Integer dealerId;
	
	@Column(name = "DsmId")
	private Integer dsmId;
	
	@Column(name="DsmName")
	private String dsmName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDsmId() {
		return dsmId;
	}

	public void setDsmId(Integer dsmId) {
		this.dsmId = dsmId;
	}

	/**
	 * @return the dealerId
	 */
	public Integer getDealerId() {
		return dealerId;
	}

	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	/**
	 * @return the dsmName
	 */
	public String getDsmName() {
		return dsmName;
	}

	/**
	 * @param dsmName the dsmName to set
	 */
	public void setDsmName(String dsmName) {
		this.dsmName = dsmName;
	}

}
