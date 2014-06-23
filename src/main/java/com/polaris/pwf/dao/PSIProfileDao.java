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
	// AS/400 data source
	private static String QUERY_BY_DEALER_AND_CANCELED = ""
			+ "SELECT profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, CAST( CAST(status.N9DESC AS CHAR(50)) AS VARCHAR(50)) "
			+ "FROM cm006f dealer JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "LEFT OUTER JOIN ot077f header ON header.N7IPID = profile.N1IPID AND header.N7DLR  = dealer.ptcust "
			+ "INNER JOIN ot079f status ON header.N7STID = status.N9STID "
			+ "WHERE dealer.ptcust = :dealerId "
			+ "AND dealer.PTCANDT= :canceled";
// SQL Server (DealersExtension) data source
//	private static String QUERY_BY_DEALER_AND_CANCELED = ""
//			+ "SELECT profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, status.N9DESC "
//			+ "FROM Profile.cm006f dealer JOIN Profile.ot071f_Profile profile ON profile.N1PDLN = dealer.PTSFAM "
//			+ "LEFT OUTER JOIN Profile.ot077f_DealerProfileHeader header ON header.N7IPID = profile.N1IPID AND header.N7DLR  = dealer.ptcust "
//			+ "INNER JOIN Profile.ot079f_DealerProfileHeaderStatus status ON header.N7STID = status.N9STID "
//			+ "WHERE dealer.ptcust = :dealerId "
//			+ "AND dealer.PTCANDT= :canceled";
	
// AS/400 data source
	private static String QUERY_BY_ID = ""
			+ "select profile.N1IPID, profile.n1DESC, profile.N1TDAT, profile.N1PDLN, CAST( CAST(status.N9DESC AS CHAR(50)) AS VARCHAR(50), profile.N1LGLT, "
			+ "header.N7DHID, header.N7DLR, header.N7MAIL, header.N7SBDT, header.N7APDT "
			+ "from Profile.OT071F_Profile profile left outer join Profile.OT077F_DealerProfileHeader header on header.N7IPID = profile.N1IPID "
			+ "left outer join Profile.OT079F_DealerProfileHeaderStatus status on header.N7STID = status.N9STID "
			+ "where profile.N1IPID = :profileId";
// SQL Server (DealersExtension) data source
//	private static String QUERY_BY_ID = ""
//			+ "select profile.N1IPID, profile.n1DESC, profile.N1TDAT, profile.N1PDLN, status.N9DESC, profile.N1LGLT, "
//			+ "header.N7DHID, header.N7DLR, header.N7EMAIL, header.N7SBDT, header.N7APDT "
//			+ "from Profile.OT071F_Profile profile left outer join Profile.OT077F_DealerProfileHeader header on header.N7IPID = profile.N1IPID "
//			+ "left outer join Profile.OT079F_DealerProfileHeaderStatus status on header.N7STID = status.N9STID "
//			+ "where profile.N1IPID = :profileId";

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
			// AS/400 data source
			profile.setId(((BigDecimal) result[0]).intValueExact());
			// SQL Server (Dealers Extension) data source
			// profile.setId((Integer) result[0]);
			profile.setName(trimString((String) result[1]));
			// AS/400 data source
			profile.setTargetCompleteDate((Date) result[2]);
			// SQL Server (Dealers Extension) data source
			// profile.setTargetCompleteDate(convertDate((String) result[2]));
			profile.setType(trimString((String) result[3]));
			profile.setStatus(trimString((String) result[4]));
			
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
		// AS/400 data source
		profile.setId(((BigDecimal) result[0]).intValueExact());
		// SQL Server (Dealers Extension) data source
//		profile.setId((Integer) result[0]);
		profile.setName(trimString((String) result[1]));
		// AS/400 data source
		profile.setTargetCompleteDate((Date) result[2]);
		// SQL Server (Dealers Extension) data source
//		profile.setTargetCompleteDate(convertDate((String) result[2]));
		profile.setType(trimString((String) result[3]));
		profile.setStatus(trimString((String) result[4]));
		profile.setLegalText(trimString((String) result[5]));
		// AS/400 data source
		profile.setHeaderId(((BigDecimal) result[6]).intValueExact());
		// SQL Server (Dealers Extension) data source
//		profile.setHeaderId((Integer) result[6]);
		// AS/400 data source
		profile.setDealer(((BigDecimal) result[7]).intValueExact());
//		profile.setDealer((Integer) result[7]);
		profile.setEmail(trimString((String) result[8]));
		// AS/400 data source
		profile.setSubmittedDate((Date) result[9]); 
		profile.setApprovedDate((Date) result[10]);
		// SQL Server (Dealers Extension) data source
//		profile.setSubmittedDate(convertDate((String) result[9])); 
//		profile.setApprovedDate(convertDate((String) result[10]));
		
		entityManager.close();
		
		return profile;
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
