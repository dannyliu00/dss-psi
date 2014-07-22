package com.polaris.psi.resource;

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

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.ProfileService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class ProfilesResourceTest {

	private ProfilesResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private ProfileService mockService;
	private List<ProfileDto> profiles;
	@Mock private ProfileDto profile;
	private int expectedId, authRoleId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		authRoleId = 1;
		
		profiles = new ArrayList<ProfileDto>();
		profiles.add(profile);

		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.getAuthorizationRoleId()).thenReturn(authRoleId);
		when(mockUserData.isDealer()).thenReturn(true);
		when(mockUserData.isDsm()).thenReturn(true);
		when(mockUserData.isRsm()).thenReturn(true);

		resource = new ProfilesResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}

	@Test
	public void testGetProfilesDealer() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getCurrentDealerProfiles(expectedId)).thenReturn(profiles);
		
		List<ProfileDto> results = resource.getDealerProfiles(expectedId);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();
		verify(mockService).getCurrentDealerProfiles(expectedId);
	}

	@Test
	public void testGetProfilesDealerNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);

		List<ProfileDto> results = resource.getDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();

		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetProfilesDealerNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isDealer()).thenReturn(false);

		List<ProfileDto> results = resource.getDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();

		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetCurrentProfilesDealer() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getCurrentDealerProfiles(expectedId)).thenReturn(profiles);
		
		List<ProfileDto> results = resource.getCurrentDealerProfiles(expectedId);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();
		verify(mockService).getCurrentDealerProfiles(expectedId);
		
		verifyNoMoreInteractions(mockService, mockUserData, mockSessionHelper);
	}

	@Test
	public void testGetCurrentProfilesDealerNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);

		List<ProfileDto> results = resource.getCurrentDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();

		verifyNoMoreInteractions(mockUserData, mockSessionHelper);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetCurrentProfilesDealerNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isDealer()).thenReturn(false);

		List<ProfileDto> results = resource.getCurrentDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();

		verifyNoMoreInteractions(mockUserData, mockSessionHelper);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetHistoricalProfilesDealer() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getHistoricalDealerProfiles(expectedId)).thenReturn(profiles);
		
		List<ProfileDto> results = resource.getHistoricalDealerProfiles(expectedId);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();
		verify(mockService).getHistoricalDealerProfiles(expectedId);
		
		verifyNoMoreInteractions(mockService, mockUserData, mockSessionHelper);
	}

	@Test
	public void testGetHistoricalProfilesDealerNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);

		List<ProfileDto> results = resource.getHistoricalDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();

		verifyNoMoreInteractions(mockUserData, mockSessionHelper);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetHistoricalProfilesDealerNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isDealer()).thenReturn(false);

		List<ProfileDto> results = resource.getHistoricalDealerProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();

		verifyNoMoreInteractions(mockUserData, mockSessionHelper);
		verifyZeroInteractions(mockService);
	}

}
