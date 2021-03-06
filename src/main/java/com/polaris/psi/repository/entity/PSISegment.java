/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

/**
 * @author bericks
 *
 */
public class PSISegment implements Serializable {

	private static final long serialVersionUID = 1773120674813445145L;
	
	private Integer id;
	private String name;
	private Integer recMinimum;
	private Integer recMaximum;
	private Integer recOsCount;
	private String subSegment;

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
	 * @return the recMinimum
	 */
	public Integer getRecMinimum() {
		return recMinimum;
	}
	
	/**
	 * @param recMinimum the recMinimum to set
	 */
	public void setRecMinimum(Integer recMinimum) {
		this.recMinimum = recMinimum;
	}
	
	/**
	 * @return the recMaximum
	 */
	public Integer getRecMaximum() {
		return recMaximum;
	}
	
	/**
	 * @param recMaximum the recMaximum to set
	 */
	public void setRecMaximum(Integer recMaximum) {
		this.recMaximum = recMaximum;
	}
	
	/**
	 * @return the recOsCount
	 */
	public Integer getRecOsCount() {
		return recOsCount;
	}

	/**
	 * @param recOsCount the recOsCount to set
	 */
	public void setRecOsCount(Integer recOsCount) {
		this.recOsCount = recOsCount;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((recMinimum == null) ? 0 : recMinimum.hashCode());
		result = prime * result
				+ ((recOsCount == null) ? 0 : recOsCount.hashCode());
		result = prime * result
				+ ((subSegment == null) ? 0 : subSegment.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PSISegment other = (PSISegment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recMinimum == null) {
			if (other.recMinimum != null)
				return false;
		} else if (!recMinimum.equals(other.recMinimum))
			return false;
		if (recOsCount == null) {
			if (other.recOsCount != null)
				return false;
		} else if (!recOsCount.equals(other.recOsCount))
			return false;
		if (subSegment == null) {
			if (other.subSegment != null)
				return false;
		} else if (!subSegment.equals(other.subSegment))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSISegment [id=" + id + ", name=" + name + ", recMinimum=" + recMinimum
				+ ", recOsCount=" + recOsCount + ", subSegment=" + subSegment
				+ "]";
	}

}
