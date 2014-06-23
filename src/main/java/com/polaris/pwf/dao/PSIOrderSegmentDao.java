/**
 * 
 */
package com.polaris.pwf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Repository
public class PSIOrderSegmentDao extends AbstractPolarisMinneapolisDao<PSIOrderSegment> {

	private static Logger LOG = Logger.getLogger(PSIOrderSegmentDao.class);
	
	private static String QUERY_BY_PROFILE_AND_DEALER = ""
			+ "SELECT pandos.N4PSID, pandos.N4OSEG, pandos.N4SORT, "
			+ "os.C7SBSG, oscomp.N5ID, oscomp.N5CODE, oscomp.N5DLR, oscomp.N5RMIN, oscomp.N5REC, oscomp.N5RMAX "
			+ "  FROM OT074F pandos INNER JOIN OT025F os ON os.C7OSEG = pandos.N4OSEG "
			+ "  INNER JOIN OT075F oscomp ON oscomp.N5IPID = pandos.N4IPID AND oscomp.N5OSEG = pandos.N4OSEG "
			+ " WHERE pandos.N4IPID = :profileId AND oscomp.N5DLR = :dealerId";
	
	public PSIOrderSegmentDao() {
		super(PSIOrderSegment.class);
	}
	
	public List<PSIOrderSegment> retrieveByProfileAndDealer(Integer profileId, Integer dealerId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_AND_DEALER);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_PROFILE_AND_DEALER);
			LOG.trace("query paramters: profileId = " + profileId + ", dealerId = " + dealerId);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<PSIOrderSegment> orderSegments = new ArrayList<PSIOrderSegment>();
		for (Object[] result : results) {
			PSIOrderSegment os = new PSIOrderSegment();
			os.setId((Integer) result[0]);
			os.setName(CommonUtils.trimString((String) result[1]));
			os.setSort((Integer) result[2]);
			os.setSubSegment(CommonUtils.trimString((String) result[3]));
			os.setComplianceId((Integer) result[4]);
			os.setPeriodCode(CommonUtils.trimString((String) result[5]));
			os.setDealerId((Integer) result[6]);
			os.setRecMinimum((Integer) result[7]);
			os.setRecommended((Integer) result[8]);
			os.setRecMaximum((Integer) result[9]);
			
			orderSegments.add(os);
		}
		
		entityManager.close();
		
		return orderSegments;
	}

}
