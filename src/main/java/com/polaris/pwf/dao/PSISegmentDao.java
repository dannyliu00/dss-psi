/**
 * 
 */
package com.polaris.pwf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSISegment;

/**
 * @author bericks
 *
 */
@Repository
public class PSISegmentDao extends AbstractPolarisDealersExtensionDao<PSISegment> {

	private static Logger LOG = Logger.getLogger(PSISegmentDao.class);

	private static String QUERY_BY_PROFILE_DEALER_AND_TYPE = ""
			+ "SELECT distinct segComp.N6ID, segComp.N6CODE, segComp.N6SPSEG, segComp.N6SMIN, segComp.N6SMAX, segments.MISBSG "
			+ "FROM Profile.OT076F_ProfileSuperSegmentCompliance segComp inner join Profile.FG004F segments "
			+ "on segComp.N6SPSEG = segments.MISPSG "
			+ "WHERE segComp.N6IPID = :profileId and segComp.N6DLR = :dealerId and segments.MIPTYP = :type";

	public PSISegmentDao() {
		super(PSISegment.class);
	}
	
	public List<PSISegment> retrieveByProfileDealerAndType(Integer profileId, Integer dealerId, String type) {
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_DEALER_AND_TYPE);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		query.setParameter("type", type);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_PROFILE_DEALER_AND_TYPE);
			LOG.trace("query paramters: profileId = " + profileId + ", dealerId = " + dealerId + ", type = " + type);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<PSISegment> segments = new ArrayList<PSISegment>();
		
		for (Object[] result : results) {
			PSISegment segment = new PSISegment();
			segment.setId((Integer) result[0]);
			segment.setPeriodCode(((String) result[1]).trim());
			segment.setName(((String) result[2]).trim());
			segment.setRecMinimum((Integer) result[3]);
			segment.setRecMaximum((Integer) result[4]);
			segment.setSubSegment((String) result[5]);
			
			segments.add(segment);
		}
		
		entityManager.close();
		
		return segments;
	}
}
