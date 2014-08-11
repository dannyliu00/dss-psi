package com.polaris.psi.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileOrderDao;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfileOrder;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.mapper.SegmentStockingProfileOrderMapper;
import com.polaris.psi.util.AttributeHelper;
import com.polaris.pwf.dao.PSIProfileDao;
import com.polaris.pwf.dao.SegmentStockingProfileDao;

public class SegmentStockingProfileOrderServiceTest {

	private SegmentStockingProfileOrderService orderService;
	@Mock private AttributeHelper mockAttributeHelper;
	@Mock private SegmentStockingProfileDao mockStockingProfileDao;
	@Mock private DealerProfileOrderDao mockProfileOrderDao;
	@Mock private SegmentStockingProfileOrderMapper mockMapper;
	@Mock private SegmentStockingProfile mockStockingProfile;
	@Mock private OrderSegmentDto mockOrderSegment;
	@Mock private SegmentStockingProfileOrder mockOrderProfile;
	@Mock private ProfileDetailsDto mockDetailsDto;
	@Mock private PSIProfileDao mockPSIProfileDao;
	@Mock private PSIProfile mockPSIProfile;
	private List<OrderSegmentDto> orderSegments;
	private List<SegmentStockingProfile> mockProfileResults;
	private int expectedDealerId, expectedProfileId, expectedPeriodId;
	private String expectedUserName, expectedType, expectedProfileCode, expectedSegmentCode;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedUserName = "UTUSER";
		expectedProfileId = 1111;
		expectedDealerId = 999;
		expectedPeriodId = 11;
		expectedType = "UTTYPE";
		expectedProfileCode = "UT_PROFILE";
		expectedSegmentCode = "UT_SEGMENT_CODE";
		
		mockProfileResults = new ArrayList<SegmentStockingProfile>();
		mockProfileResults.add(mockStockingProfile);
		
		orderSegments = new ArrayList<OrderSegmentDto>();
		orderSegments.add(mockOrderSegment);
		
		when(mockDetailsDto.getOrderSegments()).thenReturn(orderSegments);
		
		when(mockOrderSegment.getDealerId()).thenReturn(expectedDealerId);
		when(mockOrderSegment.getProfileId()).thenReturn(expectedProfileId);
		
		when(mockPSIProfileDao.retrieveProfileById(expectedProfileId, expectedDealerId)).thenReturn(mockPSIProfile);
		when(mockPSIProfile.getType()).thenReturn(expectedType);
		
		when(mockStockingProfileDao.retrieveSegmentProfilesList(expectedDealerId, expectedType)).thenReturn(mockProfileResults);
		
		when(mockAttributeHelper.getAttribute("sendToStockingProfile")).thenReturn("false");

