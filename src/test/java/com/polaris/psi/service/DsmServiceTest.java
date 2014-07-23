package com.polaris.psi.service;

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

import com.polaris.psi.repository.dao.DealerAndDsmDao;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.DsmDealerProfilesDto;
import com.polaris.psi.service.mapper.DsmDealerProfileMapper;
import com.polaris.pwf.dao.PSIProfileDao;

public class DsmServiceTest {

	private DsmService service;
	@Mock private DealerAndDsmDao mockDsmDao;
	@Mock private PSIProfileDao mockProfileDao;
	@Mock private DsmDealerProfileMapper mockMapper;
	private List<DealerAndDsm> dsmDealers;
	private DealerAndDsm dsmDealer;
	private Integer dealerId, dsmId, rsmId;
	private String productType;
	private List<PSIProfile> psiProfiles;
	@Mock PSIProfile mockProfile;
	private List<DsmDealerProfilesDto> dtos;
	@Mock DsmDealerProfilesDto mockDto;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dealerId = 999;
		dsmId = 888;
		rsmId = 777;
		productType = "UT";
		
		dsmDealer = new DealerAndDsm();
		dsmDealer.setDealerId(dealerId);
		dsmDealer.setDealerName("UT Dealer");
		dsmDealer.setDsmId(dsmId);
		dsmDealer.setDsmName("UT DSM Name");
		dsmDealer.setProductLine("Z"); // valid value from Constants.PRODUCT_LINE_REGEX
		dsmDealer.setRsmId(rsmId);
		dsmDealer.setRsmName("UT RSM Name");
		
		dsmDealers = new ArrayList<DealerAndDsm>();
		dsmDealers.add(dsmDealer);
		
		psiProfiles = new ArrayList<PSIProfile>();
		psiProfiles.add(mockProfile);
		
		dtos = new ArrayList<DsmDealerProfilesDto>();
		dtos.add(mockDto);
		
		when(mockDsmDao.selectByDsmId(dsmId, productType)).thenReturn(dsmDealers);
		when(mockDsmDao.selectByRsmId(rsmId, productType)).thenReturn(dsmDealers);
		when(mockMapper.mapToDto(dsmDealer, mockProfile)).thenReturn(mockDto);
		
		service = new DsmService();
		service.dsmDao = mockDsmDao;
		service.psiProfileDao = mockProfileDao;
		service.mapper = mockMapper;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockDsmDao, mockMapper, mockProfileDao);
	}

	@Test
	public void testGetDsmCurrentProfiles() {
		when(mockProfileDao.retrieveDsmCurrentProfileListByDealerId(dealerId, productType)).thenReturn(psiProfiles);

		List<DsmDealerProfilesDto> results = service.getDsmCurrentProfiles(dsmId, productType);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByDsmId(dsmId, productType);
		verify(mockProfileDao).retrieveDsmCurrentProfileListByDealerId(dealerId, productType);
		verify(mockMapper).mapToDto(dsmDealer, mockProfile);
	}

	@Test
	public void testGetDsmHistoricalProfiles() {
		when(mockProfileDao.retrieveDsmHistoryProfileListByDealerId(dealerId, productType)).thenReturn(psiProfiles);

		List<DsmDealerProfilesDto> results = service.getDsmHistoricalProfiles(dsmId, productType);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByDsmId(dsmId, productType);
		verify(mockProfileDao).retrieveDsmHistoryProfileListByDealerId(dealerId, productType);
		verify(mockMapper).mapToDto(dsmDealer, mockProfile);
	}

	@Test
	public void testGetRsmProfiles() {
		when(mockProfileDao.retrieveDsmCurrentProfileListByDealerId(dealerId, productType)).thenReturn(psiProfiles);

		List<DsmDealerProfilesDto> results = service.getRsmProfiles(rsmId, productType);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByRsmId(rsmId, productType);
		verify(mockProfileDao).retrieveDsmCurrentProfileListByDealerId(dealerId, productType);
		verify(mockMapper).mapToDto(dsmDealer, mockProfile);
	}

	@Test
	public void testGetRsmCurrentProfiles() {
		when(mockProfileDao.retrieveDsmCurrentProfileListByDealerId(dealerId, productType)).thenReturn(psiProfiles);

		List<DsmDealerProfilesDto> results = service.getRsmCurrentProfiles(rsmId, productType);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByRsmId(rsmId, productType);
		verify(mockProfileDao).retrieveDsmCurrentProfileListByDealerId(dealerId, productType);
		verify(mockMapper).mapToDto(dsmDealer, mockProfile);
	}

	@Test
	public void testGetRsmHistoricalProfiles() {
		when(mockProfileDao.retrieveDsmHistoryProfileListByDealerId(dealerId, productType)).thenReturn(psiProfiles);

		List<DsmDealerProfilesDto> results = service.getRsmHistoricalProfiles(rsmId, productType);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByRsmId(rsmId, productType);
		verify(mockProfileDao).retrieveDsmHistoryProfileListByDealerId(dealerId, productType);
		verify(mockMapper).mapToDto(dsmDealer, mockProfile);
	}

}
