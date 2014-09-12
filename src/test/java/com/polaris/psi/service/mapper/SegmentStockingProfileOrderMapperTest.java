package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.SegmentStockingProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfileOrder;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class SegmentStockingProfileOrderMapperTest {

	private SegmentStockingProfileOrderMapper mapper;
	@Mock SegmentStockingProfile mockProfile;
	@Mock OrderSegmentDto mockOrderSegment;
	@Mock SegmentStockingProfileOrder mockOrder;
	private String userName, expectedProfileCode, expectedSegmentCode;
	private int expectedPeriodId, expectedQty;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userName = "UT_NAME";
		expectedProfileCode = "UT_PROFILE";
		expectedSegmentCode = "UT_SEGMENT_CODE";
		expectedPeriodId = 999;
		
		when(mockProfile.getProfileCode()).thenReturn(expectedProfileCode);
		when(mockProfile.getSegmentCode()).thenReturn(expectedSegmentCode);
		
		when(mockOrderSegment.getFinalQty()).thenReturn(expectedQty);
		when(mockOrderSegment.getPeriodId()).thenReturn(expectedPeriodId);
		
		mapper = new SegmentStockingProfileOrderMapper();
	}

	@Test
	public void testCreateNewOrder() {
		SegmentStockingProfileOrder result = mapper.createNewOrder(mockProfile, mockOrderSegment, userName);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 1);
		Date date = cal.getTime();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date defaultDate = cal.getTime();
		assertTrue(date.after(result.getChangeDate()));
		assertTrue(date.after(result.getChangeTime()));
		assertTrue(date.after(result.getCreateDate()));
		assertTrue(date.after(result.getCreateTime()));
		assertTrue(result.getUserDef4().before(defaultDate));
		assertTrue(result.getUserDef5().before(defaultDate));
		
		assertEquals(Constants.PROGRAM_CODE, result.getChangeProgram());
		assertEquals(userName, result.getChangeUser());
		assertEquals(Constants.PROGRAM_CODE, result.getCreateProgram());
		assertEquals(userName, result.getCreateUser());
		assertEquals(expectedSegmentCode, result.getDetailToken());
		assertEquals(expectedProfileCode, result.getProfileName());
		assertEquals(expectedPeriodId, result.getProfilePeriod());
		assertEquals(expectedQty, result.getRecommendedMinQty());
		assertEquals("", result.getUserDef1());
		assertEquals("", result.getUserDef2());
		assertEquals("", result.getUserDef3());
		assertEquals("", result.getUserDef6());
		assertEquals(-1, result.getUserDef7());
		assertEquals(-1, result.getUserDef8());
		assertEquals(-1, result.getUserDef9());
		assertEquals("", result.getUserDef10());
		
		verify(mockProfile).getProfileCode();
		verify(mockProfile).getSegmentCode();
		verify(mockOrderSegment).getFinalQty();
		verify(mockOrderSegment).getPeriodId();
		
		verifyNoMoreInteractions(mockOrderSegment, mockProfile);
	}

	@Test
	public void testUpdateExistingOrder() {
		mapper.updateExistingOrder(mockOrder, mockOrderSegment, userName);
		
		verify(mockOrderSegment).getFinalQty();
		verify(mockOrder).setChangeDate(any(Date.class));
		verify(mockOrder).setChangeProgram(Constants.PROGRAM_CODE);
		verify(mockOrder).setChangeTime(any(Date.class));
		verify(mockOrder).setChangeUser(userName);
		verify(mockOrder).setRecommendedMinQty(expectedQty);
		
		verifyNoMoreInteractions(mockOrderSegment, mockOrder);
	}

}
