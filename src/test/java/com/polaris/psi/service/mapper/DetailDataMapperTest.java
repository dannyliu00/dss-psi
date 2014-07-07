package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class DetailDataMapperTest {

	private DetailDataMapper mapper;
	@Mock private OrderSegmentDto mockDto;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetail mockDetail;
	private Integer expectedActual, expectedReasonCode, expectedOSId, expectedDsmQty, expectedDsmReason;
	private String expectedUserName, expectedComments, expectedDsmComments;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedActual = 1;
		expectedReasonCode = 111;
		expectedOSId = 999;
		expectedUserName = "UTUser";
		expectedComments = "UT comments";
		
		when(mockDto.getActual()).thenReturn(expectedActual);
		when(mockDto.getDealerComments()).thenReturn(expectedComments);
		when(mockDto.getReasonCode()).thenReturn(expectedReasonCode);
		when(mockDto.getModifiedUserName()).thenReturn(expectedUserName);
		when(mockDto.getProfileOrderSegmentId()).thenReturn(expectedOSId);
		
		mapper = new DetailDataMapper();
	}

	@Test
	public void testCreateInitialDetail() {
		DealerProfileDetail result = mapper.createInitialDetail(mockDto, mockHeader);
		
		assertEquals(expectedActual, result.getActual());
		assertEquals(expectedReasonCode, result.getDealerReasonCode());
		assertEquals(expectedOSId.intValue(), result.getProfileOrderSegmentId());
		assertEquals(expectedUserName, result.getCreatedUser());
		assertEquals(expectedComments, result.getDealerComments());
		
		assertNotNull(result.getAdminApprovedQty());
		assertNotNull(result.getAdminComments());
		assertNotNull(result.getAdminReasonCode());		
		assertNotNull(result.getChangedDate());
		assertNotNull(result.getChangedProgram());
		assertNotNull(result.getChangedTime());
		assertNotNull(result.getChangedUser());
		assertNotNull(result.getCreatedDate());
		assertNotNull(result.getCreatedProgram());
		assertNotNull(result.getCreatedTime());
		assertNotNull(result.getDsmComments());
		assertNotNull(result.getDsmReasonCode());
		assertNotNull(result.getDsmRecommendedQty());
		assertNotNull(result.getFinalQty());
		assertNotNull(result.getHeader());
		
		verify(mockDto).getActual();
		verify(mockDto).getDealerComments();
		verify(mockDto).getReasonCode();
		verify(mockDto, times(2)).getModifiedUserName();
		verify(mockDto).getProfileOrderSegmentId();
	}
	
	@Test
	public void testUpdateDealerEnteredDetails() {
		mapper.updateDealerEnteredDetails(mockDetail, mockDto);
		
		verify(mockDetail).setActual(expectedActual);
		verify(mockDetail).setChangedProgram(Constants.PROGRAM_CODE);
		verify(mockDetail).setChangedUser(expectedUserName);
		verify(mockDetail).setDealerComments(expectedComments);
		verify(mockDetail).setDealerReasonCode(expectedReasonCode);
	}

	@Test
	public void testUpdateDsmEnteredDetails() {
		expectedDsmQty = 2;
		expectedDsmReason = 222;
		expectedDsmComments = "UT DSM comments";
		
		when(mockDto.getDsmComments()).thenReturn(expectedDsmComments);
		when(mockDto.getDsmQty()).thenReturn(expectedDsmQty);
		when(mockDto.getDsmReasonCode()).thenReturn(expectedDsmReason);
		
		mapper.updateDsmEnteredDetails(mockDetail, mockDto);
		
		verify(mockDetail).setChangedProgram(Constants.PROGRAM_CODE);
		verify(mockDetail).setChangedUser(expectedUserName);
		verify(mockDetail).setDsmComments(expectedDsmComments);
		verify(mockDetail).setDsmReasonCode(expectedDsmReason);
		verify(mockDetail).setDsmRecommendedQty(expectedDsmQty);
	}

	@Test
	public void testSetDateNull() {
		Date result = mapper.setDate(null);
		assertEquals(Constants.DEFAULT_DATE.getTime(), result);
	}

	@Test
	public void testSetDate() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Date result = mapper.setDate(date);
		assertEquals(date, result);
	}

	@Test
	public void testSetIntegerValueNull() {
		int result = mapper.setIntegerValue(null);
		assertEquals(-1, result);
	}

	@Test
	public void testSetIntegerValue() {
		int result = mapper.setIntegerValue(100);
		assertEquals(100, result);
	}

	@Test
	public void testSetStringValueNull() {
		String result = mapper.setStringValue(null);
		
		assertNotNull(result);
		assertEquals(0, result.length());
	}

	@Test
	public void testSetStringValueEmpty() {
		String result = mapper.setStringValue("");
		
		assertNotNull(result);
		assertEquals(0, result.length());
	}

	@Test
	public void testSetStringValue() {
		String result = mapper.setStringValue(expectedComments);
		
		assertNotNull(result);
		assertEquals(expectedComments, result);
	}

}
