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

public class ReasonCodeResourceTest {

	private ReasonCodeResource resource;
	@Mock private ReasonCodeService mockService;
	private int authorizationRoleId;
	private List<ReasonCodeDto> mockDtos;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		authorizationRoleId = 1;
		
		when(mockService.getReasonCodes(authorizationRoleId)).thenReturn(mockDtos);
		
		resource = new ReasonCodeResource();
		resource.service = mockService;
		
	}

	@Test
	public void testGetReasonCodes() {
		List<ReasonCodeDto> results = resource.getReasonCodes(authorizationRoleId);
		
		verify(mockService).getReasonCodes(authorizationRoleId);
		
		assertEquals(mockDtos, results);
		
	}

}
