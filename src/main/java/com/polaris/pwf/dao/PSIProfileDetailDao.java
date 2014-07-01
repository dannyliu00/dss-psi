/**
 * 
 */
package com.polaris.pwf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfileDetailDao extends AbstractPolarisMinneapolisDao<PSIProfileDetail> {

	private static Logger LOG = Logger.getLogger(PSIProfileDetailDao.class);

	private static String QUERY_BY_HEADER = ""
			+ "SELECT header.N7DHID, detail.N8DDID, detail.N8PSID, detail.N8DQTY, "
			+ "detail.N8DCOD, detail.N8DCOM, detail.N8SQTY, detail.N8SCOD, detail.N8SCOM, detail.N8AQTY, "
			+ "detail.N8ACOD, detail.N8ACOM, detail.N8QTY "
			+ "  FROM OT077F header "
			+ "  LEFT OUTER JOIN OT078F detail ON detail.N8DHID = header.N7DHID "
			+ " WHERE header.N7DHID = :headerId";

	public PSIProfileDetailDao() {
		super(PSIProfileDetail.class);
	}
	
	public List<PSIProfileDetail> retrieveByHeaderId(Integer headerId) {
		Query query = entityManager.createNativeQuery(QUERY_BY_HEADER);
		query.setParameter("headerId", headerId);
		
		if(LOG.isTraceEnabled()) {
			LOG.trace("query to run: " + QUERY_BY_HEADER);
			LOG.trace("query paramters: headerId = " + headerId);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		
		for (Object[] result : results) {
			PSIProfileDetail detail = new PSIProfileDetail();
			detail.setHeaderId(CommonUtils.convertToInteger((BigDecimal) result[0]));
			detail.setId(CommonUtils.convertToInteger((BigDecimal) result[1]));
			detail.setProfileOrderSegmentId(CommonUtils.convertToInteger((BigDecimal) result[2]));
			detail.setRequestedQty(CommonUtils.convertToInteger((BigDecimal) result[3]));
			detail.setReasonCode(CommonUtils.convertToInteger((BigDecimal) result[4]));
			detail.setDealerComments(CommonUtils.trimString(convertToString(result[5])));
			detail.setDsmQty(CommonUtils.convertToInteger((BigDecimal) result[6]));
			detail.setDsmReasonCode(CommonUtils.convertToInteger((BigDecimal) result[7]));
			detail.setDsmComments(CommonUtils.trimString(convertToString(result[8])));
			detail.setAdminQty(CommonUtils.convertToInteger((BigDecimal) result[9]));
			detail.setAdminReasonCode(CommonUtils.convertToInteger((BigDecimal) result[10]));
			detail.setAdminComments(CommonUtils.trimString(convertToString(result[11])));
			detail.setFinalQty(CommonUtils.convertToInteger((BigDecimal) result[12]));
			
			details.add(detail);
		}
		
		entityManager.close();
		
		return details;
	}
	
	public PSIProfileDetail retrieveById(Integer detailId) {
		
		return null;
	}
	
	protected String convertToString(Object obj) {
		try {
			Character c = (Character) obj;
			return c.toString();
		} catch (ClassCastException cce) {
			LOG.info("Object parameter failed to be cast as a Character");
			return (String) obj;
		}
	}
	
}
