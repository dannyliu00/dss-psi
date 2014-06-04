package com.polaris.psi.resource.dto.util;

import java.util.List;

public interface ITotalsCalculator<T, E> {
	public void calculateTotals(T clazz, List<E> entities);
}
