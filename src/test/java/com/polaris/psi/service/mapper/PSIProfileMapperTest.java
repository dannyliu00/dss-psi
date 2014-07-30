package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.ProfileDto;

public class PSIProfileMapperTest {
	
	private PSIProfileMapper mapper;
	@Mock private ProfileTypeMapper mockTypeMapper;
	@Mock private PSIProfile mockProfile;
	@Mock private ProfileDto mockDto;
	private String expectedName, expectedStatus, expectedType, expectedEmail;
	private Integer expectedProfileId;
	private Date expectedTargetCompletion, expectedModified;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedName = "UT Name";
		expectedStatus = "UT Status";
		expectedType = "UT Type";
		expectedEmail = "UT@local";
		expectedTargetCompletion = Calendar.getInstance().getTime();
		expectedModified = Calendar.getInstance().getTime();
		expectedProfileId = 999;
		
		when(mockProfile.getId()).thenReturn(expectedProfileId);
		when(mockProfile.getLastModifiedDate()).thenReturn(expectedModified);
		when(mockProfile.getName()).thenReturn(expectedName);
		when(mockProfile.getStatus()).thenReturn(expectedStatus);
		when(mockProfile.getTargetCompleteDate()).thenReturn(expectedTargetCompletion);
		when(mockProfile.getType()).thenReturn(expectedType);
		when(mockProfile.getEmail()).thenReturn(expectedEmail);
		
		mapper = new PSIProfileMapper();
		mapper.typeMapper = mockTypeMapper;
	}

	@Test
	public void testMapToDtoPSIProfile() {

		ProfileDto result = mapper.mapToDto(mockProfile);
		
		assertEquals(expectedModified, result.getModifiedDate());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(expectedStatus, result.getStatus());
		assertEquals(expectedTargetCompletion, result.getTargetCompletionDate());
		assertEquals(expectedEmail, result.getDealerEmail());
		
		verify(mockProfile).getId();
		verify(mockProfile).getLastModifiedDate();
		verify(mockProfile).getName();
		verify(mockProfile).getStatus();
		verify(mockProfile).getTargetCompleteDate();
		verify(mockProfile, times(2)).getType();
		verify(mockProfile).getEmail();
		
		verify(mockTypeMapper).mapTypeToProfile(expectedType, result);
		verifyNoMoreInteractions(mockProfile, mockTypeMapper, mockDto);
	}

}
