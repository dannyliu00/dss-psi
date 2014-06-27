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

public class DsmProfilesResourceTest {

	private DsmProfilesResource resource;
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
		when(mockUserData.isDsm()).thenReturn(true);
		when(mockService.getDealerProfiles(expectedId)).thenReturn(profiles);

		resource = new DsmProfilesResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}

	@Test
	public void testGetProfilesDsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		List<ProfileDto> results = resource.getDsmProfiles(expectedId);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDealerProfiles(expectedId);
	}

	@Test
	public void testGetProfilesDsmNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);
		List<ProfileDto> results = resource.getDsmProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetProfilesDsmNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isDsm()).thenReturn(false);
		List<ProfileDto> results = resource.getDsmProfiles(expectedId);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verifyZeroInteractions(mockService);
	}

}
