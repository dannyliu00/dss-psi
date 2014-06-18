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

		int result = lhs.getName().compareTo(rhs.getName());
		
		if(result == 0) result = lhs.getPeriodStartDate().compareTo(rhs.getPeriodStartDate());
		
		return result;
	}

}
