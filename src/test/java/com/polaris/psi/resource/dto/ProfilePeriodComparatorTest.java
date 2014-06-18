package com.polaris.psi.resource.dto;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ProfilePeriodComparatorTest {

	private ProfilePeriodComparator comparator;
	private ProfilePeriodDto lhs, rhs;

	@Before
	public void setUp() throws Exception {
		lhs = new ProfilePeriodDto();
		rhs = new ProfilePeriodDto();
		
		Calendar cal = Calendar.getInstance();
		Date lhsStartDate = cal.getTime();

		cal.add(Calendar.DAY_OF_MONTH, 3);
		Date rhsStartDate = cal.getTime();
		
		lhs.setStartDate(lhsStartDate);
		rhs.setStartDate(rhsStartDate);
		
		comparator = new ProfilePeriodComparator();
	}

	@Test
	public void testCompare() {
		int result = comparator.compare(lhs, rhs);
		
		assertTrue(result < 0);
		
		result = comparator.compare(rhs, lhs);
		
		assertTrue(result > 0);
	}

}
