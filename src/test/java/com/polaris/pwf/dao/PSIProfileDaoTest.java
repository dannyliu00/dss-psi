package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSIProfile;

public class PSIProfileDaoTest {

	private PSIProfileDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private BigDecimal expectedId, expectedHeaderId, expectedDealer;
	private Date expectedDate, expectedSubmittedDate, expectedApprovedDate;
	private String expectedName, expectedStatus, expectedType, expectedLegal, expectedEmail;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = new BigDecimal(999);
		expectedHeaderId = new BigDecimal(888);
		expectedDealer = new BigDecimal(777);
		
		expectedDate = Calendar.getInstance().getTime();
		expectedSubmittedDate = expectedDate;
		expectedApprovedDate = expectedDate;
		
		expectedName = "UT Name";
		expectedStatus = "UT Status";
		expectedType = "UT Type";
		expectedLegal = "UT Legal Text";
		expectedEmail = "ut@test.local";
		
		mockResult = new Object[11];
		mockResult[0] = expectedId;
		mockResult[1] = expectedName;
		mockResult[2] = expectedDate;
		mockResult[3] = expectedType;
		mockResult[4] = expectedStatus;
		mockResult[5] = expectedLegal;
		mockResult[6] = expectedHeaderId;
		mockResult[7] = expectedDealer;
		mockResult[8] = expectedEmail;
		mockResult[9] = expectedSubmittedDate;
		mockResult[10] = expectedApprovedDate;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);
		
		dao = new PSIProfileDao();
		dao.entityManager = mockEM;
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("dealerId", expectedId.intValue())).thenReturn(mockQuery);
		when(mockQuery.setParameter("canceled", 0)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
	}

	@Test
	public void testRetrieveListByDealerId() {
		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveListByDealerId(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", 0);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(results.size(), 1);
		PSIProfile result = results.get(0);
		assertEquals(expectedId.intValue(), result.getId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveProfileById() {
		Integer id = expectedId.intValue();
		PSIProfile result = dao.retrieveProfileById(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", id);
		verify(mockQuery).setMaxResults(1);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(expectedId.intValue(), result.getId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

}
