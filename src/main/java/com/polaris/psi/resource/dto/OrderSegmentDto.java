/**
 * 
 */
package com.polaris.psi.resource.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author bericks
 *
 */
public class OrderSegmentDto implements Serializable {

	private static final long serialVersionUID = -4692650680058882881L;
	
	private int id;
	private String name;
	
	private List<SegmentQuantityDto> quanitities;
	
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

	/**
	 * @return the quanitities
	 */
	public List<SegmentQuantityDto> getQuanitities() {
		return quanitities;
	}

	/**
	 * @param quanitities the quanitities to set
	 */
	public void setQuanitities(List<SegmentQuantityDto> quanitities) {
		this.quanitities = quanitities;
	}
	
}
