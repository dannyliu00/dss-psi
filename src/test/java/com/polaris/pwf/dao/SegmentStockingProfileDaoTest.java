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

import com.polaris.psi.repository.entity.SegmentStockingProfile;

public class SegmentStockingProfileDaoTest {

	private SegmentStockingProfileDao dao;
	@Mock private EntityManager mockEntityManager;
	@Mock private Query mockQuery;
	private List<Object[]> mockResults;
	private Object[] mockResult;
	private BigDecimal expectedDealerId, expectedQuantity, expectedPeriodId;
	private String expectedProductLine, expectedProfile, expectedSegmentCode, expectedOrderSegmentCode, expectedPeriodCode;
	private Date expectedStartDate, expectedEndDate;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedDealerId = new BigDecimal(999);
		expectedProductLine = "UTTYPE";
		
		expectedQuantity = new BigDecimal(1);
		expectedPeriodId = new BigDecimal(111);
		
		expectedProfile = "UT_LINE";
		expectedSegmentCode = "UT_SEGMENT_CODDE";
		expectedOrderSegmentCode = "UT_OS_CODE";
		expectedPeriodCode = "UT_PERIOD";
		
		Calendar cal = Calendar.getInstance();
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 3);
		Date endDate = cal.getTime();

		expectedStartDate = startDate;
		expectedEndDate = endDate;
		
		mockResults = new ArrayList<Object[]>();
		mockResult = new Object[12];
		mockResult[0] = expectedDealerId;
		mockResult[1] = expectedProductLine;
		mockResult[2] = expectedProfile;
		mockResult[3] = expectedSegmentCode;
		mockResult[4] = expectedOrderSegmentCode;
		mockResult[5] = expectedPeriodCode;
		mockResult[6] = expectedStartDate;
		mockResult[7] = expectedEndDate;
		mockResult[8] = expectedProfile;
		mockResult[9] = expectedSegmentCode;
		mockResult[10] = expectedPeriodId;
		mockResult[11] = expectedQuantity;
		mockResults.add(mockResult);
		
		when(mockEntityManager.createNativeQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("dealerId", expectedDealerId)).thenReturn(mockQuery);
		when(mockQuery.setParameter("type", expectedProductLine)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResults);
		
		dao = new SegmentStockingProfileDao();
		dao.entityManager = mockEntityManager;
		
	}

	@Test
	public void testRetrieveSegmentProfilesList() {
		int id = expectedDealerId.intValueExact();
		List<SegmentStockingProfile> results = dao.retrieveSegmentProfilesList(id, expectedProductLine);
		
		assertEquals(1, results.size());
		SegmentStockingProfile profile = results.get(0);
		
		assertEquals(expectedDealerId.intValueExact(), profile.getDealerId());
		assertEquals(expectedProductLine, profile.getProductLine());
		assertEquals(expectedProfile, profile.getProfileCode());
		assertEquals(expectedSegmentCode, profile.getSegmentCode());
		assertEquals(expectedOrderSegmentCode, profile.getOrderSegmentCode());
		assertEquals(expectedPeriodCode, profile.getPeriodCode());
		assertEquals(expectedStartDate, profile.getPeriodStartDate());
		assertEquals(expectedEndDate, profile.getPeriodEndDate());
		assertEquals(expectedProfile, profile.getStockingProfileProfileCode());
		assertEquals(expectedSegmentCode, profile.getStockingProfileSegmentCode());
		assertEquals(expectedPeriodId.intValueExact(), profile.getStockingProfilePeriodId());
		assertEquals(expectedQuantity.intValueExact(), profile.getRecommendedQty());
		
		verify(mockEntityManager).createNativeQuery(anyString());
		verify(mockEntityManager).close();
		verify(mockQuery).setParameter("dealerId", id);
		verify(mockQuery).setParameter("type", expectedProductLine);
		verify(mockQuery).getResultList();
		
		verifyNoMoreInteractions(mockEntityManager, mockQuery);
		
	}

}
