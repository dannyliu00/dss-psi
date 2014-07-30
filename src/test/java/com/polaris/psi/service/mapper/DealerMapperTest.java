package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.resource.dto.DealerDto;

public class DealerMapperTest {

	private DealerMapper mapper;
	private DealerDto expectedDto;
	@Mock private Dealer mockDealer;
	@Mock private DealerAndDsm mockDsm;
	private Integer expectedCompany, expectedId;
	private String expectedName, expectedCity, expectedState, expectedZip, expectedDsmName,expectedDsmEmail, expectedRsmEmail, expectedDealerEmail;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedCompany = new Integer(999);
		expectedId = new Integer(11);
		expectedName = "U.T. Name";
		expectedCity = "U.T. City";
		expectedState = "U.T. State";
		expectedZip = "12345-9099";
		expectedDsmName = "U.T. DSM Name";
		expectedDsmEmail = "dsm@email.com";
		expectedRsmEmail = "rsm@email.com";
		expectedDealerEmail = "dealer@mail.com";
		
		when(mockDealer.getCity()).thenReturn(expectedCity);
		when(mockDealer.getCompany()).thenReturn(expectedCompany);
		when(mockDealer.getId()).thenReturn(expectedId);
		when(mockDealer.getName()).thenReturn(expectedName);
		when(mockDealer.getState()).thenReturn(expectedState);
		when(mockDealer.getZip()).thenReturn(expectedZip);
		
		when(mockDsm.getDealerEmail()).thenReturn(expectedDealerEmail);
		when(mockDsm.getDsmName()).thenReturn(expectedDsmName);

		when(mockDsm.getDsmEmailAddress()).thenReturn(expectedDsmEmail);
		when(mockDsm.getRsmEmailAddress()).thenReturn(expectedRsmEmail);
		
		
		expectedDto = new DealerDto();
		expectedDto.setCity(expectedCity);
		expectedDto.setCompany(expectedCompany);
		expectedDto.setDealerId(expectedId);
		expectedDto.setName(expectedName);
		expectedDto.setState(expectedState);
		expectedDto.setZip(expectedZip);
		expectedDto.setDsmName(expectedDsmName);
		expectedDto.setEmailAddress(expectedDealerEmail);
		
		
		expectedDto.setDsmEmailAddress(expectedDsmEmail);
		expectedDto.setRsmEmailAddress(expectedRsmEmail);
		
		mapper = new DealerMapper();
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockDealer, mockDsm);
	}

	@Test
	public void testMapToDto() {
		DealerDto result = mapper.mapToDto(mockDealer, mockDsm);
		
		assertEquals(result.getCity(), expectedDto.getCity());
		assertEquals(result.getCompany(), expectedDto.getCompany());
		assertEquals(result.getDealerId(), expectedDto.getDealerId());
		assertEquals(result.getName(), expectedDto.getName());
		assertEquals(result.getState(), expectedDto.getState());
		assertEquals(result.getZip(), expectedDto.getZip());
		assertEquals(result.getDsmName(), expectedDto.getDsmName());
		assertEquals(result.getEmailAddress(), expectedDto.getEmailAddress());
		assertEquals(result.getDsmEmailAddress(), expectedDto.getDsmEmailAddress());
		assertEquals(result.getRsmEmailAddress(), expectedDto.getRsmEmailAddress());
		
		verify(mockDealer).getCity();
		verify(mockDealer).getCompany();
		verify(mockDealer).getId();
		verify(mockDealer).getName();
		verify(mockDealer).getState();
		verify(mockDealer).getZip();
		
		verify(mockDsm).getDsmName();
		verify(mockDsm).getDsmEmailAddress();
		verify(mockDsm).getRsmEmailAddress();
	}

}
