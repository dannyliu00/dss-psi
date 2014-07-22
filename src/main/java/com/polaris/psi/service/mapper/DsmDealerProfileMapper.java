/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
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
		ddProfile.setDealerName(dealerAndDsm.getDealerName().trim());
		ddProfile.setDsmId(dealerAndDsm.getDsmId());
		ddProfile.setDsmName(dealerAndDsm.getDsmName().trim());
		ddProfile.setModifiedDate(psiProfile.getLastModifiedDate());
		ddProfile.setProfileId(psiProfile.getId());
		ddProfile.setProfileName(psiProfile.getName());
		ddProfile.setRsmId(dealerAndDsm.getRsmId());
		ddProfile.setRsmName(dealerAndDsm.getRsmName().trim());
		ddProfile.setType(dealerAndDsm.getProductLine().trim());
		ddProfile.setStatus(psiProfile.getStatus() != null ? psiProfile.getStatus().trim() : Constants.DEFAULT_PROFILE_STATUS);
		ddProfile.setNonCompliant(psiProfile.isNonCompliant());
		
		return ddProfile;
	}
	
}
