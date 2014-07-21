package com.polaris.psi.service;

import static org.junit.Assert.fail;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.PSILogDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.service.mapper.PSILogMapper;

public class LogServiceTest {

	private LogService service;
	@Mock private PSILog mockLog;
	@Mock private PSILogDao mockDao;
	@Mock private PSILogMapper mockMapper;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetail mockDetail;
	@Mock private PSIOrderSegment mockOS;
	private String userName;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userName = "UTUser";
		
		when(mockMapper.mapDealerDataToLog(mockHeader, mockDetail, mockOS, userName)).thenReturn(mockLog);		
		when(mockHeader.getChangeUser()).thenReturn(userName);
		
		service = new LogService();
		service.logDao = mockDao;
		service.mapper = mockMapper;
	}

	@Test
	public void testWriteDealerChangesToLog() {
		
		service.writeDealerChangesToLog(mockHeader, mockDetail, mockOS);
		
		verify(mockMapper).mapDealerDataToLog(mockHeader, mockDetail, mockOS, userName);
		verify(mockHeader).getChangeUser();
		verify(mockDao).insert(mockLog);
		
		verifyNoMoreInteractions(mockLog, mockDao, mockMapper, mockHeader, mockDetail, mockOS);
	}

}
