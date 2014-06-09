/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.resource.dto.DealerDto;

/**
 * @author bericks
 *
 */
@Component
public class DealerMapper {

	public DealerDto mapToDto(Dealer dealer, DealerAndDsm dsm) {
		DealerDto dto = new DealerDto();
		dto.setCity(dealer.getCity().trim());
		dto.setCompany(dealer.getCompany());
		dto.setDealerId(dealer.getId());
		dto.setName(dealer.getName().trim());
		dto.setState(dealer.getState().trim());
		dto.setZip(dealer.getZip().trim());
		
		dto.setDsmName(dsm.getDsmName().trim());
		
		return dto;
	}
	
}
