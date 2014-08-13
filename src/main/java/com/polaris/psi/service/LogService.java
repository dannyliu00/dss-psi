package com.polaris.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.PSILogDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.service.mapper.PSILogMapper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Service
public class LogService {

	private static final SplunkLogger LOG = new SplunkLogger(LogService.class);

	@Autowired
	PSILogDao logDao;
	
	@Autowired
	PSILogMapper mapper;
	
	public void writeDealerChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		LOG.methodStart(PolarisIdentity.get(), "writeDealerChangesToLog");

		try {
			PSILog log = mapper.mapDealerDataToLog(header, orderSegment);
			
			int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
			log.setRowNumber(rows + 1);
			
			logDao.insert(log);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "writeDealerChangesToLog", e);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "writeDealerChangesToLog");
	}
	
	public void writeDsmChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		LOG.methodStart(PolarisIdentity.get(), "writeDsmChangesToLog");

		try {
			PSILog log = mapper.mapDsmDataToLog(header, orderSegment);
			
			int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
			log.setRowNumber(rows + 1);
			
			logDao.insert(log);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "writeDsmChangesToLog", e);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "writeDsmChangesToLog");
	}
	
	public void writeRsmChangesToLog(DealerProfileHeader header, OrderSegmentDto orderSegment) {
		LOG.methodStart(PolarisIdentity.get(), "writeRsmChangesToLog");

		try {
			PSILog log = mapper.mapRsmDataToLog(header, orderSegment);
			
			int rows = logDao.getLogEntryCount(header.getId(), orderSegment.getId());
			log.setRowNumber(rows + 1);
			
			logDao.insert(log);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "writeRsmChangesToLog", e);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "writeRsmChangesToLog");
	}
	
}
