package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

import com.polaris.psi.repository.entity.PSIOrderSegment;

public class PSIOrderSegmentDaoTest {
	
	private PSIOrderSegmentDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult, mockResult2;
	private List<Object[]> mockResults;
	private Integer expectedProfileId, expectedDealerId;
	private BigDecimal expectedId, expectedSort, expectedComplianceId, expectedMin, expectedRec, expectedMax, expectedPeriodId;
	private String expectedOsCode, expectedOsCode2, expectedOsName, expectedPeriodCode, expectedPeriodCode2;
	@Mock private PSIOrderSegment mockOrderSegment;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedProfileId = 999;
		expectedDealerId = 888;

		expectedComplianceId = new BigDecimal(777);
		expectedId = new BigDecimal(100);
		expectedSort = new BigDecimal(1);
		expectedMin = new BigDecimal(1);
		expectedRec = new BigDecimal(3);
		expectedMax = new BigDecimal(5);
		expectedPeriodId = new BigDecimal(10);
		expectedOsCode = "UT OS Name";
		expectedOsCode2 = "UT OS Name 2";
		expectedOsName = "UT SubSegment";
		expectedPeriodCode = "UT Period";
		expectedPeriodCode2 = "UT Period 2";

		mockResults = new ArrayList<Object[]>();
		
		mockResult = new Object[10];
		mockResult[0] = expectedId;
		mockResult[1] = expectedOsCode;
		mockResult[2] = expectedSort;
		mockResult[3] = expectedOsName;
		mockResult[4] = expectedComplianceId;
		mockResult[5] = expectedPeriodCode;
		mockResult[6] = expectedMin;
		mockResult[7] = expectedRec;
		mockResult[8] = expectedMax;
		mockResult[9] = expectedPeriodId;
		
		mockResult2 = new Object[10];
		mockResult2[0] = expectedId;
		mockResult2[1] = expectedOsCode2;
		mockResult2[2] = expectedSort;
		mockResult2[3] = expectedOsName;
		mockResult2[4] = expectedComplianceId;
		mockResult2[5] = expectedPeriodCode2;
		mockResult2[6] = expectedMin;
		mockResult2[7] = expectedRec;
		mockResult2[8] = expectedMax;
		mockResult2[9] = expectedPeriodId;
		
		mockResults.add(mockResult);
		mockResults.add(mockResult2);
		
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

