package com.polaris.psi.resource;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.ProfileService;

public class ProfilesResourceTest {

	private ProfilesResource resource;
	@Mock private ProfileService mockService;
	private List<Profile> profiles;
	private Date expectedDate;
	private String expectedType;
	private String expectedName;
	private int expectedId;
	private String expectedStatus;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(mockService.getDealerProfiles(expectedId)).thenReturn(profiles);

		Calendar modified = Calendar.getInstance();
		modified.set(2014, 3, 30);
		expectedDate = modified.getTime();
		expectedType = "motorcycle";
		expectedName = "Victory Inventory Profile 04/30/14";
		expectedId = 999;
		expectedStatus = "Not Started";

		resource = new ProfilesResource();
		resource.service = mockService;
	}

	@Test
	public void testGetProfiles() {
		int id = 999;
		List<ProfileDto> results = resource.getProfiles(id);
		
		verify(mockService).getDealerProfiles(id);
		
		assertTrue(results.size() == 2);
		ProfileDto result = results.get(0);
		assertTrue(result.getType().equals(expectedType));
		assertTrue(result.getName().equals(expectedName));
		assertTrue(result.getProfileId() == expectedId);
		assertTrue(result.getStatus().equals(expectedStatus));
	}

}
