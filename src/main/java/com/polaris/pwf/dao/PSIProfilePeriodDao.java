/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfilePeriodDao extends AbstractPolarisMinneapolisDao<PSIProfilePeriod> {

	private static final SplunkLogger LOG = new SplunkLogger(PSIProfilePeriodDao.class);
	
	private static String QUERY_BY_PROFILE_ID = ""
			+ "SELECT N0PPID, N0CODE, N0DESC, N0SDAT, N0EDAT, N3SORT "
			+ "  FROM OT070F period INNER JOIN OT073F pp ON pp.N3PPID = period.N0PPID "
			+ " WHERE pp.N3IPID = :profileId"
			+ " ORDER BY N3SORT ASC";
	
	private static String QUERY_BY_PROFILE_ID_AND_DLR = ""
			+ "SELECT period.N0PPID, period.N0CODE, period.N0DESC, period.N0SDAT, period.N0EDAT, pp.N3SORT, "
			+ "       pComp.P4RMIN, pComp.P4REC, pComp.P4RMAX "
			+ "  FROM OT070F period INNER JOIN OT073F pp ON pp.N3PPID = period.N0PPID"
			+ "       INNER JOIN OT085F pComp on pComp.P4IPID = pp.N3IPID AND pComp.P4CODE = period.N0CODE"
			+ " WHERE pp.N3IPID = :profileId "
			+ "   AND pcomp.P4DLR = :dealerId "
			+ "ORDER BY N3SORT ASC";
	
	public PSIProfilePeriodDao() {
		super(PSIProfilePeriod.class);
	}
	
	/**
	 * Retrieves a list of inventory profile sell-in periods for the given profile ID
	 * 
	 * @param profileId
	 * @return
	 */
	public List<PSIProfilePeriod> retrieveByProfileId(Integer profileId) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveByProfileId");
		
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_ID);
		query.setParameter("profileId", profileId);
		
		LOG.trace(PolarisIdentity.get(), "retrieveByProfileId", "query to run: " + QUERY_BY_PROFILE_ID 
				+ "query paramters: profileId = " + profileId);
		
		List<PSIProfilePeriod> periods = new ArrayList<PSIProfilePeriod>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();

			for (Object[] result : results) {
				PSIProfilePeriod period = new PSIProfilePeriod();
				period.setId(CommonUtils.convertToInteger((BigDecimal) result[0]));
				period.setPeriodCode(CommonUtils.trimString((String) result[1]));
				period.setName(CommonUtils.trimString((String) result[2]));
				period.setStartDate((Date) result[3]);
				period.setEndDate((Date) result[4]);
				period.setSort(CommonUtils.convertToInteger((BigDecimal) result[5]));
				periods.add(period);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveByProfileId", e);
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveByProfileId", "entityManager closed");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByProfileId");
		
		return periods;
	}

	/**
	 * Retrieves a list of inventory profile sell-in periods for the given profile ID
	 * 
	 * @param profileId
	 * @return
	 */
	public List<PSIProfilePeriod> retrieveByProfileAndDealer(Integer profileId, Integer dealerId) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveByProfileAndDealer");
		
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_ID_AND_DLR);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		
		LOG.trace(PolarisIdentity.get(), "retrieveByProfileAndDealer", "query to run: " + QUERY_BY_PROFILE_ID_AND_DLR 
				+ "query paramters: profileId = " + profileId + ", dealerId = " + dealerId);
		
		List<PSIProfilePeriod> periods = new ArrayList<PSIProfilePeriod>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();

			for (Object[] result : results) {
				PSIProfilePeriod period = new PSIProfilePeriod();
				period.setId(CommonUtils.convertToInteger((BigDecimal) result[0]));
				period.setPeriodCode(CommonUtils.trimString((String) result[1]));
				period.setName(CommonUtils.trimString((String) result[2]));
				period.setStartDate((Date) result[3]);
				period.setEndDate((Date) result[4]);
				period.setSort(CommonUtils.convertToInteger((BigDecimal) result[5]));
				period.setMinimum(CommonUtils.convertToInt((BigDecimal) result[6]));
				period.setRecommended(CommonUtils.convertToInt((BigDecimal) result[7]));
				period.setMaximum(CommonUtils.convertToInt((BigDecimal) result[8]));
				
				periods.add(period);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveByProfileAndDealer", e);
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveByProfileAndDealer", "entityManager closed");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByProfileAndDealer");
		
		return periods;
	}

}
