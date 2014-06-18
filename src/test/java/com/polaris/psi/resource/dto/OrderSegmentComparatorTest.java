package com.polaris.psi.resource.dto;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderSegmentComparatorTest {
	
	private OrderSegmentComparator comparator;
	@Mock private OrderSegmentDto os1, os2, os3;
	private Calendar cal;
	private Date oneDate, anotherDate;
	private String name1, name2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		name1 = "name 1";
		name2 = "name 2";

		when(os1.getName()).thenReturn(name1);
		when(os2.getName()).thenReturn(name2);
		when(os3.getName()).thenReturn(name1);
		
		cal = Calendar.getInstance();
		oneDate = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 3);
		anotherDate = cal.getTime();
		
		when(os1.getPeriodStartDate()).thenReturn(oneDate);
		when(os2.getPeriodStartDate()).thenReturn(anotherDate);
		when(os3.getPeriodStartDate()).thenReturn(anotherDate);
		
		comparator = new OrderSegmentComparator();
	}

	@Test
	public void testCompareStartNameOnly() {
		int result = comparator.compare(os1, os2);
		
		assertTrue(result < 0);
		
		verify(os1).getName();
		verify(os2).getName();
		
		verifyNoMoreInteractions(os1, os2);
		verifyZeroInteractions(os3);
	}

	@Test
	public void testCompareNameAndStartDate() {
		int result = comparator.compare(os1, os3);
		
		assertTrue(result < 0);
		
		verify(os1).getName();
		verify(os1).getPeriodStartDate();
		verify(os3).getName();
		verify(os3).getPeriodStartDate();
		
		verifyNoMoreInteractions(os1, os3);
		verifyZeroInteractions(os2);
	}

	@Test
	public void testCompareEqual() {
		int result = comparator.compare(os1, os1);
		
		assertTrue(result == 0);
		
		verify(os1, times(2)).getName();
		verify(os1, times(2)).getPeriodStartDate();
		
		verifyNoMoreInteractions(os1);
		verifyZeroInteractions(os2, os3);
	}

}
