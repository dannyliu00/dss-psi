/**
 * 
 */
package com.polaris.psi.repository.dao;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.ProductLine;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

/**
 * @author pceder
 *
 */
@Repository
public class ProductLineDao extends AbstractPolarisDealersExtensionDao<ProductLine> {
	
	
	public ProductLineDao() {
		super(ProductLine.class);
	}
}
