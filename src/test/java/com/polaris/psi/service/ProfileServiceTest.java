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

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.dao.ProfileDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.ProfileMapper;
import com.polaris.psi.service.mapper.ProfileTypeMapper;

public class ProfileServiceTest {
	
	private ProfileService service;

	@Mock private DealerProfileHeaderDao mockHeaderDao;
	@Mock private ProfileDao mockProfileDao;
	@Mock private SegmentService mockSegmentService;
	@Mock private OrderSegmentService mockOrderSegmentService;
	@Mock private ProfilePeriodService mockProfilePeriodService;
	@Mock private ProfileMapper mockMapper;
	@Mock private ProfileTypeMapper mockTypeMapper;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private Profile mockProfile;
	@Mock private ProfileDto mockDto;
	@Mock private OrderSegmentDto mockOrderSegmentDto;
	@Mock private SegmentDto mockSegmentDto;
	@Mock private ProfilePeriodDto mockProfilePeriodDto;
	
	private List<DealerProfileHeader> mockHeaders;
	private List<Profile> mockProfiles;
	private List<OrderSegmentDto> mockOrderSegmentDtos;
	private List<SegmentDto> mockSegmentDtos;
	private List<ProfilePeriodDto> mockProfilePeriodDtos;

	private int dealerId;
	private String expectedSubSegment, expectedType;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		dealerId = 999;
		expectedSubSegment = "U.T. SubSegment";
		expectedType = "U.T. Type";
		
		mockHeaders = new ArrayList<DealerProfileHeader>();
		mockHeaders.add(mockHeader);
		
		mockProfiles = new ArrayList<Profile>();
		mockProfiles.add(mockProfile);
		
		mockOrderSegmentDtos = new ArrayList<OrderSegmentDto>();
		mockOrderSegmentDtos.add(mockOrderSegmentDto);
		when(mockOrderSegmentDto.getSubSegment()).thenReturn(expectedSubSegment);
		
		mockSegmentDtos = new ArrayList<SegmentDto>();
		mockSegmentDtos.add(mockSegmentDto);
		when(mockSegmentDto.getType()).thenReturn(expectedType);
		
		mockProfilePeriodDtos = new ArrayList<ProfilePeriodDto>();
		mockProfilePeriodDtos.add(mockProfilePeriodDto);
		
		service = new ProfileService();
		service.headerDao = mockHeaderDao;
		service.mapper = mockMapper;
		service.orderSegmentService = mockOrderSegmentService;
		service.profileDao = mockProfileDao;
		service.profilePeriodService = mockProfilePeriodService;
		service.segmentService = mockSegmentService;
		service.typeMapper = mockTypeMapper;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockHeaderDao);
	}

	@Test
	public void testGetDealerProfiles() {
		when(mockHeaderDao.getDealerHeaders(dealerId)).thenReturn(mockHeaders);
		when(mockHeader.getProfile()).thenReturn(mockProfile);
		when(mockMapper.mapToDto(mockProfile)).thenReturn(mockDto);
		when(mockOrderSegmentService.retrieveByProfile(mockProfile)).thenReturn(mockOrderSegmentDtos);
		when(mockSegmentService.retrieveBySubSegment(expectedSubSegment)).thenReturn(mockSegmentDtos);
		when(mockSegmentService.retrieveByProfile(mockProfile)).thenReturn(mockSegmentDtos);
		
		service.getDealerProfiles(dealerId);
		
		verify(mockHeaderDao).getDealerHeaders(dealerId);
		verify(mockHeader).getProfile();
		verify(mockMapper).mapToDto(mockProfile);
		verify(mockOrderSegmentService).retrieveByProfile(mockProfile);
		verify(mockSegmentService).retrieveBySubSegment(expectedSubSegment);
		verify(mockTypeMapper).mapTypeToProfile(expectedType, mockDto);
		verify(mockSegmentService).retrieveByProfile(mockProfile);
	}

	@Test
	public void testGetDealerProfile() {
		int profileId = 99999;
		
		when(mockProfileDao.retrieveProfileById(profileId)).thenReturn(mockProfile);
		when(mockMapper.mapToDto(mockProfile)).thenReturn(mockDto);
		when(mockSegmentService.retrieveByProfile(mockProfile)).thenReturn(mockSegmentDtos);
		when(mockSegmentService.retrieveBySubSegment(expectedSubSegment)).thenReturn(mockSegmentDtos);
		when(mockOrderSegmentService.retrieveByProfile(mockProfile)).thenReturn(mockOrderSegmentDtos);
		when(mockProfilePeriodService.getPeriodsByProfile(mockProfile)).thenReturn(mockProfilePeriodDtos);
		
		ProfileDto result = service.getDealerProfile(profileId);

		verify(mockProfileDao).retrieveProfileById(profileId);
		verify(mockMapper).mapToDto(mockProfile);
		verify(mockSegmentService).retrieveByProfile(mockProfile);
		verify(mockSegmentService).retrieveBySubSegment(expectedSubSegment);
		verify(mockProfilePeriodService).getPeriodsByProfile(mockProfile);
		verify(mockTypeMapper).mapTypeToProfile(expectedType, mockDto);
		verify(mockDto).setSegments(mockSegmentDtos);
		verify(mockDto).setType(expectedType);
		verify(mockDto).setOrderSegments(mockOrderSegmentDtos);
		verify(mockDto).setPeriods(mockProfilePeriodDtos);
		
		assertEquals(mockDto, result);
	}

}
