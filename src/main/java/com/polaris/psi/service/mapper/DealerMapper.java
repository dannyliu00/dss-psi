/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.resource.dto.DealerDto;

/**
 * @author bericks
 *
 */
@Component
public class DealerMapper {

	public DealerDto mapToDto(Dealer dealer) {
		DealerDto dto = new DealerDto();
		dto.setCity(dealer.getCity());
		dto.setCompany(dealer.getCompany());
		dto.setDealerId(dealer.getId());
		dto.setName(dealer.getName());
		dto.setState(dealer.getState());
		dto.setZip(dealer.getZip());
		
		return dto;
	}
	
}
