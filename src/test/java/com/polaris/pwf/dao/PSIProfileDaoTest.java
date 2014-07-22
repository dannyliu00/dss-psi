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
		expectedProfileStatus = Constants.ACTIVE_PROFILE_STATUS;
		expectedStatus = Constants.IN_PROGRESS_STATUS;
		expectedType = "UT Type";
		expectedLegal = new Character('c');
		expectedEmail = "ut@test.local";
		
		dao = new PSIProfileDao();
		dao.entityManager = mockEM;
		
		when(mockEM.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("dealerId", expectedId.intValue())).thenReturn(mockQuery);
		when(mockQuery.setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE)).thenReturn(mockQuery);
		when(mockQuery.setParameter("type", expectedType)).thenReturn(mockQuery);
	}

	@Test
	public void testRetrieveDealerCurrentProfileListByDealerId() {
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
		
		when(mockQuery.setParameter("status", Constants.ACTIVE_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDealerCurrentProfileListByDealerId(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
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
	public void testRetrieveDealerCurrentProfileListByDealerIdNoResults() {
		when(mockQuery.setParameter("status", Constants.ACTIVE_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(new ArrayList<PSIProfile>());

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDealerCurrentProfileListByDealerId(id);
		
		assertEquals(0, results.size());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveDealerHistoryProfileListByDealerId() {
		mockResult = new Object[15];
		mockResult[0] = Constants.HISTORICAL_PROFILE_STATUS;
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

		when(mockQuery.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDealerHistoryProfileListByDealerId(id);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(1, results.size());
		PSIProfile result = results.get(0);
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(Constants.HISTORICAL_PROFILE_STATUS, result.getProfileStatus());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveDealerHistoryProfileListByDealerIdNoResults() {
		when(mockQuery.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(new ArrayList<PSIProfile>());

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDealerHistoryProfileListByDealerId(id);
		
		assertEquals(0, results.size());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	public void testRetrieveProfileByIdWithCreatedDate() {
		expectedCreatedDate = Calendar.getInstance().getTime();
		mockResult = new Object[15];
		mockResult[0] = Constants.HISTORICAL_PROFILE_STATUS;
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
		mockResult[13] = expectedCreatedDate;
		mockResult[14] = null;
		
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);

		when(mockQuery.getResultList()).thenReturn(mockResults);

		Integer id = expectedId.intValue();
		Integer dealerId = expectedDealer.intValue();
		PSIProfile result = dao.retrieveProfileById(id, dealerId);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", id);
		verify(mockQuery).setParameter("dealerId", dealerId);
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
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
		mockResult = new Object[15];
		mockResult[0] = Constants.ACTIVE_PROFILE_STATUS;
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
		mockResult[13] = expectedCreatedDate;
		mockResult[14] = expectedChangedDate;
		mockResults = new ArrayList<Object[]>();
		mockResults.add(mockResult);

		when(mockQuery.getResultList()).thenReturn(mockResults);

		Integer id = expectedId.intValue();
		Integer dealerId = expectedDealer.intValue();
		PSIProfile result = dao.retrieveProfileById(id, dealerId);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("profileId", id);
		verify(mockQuery).setParameter("dealerId", dealerId);
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(Constants.ACTIVE_PROFILE_STATUS, result.getProfileStatus());
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

	@Test
	public void testRetrieveDsmCurrentProfileListByDealerId() {
		Integer id = expectedId.intValue();
		mockResult = new Object[15];
		mockResult[0] = Constants.ACTIVE_PROFILE_STATUS;
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

		when(mockQuery.setParameter("status", Constants.ACTIVE_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);

		List<PSIProfile> results = dao.retrieveDsmCurrentProfileListByDealerId(id, expectedType);

		assertEquals(1, results.size());
		PSIProfile result = results.get(0);
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(Constants.ACTIVE_PROFILE_STATUS, result.getProfileStatus());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
		verify(mockQuery).setParameter("type", expectedType);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveDsmCurrentProfileListByDealerIdNoResults() {		
		when(mockQuery.setParameter("status", Constants.ACTIVE_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(new ArrayList<PSIProfile>());
		
		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDsmCurrentProfileListByDealerId(id, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.ACTIVE_PROFILE_STATUS);
		verify(mockQuery).setParameter("type", expectedType);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveDsmHistoryProfileListByDealerId() {
		mockResult = new Object[15];
		mockResult[0] = Constants.HISTORICAL_PROFILE_STATUS;
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
		
		when(mockQuery.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDsmHistoryProfileListByDealerId(id, expectedType);
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		verify(mockQuery).setParameter("type", expectedType);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		assertEquals(1, results.size());
		PSIProfile result = results.get(0);
		assertEquals(expectedId.intValue(), result.getId().intValue());
		assertEquals(expectedName, result.getName());
		assertEquals(Constants.HISTORICAL_PROFILE_STATUS, result.getProfileStatus());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedDate, result.getTargetCompleteDate());
		assertEquals(expectedType, result.getType());
		assertEquals(BooleanUtils.toBoolean(expectedNonCompliant.intValueExact()), result.isNonCompliant());
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

	@Test
	public void testRetrieveDsmHistoryProfileListByDealerIdNoResults() {
		when(mockQuery.setParameter("status", Constants.HISTORICAL_PROFILE_STATUS)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(new ArrayList<PSIProfile>());

		Integer id = expectedId.intValue();
		List<PSIProfile> results = dao.retrieveDsmHistoryProfileListByDealerId(id, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockEM).createNativeQuery(anyString());
		verify(mockQuery).setParameter("dealerId", expectedId.intValue());
		verify(mockQuery).setParameter("canceled", Constants.DEALER_NOT_CANCELED_CODE);
		verify(mockQuery).setParameter("status", Constants.HISTORICAL_PROFILE_STATUS);
		verify(mockQuery).setParameter("type", expectedType);
		verify(mockQuery).getResultList();
		verify(mockEM).close();
		
		verifyNoMoreInteractions(mockEM, mockQuery);
	}

}
