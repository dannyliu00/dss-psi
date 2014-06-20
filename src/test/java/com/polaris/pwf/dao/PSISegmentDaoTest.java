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

import com.polaris.psi.repository.entity.PSISegment;

public class PSISegmentDaoTest {

	private PSISegmentDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private Integer expectedId, expectedProfileId, expectedDealerId, expectedMin, expectedMax;
	private String expectedPeriodCode, expectedName, expectedSubSegment, expectedType;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 100;
		expectedProfileId = 999;
		expectedDealerId = 888;
		expectedMin = 1;
		expectedMax = 5;
		expectedPeriodCode = "UT period code";
		expectedName = "UT Name";
		expectedSubSegment = "UT SubSegment";
		
		mockResult = new Object[6];
		mockResult[0] = expectedId;
		mockResult[1] = expectedPeriodCode;
		mockResult[2] = expectedName;
		mockResult[3] = expectedMin;
		mockResult[4] = expectedMax;
		mockResult[5] = expectedSubSegment;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);
		
		dao = new PSISegmentDao();
		dao.entityManager = mockEM;
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("profileId", expectedProfileId)).thenReturn(mockQuery);
		when(mockQuery.setParameter("dealerId", expectedDealerId)).thenReturn(mockQuery);
		when(mockQuery.setParameter("type", expectedType)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
	}

	@Test
	public void testRetrieveByProfileDealerAndType() {
		List<PSISegment> results = dao.retrieveByProfileDealerAndType(expectedProfileId, expectedDealerId, expectedType);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", expectedProfileId);
		verify(mockQuery).setParameter("dealerId", expectedDealerId);
		verify(mockQuery).setParameter("type", expectedType);
		verify(mockQuery).getResultList();
		verify(mockEM).close();

		assertEquals(results.size(), 1);
		PSISegment result = results.get(0);
		assertEquals(expectedId, result.getId());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedMin, result.getRecMinimum());
		assertEquals(expectedMax, result.getRecMaximum());
		assertEquals(expectedSubSegment, result.getSubSegment());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

}
