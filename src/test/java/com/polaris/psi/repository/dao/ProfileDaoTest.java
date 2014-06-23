package com.polaris.psi.repository.dao;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProfileDaoTest {

	private ProfileDao dao;
	@Mock private EntityManager mockEM;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dao = new ProfileDao();
	}

	@Test
	public void testRetrieveListByDealerId() {
//		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProfileById() {
//		fail("Not yet implemented");
	}

}
