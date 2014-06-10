package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.OrderSegment;
import com.polaris.psi.repository.entity.Profile;
import com.polaris.psi.repository.entity.ProfileAndPeriod;
import com.polaris.psi.repository.entity.ProfileOrderSegmentCompliance;
import com.polaris.psi.repository.entity.ProfileSegmentCompliance;
import com.polaris.psi.repository.entity.ProfileStatus;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.CommonUtils;

public class ProfileMapperTest {

	private ProfileMapper mapper;
	@Mock private Profile mockProfile;
	@Mock private ProfileStatus mockStatus;
	private int expectedId;
	private String expectedTargetCompleteDate, expectedName, expectedStatus;
	private Date expectedModifiedDate;

	private List<ProfileAndPeriod> periods;
	private List<OrderSegment> orderSegments;
	private List<ProfileOrderSegmentCompliance> osComplianceValues;
	private List<ProfileSegmentCompliance> segmentComplianceValues;
	private List<DealerProfileHeader> profileHeaders;
	
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
	public void testMapToDto() {
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

}
