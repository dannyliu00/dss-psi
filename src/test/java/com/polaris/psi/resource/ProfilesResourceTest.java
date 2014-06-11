package com.polaris.psi.resource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.ProfileService;

public class ProfilesResourceTest {

	private ProfilesResource resource;
	@Mock private ProfileService mockService;
	private List<ProfileDto> profiles;
	private int expectedId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(mockService.getDealerProfiles(expectedId)).thenReturn(profiles);

		expectedId = 999;

		resource = new ProfilesResource();
		resource.service = mockService;
	}

	@Test
	public void testGetProfiles() {
		resource.getProfiles(expectedId);
		
		verify(mockService).getDealerProfiles(expectedId);
	}

}