		orderService = new SegmentStockingProfileOrderService();
		orderService.mapper = mockMapper;
		orderService.orderDao = mockProfileOrderDao;
		orderService.stockingDao = mockStockingProfileDao;
		orderService.psiProfileDao = mockPSIProfileDao;
		orderService.attributeHelper = mockAttributeHelper;
	}
	
	@Test
	public void testSaveStockingProfileSendFalse() {
		orderService.saveStockingProfiles(mockDetailsDto, expectedUserName);
		
		verify(mockDetailsDto).setSuccessful(true);
		verify(mockDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		
		verifyZeroInteractions(mockDetailsDto, mockMapper, mockOrderProfile, mockOrderSegment, mockProfileOrderDao, 
				mockStockingProfile, mockStockingProfileDao);
	}

	@Test
	public void testSaveStockingProfileSendTrueNewProfile() {
		when(mockOrderSegment.getOsCode()).thenReturn("matching name");
		when(mockStockingProfile.getOrderSegmentCode()).thenReturn("matching name");
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(null);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(expectedSegmentCode);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(expectedPeriodId);
		when(mockMapper.createNewOrder(mockStockingProfile, mockOrderSegment, expectedUserName)).thenReturn(mockOrderProfile);
		when(mockAttributeHelper.getAttribute("sendToStockingProfile")).thenReturn("true");
		
		orderService.saveStockingProfiles(mockDetailsDto, expectedUserName);
		
		verify(mockDetailsDto, times(2)).getOrderSegments();
		verify(mockDetailsDto).setSuccessful(true);
		verify(mockDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockOrderSegment).getDealerId();
		verify(mockOrderSegment).getProfileId();
		verify(mockOrderSegment).getOsCode();
		verify(mockPSIProfileDao).retrieveProfileById(expectedProfileId, expectedDealerId);
		verify(mockPSIProfile).getType();
		verify(mockStockingProfileDao).retrieveSegmentProfilesList(expectedDealerId, expectedType);
		verify(mockStockingProfile).getOrderSegmentCode();
		verify(mockStockingProfile).getStockingProfileProfileCode();
		verify(mockMapper).createNewOrder(mockStockingProfile, mockOrderSegment, expectedUserName);
		verify(mockProfileOrderDao).insert(mockOrderProfile);
		
		verifyNoMoreInteractions(mockDetailsDto, mockMapper, mockOrderProfile, mockOrderSegment, mockProfileOrderDao, 
				mockPSIProfile, mockPSIProfileDao, mockStockingProfile, mockStockingProfileDao);
	}

	@Test
	public void testSaveStockingProfileSendTrueExistingProfile() {
		when(mockOrderSegment.getOsCode()).thenReturn("matching name");
		when(mockStockingProfile.getOrderSegmentCode()).thenReturn("matching name");
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(expectedProfileCode);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(expectedSegmentCode);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(expectedPeriodId);
		when(mockProfileOrderDao.retrieve(expectedProfileCode, expectedSegmentCode, expectedPeriodId)).thenReturn(mockOrderProfile);
		when(mockAttributeHelper.getAttribute("sendToStockingProfile")).thenReturn("true");
		
		orderService.saveStockingProfiles(mockDetailsDto, expectedUserName);
		
		verify(mockDetailsDto, times(2)).getOrderSegments();
		verify(mockDetailsDto).setSuccessful(true);
		verify(mockDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockOrderSegment).getDealerId();
		verify(mockOrderSegment).getProfileId();
		verify(mockOrderSegment).getOsCode();
		verify(mockPSIProfileDao).retrieveProfileById(expectedProfileId, expectedDealerId);
		verify(mockPSIProfile).getType();
		verify(mockStockingProfileDao).retrieveSegmentProfilesList(expectedDealerId, expectedType);
		verify(mockStockingProfile).getOrderSegmentCode();
		verify(mockStockingProfile, times(2)).getStockingProfilePeriodId();
		verify(mockStockingProfile, times(2)).getStockingProfileProfileCode();
		verify(mockStockingProfile, times(2)).getStockingProfileSegmentCode();
		verify(mockProfileOrderDao).retrieve(expectedProfileCode, expectedSegmentCode, expectedPeriodId);
		verify(mockProfileOrderDao).update(mockOrderProfile);
		verify(mockMapper).updateExistingOrder(mockOrderProfile, mockOrderSegment, expectedUserName);

		verifyNoMoreInteractions(mockDetailsDto, mockMapper, mockOrderProfile, mockOrderSegment, mockProfileOrderDao, 
				mockPSIProfile, mockPSIProfileDao, mockStockingProfile, mockStockingProfileDao);
	}

	@Test
	public void testRetrieveStockingProfiles() {
		when(mockStockingProfileDao.retrieveSegmentProfilesList(expectedDealerId, expectedType)).thenReturn(mockProfileResults);
		
		orderService.retrieveStockingProfiles(expectedDealerId, expectedType);
		
		verify(mockStockingProfileDao).retrieveSegmentProfilesList(expectedDealerId, expectedType);
		verifyNoMoreInteractions(mockProfileOrderDao);
		verifyZeroInteractions(mockMapper, mockStockingProfileDao);
	}
	
	@Test
	public void testGetMatchingStockingProfileNullResult() {
		when(mockOrderSegment.getOsCode()).thenReturn("no match");
		when(mockStockingProfile.getOrderSegmentCode()).thenReturn("NO MATCH");
		
		SegmentStockingProfile result = orderService.getMatchingStockingProfile(mockProfileResults, mockOrderSegment);
		
		assertTrue(result == null);
		
	}

	@Test
	public void testGetMatchingStockingProfile() {
		when(mockOrderSegment.getOsCode()).thenReturn("match");
		when(mockStockingProfile.getOrderSegmentCode()).thenReturn("match");
		
		SegmentStockingProfile result = orderService.getMatchingStockingProfile(mockProfileResults, mockOrderSegment);
		
		assertTrue(result != null);
		
	}
	
	@Test
	public void testIsExistingStockingProfileFalseNullProfile() {
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(null);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(expectedSegmentCode);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(expectedPeriodId);
		
		boolean result = orderService.isExistingStockingProfile(mockStockingProfile);
		
		assertFalse(result);
	}

	@Test
	public void testIsExistingStockingProfileFalseNullSegment() {
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(expectedProfileCode);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(null);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(expectedPeriodId);
		
		boolean result = orderService.isExistingStockingProfile(mockStockingProfile);
		
		assertFalse(result);
	}

	@Test
	public void testIsExistingStockingProfileFalseNonZeroPeriod() {
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(expectedProfileCode);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(expectedSegmentCode);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(0);
		
		boolean result = orderService.isExistingStockingProfile(mockStockingProfile);
		
		assertFalse(result);
	}

	@Test
	public void testIsExistingStockingProfileTrue() {
		when(mockStockingProfile.getStockingProfileProfileCode()).thenReturn(expectedProfileCode);
		when(mockStockingProfile.getStockingProfileSegmentCode()).thenReturn(expectedSegmentCode);
		when(mockStockingProfile.getStockingProfilePeriodId()).thenReturn(expectedPeriodId);
		
		boolean result = orderService.isExistingStockingProfile(mockStockingProfile);
		
		assertTrue(result);
	}

}
