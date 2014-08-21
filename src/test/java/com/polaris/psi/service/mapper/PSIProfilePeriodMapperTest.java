package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSIProfilePeriod;
import com.polaris.psi.resource.dto.ProfilePeriodDto;

public class PSIProfilePeriodMapperTest {

	private PSIProfilePeriodMapper mapper;
	@Mock PSIProfilePeriod mockPeriod;
	private Integer expectedId, expectedSort, expectedMin, expectedRec, expectedMax;
	private String expectedName, expectedCode;
	private Date expectedEndDate, expectedStartDate;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedSort = 1;
		expectedName = "UT Name";
		expectedCode = "UT Code";
		expectedEndDate = Calendar.getInstance().getTime();
		expectedStartDate = Calendar.getInstance().getTime();
		expectedMin = 1;
		expectedRec = 2;
		expectedMax = 3;
		
		when(mockPeriod.getEndDate()).thenReturn(expectedEndDate);
		when(mockPeriod.getId()).thenReturn(expectedId);
		when(mockPeriod.getName()).thenReturn(expectedName);
		when(mockPeriod.getPeriodCode()).thenReturn(expectedCode);
		when(mockPeriod.getSort()).thenReturn(expectedSort);
		when(mockPeriod.getStartDate()).thenReturn(expectedStartDate);
		when(mockPeriod.getMinimum()).thenReturn(expectedMin);
		when(mockPeriod.getRecommended()).thenReturn(expectedRec);
		when(mockPeriod.getMaximum()).thenReturn(expectedMax);
		
		mapper = new PSIProfilePeriodMapper();
	}

	@Test
	public void testMapToDtoPSIProfilePeriod() {
		ProfilePeriodDto result = mapper.mapToDto(mockPeriod);
		
		assertEquals(expectedEndDate, result.getEndDate());
		assertEquals(expectedCode, result.getCode());
		assertEquals(expectedId.intValue(), result.getId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedStartDate, result.getStartDate());
		assertEquals(expectedSort.intValue(), result.getSort());
		assertEquals(expectedMin.intValue(), result.getRecMinimum());
		assertEquals(expectedRec.intValue(), result.getRecommended());
		assertEquals(expectedMax.intValue(), result.getRecMaximum());
		
		verify(mockPeriod).getEndDate();
		verify(mockPeriod).getId();
		verify(mockPeriod).getName();
		verify(mockPeriod).getPeriodCode();
		verify(mockPeriod).getSort();
		verify(mockPeriod).getStartDate();
		verify(mockPeriod).getMinimum();
		verify(mockPeriod).getRecommended();
		verify(mockPeriod).getMaximum();
	}

}
