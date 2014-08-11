/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Repository
public class PSISegmentDao extends AbstractPolarisMinneapolisDao<PSISegment> {

	private static final SplunkLogger LOG = new SplunkLogger(PSIOrderSegmentDao.class);
	
	private static String QUERY_BY_PROFILE_DEALER_AND_TYPE = ""
			+ "SELECT distinct segComp.N6ID, segComp.N6SSID, segComp.N6SMIN, segComp.N6SMAX, segComp.N6OREQ, segments.MISBSG "
			+ "  FROM OT076F segComp inner join FG004F segments "
			+ "    on segComp.N6SSID = segments.MISPSG "
			+ " WHERE segComp.N6IPID = :profileId and segComp.N6DLR = :dealerId and segments.MIPTYP = :type";

	public PSISegmentDao() {
		super(PSISegment.class);
	}
	
	/**
	 * Retrieves a list of inventory profile super segments for a given profile ID, dealer ID and product line.
	 * 
	 * @param profileId
	 * @param dealerId
	 * @param type
	 * @return
	 */
	public List<PSISegment> retrieveByProfileDealerAndType(Integer profileId, Integer dealerId, String type) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveByProfileDealerAndType");
		
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_DEALER_AND_TYPE);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		query.setParameter("type", type);
		
		LOG.trace(PolarisIdentity.get(), "retrieveByProfileDealerAndType", "query to run: " + QUERY_BY_PROFILE_DEALER_AND_TYPE 
				+ "query paramters: profileId = " + profileId + ", dealerId = " + dealerId + ", type = " + type);
		
		List<PSISegment> segments = new ArrayList<PSISegment>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				PSISegment segment = new PSISegment();
				segment.setId(CommonUtils.convertToInteger((BigDecimal) result[0]));
				segment.setName(CommonUtils.trimString((String) result[1]));
				segment.setRecMinimum(CommonUtils.convertToInteger((BigDecimal) result[2]));
				segment.setRecMaximum(CommonUtils.convertToInteger((BigDecimal) result[3]));
				segment.setRecOsCount(CommonUtils.convertToInteger((BigDecimal) result[4]));
				segment.setSubSegment(CommonUtils.trimString((String) result[5]));
				
				if(segment.getSubSegment() != null) segments.add(segment);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveByProfileDealerAndType", e);
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveByProfileDealerAndType", "entityManager closed");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByProfileDealerAndType");
		
		return segments;
	}
}
