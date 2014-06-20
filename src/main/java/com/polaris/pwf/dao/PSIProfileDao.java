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

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfileDao extends AbstractPolarisMinneapolisDao<PSIProfile> {

	private static Logger LOG = Logger.getLogger(PSIProfileDao.class);
	private static String QUERY_BY_DEALER_AND_CANCELED = ""
			+ "SELECT profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, status.N9DESC "
			+ "FROM cm006f dealer JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "LEFT OUTER JOIN ot077f header ON header.N7IPID = profile.N1IPID AND header.N7DLR  = dealer.ptcust "
			+ "LEFT OUTER JOIN ot079f status ON header.N7STID = status.N9STID "
			+ "WHERE dealer.ptcust = :dealerId "
			+ "AND dealer.PTCANDT= :canceled";
	
	private static String QUERY_BY_ID = ""
			+ "select profile.N1IPID, profile.n1DESC, profile.N1TDAT, profile.N1PDLN, profile.N1LGLT, "
			+ "header.N7DHID, header.N7DLR, header.N7EMAIL, header.N7SBDT, header.N7APDT, status.N9DESC "
			+ "from Profile.OT071F_Profile profile "
			+ "left outer join Profile.OT077F_DealerProfileHeader header on header.N7IPID = profile.N1IPID "
			+ "inner join Profile.OT079F_DealerProfileHeaderStatus status on header.N7STID = status.N9STID "
			+ "where profile.N1IPID = :profileId";

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
			profile.setId(((BigDecimal) result[0]).intValueExact());
			profile.setName(((String) result[1]).trim());
			profile.setTargetCompleteDate((Date) result[2]);
			profile.setType(((String) result[3]).trim());
			profile.setStatus(((String) result[4]).trim());
			
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
		Object[] result = results.get(0);

		PSIProfile profile = new PSIProfile();
		profile.setId(((BigDecimal) result[0]).intValueExact());
		profile.setName(((String) result[1]).trim());
		profile.setTargetCompleteDate((Date) result[2]);
		profile.setType(((String) result[3]).trim());
		profile.setStatus(((String) result[4]).trim());
		profile.setLegalText(((String) result[5]).trim());
		profile.setHeaderId(((BigDecimal) result[6]).intValueExact());
		profile.setDealer(((BigDecimal) result[7]).intValueExact());
		profile.setEmail(((String) result[8]).trim());
		profile.setSubmittedDate((Date) result[9]); 
		profile.setApprovedDate((Date) result[10]);
		
		entityManager.close();
		
		return profile;
	}
}
