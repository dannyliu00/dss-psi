/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Repository
public class PSIOrderSegmentDao extends AbstractPolarisMinneapolisDao<PSIOrderSegment> {

	private static final SplunkLogger LOG = new SplunkLogger(PSIOrderSegmentDao.class);
	
	private static String QUERY_BY_PROFILE_AND_DEALER = ""
			+ "SELECT distinct pandos.N4PSID, pandos.N4OSEG, pandos.N4SORT, os.A5DESC, oscomp.N5ID, "
			+ " oscomp.N5CODE, oscomp.N5RMIN, oscomp.N5REC, oscomp.N5RMAX, period.N3SORT, period.N0PPID "
			+ "  FROM OT074F pandos INNER JOIN OT005F os ON os.A5OSEG = pandos.N4OSEG "
			+ "  INNER JOIN OT075F oscomp ON oscomp.N5IPID = pandos.N4IPID AND oscomp.N5OSEG = pandos.N4OSEG "
			+ "  INNER JOIN (SELECT periods.N0CODE, pperiod.N3SORT, pperiod.N3IPID, periods.N0PPID "
			+ "					FROM OT073F pperiod INNER JOIN OT070F periods ON pperiod.N3PPID = periods.N0PPID"
			+ "					ORDER BY pperiod.n3sort asc) period ON period.N0CODE = oscomp.N5CODE AND period.N3IPID = pandos.N4IPID "
			+ " WHERE pandos.N4IPID = :profileId "
			+ "   AND oscomp.N5DLR = :dealerId "
			+ " ORDER BY pandos.N4SORT ASC, period.N3SORT ASC";
	
	public PSIOrderSegmentDao() {
		super(PSIOrderSegment.class);
	}
	
	/**
	 * Retrieves the order segments and compliance values for a dealer's inventory profile 
	 * based on a profile ID and dealer ID
	 * 
	 * @param profileId
	 * @param dealerId
	 * @return
	 */
	public List<PSIOrderSegment> retrieveByProfileAndDealer(Integer profileId, Integer dealerId) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveByProfileAndDealer");
		
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_AND_DEALER);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		
		LOG.trace(PolarisIdentity.get(), "retrieveByProfileAndDealer", "query to run: " + QUERY_BY_PROFILE_AND_DEALER 
				+ "query paramters: profileId = " + profileId + ", dealerId = " + dealerId);
		
		List<PSIOrderSegment> orderSegments = new ArrayList<PSIOrderSegment>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				PSIOrderSegment os = new PSIOrderSegment();
				os.setProfileId(profileId);
				os.setDealerId(dealerId);
				os.setId(CommonUtils.convertToInteger((BigDecimal) result[0]));
				os.setOsCode(CommonUtils.trimString((String) result[1]));
				os.setSort(CommonUtils.convertToInteger((BigDecimal) result[2]));
				os.setOsName(CommonUtils.trimString((String) result[3]));
				os.setComplianceId(CommonUtils.convertToInteger((BigDecimal) result[4]));
				os.setPeriodCode(CommonUtils.trimString((String) result[5]));
				os.setRecMinimum(CommonUtils.convertToInteger((BigDecimal) result[6]));
				os.setRecommended(CommonUtils.convertToInteger((BigDecimal) result[7]));
				os.setRecMaximum(CommonUtils.convertToInteger((BigDecimal) result[8]));
				os.setPeriodId(CommonUtils.convertToInteger((BigDecimal) result[9]));
				
				if(!doesListContain(orderSegments, os)) orderSegments.add(os);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveByProfileAndDealer", e);
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveByProfileAndDealer", "entityManager closed");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByProfileAndDealer");
		
		return orderSegments;
	}
	
	protected boolean doesListContain(List<PSIOrderSegment> oses, PSIOrderSegment os) {
		
		LOG.methodStart(PolarisIdentity.get(), "doesListContain");
		
		for (PSIOrderSegment orderSegment : oses) {
			if(orderSegment.getOsCode().equals(os.getOsCode()) && orderSegment.getPeriodCode().equals(os.getPeriodCode())) {
				LOG.methodEnd(PolarisIdentity.get(), "doesListContain");

				return true;
			}
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "doesListContain");
		
		return false;
	}

}
