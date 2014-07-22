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

	private static String QRY_BY_DLR_STATUS = ""
			+ "SELECT pstatus.N2DESC, profile.N1IPID, trim(profile.N1DESC), profile.N1TDAT, profile.N1PDLN, Trim(status.N9DESC), header.N7NFLG "
			+ "  FROM cm006f dealer INNER JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "       INNER JOIN ot072f pstatus ON pstatus.N2STID = profile.N1STID "
			+ "       LEFT OUTER JOIN ot077f header ON header.N7DLR = ptcust AND header.N7IPID = profile.N1IPID "
			+ "       LEFT OUTER JOIN ot079f status ON status.N9STID = header.N7STID "
			+ " WHERE dealer.ptcust = :dealerId "
			+ "   AND dealer.ptcandt = :canceled "
			+ "   AND pstatus.N2DESC = :status "
			+ "   AND EXISTS (SELECT * FROM ot075f where N5IPID = N1IPID and N5DLR = PTCUST)";
	private static String QRY_BY_DLR_AND_TYPE = ""
			+ "SELECT pstatus.N2DESC, profile.N1IPID, trim(profile.N1DESC), profile.N1TDAT, profile.N1PDLN, Trim(status.N9DESC), header.N7NFLG "
			+ "  FROM cm006f dealer INNER JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "       INNER JOIN ot072f pstatus ON pstatus.N2STID = profile.N1STID "
			+ "       LEFT OUTER JOIN ot077f header ON header.N7DLR = ptcust AND header.N7IPID = profile.N1IPID "
			+ "       LEFT OUTER JOIN ot079f status ON status.N9STID = header.N7STID "
			+ " WHERE dealer.ptcust = :dealerId "
			+ "   AND dealer.ptcandt = :canceled "
			+ "   AND pstatus.N2DESC = :status "
			+ "   AND profile.N1PDLN = :type "
			+ "   AND EXISTS (SELECT * FROM ot075f where N5IPID = N1IPID and N5DLR = PTCUST)";
	private static String QUERY_BY_DLR_PROFILE_IDS = ""
			+ "SELECT pstatus.N2DESC, profile.N1IPID, profile.N1DESC, profile.N1TDAT, profile.N1PDLN, trim(status.N9DESC), "
			+ "       header.N7NFLG, profile.N1LGLT, header.N7DHID, header.N7DLR, header.N7MAIL, header.N7SBDT, "
			+ "       header.N7APDT, header.N7CRDT, header.N7CHDT "
			+ "  FROM cm006f dealer INNER JOIN ot071f profile ON profile.N1PDLN = dealer.PTSFAM "
			+ "       INNER JOIN ot072f pstatus ON pstatus.N2STID = profile.N1STID "
			+ "       LEFT OUTER JOIN ot077f header ON header.N7DLR = ptcust AND header.N7IPID = profile.N1IPID "
			+ "       LEFT OUTER JOIN ot079f status ON status.N9STID = header.N7STID "
			+ " WHERE dealer.ptcust = :dealerId "
			+ "   AND dealer.ptcandt = :canceled "
			+ "   AND profile.N1IPID = :profileId "
			+ "   AND EXISTS (SELECT * FROM ot075f where N5IPID = N1IPID and N5DLR = PTCUST)";

	public PSIProfileDao() {
		super(PSIProfile.class);
	}
	
	public List<PSIProfile> retrieveDealerCurrentProfileListByDealerId(Integer dealerId) {
		Query query = entityManager.createNativeQuery(QRY_BY_DLR_STATUS);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		query.setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_BY_DLR_STATUS);
			LOG.trace("query paramters: dealerId = " + dealerId 
					+ ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE 
					+ ", status = " + Constants.ACTIVE_PROFILE_STATUS);
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
			
			profiles.add(profile);
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public List<PSIProfile> retrieveDealerHistoryProfileListByDealerId(Integer dealerId) {
		Query query = entityManager.createNativeQuery(QRY_BY_DLR_STATUS);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		query.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_BY_DLR_STATUS);
			LOG.trace("query paramters: dealerId = " + dealerId 
					+ ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE
					+ ", status = " + Constants.HISTORICAL_PROFILE_STATUS);
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
			
			profiles.add(profile);
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public List<PSIProfile> retrieveDsmCurrentProfileListByDealerId(Integer dealerId, String type) {
		Query query = entityManager.createNativeQuery(QRY_BY_DLR_AND_TYPE);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		query.setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
		query.setParameter("type", type);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_BY_DLR_AND_TYPE);
			LOG.trace("query paramters: dealerId = " + dealerId 
					+ ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE
					+ ", status = " + Constants.ACTIVE_PROFILE_STATUS
					+ ", type = " + type);
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
	
	public List<PSIProfile> retrieveDsmHistoryProfileListByDealerId(Integer dealerId, String type) {
		Query query = entityManager.createNativeQuery(QRY_BY_DLR_AND_TYPE);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		query.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		query.setParameter("type", type);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QRY_BY_DLR_AND_TYPE);
			LOG.trace("query paramters: dealerId = " + dealerId 
					+ ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE
					+ ", status = " + Constants.HISTORICAL_PROFILE_STATUS
					+ ", type = " + type);
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
			
			profiles.add(profile);
		}
		
		entityManager.close();
		
        return profiles;
	}
	
	public PSIProfile retrieveProfileById(Integer profileId, Integer dealerId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_DLR_PROFILE_IDS);
		query.setParameter("profileId", profileId);
		query.setParameter("dealerId", dealerId);
		query.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_DLR_PROFILE_IDS);
			LOG.trace("query paramters: profileId = " + profileId + ", dealerId = " + dealerId 
					+ ", canceled = " + Constants.DEALER_NOT_CANCELED_CODE);
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
