/**
 * 
 */
package com.polaris.psi.resource.dto.util;

import java.util.List;

import com.polaris.psi.resource.dto.IBaseDto;

/**
 * @author bericks
 * @param <E>
 *
 */
public class TotalsCalculator implements ITotalsCalculator<IBaseDto, IBaseDto> {

	@Override
	public void calculateTotals(IBaseDto clazz, List<IBaseDto> entities) {
		int minimumQty = 0;
		int maximumQty = 0;
		int recommended = 0;
		int actual = 0;
		
		for (IBaseDto iBaseDto : entities) {
			minimumQty += iBaseDto.getRecMinimum();
			maximumQty += iBaseDto.getRecMaximum();
			recommended += iBaseDto.getRecommended();
			actual += iBaseDto.getActual();
		}
		
		clazz.setRecMaximum(maximumQty);
		clazz.setRecMinimum(minimumQty);
		clazz.setRecommended(recommended);
		clazz.setActual(actual);
	}
}
