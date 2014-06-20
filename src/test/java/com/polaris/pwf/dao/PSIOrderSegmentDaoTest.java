package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
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

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfileDetail;

public class PSIOrderSegmentDaoTest {
	
	private PSIOrderSegmentDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private Integer expectedId, expectedProfileId, expectedDealerId, expectedSort, expectedComplianceId;
	private Integer expectedMin, expectedRec, expectedMax;
	private String expectedName, expectedSubSegment, expectedPeriodCode;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedProfileId = 999;
		expectedDealerId = 888;
		expectedComplianceId = 777;
		expectedId = 100;
		expectedSort = 1;
		expectedMin = 1;
		expectedRec = 3;
		expectedMax = 5;
		expectedName = "UT OS Name";
		expectedSubSegment = "UT SubSegment";
		expectedPeriodCode = "UT Period";

		mockResult = new Object[10];
		mockResult[0] = expectedId;
		mockResult[1] = expectedName;
		mockResult[2] = expectedSort;
		mockResult[3] = expectedSubSegment;
		mockResult[4] = expectedComplianceId;
		mockResult[5] = expectedPeriodCode;
		mockResult[6] = expectedDealerId;
		mockResult[7] = expectedMin;
		mockResult[8] = expectedRec;
		mockResult[9] = expectedMax;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);
		
		dao = new PSIOrderSegmentDao();
		dao.entityManager = mockEM;
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("profileId", expectedProfileId)).thenReturn(mockQuery);
		when(mockQuery.setParameter("dealerId", expectedDealerId)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
	}

	@Test
	public void testRetrieveByProfileAndDealer() {
		List<PSIOrderSegment> results = dao.retrieveByProfileAndDealer(expectedProfileId, expectedDealerId);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", expectedProfileId);
		verify(mockQuery).setParameter("dealerId", expectedDealerId);
		verify(mockQuery).getResultList();
		verify(mockEM).close();

		assertEquals(results.size(), 1);
		PSIOrderSegment result = results.get(0);
		assertEquals(expectedId, result.getId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedSort, result.getSort());
		assertEquals(expectedSubSegment, result.getSubSegment());
		assertEquals(expectedComplianceId, result.getComplianceId());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedDealerId, result.getDealerId());
		assertEquals(expectedMin, result.getRecMinimum());
		assertEquals(expectedRec, result.getRecommended());
		assertEquals(expectedMax, result.getRecMaximum());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

}
