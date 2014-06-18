package com.polaris.psi.resource.dto.util;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.BaseDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentQuantityDto;

public class ProfilePeriodTotalsCalculatorTest {

	private ProfilePeriodTotalsCalculator calculator;
	@Mock private ProfileDto profile;
	@Mock private ProfilePeriodDto period1, period2;
	@Mock private SegmentQuantityDto segment1, segment2;
	@SuppressWarnings("rawtypes")
	private List periods;
	@SuppressWarnings("rawtypes")
	private List quantities;
	private int minimum;
	private int maximum;
	private int recommended;
	private int actual;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		minimum = 1;
		maximum = 5;
		recommended = 3;
		actual = 0;
		
		periods = new ArrayList<BaseDto>();
		quantities = new ArrayList<BaseDto>();
		
		when(segment1.getRecMinimum()).thenReturn(minimum);
		when(segment1.getRecMaximum()).thenReturn(maximum);
		when(segment1.getRecommended()).thenReturn(recommended);
		when(segment1.getActual()).thenReturn(actual);
		when(segment2.getRecMinimum()).thenReturn(minimum);
		when(segment2.getRecMaximum()).thenReturn(maximum);
		when(segment2.getRecommended()).thenReturn(recommended);
		when(segment2.getActual()).thenReturn(actual);
		
		when(period1.getQuantities()).thenReturn(quantities);
		when(period2.getQuantities()).thenReturn(quantities);
		
		when(profile.getPeriods()).thenReturn(periods);
		
		calculator = new ProfilePeriodTotalsCalculator();
	}

	@Test
	public void testCalculateProfilePeriodTotals() {
//		calculator.calculateProfilePeriodTotals(profile);
//		
//		verify(profile).getPeriods();
//		verify(period1).getQuantities();
//		verify(period2).getQuantities();
//
//		verify(segment1).getActual();
//		verify(segment1).getRecMaximum();
//		verify(segment1).getRecMinimum();
//		verify(segment1).getRecMaximum();
//
//		verify(segment2).getActual();
//		verify(segment2).getRecMaximum();
//		verify(segment2).getRecMinimum();
//		verify(segment2).getRecMaximum();
//		
//		verify(profile.getRecMaximum()).equals(maximum * 2);
//		verify(profile.getRecMinimum()).equals(minimum * 2);
//		verify(profile.getRecommended()).equals(recommended * 2);
	}

}
