package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
	private Integer expectedId, expectedHeaderId, expectedProfileOrderSegmentId;
	private Integer expectedRequestedQty, expectedDsmQty, expectedAdminQty, expectedFinalQty;
	private Integer expectedReasonCode, expectedDsmReasonCode, expectedAdminReasonCode;
	private String expectedDealerComments, expectedDsmComments, expectedAdminComments;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedHeaderId = 888;
		expectedProfileOrderSegmentId = 777;
		expectedRequestedQty = 1;
		expectedDsmQty = 2;
		expectedAdminQty = 3;
		expectedFinalQty = 3;
		expectedReasonCode = 100;
		expectedDsmReasonCode = 110;
		expectedAdminReasonCode = 112;
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
		List<PSIProfileDetail> results = dao.retrieveByHeaderId(expectedId);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("headerId", expectedId);
		verify(mockQuery).getResultList();
		verify(mockEM).close();

		assertEquals(results.size(), 1);
		PSIProfileDetail result = results.get(0);
		assertEquals(expectedId, result.getId());
		assertEquals(expectedHeaderId, result.getHeaderId());
		assertEquals(expectedProfileOrderSegmentId, result.getProfileOrderSegmentId());
		assertEquals(expectedRequestedQty, result.getRequestedQty());
		assertEquals(expectedDsmQty, result.getDsmQty());
		assertEquals(expectedAdminQty, result.getAdminQty());
		assertEquals(expectedFinalQty, result.getFinalQty());
		assertEquals(expectedReasonCode, result.getReasonCode());
		assertEquals(expectedDsmReasonCode, result.getDsmReasonCode());
		assertEquals(expectedAdminReasonCode, result.getAdminReasonCode());
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
