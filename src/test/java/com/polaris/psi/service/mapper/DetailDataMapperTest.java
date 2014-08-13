package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	private Integer expectedActual, expectedReasonCode, expectedOSId, expectedDsmQty, expectedDsmReason, expectedRsmQty, expectedRsmReason;
	private String expectedUserName, expectedComments, expectedDsmComments, expectedRsmComments, expectedPeriodCode;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedActual = 1;
		expectedReasonCode = 111;
		expectedOSId = 999;
		expectedUserName = "UTUser";
		expectedComments = "UT comments";
		expectedPeriodCode = "UTCODE";
		
		when(mockDto.getActual()).thenReturn(expectedActual);
		when(mockDto.getDealerComments()).thenReturn(expectedComments);
		when(mockDto.getReasonCode()).thenReturn(expectedReasonCode);
		when(mockDto.getModifiedUserName()).thenReturn(expectedUserName);
		when(mockDto.getProfileOrderSegmentId()).thenReturn(expectedOSId);
		when(mockDto.getPeriodCode()).thenReturn(expectedPeriodCode);
		
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
		assertEquals(expectedPeriodCode, result.getPeriodCode());
		
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
		verify(mockDto).getPeriodCode();
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
		
		mapper.updateDsmEnteredDetails(mockDetail, mockDto, expectedUserName);
		
		verify(mockDetail).setChangedProgram(Constants.PROGRAM_CODE);
		verify(mockDetail).setChangedUser(expectedUserName);
		verify(mockDetail).setDsmComments(expectedDsmComments);
		verify(mockDetail).setDsmReasonCode(expectedDsmReason);
		verify(mockDetail).setDsmRecommendedQty(expectedDsmQty);
	}

	@Test
	public void testUpdateRsmEnteredDetails() {
		expectedRsmQty = 1;
		expectedRsmReason = 111;
		expectedRsmComments = "UT RSM comments";
		
		when(mockDto.getAdminComments()).thenReturn(expectedRsmComments);
		when(mockDto.getAdminQty()).thenReturn(expectedRsmQty);
		when(mockDto.getAdminReasonCode()).thenReturn(expectedRsmReason);
		
		mapper.updateRsmEnteredDetails(mockDetail, mockDto, expectedUserName);
		
		verify(mockDetail).setChangedProgram(Constants.PROGRAM_CODE);
		verify(mockDetail).setChangedUser(expectedUserName);
		verify(mockDetail).setAdminComments(expectedRsmComments);
		verify(mockDetail).setAdminReasonCode(expectedRsmReason);
		verify(mockDetail).setAdminApprovedQty(expectedRsmQty);
	}
	
	@Test
	public void testUpdateApprovedDetailsDefault() {
		String status = Constants.RETURNED_TO_DEALER;
		
		mapper.updateApprovedDetails(mockDetail, mockDto, status);
		
		verify(mockDetail).setFinalQty(-1);
	}

	@Test
	public void testUpdateApprovedDetailsDealerValue() {
		String status = Constants.APPROVED_AS_REQUESTED;
		
		mapper.updateApprovedDetails(mockDetail, mockDto, status);
		
		verify(mockDto).getActual();
		verify(mockDetail).setFinalQty(expectedActual);
	}

	@Test
	public void testUpdateApprovedDetailsDsmValue() {
		expectedDsmQty = 5;
		when(mockDto.getDsmQty()).thenReturn(expectedDsmQty);
		String status = Constants.APPROVED_W_CHANGES;
		
		mapper.updateApprovedDetails(mockDetail, mockDto, status);
		
		verify(mockDto).getDsmQty();
		verify(mockDetail).setFinalQty(expectedDsmQty);
	}

	@Test
	public void testUpdateApprovedDetailsRsmValueCompliant() {
		expectedRsmQty = 6;
		when(mockDto.getAdminQty()).thenReturn(expectedRsmQty);
		String status = Constants.APPROVED_COMPLIANT;
		
		mapper.updateApprovedDetails(mockDetail, mockDto, status);
		
		verify(mockDto).getAdminQty();
		verify(mockDetail).setFinalQty(expectedRsmQty);
	}

	@Test
	public void testUpdateApprovedDetailsRsmValueNonCompliant() {
		expectedRsmQty = 7;
		when(mockDto.getAdminQty()).thenReturn(expectedRsmQty);
		String status = Constants.APPROVED_NONCOMPLIANT;
		
		mapper.updateApprovedDetails(mockDetail, mockDto, status);
		
		verify(mockDto).getAdminQty();
		verify(mockDetail).setFinalQty(expectedRsmQty);
	}

}
