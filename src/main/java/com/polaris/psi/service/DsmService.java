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
	
	public List<DsmDealerProfilesDto> getDsmCurrentProfiles(Integer dsmId, String type) {
		List<DealerAndDsm> dsmDealers = dsmDao.selectByDsmId(dsmId, type);
		List<DsmDealerProfilesDto> dtos = new ArrayList<DsmDealerProfilesDto>();
		
		for (DealerAndDsm dealerAndDsm : dsmDealers) {
			Integer dealerId = dealerAndDsm.getDealerId();

			List<PSIProfile> psiProfiles = psiProfileDao.retrieveDsmCurrentProfileListByDealerId(dealerId, type);
			for (PSIProfile profile : psiProfiles) {
				dtos.add(mapper.mapToDto(dealerAndDsm, profile));
			}
		}
		
		return dtos;
	}
	
	public List<DsmDealerProfilesDto> getDsmHistoricalProfiles(Integer dsmId, String type) {
		List<DealerAndDsm> dsmDealers = dsmDao.selectByDsmId(dsmId, type);
		List<DsmDealerProfilesDto> dtos = new ArrayList<DsmDealerProfilesDto>();
		
		for (DealerAndDsm dealerAndDsm : dsmDealers) {
			Integer dealerId = dealerAndDsm.getDealerId();

			List<PSIProfile> psiProfiles = psiProfileDao.retrieveDsmHistoryProfileListByDealerId(dealerId, type);
			for (PSIProfile profile : psiProfiles) {
				dtos.add(mapper.mapToDto(dealerAndDsm, profile));
			}
		}
		
		return dtos;
	}
	
	public List<DsmDealerProfilesDto> getRsmProfiles(Integer rsmId, String type) {
		List<DealerAndDsm> dsmDealers = dsmDao.selectByRsmId(rsmId, type);
		List<DsmDealerProfilesDto> dtos = new ArrayList<DsmDealerProfilesDto>();
		
		for (DealerAndDsm dealerAndDsm : dsmDealers) {
			Integer dealerId = dealerAndDsm.getDealerId();

			List<PSIProfile> psiProfiles = psiProfileDao.retrieveDsmCurrentProfileListByDealerId(dealerId, type);
			for (PSIProfile profile : psiProfiles) {
				dtos.add(mapper.mapToDto(dealerAndDsm, profile));
			}
		}
		
		return dtos;
	}
	
}
