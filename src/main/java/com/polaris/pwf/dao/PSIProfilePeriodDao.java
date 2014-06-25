/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfilePeriodDao extends AbstractPolarisMinneapolisDao<PSIProfilePeriod> {

	private static Logger LOG = Logger.getLogger(PSIProfilePeriodDao.class);

	private static String QUERY_BY_PROFILE_ID = ""
			+ "SELECT N0PPID, N0CODE, N0DESC, N0SDAT, N0EDAT, N3SORT "
			+ "  FROM OT070F period INNER JOIN OT073F pp ON pp.N3PPID = period.N0PPID "
			+ " WHERE pp.N3IPID = :profileId";
	
	public PSIProfilePeriodDao() {
		super(PSIProfilePeriod.class);
	}
	
	public List<PSIProfilePeriod> retrieveByProfileId(Integer profileId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_PROFILE_ID);
		query.setParameter("profileId", profileId);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_PROFILE_ID);
			LOG.trace("query paramters: profileId = " + profileId);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();

		List<PSIProfilePeriod> periods = new ArrayList<PSIProfilePeriod>();
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
		
		entityManager.close();
		
		return periods;
	}
}
