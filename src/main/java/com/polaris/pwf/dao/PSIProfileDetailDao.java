/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfileDetailDao extends AbstractPolarisMinneapolisDao<PSIProfileDetail> {

	private static final SplunkLogger LOG = new SplunkLogger(PSIProfileDetailDao.class);
	
	private static String QUERY_BY_HEADER = ""
			+ "SELECT header.N7DHID, detail.N8DDID, detail.N8PSID, detail.N8DQTY, "
			+ "detail.N8DCOD, trim(detail.N8DCOM), detail.N8SQTY, detail.N8SCOD, trim(detail.N8SCOM), detail.N8AQTY, "
			+ "detail.N8ACOD, trim(detail.N8ACOM), detail.N8QTY, N8CODE "
			+ "  FROM OT077F header "
			+ "  LEFT OUTER JOIN OT078F detail ON detail.N8DHID = header.N7DHID "
			+ " WHERE header.N7DHID = :headerId";

	public PSIProfileDetailDao() {
		super(PSIProfileDetail.class);
	}
	
	/**
	 * Retrieves the details of an existing dealer inventory profile based on the header ID
	 * 
	 * @param headerId
	 * @return
	 */
	public List<PSIProfileDetail> retrieveByHeaderId(Integer headerId) {
		
		LOG.methodStart(PolarisIdentity.get(), "retrieveByHeaderId");
		
		Query query = entityManager.createNativeQuery(QUERY_BY_HEADER);
		query.setParameter("headerId", headerId);
		
		LOG.trace(PolarisIdentity.get(), "retrieveByHeaderId", "query to run: " + QUERY_BY_HEADER 
				+ " query paramters: headerId = " + headerId);
		
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			
			for (Object[] result : results) {
				PSIProfileDetail detail = new PSIProfileDetail();
				detail.setHeaderId(CommonUtils.convertToInteger((BigDecimal) result[0]));
				detail.setId(CommonUtils.convertToInteger((BigDecimal) result[1]));
				detail.setProfileOrderSegmentId(CommonUtils.convertToInteger((BigDecimal) result[2]));
				detail.setRequestedQty(CommonUtils.convertToInteger((BigDecimal) result[3]));
				detail.setReasonCode(CommonUtils.convertToInteger((BigDecimal) result[4]));
				detail.setDealerComments(CommonUtils.trimString((String) result[5]));
				detail.setDsmQty(CommonUtils.convertToInteger((BigDecimal) result[6]));
				detail.setDsmReasonCode(CommonUtils.convertToInteger((BigDecimal) result[7]));
				detail.setDsmComments(CommonUtils.trimString((String) result[8]));
				detail.setAdminQty(CommonUtils.convertToInteger((BigDecimal) result[9]));
				detail.setAdminReasonCode(CommonUtils.convertToInteger((BigDecimal) result[10]));
				detail.setAdminComments(CommonUtils.trimString((String) result[11]));
				detail.setFinalQty(CommonUtils.convertToInteger((BigDecimal) result[12]));
				detail.setPeriodCode(CommonUtils.trimString((String) result[13]));
				
				details.add(detail);
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "retrieveByHeaderId", e);
		} finally {
			entityManager.close();
			LOG.trace(PolarisIdentity.get(), "retrieveByHeaderId", "closed entityManager");
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "retrieveByHeaderId");
		
		return details;
	}
	
}
