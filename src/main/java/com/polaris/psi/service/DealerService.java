/**
 * 
 */
package com.polaris.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerAndDsmDao;
import com.polaris.psi.repository.dao.DealerInfoDao;
import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.mapper.DealerMapper;

/**
 * @author bericks
 *
 */
@Service
public class DealerService {
	
	@Autowired
	DealerInfoDao dealerInfoDao;
	
	@Autowired
	DealerAndDsmDao dsmDao;
	
	@Autowired
	DealerMapper mapper;

	public DealerDto getDealer(Integer id, String type) {
		Dealer dealer = dealerInfoDao.select(id);
		DealerAndDsm dsm = dsmDao.selectByDealerId(id, type);
		return mapper.mapToDto(dealer, dsm);
	}
	
}
