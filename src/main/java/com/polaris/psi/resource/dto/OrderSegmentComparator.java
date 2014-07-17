/**
 * 
 */
package com.polaris.psi.resource.dto;

import org.springframework.stereotype.Component;

/**
 * @author bericks
 *
 */
@Component
public class OrderSegmentComparator implements IOrderSegmentComparator {

	@Override
	public int compare(OrderSegmentDto lhs, OrderSegmentDto rhs) {

		return lhs.getSort().compareTo(rhs.getSort());
	}

}
