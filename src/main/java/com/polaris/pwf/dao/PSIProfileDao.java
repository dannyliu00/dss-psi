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

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.util.CommonUtils;

/**
 * Data access object for retrieving Dealer Profiles.
 * @author bericks
 *
 */
@Repository
public class PSIProfileDao extends AbstractPolarisMinneapolisDao<PSIProfile> {

	private static Logger LOG = Logger.getLogger(PSIProfileDao.class);

	private static String QUERY_BY_DEALER_AND_CANCELED = ""
			+ "SELECT pstatus.N2DESC, profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, CAST( CAST(status.N9DESC AS CHAR(50)) AS VARCHAR(50)) as N9DESC "
			+ "  FROM cm006f dealer INNER JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "  INNER JOIN ot072f pstatus ON pstatus.N2STID = profile.N1STID "
			+ "  LEFT OUTER JOIN ot077f header ON header.N7IPID = profile.N1IPID AND header.N7DLR  = dealer.ptcust "
			+ "  LEFT OUTER JOIN ot079f status ON header.N7STID = status.N9STID "
			+ " WHERE dealer.ptcust = :dealerId AND dealer.PTCANDT= :canceled ";
	private static String QUERY_BY_ID = ""
			+ "SELECT pstatus.N2DESC, profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, CAST( CAST(status.N9DESC AS CHAR(50)) AS VARCHAR(50)) as N9DESC, "
			+ "profile.N1LGLT, header.N7DHID, header.N7DLR, header.N7MAIL, header.N7SBDT, header.N7APDT "
			+ "  FROM OT071F profile INNER JOIN OT072F pstatus ON pstatus.N2STID = profile.N1STID "
			+ "  LEFT OUTER JOIN OT077F header ON header.N7IPID = profile.N1IPID "
			+ "  LEFT OUTER JOIN OT079F status ON header.N7STID = status.N9STID "
			+ " WHERE profile.N1IPID = :profileId";

	public PSIProfileDao() {
		super(PSIProfile.class);
	}
	
	public List<PSIProfile> retrieveListByDealerId(Integer dealerId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_DEALER_AND_CANCELED);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_DEALER_AND_CANCELED);
			LOG.trace("query paramters: dealerId = " + dealerId + ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<PSIProfile> profiles = new ArrayList<PSIProfile>();
		for (Object[] result : results) {
			PSIProfile profile = new PSIProfile();
			profile.setProfileStatus(trimString((String) result[0]));
			profile.setId(((BigDecimal) result[1]).intValueExact());
			profile.setName(trimString((String) result[2]));
			profile.setTargetCompleteDate((Date) result[3]);
			profile.setType(trimString((String) result[4]));
			profile.setStatus(trimString((String) result[5]));
			
			profiles.add(profile);
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public PSIProfile retrieveProfileById(Integer profileId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_ID);
		query.setParameter("profileId", profileId);
		query.setMaxResults(1);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_ID);
			LOG.trace("query paramters: profileId = " + profileId);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		if(results.size() < 1) return null;
		
		Object[] result = results.get(0);

		PSIProfile profile = new PSIProfile();
		profile.setProfileStatus(trimString((String) result[0]));
		profile.setId(convertToInteger((BigDecimal) result[1]));
		profile.setName(trimString((String) result[2]));
		profile.setTargetCompleteDate((Date) result[3]);
		profile.setType(trimString((String) result[4]));
		profile.setStatus(trimString((String) result[5]));
		profile.setLegalText(trimString((Character) result[6]));
		profile.setHeaderId(convertToInteger((BigDecimal) result[7]));
		profile.setDealer(convertToInteger((BigDecimal) result[8]));
		profile.setEmail(trimString((String) result[9]));
		profile.setSubmittedDate((Date) result[10]); 
		profile.setApprovedDate((Date) result[11]);
		
		entityManager.close();
		
		return profile;
	}
	
	protected Integer convertToInteger(BigDecimal value) {
		if(value == null) return null;
		
		return value.intValueExact();
	}
	
	protected String trimString(Character value) {
		if(value == null) return null;
		
		return trimString(value.toString());
	}
	
	protected String trimString(String value) {
		if(value == null) return null;
		
		return value.trim();
	}
	
	protected Date convertDate(String value) {
		if(value == null) return null;
		
		return CommonUtils.convertDate(value);
	}
}
