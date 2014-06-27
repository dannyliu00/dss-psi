package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class ProfileDetailsResourceTest {
	
	private ProfileDetailsResource resource;
	@Mock SessionHelper mockSessionHelper;
	@Mock UserData mockUserData;
	@Mock OrderSegmentService mockService;
	private Integer authId, expectedDealerId;
	private int dealerId;
	private boolean isDealer;
	private String customerClass, expectedResult;
	private List<OrderSegmentDto> orderSegments;
	@Mock private OrderSegmentDto mockDto;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		authId = 999;
		expectedDealerId = 888;
		isDealer = true;
		customerClass = "UT-DLR";
		expectedResult = Constants.SAVE_SUCCESSFUL;
		orderSegments = new ArrayList<OrderSegmentDto>();
		orderSegments.add(mockDto);
		
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.getAuthorizationRoleId()).thenReturn(authId);
		when(mockUserData.isDealer()).thenReturn(isDealer);
		when(mockUserData.getCustomerClass()).thenReturn(customerClass);
		when(mockUserData.getDealerId()).thenReturn(expectedDealerId);
		when(mockService.saveOrderSegmentQuantities(orderSegments)).thenReturn(orderSegments);
		
		resource = new ProfileDetailsResource();
		resource.sessionHelper = mockSessionHelper;
		resource.service = mockService;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockSessionHelper, mockUserData);
	}

	@Test
	public void testSaveQuantitiesAuthorized() {
		dealerId = 888;
		when(mockDto.getDealerId()).thenReturn(dealerId);
		
		String result = resource.saveQuantities(orderSegments);
		
		assertEquals(expectedResult, result);
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
//		verify(mockUserData).getAuthorizationRoleId();
//		verify(mockUserData).isDealer();
//		verify(mockUserData).getCustomerClass();
	}

	@Test
	public void testSaveQuantitiesNotAuthorized() {
		expectedResult = Constants.NOT_AUTHORIZED;
		dealerId = 777;
		when(mockDto.getDealerId()).thenReturn(dealerId);

		String result = resource.saveQuantities(orderSegments);
		
		assertEquals(expectedResult, result);
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
//		verify(mockUserData).getAuthorizationRoleId();
//		verify(mockUserData).isDealer();
//		verify(mockUserData).getCustomerClass();
	}

}
