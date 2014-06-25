package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
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

import com.polaris.psi.repository.entity.PSIProfilePeriod;

public class PSIProfilePeriodDaoTest {

	private PSIProfilePeriodDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private BigDecimal expectedId, expectedSort;
	private Integer profileId;
	private String expectedPeriodCode, expectedName;
	private Date expectedStartDate, expectedEndDate;
	@Mock private PSIProfilePeriod mockPeriod;
	private List<Object[]> mockResults;
	private Object[] mockResult;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = new BigDecimal(999);
		expectedSort = new BigDecimal(1);
		profileId = 88;
		expectedPeriodCode = "UT PeriodCode";
		expectedName = "UT Name";
		Calendar cal = Calendar.getInstance();
		expectedStartDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 5);
		expectedEndDate = cal.getTime();
		
		mockResult = new Object[6];
		mockResult[0] = expectedId;
		mockResult[1] = expectedPeriodCode;
		mockResult[2] = expectedName;
		mockResult[3] = expectedStartDate;
		mockResult[4] = expectedEndDate;
		mockResult[5] = expectedSort;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("profileId", profileId)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
		
		dao = new PSIProfilePeriodDao();
		dao.entityManager = mockEM;
		
	}

	@Test
	public void testRetrieveByProfileId() {
		List<PSIProfilePeriod> results = dao.retrieveByProfileId(profileId);
		
		assertEquals(1, results.size());
		PSIProfilePeriod result = results.get(0);
		assertEquals(expectedEndDate, result.getEndDate());
		assertEquals(expectedId.intValueExact(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		assertEquals(expectedSort.intValueExact(), result.getSort().intValue());
		assertEquals(expectedStartDate, result.getStartDate());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", profileId);
		verify(mockQuery).getResultList();
		
	}

}
