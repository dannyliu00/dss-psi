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

import com.polaris.psi.repository.entity.PSIOrderSegment;

public class PSIOrderSegmentDaoTest {
	
	private PSIOrderSegmentDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private Integer expectedProfileId, expectedDealerId;
	private BigDecimal expectedId, expectedSort, expectedComplianceId, expectedMin, expectedRec, expectedMax;
	private String expectedName, expectedSubSegment, expectedPeriodCode;
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
		expectedName = "UT OS Name";
		expectedSubSegment = "UT SubSegment";
		expectedPeriodCode = "UT Period";

		mockResult = new Object[9];
		mockResult[0] = expectedId;
		mockResult[1] = expectedName;
		mockResult[2] = expectedSort;
		mockResult[3] = expectedSubSegment;
		mockResult[4] = expectedComplianceId;
		mockResult[5] = expectedPeriodCode;
		mockResult[6] = expectedMin;
		mockResult[7] = expectedRec;
		mockResult[8] = expectedMax;
		
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
		assertEquals(expectedProfileId, result.getProfileId());
		assertEquals(expectedDealerId, result.getDealerId());
		assertEquals(expectedId.intValueExact(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedSort.intValueExact(), result.getSort().intValue());
		assertEquals(expectedSubSegment, result.getSubSegment());
		assertEquals(expectedComplianceId.intValueExact(), result.getComplianceId().intValue());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedMin.intValueExact(), result.getRecMinimum().intValue());
		assertEquals(expectedRec.intValueExact(), result.getRecommended().intValue());
		assertEquals(expectedMax.intValueExact(), result.getRecMaximum().intValue());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}
	
	@Test
	public void testDoesListContainTrue() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		PSIOrderSegment testOs = new PSIOrderSegment();
		testOs.setName(expectedName);
		testOs.setPeriodCode(expectedPeriodCode);
		oses.add(testOs);

		when(mockOrderSegment.getName()).thenReturn(expectedName);
		when(mockOrderSegment.getPeriodCode()).thenReturn(expectedPeriodCode);
		
		boolean result = dao.doesListContain(oses, mockOrderSegment);
		
		assertEquals(true, result);
	}

	@Test
	public void testDoesListContainFalse() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		PSIOrderSegment testOs = new PSIOrderSegment();
		testOs.setName(expectedName + " unique");
		testOs.setPeriodCode(expectedPeriodCode + " unique");
		oses.add(testOs);

		when(mockOrderSegment.getName()).thenReturn(expectedName);
		when(mockOrderSegment.getPeriodCode()).thenReturn(expectedPeriodCode);
		
		boolean result = dao.doesListContain(oses, mockOrderSegment);
		
		assertEquals(false, result);
	}

}
