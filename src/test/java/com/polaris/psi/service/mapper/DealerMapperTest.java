package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.resource.dto.DealerDto;

public class DealerMapperTest {

	private DealerMapper mapper;
	private DealerDto expectedDto;
	@Mock private Dealer mockDealer;
	private Integer expectedCompany, expectedId;
	private String expectedName, expectedCity, expectedState, expectedZip, expectedClass;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedCompany = new Integer(999);
		expectedId = new Integer(11);
		expectedName = "U.T. Name";
		expectedCity = "U.T. City";
		expectedState = "U.T. State";
		expectedZip = "12345-9099";
		expectedClass = "U.T. classification";
		
		when(mockDealer.getCity()).thenReturn(expectedCity);
//		when(mockDealer.getClassification()).thenReturn(expectedClass);
		when(mockDealer.getCompany()).thenReturn(expectedCompany);
		when(mockDealer.getId()).thenReturn(expectedId);
		when(mockDealer.getName()).thenReturn(expectedName);
		when(mockDealer.getState()).thenReturn(expectedState);
		when(mockDealer.getZip()).thenReturn(expectedZip);
		
		expectedDto = new DealerDto();
		expectedDto.setCity(expectedCity);
		expectedDto.setCompany(expectedCompany);
		expectedDto.setDealerId(expectedId);
		expectedDto.setName(expectedName);
		expectedDto.setState(expectedState);
		expectedDto.setZip(expectedZip);
		
		mapper = new DealerMapper();
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockDealer);
	}

	@Test
	public void testMapToDto() {
		DealerDto result = mapper.mapToDto(mockDealer);
		
		assertTrue(result.getCity().equals(expectedDto.getCity()));
		assertTrue(result.getCompany().equals(expectedDto.getCompany()));
		assertTrue(result.getDealerId().equals(expectedDto.getDealerId()));
		assertTrue(result.getName().equals(expectedDto.getName()));
		assertTrue(result.getState().equals(expectedDto.getState()));
		assertTrue(result.getZip().equals(expectedDto.getZip()));
		
		verify(mockDealer).getCity();
//		verify(mockDealer).getClassification();
		verify(mockDealer).getCompany();
		verify(mockDealer).getId();
		verify(mockDealer).getName();
		verify(mockDealer).getState();
		verify(mockDealer).getZip();
	}

}
