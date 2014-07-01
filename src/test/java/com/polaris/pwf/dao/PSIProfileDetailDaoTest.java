package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSIProfileDetail;

public class PSIProfileDetailDaoTest {

	private PSIProfileDetailDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private BigDecimal expectedId, expectedHeaderId, expectedProfileOrderSegmentId;
	private BigDecimal expectedRequestedQty, expectedDsmQty, expectedAdminQty, expectedFinalQty;
	private BigDecimal expectedReasonCode, expectedDsmReasonCode, expectedAdminReasonCode;
	private String expectedDealerComments, expectedDsmComments, expectedAdminComments;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = new BigDecimal(999);
		expectedHeaderId = new BigDecimal(888);
		expectedProfileOrderSegmentId = new BigDecimal(777);
		expectedRequestedQty = new BigDecimal(1);
		expectedDsmQty = new BigDecimal(2);
		expectedAdminQty = new BigDecimal(3);
		expectedFinalQty = new BigDecimal(3);
		expectedReasonCode = new BigDecimal(100);
		expectedDsmReasonCode = new BigDecimal(110);
		expectedAdminReasonCode = new BigDecimal(112);
		expectedDealerComments = "UT Dealer Comments";
		expectedDsmComments = "UT DSM Comments";
		expectedAdminComments = "UT Admin Comments";
		
		mockResult = new Object[13];
		mockResult[0] = expectedHeaderId;
		mockResult[1] = expectedId;
		mockResult[2] = expectedProfileOrderSegmentId;
		mockResult[3] = expectedRequestedQty;
		mockResult[4] = expectedReasonCode;
		mockResult[5] = expectedDealerComments;
		mockResult[6] = expectedDsmQty;
		mockResult[7] = expectedDsmReasonCode;
		mockResult[8] = expectedDsmComments;
		mockResult[9] = expectedAdminQty;
		mockResult[10] = expectedAdminReasonCode;
		mockResult[11] = expectedAdminComments;
		mockResult[12] = expectedFinalQty;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);
		
		dao = new PSIProfileDetailDao();
		dao.entityManager = mockEM;
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("headerId", expectedId)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
	}

	@Test
	public void testRetrieveByHeaderId() {
		List<PSIProfileDetail> results = dao.retrieveByHeaderId(expectedId.intValueExact());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("headerId", expectedId.intValueExact());
		verify(mockQuery).getResultList();
		verify(mockEM).close();

		assertEquals(results.size(), 1);
		PSIProfileDetail result = results.get(0);
		assertEquals(expectedId.intValueExact(), result.getId().intValue());
		assertEquals(expectedHeaderId.intValueExact(), result.getHeaderId().intValue());
		assertEquals(expectedProfileOrderSegmentId.intValueExact(), result.getProfileOrderSegmentId().intValue());
		assertEquals(expectedRequestedQty.intValueExact(), result.getRequestedQty().intValue());
		assertEquals(expectedDsmQty.intValueExact(), result.getDsmQty().intValue());
		assertEquals(expectedAdminQty.intValueExact(), result.getAdminQty().intValue());
		assertEquals(expectedFinalQty.intValueExact(), result.getFinalQty().intValue());
		assertEquals(expectedReasonCode.intValueExact(), result.getReasonCode().intValue());
		assertEquals(expectedDsmReasonCode.intValueExact(), result.getDsmReasonCode().intValue());
		assertEquals(expectedAdminReasonCode.intValueExact(), result.getAdminReasonCode().intValue());
		assertEquals(expectedDealerComments, result.getDealerComments());
		assertEquals(expectedDsmComments, result.getDsmComments());
		assertEquals(expectedAdminComments, result.getAdminComments());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveById() {
//		fail("Not yet implemented");
	}

}
