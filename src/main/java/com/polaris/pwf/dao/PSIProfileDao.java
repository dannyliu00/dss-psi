/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.BooleanUtils;
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

	private static String QRY_DLR_CURRENT = ""
			+ "SELECT distinct pstatus.N2DESC, profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, "
			+ "  CAST( CAST(header.N9DESC AS CHAR(50)) AS VARCHAR(50)) as N9DESC, header.N7NFLG "
			+ "  FROM cm006f dealer INNER JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "  INNER JOIN ot072f pstatus ON pstatus.N2STID = profile.N1STID "
		    + "  INNER JOIN ot075f osComp ON osComp.N5IPID = profile.N1IPID "
		    + "  LEFT OUTER JOIN (SELECT header.N7DLR, header.N7NFLG, status.N9DESC "
		    + "  FROM ot077f header INNER JOIN ot079f status ON status.N9STID = header.N7STID) header ON header.N7DLR = osComp.N5DLR "
			+ " WHERE osComp.N5DLR = :dealerId AND dealer.PTCANDT= :canceled ";
	private static String QUERY_BY_ID = ""
			+ "SELECT distinct pstatus.N2DESC, profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, "
			+ "       CAST( CAST(header.N9DESC AS CHAR(50)) AS VARCHAR(50)) as N9DESC, header.N7NFLG, profile.N1LGLT, "
			+ "       header.N7DHID, header.N7DLR, header.N7MAIL, header.N7SBDT, header.N7APDT, header.N7CRDT, header.N7CHDT "
			+ "  FROM OT071F profile INNER JOIN OT072F pstatus ON pstatus.N2STID = profile.N1STID "
			+ "       INNER JOIN ot075f osComp ON osComp.N5IPID = profile.N1IPID "
			+ "       LEFT OUTER JOIN (SELECT header.N7DHID, header.N7DLR, header.N7NFLG, header.N7MAIL, header.N7SBDT, header.N7APDT, header.N7CRDT, header.N7CHDT, status.N9DESC "
			+ "	                         FROM ot077f header INNER JOIN ot079f status ON status.N9STID = header.N7STID) header ON header.N7DLR = osComp.N5DLR "
			+ " WHERE profile.N1IPID = :profileId "
			+ "   AND osComp.N5DLR = :dealerId" ;

	public PSIProfileDao() {
		super(PSIProfile.class);
	}
	
	public List<PSIProfile> retrieveDealerCurrentProfileListByDealerId(Integer dealerId) {
		Query query = entityManager.createNativeQuery(QRY_DLR_CURRENT);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_DLR_CURRENT);
			LOG.trace("query paramters: dealerId = " + dealerId + ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<PSIProfile> profiles = new ArrayList<PSIProfile>();
		for (Object[] result : results) {
			PSIProfile profile = new PSIProfile();
			profile.setProfileStatus(CommonUtils.trimString((String) result[0]));
			profile.setId(((BigDecimal) result[1]).intValueExact());
			profile.setName(CommonUtils.trimString((String) result[2]));
			profile.setTargetCompleteDate((Date) result[3]);
			profile.setType(CommonUtils.trimString((String) result[4]));
			profile.setStatus(CommonUtils.trimString((String) result[5]));
			profile.setNonCompliant(BooleanUtils.toBoolean(CommonUtils.convertToInt((BigDecimal) result[6])));
			
			String status = profile.getProfileStatus();
			if(status.equals(Constants.ACTIVE)) profiles.add(profile);
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public List<PSIProfile> retrieveDsmCurrentProfileListByDealerId(Integer dealerId) {
		Query query = entityManager.createNativeQuery(QRY_DLR_CURRENT);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_DLR_CURRENT);
			LOG.trace("query paramters: dealerId = " + dealerId + ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<PSIProfile> profiles = new ArrayList<PSIProfile>();
		for (Object[] result : results) {
			PSIProfile profile = new PSIProfile();
			profile.setProfileStatus(CommonUtils.trimString((String) result[0]));
			profile.setId(((BigDecimal) result[1]).intValueExact());
			profile.setName(CommonUtils.trimString((String) result[2]));
			profile.setTargetCompleteDate((Date) result[3]);
			profile.setType(CommonUtils.trimString((String) result[4]));
			profile.setStatus(CommonUtils.trimString((String) result[5]));
			profile.setNonCompliant(BooleanUtils.toBoolean(CommonUtils.convertToInt((BigDecimal) result[6])));
			
			String status = profile.getStatus();
			if(status != null && (status.equals(Constants.PENDING_STATUS) || status.equals(Constants.RETURNED_TO_DSM))) {
				profiles.add(profile);
			}
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public PSIProfile retrieveProfileById(Integer profileId, Integer dealerId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_ID);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
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
		profile.setProfileStatus(CommonUtils.trimString((String) result[0]));
		profile.setId(CommonUtils.convertToInteger((BigDecimal) result[1]));
		profile.setName(CommonUtils.trimString((String) result[2]));
		profile.setTargetCompleteDate((Date) result[3]);
		profile.setType(CommonUtils.trimString((String) result[4]));
		profile.setStatus(CommonUtils.trimString((String) result[5]));
		profile.setNonCompliant(BooleanUtils.toBoolean(CommonUtils.convertToInt((BigDecimal) result[6])));
		profile.setLegalText(CommonUtils.trimString((Character) result[7]));
		profile.setHeaderId(CommonUtils.convertToInteger((BigDecimal) result[8]));
		profile.setDealer(CommonUtils.convertToInteger((BigDecimal) result[9]));
		profile.setEmail(CommonUtils.trimString((String) result[10]));
		profile.setSubmittedDate((Date) result[11]); 
		profile.setApprovedDate((Date) result[12]);
		profile.setLastModifiedDate(result[14] != null ? (Date) result[14] : (Date) result[13]);
		
		entityManager.close();
		
		return profile;
	}
}
