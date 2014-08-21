/**
 * 
 */
package com.polaris.psi.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.ReasonCodeDao;
import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.psi.resource.dto.ReasonCodeDto;
import com.polaris.psi.service.mapper.ReasonCodeMapper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Service
public class ReasonCodeService {

	private static final SplunkLogger LOG = new SplunkLogger(ReasonCodeService.class);

	@Autowired
	ReasonCodeDao dao;
	
	@Autowired
	ReasonCodeMapper mapper;
	
	/**
	 * @param roleId
	 * @return
	 */
	public List<ReasonCodeDto> getReasonCodes(int roleId) {
		
		LOG.methodStart(PolarisIdentity.get(), "getReasonCodes");

		List<ReasonCode> reasonCodes = dao.getReasonCodes(roleId);
		List<ReasonCodeDto> dtos = new LinkedList<ReasonCodeDto>();
		
		for (ReasonCode reasonCode : reasonCodes) {
			dtos.add(mapper.mapToDto(reasonCode));
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "getReasonCodes");

		return dtos;
	}
	
	/*
	 * Get all reason codes
	 */
	public List<ReasonCodeDto> getAllCodes() {
		
		LOG.methodStart(PolarisIdentity.get(), "getReasonCodes");

		List<ReasonCode> reasonCodes = dao.selectAll();
		List<ReasonCodeDto> dtos = new LinkedList<ReasonCodeDto>();
		
		for (ReasonCode reasonCode : reasonCodes) {
			dtos.add(mapper.mapToDto(reasonCode));
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "getReasonCodes");

		return dtos;
	}
}
