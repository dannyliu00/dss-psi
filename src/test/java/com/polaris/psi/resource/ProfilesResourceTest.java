package com.polaris.psi.resource;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.polaris.psi.model.InventoryProfile;

public class ProfilesResourceTest {

	ProfilesResource resource;
	Date expectedDate;
	String expectedName;
	int expectedId;
	String expectedStatus;
	
	
	@Before
	public void setUp() throws Exception {
		Calendar modified = Calendar.getInstance();
		modified.set(2014, 3, 30);
		expectedDate = modified.getTime();
		expectedName = "Victory Inventory Profile 04/30/14";
		expectedId = 999;
		expectedStatus = "Not Started";
		resource = new ProfilesResource();
	}

	@Test
	public void testGetProfiles() {
		int id = 999;
		List<InventoryProfile> results = resource.getProfiles(id);
		
		assertTrue(results.size() == 1);
		InventoryProfile result = results.get(0);
		assertTrue(result.getName().equals(expectedName));
		assertTrue(result.getProfileId() == expectedId);
		assertTrue(result.getStatus().equals(expectedStatus));
	}

}
