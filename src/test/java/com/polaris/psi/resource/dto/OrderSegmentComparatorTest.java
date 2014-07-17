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
	@Mock private OrderSegmentDto os1, os2;
	private int sort1, sort2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		sort1 = 1;
		sort2 = 2;
		
		when(os1.getSort()).thenReturn(sort1);
		when(os2.getSort()).thenReturn(sort2);

		comparator = new OrderSegmentComparator();
	}

	@Test
	public void testCompare() {
		int result = comparator.compare(os1, os2);
		
		assertTrue(result < 0);
		
		verify(os1).getSort();
		verify(os2).getSort();
		
		verifyNoMoreInteractions(os1, os2);
	}

	@Test
	public void testCompareEqual() {
		int result = comparator.compare(os1, os1);
		
		assertTrue(result == 0);
		
		verify(os1, times(2)).getSort();
		
		verifyNoMoreInteractions(os1);
		verifyZeroInteractions(os2);
	}

}
