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

import com.polaris.psi.resource.dto.DsmDealerProfilesDto;
import com.polaris.psi.service.DsmService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class DsmProfilesResourceTest {

	private DsmProfilesResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private DsmService mockService;
	private List<DsmDealerProfilesDto> profiles;
	@Mock private DsmDealerProfilesDto profile;
	private int expectedId;
	private String expectedType, defaultType;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedType = "UT";
		defaultType = "2";
		
		profiles = new ArrayList<DsmDealerProfilesDto>();
		profiles.add(profile);

		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isDsm()).thenReturn(true);

		resource = new DsmProfilesResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}

	@Test
	public void testGetCurrentProfilesDsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getDsmCurrentProfiles(expectedId, expectedType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getDsmCurrentProfiles(expectedType);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDsmCurrentProfiles(expectedId, expectedType);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetCurrentProfilesDsmDefaultType() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getDsmCurrentProfiles(expectedId, defaultType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getDsmCurrentProfiles(null);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDsmCurrentProfiles(expectedId, defaultType);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetCurrentProfilesDsmNullResult() {
		when(mockUserData.isDsm()).thenReturn(false);
		List<DsmDealerProfilesDto> results = resource.getDsmCurrentProfiles(expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetHistoricalProfilesDsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getDsmHistoricalProfiles(expectedId, expectedType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getDsmHistoricalProfiles(expectedType);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDsmHistoricalProfiles(expectedId, expectedType);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetHistoricalProfilesDsmDefaultType() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getDsmHistoricalProfiles(expectedId, defaultType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getDsmHistoricalProfiles(null);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getDsmHistoricalProfiles(expectedId, defaultType);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetHistoricalProfilesDsmNullResult() {
		when(mockUserData.isDsm()).thenReturn(false);
		List<DsmDealerProfilesDto> results = resource.getDsmHistoricalProfiles(expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDsm();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

}
