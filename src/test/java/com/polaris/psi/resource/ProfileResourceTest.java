package com.polaris.psi.resource;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.ProfileService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class ProfileResourceTest {

	private ProfileResource resource;
	@Mock private ProfileService mockService;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private ProfileDto mockDto;
	private int expectedProfileId, expectedDealerId, returnedDealerId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedProfileId = 999;
		expectedDealerId = 888;
		returnedDealerId = 777;
		
		resource = new ProfileResource();
		
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
		
		when(mockService.getDealerProfile(expectedProfileId, expectedDealerId)).thenReturn(mockDto);
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.getDealerId()).thenReturn(777);
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetProfileAsDealer() {
		when(mockUserData.isDealer()).thenReturn(true);

		resource.getProfile(expectedProfileId, expectedDealerId);
		
		verify(mockSessionHelper, times(2)).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();
		verify(mockService).getDealerProfile(expectedProfileId, returnedDealerId);
	}

	@Test
	public void testGetProfileAsDsm() {
		when(mockUserData.isDealer()).thenReturn(false);

		resource.getProfile(expectedProfileId, expectedDealerId);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockService).getDealerProfile(expectedProfileId, expectedDealerId);
	}

}
