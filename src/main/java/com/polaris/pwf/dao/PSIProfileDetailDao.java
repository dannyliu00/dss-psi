/**
 * 
 */
package com.polaris.pwf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.PSIProfileDetail;

/**
 * @author bericks
 *
 */
@Repository
public class PSIProfileDetailDao extends AbstractPolarisDealersExtensionDao<PSIProfileDetail> {

	private static Logger LOG = Logger.getLogger(PSIProfileDetailDao.class);

	private static String QUERY_BY_HEADER = ""
			+ "select header.N7DHID, detail.N8DDID, detail.N8PSID, detail.N8DQTY, "
			+ "detail.N8DCOD, detail.N8DCOM, detail.N8SQTY, detail.N8SCOD, detail.N8SCOM, detail.N8AQTY, "
			+ "detail.N8ACOD, detail.N8ACOM, detail.N8QTY "
			+ "from OT077F header "
			+ "left outer join OT078F detail on detail.N8DHID = header.N7DHID "
			+ "where header.N7DHID = :headerId";

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
			detail.setHeaderId((Integer) result[0]);
			detail.setId((Integer) result[1]);
			detail.setProfileOrderSegmentId((Integer) result[2]);
			detail.setRequestedQty((Integer) result[3]);
			detail.setReasonCode((Integer) result[4]);
			detail.setDealerComments(((String) result[5]).trim());
			detail.setDsmQty((Integer) result[6]);
			detail.setDsmReasonCode((Integer) result[7]);
			detail.setDsmComments(((String) result[8]).trim());
			detail.setAdminQty((Integer) result[9]);
			detail.setAdminReasonCode((Integer) result[10]);
			detail.setAdminComments(((String) result[11]).trim());
			detail.setFinalQty((Integer) result[12]);
			
			details.add(detail);
		}
		
		entityManager.close();
		
		return details;
	}
	
	public PSIProfileDetail retrieveById(Integer detailId) {
		
		return null;
	}
	
}
