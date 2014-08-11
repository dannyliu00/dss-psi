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

import com.polaris.psi.repository.entity.SegmentStockingProfile;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Repository
public class SegmentStockingProfileDao extends AbstractPolarisMinneapolisDao<SegmentStockingProfile> {
	
	private static final SplunkLogger LOG = new SplunkLogger(SegmentStockingProfileDao.class);
	
	private static String QUERY_DLR_LINE = ""
			+ "SELECT dealer.PTCUST, dealer.PTSFAM, header.A1PRFN, detail.A2DTKN, oscode.A5OSEG, period.N0DESC, "
			+ "       period.N0SDAT, period.N0EDAT, orders.A3PRFN, orders.A3DTKN, orders.A3PPID, orders.A3RCMQ "
			+ "  FROM CM006F dealer INNER JOIN OT001F header ON header.A1PRFN = dealer.PTPRFN "
			+ "       INNER JOIN OT002F detail ON detail.A2PRFN = header.A1PRFN "
			+ "       INNER JOIN OT005F oscode ON UCASE(oscode.A5OSEG) = UCASE(detail.A2OSEG) "
			+ "       LEFT OUTER JOIN OT003F orders ON orders.A3PRFN = detail.A2PRFN AND orders.A3DTKN = detail.A2DTKN "
			+ "       LEFT OUTER JOIN OT070F period ON orders.A3PPID = period.N0PPID "
			+ " WHERE dealer.PTSFAM = :type "
			+ "   AND dealer.PTCUST = :dealerId";

	public SegmentStockingProfileDao() {
		super(SegmentStockingProfile.class);
	}
	
	/**
	 * Retrieves a list of segment stocking profiles for the given dealer ID and product line
	 * 
	 * @param dealerId
	 * @param productLine
	 * @return
	 */
	public List<SegmentStockingProfile> retrieveSegmentProfilesList(int dealerId, String productLine) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveSegmentProfilesList");
		
		Query query = entityManager.createNativeQuery(QUERY_DLR_LINE);
		query.setParameter("dealerId", dealerId);
		query.setParameter("type", productLine);
		
		LOG.trace(PolarisIdentity.get(), "retrieveSegmentProfilesList", "query to run: " 
				+ QUERY_DLR_LINE + " -- query paramters: dealerId = " + dealerId  + ", type = " + productLine);
		
		List<SegmentStockingProfile> stockingProfiles = new ArrayList<SegmentStockingProfile>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				SegmentStockingProfile profile = new SegmentStockingProfile();
				profile.setDealerId(((BigDecimal) result[0]).intValueExact()); 
				profile.setProductLine(CommonUtils.trimString((String) result[1]));
				profile.setProfileCode(CommonUtils.trimString((String) result[2]));
				profile.setSegmentCode(CommonUtils.trimString((String) result[3]));
				profile.setOrderSegmentCode(CommonUtils.trimString((String) result[4]));
				profile.setPeriodCode(CommonUtils.trimString((String) result[5]));
				profile.setPeriodStartDate(CommonUtils.setDate((Date) result[6]));
				profile.setPeriodEndDate(CommonUtils.setDate((Date) result[7]));
				profile.setStockingProfileProfileCode(CommonUtils.trimString((String) result[8]));
				profile.setStockingProfileSegmentCode(CommonUtils.trimString((String) result[9]));
				profile.setStockingProfilePeriodId(((BigDecimal) result[10]).intValueExact());
				profile.setRecommendedQty(((BigDecimal) result[11]).intValueExact());
				
				stockingProfiles.add(profile);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveSegmentProfilesList", e.getMessage());
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveSegmentProfilesList", "entityManager closed");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveSegmentProfilesList");
		
        return stockingProfiles;
	}
}
