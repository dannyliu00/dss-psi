package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class HeaderDataMapperTest {

	private HeaderDataMapper mapper;
	@Mock private OrderSegmentDto mockDto;
	@Mock private DealerProfileHeaderStatus mockStatus;
	@Mock private DealerProfileHeader mockHeader;
	private String expectedUserName, expectedEmailAddress;
	private Integer expectedDealerId, expectedProfileId;
	private boolean expectedCompliance;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedUserName = "UTUser";
		expectedEmailAddress = "utuser@localhost";
		expectedDealerId = 999;
		expectedProfileId = 888;
		expectedCompliance = false;
		
		when(mockDto.getModifiedUserName()).thenReturn(expectedUserName);
		when(mockDto.getDealerEmail()).thenReturn(expectedEmailAddress);
		when(mockDto.getDealerId()).thenReturn(expectedDealerId);
		when(mockDto.getProfileId()).thenReturn(expectedProfileId);
		
		mapper = new HeaderDataMapper();
	}

	@Test
	public void testCreateNewNonSubmittedNonApprovedHeader() {
		DealerProfileHeader result = mapper.createNewNonSubmittedNonApprovedHeader(mockDto, mockStatus, expectedCompliance);
		
		assertEquals(expectedUserName, result.getCreatedUser());
		assertEquals(expectedUserName, result.getChangeUser());
		assertEquals(expectedEmailAddress, result.getEmailAddress());
		assertEquals(expectedDealerId.intValue(), result.getDealerId());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.PROGRAM_CODE, result.getChangedProgram());
		assertEquals(Constants.PROGRAM_CODE, result.getCreatedProgram());
		assertNotNull(result.getCreatedDate());
		assertNotNull(result.getCreatedTime());
		assertNotNull(result.getChangedDate());
		assertNotNull(result.getChangedTime());
		assertNotNull(result.getStatus());
		assertNotNull(result.getSubmittedDate());
		assertNotNull(result.getSubmittedTime());
		assertNotNull(result.getApprovedDate());
		assertNotNull(result.getApprovedTime());
		
		verify(mockDto, times(2)).getModifiedUserName();
		verify(mockDto).getDealerEmail();
		verify(mockDto).getDealerId();
		verify(mockDto).getProfileId();
	}
	
	@Test
	public void testCreateNewSubmittedHeader() {
		DealerProfileHeader result = mapper.createNewSubmittedHeader(mockDto, mockStatus, expectedCompliance);
		
		assertEquals(expectedUserName, result.getCreatedUser());
		assertEquals(expectedUserName, result.getChangeUser());
		assertEquals(expectedEmailAddress, result.getEmailAddress());
		assertEquals(expectedDealerId.intValue(), result.getDealerId());
		assertEquals(expectedProfileId.intValue(), result.getProfileId());
		assertEquals(Constants.PROGRAM_CODE, result.getChangedProgram());
		assertEquals(Constants.PROGRAM_CODE, result.getCreatedProgram());
		assertEquals(expectedCompliance, result.isNonCompliant());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		assertTrue(cal.getTime().before(result.getSubmittedDate()));
		assertTrue(cal.getTime().before(result.getSubmittedTime()));
		assertTrue(cal.getTime().before(result.getCreatedDate()));
		assertTrue(cal.getTime().before(result.getCreatedTime()));
		assertTrue(cal.getTime().before(result.getChangedDate()));
		assertTrue(cal.getTime().before(result.getChangedTime()));
		assertTrue(cal.getTime().after(result.getApprovedDate()));
		assertTrue(cal.getTime().after(result.getApprovedTime()));

		assertNotNull(result.getStatus());
		
		verify(mockDto, times(2)).getModifiedUserName();
		verify(mockDto).getDealerEmail();
		verify(mockDto).getDealerId();
		verify(mockDto).getProfileId();
	}
	
	@Test
	public void testUpdateApprovedHeader() {
		mapper.updateApprovedHeader(mockHeader, mockStatus, expectedUserName, expectedCompliance);
		
		verify(mockHeader).setApprovedDate(any(Date.class));
		verify(mockHeader).setApprovedTime(any(Date.class));
		verify(mockHeader).setChangedDate(any(Date.class));
		verify(mockHeader).setChangedProgram(Constants.PROGRAM_CODE);
		verify(mockHeader).setChangedTime(any(Date.class));
		verify(mockHeader).setChangeUser(expectedUserName);
		verify(mockHeader).setNonCompliant(expectedCompliance);
		verify(mockHeader).setStatus(mockStatus);
	}

	@Test
	public void testSetDateDefaultDateResult() {
		Date result = mapper.setDate(null);
		assertEquals(Constants.DEFAULT_DATE.getTime(), result);
	}

	@Test
	public void testSetDateDefaultRealResult() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Date result = mapper.setDate(date);
		assertEquals(date, result);
	}

}
