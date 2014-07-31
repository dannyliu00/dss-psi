package com.polaris.psi.resource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class RoleResourceTest {

	private RoleResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		
		resource = new RoleResource();
		resource.sessionHelper = mockSessionHelper;
	}

	@Test
	public void testGetRole() {
		resource.getRole();
		
		verify(mockSessionHelper).getUserData();
		
		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
	}

}
