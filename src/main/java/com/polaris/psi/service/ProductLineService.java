/**
 * 
 */
package com.polaris.psi.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.ProductLineDao;
import com.polaris.psi.repository.entity.ProductLine;
import com.polaris.psi.resource.dto.ProductLineDto;
import com.polaris.psi.service.mapper.ProductLineMapper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author pceder
 *
 */
@Service
public class ProductLineService {
	
	private static final SplunkLogger LOG = new SplunkLogger(ProductLineService.class);

	@Autowired
	ProductLineDao productLineDao;
	
	@Autowired
	ProductLineMapper productLineMapper;
	

	/**
	 * Retrieves Dealer and associated DSM and RSM information
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@Cacheable(com.polaris.psi.Constants.PRODUCTLINE_CACHE)
	public List<ProductLineDto> getProductLines() {
		LOG.methodStart(PolarisIdentity.get(), "getProductLines");
		
		List<ProductLine> productLines = productLineDao.selectAll();
		
		LOG.methodEnd(PolarisIdentity.get(), "getProductLines");

		return productLineMapper.mapToDto(productLines);
	}
	
}
