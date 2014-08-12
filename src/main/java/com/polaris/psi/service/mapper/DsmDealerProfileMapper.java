/**
 * 
 */
package com.polaris.psi.service.mapper;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.DsmDealerProfilesDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * DsmDealerProfileMapper handles mapping data from DealerAndDsm and PSIProfile objects to 
 * a DsmDealerProfilesDto object.
 * 
 * @author bericks
 *
 */
@Component
public class DsmDealerProfileMapper {

	private static final SplunkLogger LOG = new SplunkLogger(DsmDealerProfileMapper.class);
	
	public DsmDealerProfilesDto mapToDto(DealerAndDsm dealerAndDsm, PSIProfile psiProfile) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");
		
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
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");
		
		return ddProfile;
	}
	
}
