package com.polaris.psi.service;

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
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.service.mapper.PSILogMapper;

public class LogServiceTest {

	private LogService service;
	@Mock private PSILog mockLog;
	@Mock private PSILogDao mockDao;
	@Mock private PSILogMapper mockMapper;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetail mockDetail;
	@Mock private OrderSegmentDto mockOS;
	private int expectedCount, headerId, detailId, expectedRows;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		headerId = 999;
		detailId = 888;
		expectedCount = 0;
		expectedRows = 2;
		
		when(mockDao.count()).thenReturn(expectedCount);
		when(mockDao.getLogEntryCount(headerId, detailId)).thenReturn(expectedRows);
		when(mockMapper.mapDealerDataToLog(mockHeader, mockOS)).thenReturn(mockLog);
		when(mockHeader.getId()).thenReturn(headerId);
		when(mockOS.getId()).thenReturn(detailId);
		
		service = new LogService();
		service.logDao = mockDao;
		service.mapper = mockMapper;
	}

	@Test
	public void testWriteDealerChangesToLogWithOrderSegmentDto() {
		
		service.writeDealerChangesToLog(mockHeader, mockOS);
		
		verify(mockHeader).getId();
		verify(mockOS).getId();
		verify(mockMapper).mapDealerDataToLog(mockHeader, mockOS);
		verify(mockLog).setRowNumber(expectedRows + 1);
		verify(mockDao).getLogEntryCount(headerId, detailId);
		verify(mockDao).insert(mockLog);
		
		verifyNoMoreInteractions(mockLog, mockDao, mockMapper, mockHeader, mockDetail, mockOS);
	}

}
