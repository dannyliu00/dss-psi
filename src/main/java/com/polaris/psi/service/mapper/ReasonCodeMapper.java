/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.psi.resource.dto.ReasonCodeDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * ReasonCodeMapper maps the attributes of a ReasonCode JPA entity to a ReasonCodeDto object.
 * 
 * @author bericks
 *
 */
@Component
public class ReasonCodeMapper implements IMapper<ReasonCode, ReasonCodeDto> {

	private static final SplunkLogger LOG = new SplunkLogger(ReasonCodeMapper.class);
	
	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	public ReasonCodeDto mapToDto(ReasonCode rc) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		ReasonCodeDto dto = new ReasonCodeDto();
		dto.setDescription(rc.getDescsription());
		dto.setId(rc.getId());
		dto.setRoleId(rc.getRole());
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return dto;
	}

	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.util.List)
	 */
	@Override
	public List<ReasonCodeDto> mapToDto(List<ReasonCode> entity) {

		return null;
	}
}
