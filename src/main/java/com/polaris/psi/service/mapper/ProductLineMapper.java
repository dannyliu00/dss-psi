/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.ProductLine;
import com.polaris.psi.resource.dto.ProductLineDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * 
 * @author pceder
 *
 */
@Component
public class ProductLineMapper {

	private static final SplunkLogger LOG = new SplunkLogger(ProductLineMapper.class);
	
	public ProductLineDto mapToDto(ProductLine productLine) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");
		
		ProductLineDto dtoItem = new ProductLineDto();
		dtoItem.setProductLineId(productLine.getProductLineId());
		dtoItem.setShortDescription(productLine.getShortDescription());
		dtoItem.setDescription(productLine.getDescription());
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");
		
		return dtoItem;
	}
	
	public List<ProductLineDto> mapToDto(List<ProductLine> productLines) {
		
		List<ProductLineDto> items = new ArrayList<ProductLineDto>();
		
		for(ProductLine pl: productLines) {
			items.add(this.mapToDto(pl));
		}
		
		return items;
		
	}
}
