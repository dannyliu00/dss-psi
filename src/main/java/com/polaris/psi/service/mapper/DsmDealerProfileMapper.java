/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.DsmDealerProfilesDto;

/**
 * @author bericks
 *
 */
@Component
public class DsmDealerProfileMapper {

	public DsmDealerProfilesDto mapToDto(DealerAndDsm dealerAndDsm, PSIProfile psiProfile) {
		DsmDealerProfilesDto ddProfile = new DsmDealerProfilesDto();
		ddProfile.setDealerid(dealerAndDsm.getDealerId());
		ddProfile.setDealerName(dealerAndDsm.getDealerName());
		ddProfile.setDsmId(dealerAndDsm.getDsmId());
		ddProfile.setDsmName(dealerAndDsm.getDsmName());
		ddProfile.setModifiedDate(psiProfile.getLastModifiedDate());
		ddProfile.setProfileId(psiProfile.getId());
		ddProfile.setStatus(psiProfile.getStatus());
		ddProfile.setType(dealerAndDsm.getProductLine());
		
		return ddProfile;
	}
	
}
