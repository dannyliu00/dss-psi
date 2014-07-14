/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
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
			+ "SELECT distinct pandos.N4PSID, pandos.N4OSEG, pandos.N4SORT, os.C7SBSG, oscomp.N5ID, "
			+ " oscomp.N5CODE, oscomp.N5RMIN, oscomp.N5REC, oscomp.N5RMAX, period.N3SORT "
			+ "  FROM OT074F pandos INNER JOIN OT025F os ON os.C7OSEG = pandos.N4OSEG "
			+ "  INNER JOIN OT075F oscomp ON oscomp.N5IPID = pandos.N4IPID AND oscomp.N5OSEG = pandos.N4OSEG "
			+ "  INNER JOIN (SELECT periods.N0CODE, pperiod.N3SORT, pperiod.N3IPID "
			+ "					FROM OT073F pperiod INNER JOIN OT070F periods ON pperiod.N3PPID = periods.N0PPID"
			+ "					ORDER BY pperiod.n3sort asc) period ON period.N0CODE = oscomp.N5CODE AND period.N3IPID = pandos.N4IPID "
			+ " WHERE pandos.N4IPID = :profileId "
			+ "   AND oscomp.N5DLR = :dealerId "
			+ " ORDER BY pandos.N4SORT ASC, os.C7SBSG ASC, period.N3SORT ASC";
	
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
			os.setProfileId(profileId);
			os.setDealerId(dealerId);
			os.setId(CommonUtils.convertToInteger((BigDecimal) result[0]));
			os.setName(CommonUtils.trimString((String) result[1]));
			os.setSort(CommonUtils.convertToInteger((BigDecimal) result[2]));
			os.setSubSegment(CommonUtils.trimString((String) result[3]));
			os.setComplianceId(CommonUtils.convertToInteger((BigDecimal) result[4]));
			os.setPeriodCode(CommonUtils.trimString((String) result[5]));
			os.setRecMinimum(CommonUtils.convertToInteger((BigDecimal) result[6]));
			os.setRecommended(CommonUtils.convertToInteger((BigDecimal) result[7]));
			os.setRecMaximum(CommonUtils.convertToInteger((BigDecimal) result[8]));
			
			if(!doesListContain(orderSegments, os)) orderSegments.add(os);
		}
		
		entityManager.close();
		
		return orderSegments;
	}
	
	protected boolean doesListContain(List<PSIOrderSegment> oses, PSIOrderSegment os) {
		
		for (PSIOrderSegment orderSegment : oses) {
			if(orderSegment.getName().equals(os.getName()) && orderSegment.getPeriodCode().equals(os.getPeriodCode()))
				return true;
		}
		
		return false;
	}

}
