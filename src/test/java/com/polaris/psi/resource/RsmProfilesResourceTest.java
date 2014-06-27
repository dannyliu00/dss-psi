package com.polaris.psi.resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
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

public class RsmProfilesResourceTest {

	private RsmProfilesResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private ProfileService mockService;
	private List<ProfileDto> profiles;
	@Mock private ProfileDto profile;
	private int expectedId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		
		profiles = new ArrayList<ProfileDto>();
		profiles.add(profile);

		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isRsm()).thenReturn(true);
		when(mockService.getDealerProfiles(expectedId)).thenReturn(profiles);

		resource = new RsmProfilesResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}

	@Test
	public void testGetProfilesRsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		List<ProfileDto> results = resource.getRsmProfiles(expectedId);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDealerProfiles(expectedId);
	}

	@Test
	public void testGetProfilesRsmNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);
		List<ProfileDto> results = resource.getRsmProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetProfilesRsmNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isRsm()).thenReturn(false);
		List<ProfileDto> results = resource.getRsmProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verifyZeroInteractions(mockService);
	}

}
