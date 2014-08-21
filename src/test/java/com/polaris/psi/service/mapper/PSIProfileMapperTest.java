package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.ProfileDto;

public class PSIProfileMapperTest {
	
	private PSIProfileMapper mapper;
	@Mock private ProfileTypeMapper mockTypeMapper;
	@Mock private PSIProfile mockProfile;
	@Mock private ProfileDto mockDto;
	private List<PSIProfile> psiProfiles;
	private String expectedName, expectedStatus, expectedType, expectedEmail;
	private Integer expectedProfileId;
	private Date expectedTargetCompletion, expectedModified;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedName = "UT Name";
		expectedStatus = Constants.EXCEPTION_REQUESTED;
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
		
		psiProfiles = new ArrayList<PSIProfile>();
		psiProfiles.add(mockProfile);
		
		mapper = new PSIProfileMapper();
		mapper.typeMapper = mockTypeMapper;
	}

	@Test
	public void testMapToDtoPSIProfileList() {

		List<ProfileDto> results = mapper.mapToDto(psiProfiles);
		
		assertEquals(1, results.size());
		
		ProfileDto result = results.get(0);
		assertEquals(expectedModified, result.getModifiedDate());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.PENDING_STATUS, result.getStatus());
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

	@Test
	public void testMapToDtoPSIProfileIsNonDealer() {

		ProfileDto result = mapper.mapToDto(mockProfile, false);
		
		assertEquals(expectedModified, result.getModifiedDate());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.EXCEPTION_REQUESTED, result.getStatus());
		assertEquals(expectedTargetCompletion, result.getTargetCompletionDate());
		assertEquals(expectedEmail, result.getDealerEmail());
		
		verify(mockProfile).getId();
		verify(mockProfile).getLastModifiedDate();
		verify(mockProfile).getName();
		verify(mockProfile, times(2)).getStatus();
		verify(mockProfile).getTargetCompleteDate();
		verify(mockProfile, times(2)).getType();
		verify(mockProfile).getEmail();
		
		verify(mockTypeMapper).mapTypeToProfile(expectedType, result);
		verifyNoMoreInteractions(mockProfile, mockTypeMapper, mockDto);
	}

	@Test
	public void testMapToDtoPSIProfileIsNonDealerNullStatus() {
		when(mockProfile.getStatus()).thenReturn(null);

		ProfileDto result = mapper.mapToDto(mockProfile, false);
		
		assertEquals(expectedModified, result.getModifiedDate());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.DEFAULT_PROFILE_STATUS, result.getStatus());
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

	@Test
	public void testMapToDtoPSIProfileIsDealer() {

		ProfileDto result = mapper.mapToDto(mockProfile, true);
		
		assertEquals(expectedModified, result.getModifiedDate());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.PENDING_STATUS, result.getStatus());
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

	@Test
	public void testGetStatusDefault() {
		String result = mapper.getStatus(null);
		assertEquals(Constants.DEFAULT_PROFILE_STATUS, result);
	}

	@Test
	public void testGetStatusInProgress() {
		String result = mapper.getStatus(Constants.IN_PROGRESS_STATUS);
		assertEquals(Constants.IN_PROGRESS_STATUS, result);
	}

	@Test
	public void testGetStatusPending() {
		String result = mapper.getStatus(Constants.PENDING_STATUS);
		assertEquals(Constants.PENDING_STATUS, result);
	}

	@Test
	public void testGetStatusReturnedToDealer() {
		String result = mapper.getStatus(Constants.RETURNED_TO_DEALER);
		assertEquals(Constants.RETURNED_TO_DEALER, result);
	}

	@Test
	public void testGetStatusReturnedToDSM() {
		String result = mapper.getStatus(Constants.RETURNED_TO_DSM);
		assertEquals(Constants.PENDING_STATUS, result);
	}

	@Test
	public void testGetStatusExceptionRequested() {
		String result = mapper.getStatus(Constants.EXCEPTION_REQUESTED);
		assertEquals(Constants.PENDING_STATUS, result);
	}

	@Test
	public void testGetStatusApprovedAsRequested() {
		String result = mapper.getStatus(Constants.APPROVED_AS_REQUESTED);
		assertEquals(Constants.APPROVED, result);
	}

	@Test
	public void testGetStatusApprovedWithChanges() {
		String result = mapper.getStatus(Constants.APPROVED_W_CHANGES);
		assertEquals(Constants.APPROVED, result);
	}

	@Test
	public void testGetStatusApprovedAsCompliant() {
		String result = mapper.getStatus(Constants.APPROVED_COMPLIANT);
		assertEquals(Constants.APPROVED, result);
	}

	@Test
	public void testGetStatusApprovedAsNonCompliant() {
		String result = mapper.getStatus(Constants.APPROVED_NONCOMPLIANT);
		assertEquals(Constants.APPROVED_NONCOMPLIANT, result);
	}

}
