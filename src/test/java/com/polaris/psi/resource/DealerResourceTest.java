package com.polaris.psi.resource;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.polaris.psi.resource.dto.DealerDTO;

public class DealerResourceTest {

	private DealerResource resource;
	private int id;
	private String expectedName;
	private int expectedId;
	private String expectedCity;
	private String expectedState;
	private String expectedZip;
	
	@Before
	public void setUp() throws Exception {
		id = 1111;
		expectedName = "ENGELHART MOTORSPORTS";
		expectedId = 2021900;
		expectedCity = "MADISON";
		expectedState = "WI";
		expectedZip = "53713";
		resource = new DealerResource();
	}

	@Test
	public void testGetDealer() {
		DealerDTO actual = resource.getDealer(id);
		assertTrue(actual.getCity().equals(expectedCity));
		assertTrue(actual.getDealerId().equals(expectedId));
		assertTrue(actual.getName().equals(expectedName));
		assertTrue(actual.getState().equals(expectedState));
		assertTrue(actual.getZip().equals(expectedZip));
	}

}
