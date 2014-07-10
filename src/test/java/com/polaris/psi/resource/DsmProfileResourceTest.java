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
	private String userId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userId = "UTUSER";
		isDsm = true;
		isNotDsm = false;
		dtos = new ArrayList<OrderSegmentDto>();
		dtos.add(mockOSDto);
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isDsm()).thenReturn(isDsm);
		when(mockUserData.getUserName()).thenReturn(userId);
		when(mockDetailDto.getOrderSegments()).thenReturn(dtos);
		when(mockService.dsmApproveWithChanges(dtos)).thenReturn(dtos);
		when(mockService.dsmApproveAsRequested(mockDetailDto)).thenReturn(mockDetailDto);

		resource = new DsmProfileResource();
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
	}

	@Test
	public void testApproveWithChangesNotAuthorized() {
		when(mockUserData.isDsm()).thenReturn(isNotDsm);
		
		ProfileDetailsDto result = resource.approveWithChanges(mockDetailDto);

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
		
		ProfileDetailsDto result = resource.approveWithChanges(mockDetailDto);
		
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
		ProfileDetailsDto result = resource.approveWithChanges(mockDetailDto);
		
		assertEquals(Constants.SAVE_SUCCESSFUL, result.getMessage());
		assertEquals(true, result.isSuccessful());
		assertEquals(1, result.getOrderSegments().size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockDetailDto).getOrderSegments();
		verify(mockService).dsmApproveWithChanges(dtos);
	}

	@Test
	public void testApproveAsRequestedNotAuthorized() {
		when(mockUserData.isDsm()).thenReturn(isNotDsm);
		
		ProfileDetailsDto result = resource.approveAsRequested(mockDetailDto);

		assertEquals(Constants.NOT_AUTHORIZED, result.getMessage());
		assertEquals(false, result.isSuccessful());

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockDetailDto);
		verifyZeroInteractions(mockOSDto, mockService);
	}

	@Test
	public void testApproveAsRequested() {

		ProfileDetailsDto result = resource.approveAsRequested(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockService).dsmApproveAsRequested(mockDetailDto);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

	@Test
	public void testSubmitForExceptionNotAuthorized() {
		when(mockUserData.isDsm()).thenReturn(isNotDsm);
		
		ProfileDetailsDto result = resource.submitForException(mockDetailDto);

		assertEquals(Constants.NOT_AUTHORIZED, result.getMessage());
		assertEquals(false, result.isSuccessful());

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockDetailDto);
		verifyZeroInteractions(mockOSDto, mockService);
	}

	@Test
	public void testSubmitForException() {
		resource.submitForException(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockService).dsmSubmitForException(mockDetailDto);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

	@Test
	public void testSendToDealerNotAuthorized() {
		when(mockUserData.isDsm()).thenReturn(isNotDsm);
		
		ProfileDetailsDto result = resource.sendToDealer(mockDetailDto);

		assertEquals(Constants.NOT_AUTHORIZED, result.getMessage());
		assertEquals(false, result.isSuccessful());

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockDetailDto);
		verifyZeroInteractions(mockOSDto, mockService);
	}
	
	@Test
	public void testSendToDealer() {
		resource.sendToDealer(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockService).dsmSendToDealer(mockDetailDto);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

}
