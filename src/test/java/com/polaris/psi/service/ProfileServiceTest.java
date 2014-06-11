package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.mapper.ProfileMapper;

public class ProfileServiceTest {
	
	private ProfileService service;
	@Mock private DealerProfileHeaderDao mockHeaderDao;
	@Mock private ProfileMapper mockMapper;
	private List<DealerProfileHeader> mockHeaders;
	@Mock private DealerProfileHeader mockHeader;
	private List<Profile> mockProfiles;
	@Mock private Profile mockProfile;
	private List<ProfileDto> mockDtos;
	@Mock private ProfileDto mockDto;
	private int dealerId;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		dealerId = 999;
		mockHeaders = new LinkedList<DealerProfileHeader>();
		mockHeaders.add(mockHeader);
		
		mockProfiles = new LinkedList<Profile>();
		mockProfiles.add(mockProfile);
		
		when(mockHeaderDao.getDealerHeaders(dealerId)).thenReturn(mockHeaders);
		when(mockHeader.getProfile()).thenReturn(mockProfile);
		when(mockMapper.mapToDto(mockProfiles)).thenReturn(mockDtos);
		
		service = new ProfileService();
		service.headerDao = mockHeaderDao;
		service.mapper = mockMapper;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockHeaderDao);
	}

	@Test
	public void testGetDealerProfiles() {
		service.getDealerProfiles(dealerId);
		
		verify(mockHeaderDao).getDealerHeaders(dealerId);
		verify(mockHeader).getProfile();
		verify(mockMapper).mapToDto(mockProfiles);
	}

}
