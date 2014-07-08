package com.polaris.pwf.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import org.apache.commons.lang.BooleanUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.PSIProfile;

public class PSIProfileDaoTest {

	private PSIProfileDao dao;
	@Mock private EntityManager mockEM;
	@Mock private Query mockQuery;
	private Object[] mockResult;
	private List<Object[]> mockResults;
	private BigDecimal expectedId, expectedHeaderId, expectedDealer, expectedNonCompliant;
	private Date expectedDate, expectedSubmittedDate, expectedApprovedDate, expectedCreatedDate, expectedChangedDate;
	private Character expectedLegal;
	private String expectedName, expectedProfileStatus, expectedStatus, expectedType, expectedEmail;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = new BigDecimal(999);
		expectedHeaderId = new BigDecimal(888);
		expectedDealer = new BigDecimal(777);
		expectedNonCompliant = new BigDecimal(0);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		expectedDate = cal.getTime();
		expectedSubmittedDate = expectedDate;
		expectedApprovedDate = expectedDate;
		
		expectedName = "UT Name";
		expectedProfileStatus = "UT Profile Status";
		expectedStatus = Constants.IN_PROGRESS_STATUS;
		expectedType = "UT Type";
		expectedLegal = new Character('c');
		expectedEmail = "ut@test.local";
		
		mockResult = new Object[15];
		mockResult[0] = expectedProfileStatus;
		mockResult[1] = expectedId;
		mockResult[2] = expectedName;
		mockResult[3] = expectedDate;
		mockResult[4] = expectedType;
		mockResult[5] = expectedStatus;
		mockResult[6] = expectedNonCompliant;
		mockResult[7] = expectedLegal;
		mockResult[8] = expectedHeaderId;
		mockResult[9] = expectedDealer;
		mockResult[10] = expectedEmail;
		mockResult[11] = expectedSubmittedDate;
		mockResult[12] = expectedApprovedDate;
		
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
	public void testRetrieveCurrentDealerListByDealerId() {
		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveCurrentDealerListByDealerId(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", 0);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(1, results.size());
		PSIProfile result = results.get(0);
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileStatus, result.getProfileStatus());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveProfileByIdWithCreatedDate() {
		expectedCreatedDate = Calendar.getInstance().getTime();
		mockResult[13] = expectedCreatedDate;
		mockResult[14] = null;
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);

		Integer id = expectedId.intValue();
		PSIProfile result = dao.retrieveProfileById(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", id);
		verify(mockQuery).setMaxResults(1);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(expectedProfileStatus, result.getProfileStatus());
		assertEquals(expectedLegal.toString(), result.getLegalText());
		assertEquals(expectedHeaderId.intValueExact(), result.getHeaderId().intValue());
		assertEquals(expectedDealer.intValueExact(), result.getDealer().intValue());
		assertEquals(expectedEmail, result.getEmail());
		assertEquals(expectedSubmittedDate, result.getSubmittedDate());
		assertEquals(expectedApprovedDate, result.getApprovedDate());
		assertEquals(expectedCreatedDate, result.getLastModifiedDate());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveProfileByIdWithChangedDate() {
		expectedCreatedDate = expectedChangedDate = Calendar.getInstance().getTime();
		mockResult[13] = expectedCreatedDate;
		mockResult[14] = expectedChangedDate;
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);

		Integer id = expectedId.intValue();
		PSIProfile result = dao.retrieveProfileById(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", id);
		verify(mockQuery).setMaxResults(1);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(expectedProfileStatus, result.getProfileStatus());
		assertEquals(expectedLegal.toString(), result.getLegalText());
		assertEquals(expectedHeaderId.intValueExact(), result.getHeaderId().intValue());
		assertEquals(expectedDealer.intValueExact(), result.getDealer().intValue());
		assertEquals(expectedEmail, result.getEmail());
		assertEquals(expectedSubmittedDate, result.getSubmittedDate());
		assertEquals(expectedApprovedDate, result.getApprovedDate());
		assertEquals(expectedChangedDate, result.getLastModifiedDate());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

}
