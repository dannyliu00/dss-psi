/**
 * 
 */
package com.polaris.psi.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author bericks
 *
 */
@Entity
@Table(name = "Profile.FG004F")
@NamedQueries({
	@NamedQuery(name = Segment.GET_DISTINCT, query = Segment.GET_DISTINCT_QUERY),
	@NamedQuery(name = Segment.GET_DISTINCT_BY_SUB, query = Segment.GET_DISTINCT_BY_SUB_QUERY)
})
@IdClass(SegmentPK.class)
public class Segment implements Serializable {

	private static final long serialVersionUID = -4597215182369607466L;
	
	static final String GET_DISTINCT = "getDistinctSegments";
	static final String GET_DISTINCT_QUERY = "select distinct s FROM Segment s where s.name = :name";
	
	static final String GET_DISTINCT_BY_SUB = "getDistinctBySubSegment";
	static final String GET_DISTINCT_BY_SUB_QUERY = "select distinct s FROM Segment s where s.subSegment = :subSegment";

	@Id
	@Column(name = "MISPSG")
	private String name;
	
	@Id
	@Column(name = "MIPTYP")
	private String type;

	@Id
	@Column(name = "MISBSG")
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
