package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
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
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.psi.service.ProfileService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

public class ProfileResourceTest {

	private ProfileResource resource;
	@Mock private SessionHelper mockSessionHelper;
	@Mock private UserData mockUserData;
	@Mock private ProfileService mockService;
	@Mock OrderSegmentService mockOSService;
	@Mock private ProfileDto mockProfileDto;
	@Mock private OrderSegmentDto mockOSDto;
	@Mock private ProfileDetailsDto mockDetailDto;
	private Integer authId, expectedDealerId, expectedProfileId, returnedDealerId;
	private int dealerId;
	private boolean isDealer;
	private String customerClass, userName;
	private List<OrderSegmentDto> orderSegments;
	private ProfileDetailsDto expectedResult;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		authId = 999;
		expectedDealerId = 888;
		isDealer = true;
		customerClass = "UT-DLR";
		userName = "UTUSER";

		expectedProfileId = 999;
		expectedDealerId = 888;
		returnedDealerId = 777;
		
		orderSegments = new ArrayList<OrderSegmentDto>();
		orderSegments.add(mockOSDto);
		expectedResult = new ProfileDetailsDto();
		
		when(mockService.getDealerProfile(expectedProfileId, expectedDealerId)).thenReturn(mockProfileDto);
		when(mockSessionHelper.getUserData()).thenReturn(mockUserData);
		when(mockUserData.getDealerId()).thenReturn(expectedDealerId);
		when(mockUserData.getAuthorizationRoleId()).thenReturn(authId);
		when(mockUserData.isDealer()).thenReturn(isDealer);
		when(mockUserData.getCustomerClass()).thenReturn(customerClass);
		when(mockUserData.getUserName()).thenReturn(userName);
		when(mockDetailDto.getOrderSegments()).thenReturn(orderSegments);

		resource = new ProfileResource();
		
		resource.service = mockService;
		resource.sessionHelper = mockSessionHelper;
		resource.osService = mockOSService;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockSessionHelper, mockUserData, mockService, mockOSService);
	}

	@Test
	public void testGetProfileAsDealer() {
		when(mockUserData.getDealerId()).thenReturn(777);
		when(mockUserData.isDealer()).thenReturn(true);

		resource.getProfile(expectedProfileId, expectedDealerId);
		
		verify(mockSessionHelper, times(2)).getUserData();
		verify(mockUserData).isDealer();
		verify(mockUserData).getDealerId();
		verify(mockService).getDealerProfile(expectedProfileId, returnedDealerId);
	}

	@Test
	public void testGetProfileAsDsm() {
		when(mockUserData.isDealer()).thenReturn(false);

		resource.getProfile(expectedProfileId, expectedDealerId);
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).isDealer();
		verify(mockService).getDealerProfile(expectedProfileId, expectedDealerId);
	}

	@Test
	public void testSaveQuantitiesNoRecords() {
		orderSegments = new ArrayList<OrderSegmentDto>();
		when(mockDetailDto.getOrderSegments()).thenReturn(orderSegments);

		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NO_RECORDS);
		expectedResult.setOrderSegments(orderSegments);

		ProfileDetailsDto result = resource.saveQuantities(expectedProfileId, mockDetailDto);

		assertEquals(expectedResult.getMessage(), result.getMessage());
		verify(mockSessionHelper).getUserData();
		verify(mockDetailDto).getOrderSegments();
		verifyZeroInteractions(mockOSDto, mockUserData);
	}

	@Test
	public void testSaveQuantitiesNotAuthorized() {
		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NOT_AUTHORIZED);
		expectedResult.setOrderSegments(orderSegments);

		dealerId = 777;
		when(mockOSDto.getDealerId()).thenReturn(dealerId);

		ProfileDetailsDto result = resource.saveQuantities(expectedProfileId, mockDetailDto);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockDetailDto).getOrderSegments();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testSaveQuantities() {
		expectedResult.setSuccessful(true);
		expectedResult.setMessage(Constants.SAVE_SUCCESSFUL);
		expectedResult.setOrderSegments(orderSegments);
		when(mockOSService.saveOrderSegmentQuantities(mockDetailDto)).thenReturn(expectedResult);
		
		when(mockOSDto.getDealerId()).thenReturn(expectedDealerId);
		
		ProfileDetailsDto result = resource.saveQuantities(expectedProfileId, mockDetailDto);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockUserData).getUserName();
		verify(mockDetailDto).getOrderSegments();
		verify(mockOSService).saveOrderSegmentQuantities(mockDetailDto);
		verify(mockOSDto).setModifiedUserName(userName);
	}

	@Test
	public void testSubmitNoRecords() {
		orderSegments = new ArrayList<OrderSegmentDto>();
		when(mockDetailDto.getOrderSegments()).thenReturn(orderSegments);

		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NO_RECORDS);
		expectedResult.setOrderSegments(orderSegments);

		ProfileDetailsDto result = resource.submitQuantities(expectedProfileId, mockDetailDto);

		assertEquals(expectedResult.getMessage(), result.getMessage());
		verify(mockSessionHelper).getUserData();
		verify(mockDetailDto).getOrderSegments();
		verifyZeroInteractions(mockOSDto, mockUserData);
	}

	@Test
	public void testSubmitNotAuthorized() {
		expectedResult.setSuccessful(false);
		expectedResult.setMessage(Constants.NOT_AUTHORIZED);
		expectedResult.setOrderSegments(orderSegments);

		dealerId = 777;
		when(mockOSDto.getDealerId()).thenReturn(dealerId);

		ProfileDetailsDto result = resource.submitQuantities(expectedProfileId, mockDetailDto);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));

		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockDetailDto).getOrderSegments();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testSubmit() {
		expectedResult.setSuccessful(true);
		expectedResult.setMessage(Constants.SAVE_SUCCESSFUL);
		expectedResult.setOrderSegments(orderSegments);
		when(mockOSService.submitOrderSegmentQuantities(mockDetailDto)).thenReturn(expectedResult);
		
		when(mockOSDto.getDealerId()).thenReturn(expectedDealerId);
		
		ProfileDetailsDto result = resource.submitQuantities(expectedProfileId, mockDetailDto);
		
		assertEquals(expectedResult.getMessage(), result.getMessage());
		assertEquals(expectedResult.getOrderSegments().size(), result.getOrderSegments().size());
		assertEquals(expectedResult.getOrderSegments().get(0), result.getOrderSegments().get(0));
		
		verify(mockSessionHelper).getUserData();
		verify(mockUserData).getDealerId();
		verify(mockUserData).getUserName();
		verify(mockDetailDto).getOrderSegments();
		verify(mockOSService).submitOrderSegmentQuantities(mockDetailDto);
		verify(mockOSDto).setModifiedUserName(userName);
	}

}
