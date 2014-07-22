package com.polaris.psi.service;

import static org.mockito.Mockito.times;
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

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.service.mapper.PSIOrderSegmentMapper;
import com.polaris.psi.service.mapper.PSIProfileMapper;
import com.polaris.psi.service.mapper.PSIProfilePeriodMapper;
import com.polaris.psi.service.mapper.PSISegmentMapper;
import com.polaris.pwf.dao.PSIOrderSegmentDao;
import com.polaris.pwf.dao.PSIProfileDao;
import com.polaris.pwf.dao.PSIProfileDetailDao;
import com.polaris.pwf.dao.PSIProfilePeriodDao;
import com.polaris.pwf.dao.PSISegmentDao;

public class ProfileServiceTest {
	
	private ProfileService service;

	@Mock private PSIProfileDao mockPsiProfileDao;
	@Mock private PSIProfileDetailDao mockPsiDetailDao;
	@Mock private PSIProfileMapper mockProfileMapper;
	@Mock private PSISegmentDao mockPsiSegmentDao;
	@Mock private PSISegmentMapper mockSegmentMapper;
	@Mock private PSIOrderSegmentDao mockPsiOsDao;
	@Mock private PSIProfile mockProfile;
	@Mock private ProfileDto mockDto;
	@Mock private OrderSegmentDto mockOrderSegmentDto;
	@Mock private SegmentDto mockSegmentDto;
	@Mock private ProfilePeriodDto mockProfilePeriodDto;
	@Mock private PSIProfilePeriodDao mockProfilePeriodDao;
	@Mock private PSIProfilePeriodMapper mockProfilePeriodMapper;
	@Mock private PSIOrderSegmentMapper mockOrderSegmentMapper;
	
	private List<PSIProfile> mockProfiles;
	private List<PSIProfileDetail> mockDetails;
	private List<PSIOrderSegment> mockOSes;
	private List<PSISegment> mockSegments;
	private List<PSIProfilePeriod> mockProfilePeriods;
	private List<OrderSegmentDto> mockOrderSegmentDtos;
	private List<SegmentDto> mockSegmentDtos;
	private List<ProfilePeriodDto> mockProfilePeriodDtos;

	private int dealerId, profileId, headerId;
	private String expectedSubSegment, expectedType, typeCode;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		dealerId = 999;
		profileId = 888;
		headerId = 111;
		expectedSubSegment = "U.T. SubSegment";
		typeCode = "U.t. Type Code";
		expectedType = "U.T. Type";
		
		mockProfiles = new ArrayList<PSIProfile>();
		mockProfiles.add(mockProfile);
		
		mockSegments = new ArrayList<PSISegment>();
		
		mockOrderSegmentDtos = new ArrayList<OrderSegmentDto>();
		mockOrderSegmentDtos.add(mockOrderSegmentDto);
		when(mockOrderSegmentDto.getSubSegment()).thenReturn(expectedSubSegment);
		
		mockSegmentDtos = new ArrayList<SegmentDto>();
		mockSegmentDtos.add(mockSegmentDto);
		when(mockSegmentDto.getType()).thenReturn(expectedType);
		
		mockProfilePeriods = new ArrayList<PSIProfilePeriod>();
		when(mockProfilePeriodDao.retrieveByProfileId(profileId)).thenReturn(mockProfilePeriods);
		
		mockProfilePeriodDtos = new ArrayList<ProfilePeriodDto>();
		mockProfilePeriodDtos.add(mockProfilePeriodDto);
		
		service = new ProfileService();
		service.psiProfileDao = mockPsiProfileDao;
		service.profileMapper = mockProfileMapper;
		service.psiDetailDao = mockPsiDetailDao;
		service.psiOsDao = mockPsiOsDao;
		service.psiSegmentDao = mockPsiSegmentDao;
		service.segmentMapper = mockSegmentMapper;
		service.profilePeriodDao = mockProfilePeriodDao;
		service.periodMapper = mockProfilePeriodMapper;
		service.osMapper = mockOrderSegmentMapper;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockPsiDetailDao, mockPsiOsDao, mockPsiProfileDao, mockPsiSegmentDao);
	}

	@Test
	public void testGetCurrentDealerProfiles() {
		when(mockPsiProfileDao.retrieveDealerCurrentProfileListByDealerId(dealerId)).thenReturn(mockProfiles);
		
		service.getCurrentDealerProfiles(dealerId);
		
		verify(mockPsiProfileDao).retrieveDealerCurrentProfileListByDealerId(dealerId);
		verify(mockProfileMapper).mapToDto(mockProfiles);
		
	}

	@Test
	public void testGetHistoricalDealerProfiles() {
		when(mockPsiProfileDao.retrieveDealerHistoryProfileListByDealerId(dealerId)).thenReturn(mockProfiles);
		
		service.getHistoricalDealerProfiles(dealerId);
		
		verify(mockPsiProfileDao).retrieveDealerHistoryProfileListByDealerId(dealerId);
		verify(mockProfileMapper).mapToDto(mockProfiles);
		
	}

	@Test
	public void testGetDealerProfile() {
		when(mockProfile.getHeaderId()).thenReturn(headerId);
		when(mockProfile.getType()).thenReturn(typeCode);
		when(mockPsiProfileDao.retrieveProfileById(profileId, dealerId)).thenReturn(mockProfile);
		when(mockPsiDetailDao.retrieveByHeaderId(headerId)).thenReturn(mockDetails);
		when(mockPsiOsDao.retrieveByProfileAndDealer(profileId, dealerId)).thenReturn(mockOSes);
		when(mockPsiSegmentDao.retrieveByProfileDealerAndType(profileId, dealerId, typeCode)).thenReturn(mockSegments);
		when(mockProfileMapper.mapToDto(mockProfile)).thenReturn(mockDto);
		when(mockProfilePeriodMapper.mapToDto(mockProfilePeriods)).thenReturn(mockProfilePeriodDtos);
		when(mockOrderSegmentMapper.mapToDto(mockOSes, mockDetails)).thenReturn(mockOrderSegmentDtos);
		
		service.getDealerProfile(profileId, dealerId);
		
		verify(mockProfile, times(2)).getHeaderId();
		verify(mockProfile).getType();
		verify(mockPsiProfileDao).retrieveProfileById(profileId, dealerId);
		verify(mockPsiDetailDao).retrieveByHeaderId(headerId);
		verify(mockPsiOsDao).retrieveByProfileAndDealer(profileId, dealerId);
		verify(mockPsiSegmentDao).retrieveByProfileDealerAndType(profileId, dealerId, typeCode);
		verify(mockProfilePeriodMapper).mapToDto(mockProfilePeriods);
		verify(mockOrderSegmentMapper).mapToDto(mockOSes, mockDetails);
		
		verify(mockProfileMapper).mapToDto(mockProfile);
		verify(mockSegmentMapper).mapToDto(mockSegments);
		
	}

}
