package com.polaris.psi.resource;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
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
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class DsmProfileResourceTest {

	private DsmProfileResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private OrderSegmentService mockService;
	@Mock private ProfileDetailsDto mockDetailDto;
	private List<OrderSegmentDto> dtos;
	@Mock private OrderSegmentDto mockOSDto;
	private boolean isDsm, isNotDsm;
	private int profileId;
	private String userId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userId = "UTUSER";
		profileId = 999;
		isDsm = true;
		isNotDsm = false;
		dtos = new ArrayList<OrderSegmentDto>();
		dtos.add(mockOSDto);
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isDsm()).thenReturn(isDsm);
		when(mockUserData.getUserName()).thenReturn(userId);
		when(mockDetailDto.getOrderSegments()).thenReturn(dtos);
		when(mockService.dsmApproveWithChanges(dtos)).thenReturn(dtos);

		resource = new DsmProfileResource();
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
	}

	@Test
	public void testSendToDealer() {
//		fail("Not yet implemented");
	}

	@Test
	public void testApproveWithChangesNotAuthorized() {
		when(mockUserData.isDsm()).thenReturn(isNotDsm);
		
		ProfileDetailsDto result = resource.approveWithChanges(profileId, mockDetailDto);

		assertEquals(Constants.NOT_AUTHORIZED, result.getMessage());
		assertEquals(false, result.isSuccessful());

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockDetailDto, mockOSDto, mockService);
	}

	@Test
	public void testApproveWithChangesNoRecords() {
		when(mockDetailDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		ProfileDetailsDto result = resource.approveWithChanges(profileId, mockDetailDto);
		
		assertEquals(Constants.NO_RECORDS, result.getMessage());
		assertEquals(false, result.isSuccessful());

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockDetailDto).getOrderSegments();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockDetailDto);
		verifyZeroInteractions(mockOSDto, mockService);
	}

	@Test
	public void testApproveWithChanges() {
		ProfileDetailsDto result = resource.approveWithChanges(profileId, mockDetailDto);
		
		assertEquals(Constants.SAVE_SUCCESSFUL, result.getMessage());
		assertEquals(true, result.isSuccessful());
		assertEquals(1, result.getOrderSegments().size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockDetailDto).getOrderSegments();
		verify(mockService).dsmApproveWithChanges(dtos);
	}

	@Test
	public void testApproveAsRequested() {
//		fail("Not yet implemented");
	}

	@Test
	public void testSubmitForException() {
//		fail("Not yet implemented");
	}

}
