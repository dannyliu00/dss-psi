package com.polaris.psi.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;

public class StatusServiceTest {

	private StatusService service;
	@Mock private DealerProfileHeaderStatusDao mockDao;
	@Mock private DealerProfileHeaderStatus mockApprovedStatus, mockPendingStatus;
	private List<DealerProfileHeaderStatus> statii;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		statii = new ArrayList<DealerProfileHeaderStatus>();
		statii.add(mockApprovedStatus);
		statii.add(mockPendingStatus);
		
		when(mockDao.getApprovedWithChangesStatus()).thenReturn(mockApprovedStatus);
		when(mockDao.getPendingStatus()).thenReturn(mockPendingStatus);
		when(mockDao.selectAll()).thenReturn(statii);
		
		service = new StatusService();
		service.statusDao = mockDao;
	}

	@Test
	public void testGetPendingStatus() {
		service.getPendingStatus();
		
		verify(mockDao).getPendingStatus();
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetApprovedWithChangesStatus() {
		service.getApprovedWithChangesStatus();
		
		verify(mockDao).getApprovedWithChangesStatus();
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetAllStatus() {
		service.getAllStatus();
		
		verify(mockDao).selectAll();
		verifyNoMoreInteractions(mockDao);
	}

}
