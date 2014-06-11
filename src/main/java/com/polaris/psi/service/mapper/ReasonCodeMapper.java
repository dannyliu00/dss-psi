/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.psi.resource.dto.ReasonCodeDto;

/**
 * @author bericks
 *
 */
@Component
public class ReasonCodeMapper {

	public ReasonCodeDto mapToDto(ReasonCode rc) {
		ReasonCodeDto dto = new ReasonCodeDto();
		dto.setDescription(rc.getDescsription());
		dto.setId(rc.getId());
		dto.setRoleId(rc.getRole());
		
		return dto;
	}
}
