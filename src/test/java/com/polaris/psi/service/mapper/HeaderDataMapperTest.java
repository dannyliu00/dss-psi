package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	private String expectedUserName, expectedEmailAddress;
	private Integer expectedDealerId, expectedProfileId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedUserName = "UTUser";
		expectedEmailAddress = "utuser@localhost";
		expectedDealerId = 999;
		expectedProfileId = 888;
		
		when(mockDto.getModifiedUserName()).thenReturn(expectedUserName);
		when(mockDto.getDealerEmail()).thenReturn(expectedEmailAddress);
		when(mockDto.getDealerId()).thenReturn(expectedDealerId);
		when(mockDto.getProfileId()).thenReturn(expectedProfileId);
		
		mapper = new HeaderDataMapper();
	}

	@Test
	public void testCreateNewNonSubmittedNonApprovedHeader() {
		DealerProfileHeader result = mapper.createNewNonSubmittedNonApprovedHeader(mockDto, mockStatus);
		
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
