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

public class RsmProfilesResourceTest {

	private RsmProfilesResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private DsmService mockService;
	private List<DsmDealerProfilesDto> profiles;
	@Mock private DsmDealerProfilesDto profile;
	private int expectedId;
	private String expectedType;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedType = "UTType";
		
		profiles = new ArrayList<DsmDealerProfilesDto>();
		profiles.add(profile);

		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isRsm()).thenReturn(true);

		resource = new RsmProfilesResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}

	@Test
	public void testGetProfilesRsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getRsmProfiles(expectedId, expectedType)).thenReturn(profiles);
		
		List<DsmDealerProfilesDto> results = resource.getRsmProfiles(expectedId, expectedType);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getRsmProfiles(expectedId, expectedType);
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetProfilesRsmNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);
		List<DsmDealerProfilesDto> results = resource.getRsmProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetProfilesRsmNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isRsm()).thenReturn(false);
		List<DsmDealerProfilesDto> results = resource.getRsmProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetCurrentProfilesRsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getRsmCurrentProfiles(expectedId, expectedType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getRsmCurrentProfiles(expectedId, expectedType);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getRsmCurrentProfiles(expectedId, expectedType);

		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetCurrentProfilesRsmNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);

		List<DsmDealerProfilesDto> results = resource.getRsmCurrentProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetCurrentProfilesRsmNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isRsm()).thenReturn(false);

		List<DsmDealerProfilesDto> results = resource.getRsmCurrentProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetHistoricalProfilesRsm() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockService.getRsmHistoricalProfiles(expectedId, expectedType)).thenReturn(profiles);

		List<DsmDealerProfilesDto> results = resource.getRsmHistoricalProfiles(expectedId, expectedType);
		
		assertEquals(profiles.size(), results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();
		verify(mockService).getRsmHistoricalProfiles(expectedId, expectedType);

		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetHistoricalProfilesRsmNullResult() {
		when(mockUserData.getDealerId()).thenReturn(111);

		List<DsmDealerProfilesDto> results = resource.getRsmHistoricalProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();
		verify(mockUserData).getDealerId();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testGetHistoricalProfilesRsmNullResultTwo() {
		when(mockUserData.getDealerId()).thenReturn(expectedId);
		when(mockUserData.isRsm()).thenReturn(false);

		List<DsmDealerProfilesDto> results = resource.getRsmHistoricalProfiles(expectedId, expectedType);
		
		assertEquals(0, results.size());
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isRsm();

		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
		verifyZeroInteractions(mockService);
	}

}
