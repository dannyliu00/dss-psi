package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.ReasonCodeDto;
import com.polaris.psi.service.ReasonCodeService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class ReasonCodeResourceTest {

	private ReasonCodeResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private ReasonCodeService mockService;
	private int authorizationRoleId;
	private List<ReasonCodeDto> mockDtos;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		authorizationRoleId = 1;
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.getAuthorizationRoleId()).thenReturn(authorizationRoleId);
		when(mockService.getReasonCodes(authorizationRoleId)).thenReturn(mockDtos);
		
		resource = new ReasonCodeResource();
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
		
	}

	@Test
	public void testGetReasonCodes() {
		List<ReasonCodeDto> results = resource.getReasonCodes();
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getAuthorizationRoleId();
		verify(mockService).getReasonCodes(authorizationRoleId);
		
		assertEquals(mockDtos, results);
		
	}

}