		assertEquals(2, results.size());
		PSIOrderSegment result = results.get(0);
		assertEquals(expectedProfileId, result.getProfileId());
		assertEquals(expectedDealerId, result.getDealerId());
		assertEquals(expectedId.intValueExact(), result.getId().intValue());
		assertEquals(expectedOsCode, result.getOsCode());
		assertEquals(expectedSort.intValueExact(), result.getSort().intValue());
		assertEquals(expectedOsName, result.getOsName());
		assertEquals(expectedComplianceId.intValueExact(), result.getComplianceId().intValue());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedMin.intValueExact(), result.getRecMinimum().intValue());
		assertEquals(expectedRec.intValueExact(), result.getRecommended().intValue());
		assertEquals(expectedMax.intValueExact(), result.getRecMaximum().intValue());
		assertEquals(expectedPeriodId.intValueExact(), result.getPeriodId().intValue());
		
		PSIOrderSegment result2 = results.get(1);
		assertEquals(expectedProfileId, result2.getProfileId());
		assertEquals(expectedDealerId, result2.getDealerId());
		assertEquals(expectedId.intValueExact(), result2.getId().intValue());
		assertEquals(expectedOsCode2, result2.getOsCode());
		assertEquals(expectedSort.intValueExact(), result2.getSort().intValue());
		assertEquals(expectedOsName, result2.getOsName());
		assertEquals(expectedComplianceId.intValueExact(), result2.getComplianceId().intValue());
		assertEquals(expectedPeriodCode2, result2.getPeriodCode());
		assertEquals(expectedMin.intValueExact(), result2.getRecMinimum().intValue());
		assertEquals(expectedRec.intValueExact(), result2.getRecommended().intValue());
		assertEquals(expectedMax.intValueExact(), result2.getRecMaximum().intValue());
		assertEquals(expectedPeriodId.intValueExact(), result2.getPeriodId().intValue());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}
	
	@Test
	public void testRetrieveByProfileAndDealerSameResult() {
		mockResults = new ArrayList<Object[]>();
		
		mockResult = new Object[10];
		mockResult[0] = expectedId;
		mockResult[1] = expectedOsCode;
		mockResult[2] = expectedSort;
		mockResult[3] = expectedOsName;
		mockResult[4] = expectedComplianceId;
		mockResult[5] = expectedPeriodCode;
		mockResult[6] = expectedMin;
		mockResult[7] = expectedRec;
		mockResult[8] = expectedMax;
		mockResult[9] = expectedPeriodId;
		
		mockResult2 = new Object[10];
		mockResult2[0] = expectedId;
		mockResult2[1] = expectedOsCode;
		mockResult2[2] = expectedSort;
		mockResult2[3] = expectedOsName;
		mockResult2[4] = expectedComplianceId;
		mockResult2[5] = expectedPeriodCode;
		mockResult2[6] = expectedMin;
		mockResult2[7] = expectedRec;
		mockResult2[8] = expectedMax;
		mockResult2[9] = expectedPeriodId;
		
		mockResults.add(mockResult);
		mockResults.add(mockResult2);
		
		when(mockQuery.getResultList()).thenReturn(mockResults);
		
		List<PSIOrderSegment> results = dao.retrieveByProfileAndDealer(expectedProfileId, expectedDealerId);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", expectedProfileId);
		verify(mockQuery).setParameter("dealerId", expectedDealerId);
		verify(mockQuery).getResultList();
		verify(mockEM).close();

		assertEquals(1, results.size());
		PSIOrderSegment result = results.get(0);
		assertEquals(expectedProfileId, result.getProfileId());
		assertEquals(expectedDealerId, result.getDealerId());
		assertEquals(expectedId.intValueExact(), result.getId().intValue());
		assertEquals(expectedOsCode, result.getOsCode());
		assertEquals(expectedSort.intValueExact(), result.getSort().intValue());
		assertEquals(expectedOsName, result.getOsName());
		assertEquals(expectedComplianceId.intValueExact(), result.getComplianceId().intValue());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedMin.intValueExact(), result.getRecMinimum().intValue());
		assertEquals(expectedRec.intValueExact(), result.getRecommended().intValue());
		assertEquals(expectedMax.intValueExact(), result.getRecMaximum().intValue());
		assertEquals(expectedPeriodId.intValueExact(), result.getPeriodId().intValue());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}
	
	@Test
	public void testDoesListContainTrue() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		PSIOrderSegment testOs = new PSIOrderSegment();
		testOs.setOsCode(expectedOsCode);
		testOs.setPeriodCode(expectedPeriodCode);
		oses.add(testOs);

		when(mockOrderSegment.getOsCode()).thenReturn(expectedOsCode);
		when(mockOrderSegment.getPeriodCode()).thenReturn(expectedPeriodCode);
		
		boolean result = dao.doesListContain(oses, mockOrderSegment);
		
		assertTrue(result);
	}

	@Test
	public void testDoesListContainFalse() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		PSIOrderSegment testOs = new PSIOrderSegment();
		testOs.setOsCode(expectedOsCode + " unique");
		testOs.setPeriodCode(expectedPeriodCode + " unique");
		oses.add(testOs);

		when(mockOrderSegment.getOsCode()).thenReturn(expectedOsCode);
		when(mockOrderSegment.getPeriodCode()).thenReturn(expectedPeriodCode);
		
		boolean result = dao.doesListContain(oses, mockOrderSegment);
		
		assertFalse(result);
	}

}
