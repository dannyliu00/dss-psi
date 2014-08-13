package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.DealerService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class DealerResourceTest {

	private DealerResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private DealerService mockService;
	@Mock private DealerDto mockDto;
	private int id;
	private String type;
	private boolean isDealer;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		isDealer = true;
		id = 1111;
		type = "TYPE";

		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.isDealer()).thenReturn(isDealer);
		when(mockUserData.getDealerId()).thenReturn(id);
		when(mockService.getDealer(anyInt(), anyString())).thenReturn(mockDto);
		
		resource = new DealerResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService);
	}

	@Test
	public void testGetDealerAsDealer() {
		DealerDto actual = resource.getDealerInfo(type);
		
		assertEquals(actual, mockDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockService).getDealer(id, type);
	}

	@Test
	public void testGetDealerAsNonDealer() {
		when(mockUserData.isDealer()).thenReturn(false);
		
		int dealerId = 99;
		
		DealerDto actual = resource.getDealerInfo(type, dealerId);
		
		assertEquals(actual, mockDto);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockService).getDealer(dealerId, type);
	}

}
