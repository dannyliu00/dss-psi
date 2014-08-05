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
		when(mockHeader.getId()).thenReturn(headerId);
		when(mockOS.getId()).thenReturn(detailId);
		
		service = new LogService();
		service.logDao = mockDao;
		service.mapper = mockMapper;
	}

	@Test
	public void testWriteDealerChangesToLog() {
		when(mockMapper.mapDealerDataToLog(mockHeader, mockOS)).thenReturn(mockLog);
		
		service.writeDealerChangesToLog(mockHeader, mockOS);
		
		verify(mockHeader).getId();
		verify(mockOS).getId();
		verify(mockMapper).mapDealerDataToLog(mockHeader, mockOS);
		verify(mockLog).setRowNumber(expectedRows + 1);
		verify(mockDao).getLogEntryCount(headerId, detailId);
		verify(mockDao).insert(mockLog);
		
		verifyNoMoreInteractions(mockLog, mockDao, mockMapper, mockHeader, mockDetail, mockOS);
	}

	@Test
	public void testWriteDsmChangesToLog() {
		when(mockMapper.mapDsmDataToLog(mockHeader, mockOS)).thenReturn(mockLog);
		
		service.writeDsmChangesToLog(mockHeader, mockOS);
		
		verify(mockHeader).getId();
		verify(mockOS).getId();
		verify(mockMapper).mapDsmDataToLog(mockHeader, mockOS);
		verify(mockLog).setRowNumber(expectedRows + 1);
		verify(mockDao).getLogEntryCount(headerId, detailId);
		verify(mockDao).insert(mockLog);
		
		verifyNoMoreInteractions(mockLog, mockDao, mockMapper, mockHeader, mockDetail, mockOS);
	}

	@Test
	public void testWriteRsmChangesToLog() {
		when(mockMapper.mapRsmDataToLog(mockHeader, mockOS)).thenReturn(mockLog);
		
		service.writeRsmChangesToLog(mockHeader, mockOS);
		
		verify(mockHeader).getId();
		verify(mockOS).getId();
		verify(mockMapper).mapRsmDataToLog(mockHeader, mockOS);
		verify(mockLog).setRowNumber(expectedRows + 1);
		verify(mockDao).getLogEntryCount(headerId, detailId);
		verify(mockDao).insert(mockLog);
		
		verifyNoMoreInteractions(mockLog, mockDao, mockMapper, mockHeader, mockDetail, mockOS);
	}

}
