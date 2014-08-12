/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.psi.resource.dto.ReasonCodeDto;

/**
 * ReasonCodeMapper maps the attributes of a ReasonCode JPA entity to a ReasonCodeDto object.
 * 
 * @author bericks
 *
 */
@Component
public class ReasonCodeMapper implements IMapper<ReasonCode, ReasonCodeDto> {

	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	public ReasonCodeDto mapToDto(ReasonCode rc) {
		ReasonCodeDto dto = new ReasonCodeDto();
		dto.setDescription(rc.getDescsription());
		dto.setId(rc.getId());
		dto.setRoleId(rc.getRole());
		
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
