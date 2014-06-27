package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	@Mock private DealerAndDsm mockDsmDealer;
	private Integer dealerId, dsmId;
	private List<PSIProfile> psiProfiles;
	@Mock PSIProfile mockProfile;
	private List<DsmDealerProfilesDto> dtos;
	@Mock DsmDealerProfilesDto mockDto;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dealerId = 999;
		dsmId = 888;
		
		dsmDealers = new ArrayList<DealerAndDsm>();
		dsmDealers.add(mockDsmDealer);
		
		psiProfiles = new ArrayList<PSIProfile>();
		psiProfiles.add(mockProfile);
		
		dtos = new ArrayList<DsmDealerProfilesDto>();
		dtos.add(mockDto);
		
		when(mockDsmDao.selectByDsmId(dsmId)).thenReturn(dsmDealers);
		when(mockDsmDealer.getDealerId()).thenReturn(dealerId);
		when(mockProfileDao.retrieveListByDealerId(dealerId)).thenReturn(psiProfiles);
		when(mockMapper.mapToDto(mockDsmDealer, mockProfile)).thenReturn(mockDto);
		
		service = new DsmService();
		service.dsmDao = mockDsmDao;
		service.psiProfileDao = mockProfileDao;
		service.mapper = mockMapper;
	}

	@Test
	public void testGetProfiles() {
		List<DsmDealerProfilesDto> results = service.getProfiles(dsmId);
		
		assertEquals(dtos.size(), results.size());
		
		verify(mockDsmDao).selectByDsmId(dsmId);
		verify(mockDsmDealer).getDealerId();
		verify(mockProfileDao).retrieveListByDealerId(dealerId);
		verify(mockMapper).mapToDto(mockDsmDealer, mockProfile);
	}

}
