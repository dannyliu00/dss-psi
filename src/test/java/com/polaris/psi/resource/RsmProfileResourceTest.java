package com.polaris.psi.resource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class RsmProfileResourceTest {

	private RsmProfileResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private OrderSegmentService mockService;
	@Mock private ProfileDetailsDto mockDetailDto;
	private boolean isRsm, isNotRsm;
	private String userId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userId = "UTUSER";
		isRsm = true;
		isNotRsm = false;
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isRsm()).thenReturn(isRsm);
		when(mockUserData.getUserName()).thenReturn(userId);

		resource = new RsmProfileResource();
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
	}

	@Test
	public void testApproveAsCompliantNotAuthorized() {
		when(mockUserData.isRsm()).thenReturn(isNotRsm);
		
		resource.approveAsCompliant(mockDetailDto);

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockDetailDto).setMessage(Constants.NOT_AUTHORIZED);
		verify(mockDetailDto).setSuccessful(false);
		
		verifyNoMoreInteractions(mockDetailDto, mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testApproveAsCompliant() {
		when(mockService.rsmApproveAsCompliant(mockDetailDto, userId)).thenReturn(mockDetailDto);
		
		resource.approveAsCompliant(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getUserName();
		verify(mockService).rsmApproveAsCompliant(mockDetailDto, userId);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

	@Test
	public void testApproveAsNonCompliantNotAuthorized() {
		when(mockUserData.isRsm()).thenReturn(isNotRsm);
		
		resource.approveAsNonCompliant(mockDetailDto);

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockDetailDto).setMessage(Constants.NOT_AUTHORIZED);
		verify(mockDetailDto).setSuccessful(false);
		
		verifyNoMoreInteractions(mockDetailDto, mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testApproveAsNonCompliant() {
		when(mockService.rsmApproveAsNonCompliant(mockDetailDto, userId)).thenReturn(mockDetailDto);
		
		resource.approveAsNonCompliant(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getUserName();
		verify(mockService).rsmApproveAsNonCompliant(mockDetailDto, userId);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

	@Test
	public void testSendToDsmNotAuthorized() {
		when(mockUserData.isRsm()).thenReturn(isNotRsm);
		
		resource.sendToDsm(mockDetailDto);

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockDetailDto).setMessage(Constants.NOT_AUTHORIZED);
		verify(mockDetailDto).setSuccessful(false);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockDetailDto, mockService);
	}

	@Test
	public void testSendToDsm() {
		when(mockService.rsmSendToDsm(mockDetailDto, userId)).thenReturn(mockDetailDto);
		
		resource.sendToDsm(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getUserName();
		verify(mockService).rsmSendToDsm(mockDetailDto, userId);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

	@Test
	public void testSaveChangesNotAuthorized() {
		when(mockUserData.isRsm()).thenReturn(isNotRsm);
		
		resource.saveChanges(mockDetailDto);

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockDetailDto).setMessage(Constants.NOT_AUTHORIZED);
		verify(mockDetailDto).setSuccessful(false);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockDetailDto, mockService);
	}

	@Test
	public void testSaveChanges() {
		when(mockService.rsmSaveChanges(mockDetailDto, userId)).thenReturn(mockDetailDto);
		
		resource.saveChanges(mockDetailDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getUserName();
		verify(mockService).rsmSaveChanges(mockDetailDto, userId);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockDetailDto);
	}

}
