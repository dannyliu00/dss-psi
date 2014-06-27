package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
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
import com.polaris.psi.resource.dto.ProfileDetailsDto;
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
	private String customerClass;
	private List<OrderSegmentDto> orderSegments;
	@Mock private OrderSegmentDto mockDto;
	private ProfileDetailsDto expectedResult;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		authId = 999;
		expectedDealerId = 888;
		isDealer = true;
		customerClass = "UT-DLR";
		orderSegments = new ArrayList<OrderSegmentDto>();
		orderSegments.add(mockDto);
		expectedResult = new ProfileDetailsDto();
		
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
	public void testSaveQuantitiesNoRecords() {
		orderSegments = new ArrayList<OrderSegmentDto>();

		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NO_RECORDS);
		expectedResult.setOrderSegments(orderSegments);

		ProfileDetailsDto result = resource.saveQuantities(orderSegments);

		assertEquals(expectedResult.getMessage(), result.getMessage());
		verifyZeroInteractions(mockDto, mockSessionHelper, mockUserData);
	}

	@Test
	public void testSaveQuantitiesNotAuthorized() {
		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NOT_AUTHORIZED);
		expectedResult.setOrderSegments(orderSegments);

		dealerId = 777;
		when(mockDto.getDealerId()).thenReturn(dealerId);

		ProfileDetailsDto result = resource.saveQuantities(orderSegments);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testSaveQuantitiesAuthorized() {
		expectedResult.setSuccessful(true);
		expectedResult.setMessage(Constants.SAVE_SUCCESSFUL);
		expectedResult.setOrderSegments(orderSegments);
		
		dealerId = 888;
		when(mockDto.getDealerId()).thenReturn(dealerId);
		
		ProfileDetailsDto result = resource.saveQuantities(orderSegments);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockService).saveOrderSegmentQuantities(orderSegments);
	}

}
