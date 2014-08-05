package com.polaris.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.PSILogDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.service.mapper.PSILogMapper;

/**
 * @author bericks
 *
 */
@Service
public class LogService {

	@Autowired
	PSILogDao logDao;
	
	@Autowired
	PSILogMapper mapper;
	
	public void writeDealerChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		PSILog log = mapper.mapDealerDataToLog(header, orderSegment);
		
		int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
		log.setRowNumber(rows + 1);
		
		logDao.insert(log);
		
	}
	
	public void writeDsmChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		PSILog log = mapper.mapDsmDataToLog(header, orderSegment);
		
		int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
		log.setRowNumber(rows + 1);
		
		logDao.insert(log);
		
	}
	
	public void writeRsmChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		PSILog log = mapper.mapRsmDataToLog(header, orderSegment);
		
		int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
		log.setRowNumber(rows + 1);
		
		logDao.insert(log);
		
	}
	
}
