package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.SegmentCompliance;
import com.polaris.psi.repository.entity.ProfileStatus;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.CommonUtils;

public class ProfileMapperTest {

	private ProfileMapper mapper;
	private List<Profile> mockProfiles;
	@Mock private Profile mockProfile;
	@Mock private ProfileStatus mockStatus;
	private int expectedId, expectedMax, expectedMin;
	private String expectedTargetCompleteDate, expectedName, expectedStatus;
	private Date expectedModifiedDate;

	private List<SegmentCompliance> segmentComplianceValues;
	@Mock private SegmentCompliance mockSegmentCompliance;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedTargetCompleteDate = "04/30/2014";
		expectedName = "U.T. Profile Name";
		
		expectedModifiedDate = CommonUtils.convertDate(expectedTargetCompleteDate);
		expectedStatus = "U.T. Not Started";
		
		when(mockProfile.getId()).thenReturn(expectedId);
		when(mockProfile.getName()).thenReturn(expectedName);
		when(mockProfile.getTargetCompleteDate()).thenReturn(expectedTargetCompleteDate);
		when(mockProfile.getStatus()).thenReturn(mockStatus);
		when(mockStatus.getDescription()).thenReturn(expectedStatus);
		
		mapper = new ProfileMapper();
	}

	@Test
	public void testMapToDtoList() {
		mockProfiles = new LinkedList<Profile>();
		mockProfiles.add(mockProfile);
		
		List<ProfileDto> results = mapper.mapToDto(mockProfiles);
		
		assertEquals(mockProfiles.size(), results.size());
	}
	
	@Test
	public void testMapToDtoProfile() {
		ProfileDto actual = mapper.mapToDto(mockProfile);
		
		verify(mockProfile).getId();
		verify(mockProfile).getName();
		verify(mockProfile).getTargetCompleteDate();
		verify(mockProfile).getStatus();
		verify(mockStatus).getDescription();
		
		assertEquals(expectedId, actual.getProfileId());
		assertEquals(expectedModifiedDate, actual.getModifiedDate());
		assertEquals(expectedName, actual.getName());
		assertEquals(expectedStatus, actual.getStatus());
	}
	
	@Test
	public void testGetMaxValues() {
		expectedMax = 5;
		segmentComplianceValues = new LinkedList<SegmentCompliance>();
		segmentComplianceValues.add(mockSegmentCompliance);
		
		when(mockSegmentCompliance.getMaximum()).thenReturn(expectedMax);
		
		int actualMax = mapper.getMaxValues(segmentComplianceValues);
		
		assertEquals(expectedMax, actualMax);
	}
	
	@Test
	public void testGetMinValues() {
		expectedMin = 1;
		segmentComplianceValues = new LinkedList<SegmentCompliance>();
		segmentComplianceValues.add(mockSegmentCompliance);
		
		when(mockSegmentCompliance.getMinimum()).thenReturn(expectedMin);
		
		int actualMin = mapper.getMinValues(segmentComplianceValues);
		
		assertEquals(expectedMin, actualMin);
	}

}
