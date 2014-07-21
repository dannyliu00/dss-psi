package com.polaris.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.PSILogDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.repository.entity.PSIOrderSegment;
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
	
	public void writeToLog(DealerProfileHeader header, DealerProfileDetail detail, PSIOrderSegment os) {
		PSILog log = mapper.mapDealerDataToLog(header, detail, os, header.getChangeUser());
		
		logDao.insert(log);
	}
	
}
