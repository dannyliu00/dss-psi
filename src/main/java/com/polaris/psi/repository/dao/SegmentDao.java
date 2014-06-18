package com.polaris.psi.repository.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.Segment;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author bericks
 *
 */
@Repository
public class SegmentDao extends AbstractPolarisDealersExtensionDao<Segment> {

	public SegmentDao() {
		super(Segment.class);
	}
	
	public List<Segment> retrieveListByName(String name) {
		TypedQuery<Segment> query = entityManager.createNamedQuery("getDistinctSegments", Segment.class);
		query.setParameter("name", name);
		
		List<Segment> segments = query.getResultList();
		entityManager.close();
		
	    return segments;
	}
	
	public List<Segment> retrieveListBySubSegment(String subSegment) {
		TypedQuery<Segment> query = entityManager.createNamedQuery("getDistinctBySubSegment", Segment.class);
		query.setParameter("subSegment", subSegment);
		
		List<Segment> segments = query.getResultList();
		entityManager.close();
		
	    return segments;
	}
}
