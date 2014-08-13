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
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Service
public class DealerService {
	
	private static final SplunkLogger LOG = new SplunkLogger(DealerService.class);

	@Autowired
	DealerInfoDao dealerInfoDao;
	
	@Autowired
	DealerAndDsmDao dsmDao;
	
	@Autowired
	DealerMapper mapper;

	public DealerDto getDealer(Integer id, String type) {
		LOG.methodStart(PolarisIdentity.get(), "getDealer");
		
		Dealer dealer = dealerInfoDao.select(id);
		DealerAndDsm dsm = dsmDao.selectByDealerId(id, type);
		
		LOG.methodEnd(PolarisIdentity.get(), "getDealer");

		return mapper.mapToDto(dealer, dsm);
	}
	
}
