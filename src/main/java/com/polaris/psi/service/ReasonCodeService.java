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

/**
 * @author bericks
 *
 */
@Service
public class ReasonCodeService {

	@Autowired
	ReasonCodeDao dao;
	
	@Autowired
	ReasonCodeMapper mapper;
	
	public List<ReasonCodeDto> getReasonCodes(int roleId) {
		List<ReasonCode> reasonCodes = dao.getReasonCodes(roleId);
		List<ReasonCodeDto> dtos = new LinkedList<ReasonCodeDto>();
		
		for (ReasonCode reasonCode : reasonCodes) {
			dtos.add(mapper.mapToDto(reasonCode));
		}
		
		return dtos;
	}

}
