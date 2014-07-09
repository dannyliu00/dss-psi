package com.polaris.psi.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;

public class StatusServiceTest {

	private StatusService service;
	@Mock private DealerProfileHeaderStatusDao mockDao;
	@Mock private DealerProfileHeaderStatus mockApprovedStatus, mockPendingStatus, mockStatus;
	private List<DealerProfileHeaderStatus> statii;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		statii = new ArrayList<DealerProfileHeaderStatus>();
		statii.add(mockApprovedStatus);
		statii.add(mockPendingStatus);
		
		when(mockDao.getStatus(anyString())).thenReturn(mockStatus);
		when(mockDao.selectAll()).thenReturn(statii);
		
		service = new StatusService();
		service.statusDao = mockDao;
	}

	@Test
	public void testGetPendingStatus() {
		service.getPendingStatus();
		
		verify(mockDao).getStatus(Constants.PENDING_STATUS);
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetApprovedWithChangesStatus() {
		service.getApprovedWithChangesStatus();
		
		verify(mockDao).getStatus(Constants.APPROVED_W_CHANGES);
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetApprovedAsRequestedStatus() {
		service.getApprovedAsRequestedStatus();
		
		verify(mockDao).getStatus(Constants.APPROVED_AS_REQUESTED);
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetExceptionRequestedStatus() {
		service.getExceptionRequestedStatus();
		
		verify(mockDao).getStatus(Constants.EXCEPTION_REQUESTED);
		verifyNoMoreInteractions(mockDao);
	}

	@Test
	public void testGetAllStatus() {
		service.getAllStatus();
		
		verify(mockDao).selectAll();
		verifyNoMoreInteractions(mockDao);
	}

}
