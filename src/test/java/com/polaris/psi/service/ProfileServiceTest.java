package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;

public class ProfileServiceTest {
	
	private ProfileService service;
	@Mock private DealerProfileHeaderDao mockHeaderDao;
	private List<DealerProfileHeader> mockHeaders;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private Profile mockProfile;
	private int dealerId;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		dealerId = 999;
		mockHeaders = new LinkedList<DealerProfileHeader>();
		mockHeaders.add(mockHeader);
		
		when(mockHeaderDao.getDealerHeaders(dealerId)).thenReturn(mockHeaders);
		when(mockHeader.getProfile()).thenReturn(mockProfile);
		
		service = new ProfileService();
		service.headerDao = mockHeaderDao;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockHeaderDao);
	}

	@Test
	public void testGetDealerProfiles() {
		List<Profile> results = service.getDealerProfiles(dealerId);
		
		verify(mockHeaderDao).getDealerHeaders(dealerId);
		verify(mockHeader).getProfile();
		
		assertEquals(results.get(0), mockProfile);
	}

}
