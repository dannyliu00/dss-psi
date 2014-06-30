/**
 * 
 */
package com.polaris.psi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerAndDsmDao;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.DsmDealerProfilesDto;
import com.polaris.psi.service.mapper.DsmDealerProfileMapper;
import com.polaris.pwf.dao.PSIProfileDao;

/**
 * @author bericks
 *
 */
@Service
public class DsmService {
	
	@Autowired
	DealerAndDsmDao dsmDao;
	
	@Autowired
	PSIProfileDao psiProfileDao;
	
	@Autowired
	DsmDealerProfileMapper mapper;
	
	public List<DsmDealerProfilesDto> getProfiles(Integer dsmId) {
		
		List<DealerAndDsm> dsmDealers = dsmDao.selectByDsmId(dsmId);
		List<DsmDealerProfilesDto> dtos = new ArrayList<DsmDealerProfilesDto>();
		
		for (DealerAndDsm dealerAndDsm : dsmDealers) {
			Integer dealerId = dealerAndDsm.getDealerId();

			List<PSIProfile> psiProfiles = psiProfileDao.retrieveListByDealerId(dealerId);
			for (PSIProfile profile : psiProfiles) {
				dtos.add(mapper.mapToDto(dealerAndDsm, profile));
			}
		}
		
		return dtos;
	}
	
}