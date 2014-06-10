/**
 * 
 */
package com.polaris.psi.resource.dto.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.IBaseDto;

/**
 * @author bericks
 *
 */
public class TotalsCalculatorTest {

	private ITotalsCalculator<IBaseDto, IBaseDto> calculator;
	private IBaseDto dto;
	@Mock private IBaseDto mockDto1, mockDto2;
	private List<IBaseDto> children;
	private int minimum;
	private int maximum;
	private int recommended;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		minimum = 1;
		maximum = 5;
		recommended = 3;
		children = new ArrayList<IBaseDto>();
		
		MockitoAnnotations.initMocks(this);
		when(mockDto1.getRecMinimum()).thenReturn(minimum);
		when(mockDto1.getRecMaximum()).thenReturn(maximum);
		when(mockDto1.getRecommended()).thenReturn(recommended);

		when(mockDto2.getRecMinimum()).thenReturn(minimum);
		when(mockDto2.getRecMaximum()).thenReturn(maximum);
		when(mockDto2.getRecommended()).thenReturn(recommended);
		
		children.add(mockDto1);
		children.add(mockDto2);
		
		dto = new IBaseDto() {
			
			private int recMinimum;
			private int recMaximum;
			private int recommended;
			private int actual;
			
			@Override
			public void setRecommended(int rec) {
				this.recommended = rec;
			}
			
			@Override
			public void setRecMinimum(int min) {
				this.recMinimum = min;
			}
			
			@Override
			public void setRecMaximum(int max) {
				this.recMaximum = max;
			}
			
			@Override
			public int getRecommended() {
				return this.recommended;
			}
			
			@Override
			public int getRecMinimum() {
				return this.recMinimum;
			}
			
			@Override
			public int getRecMaximum() {
				return this.recMaximum;
			}

			@Override
			public int getActual() {
				return this.actual;
			}

			@Override
			public void setActual(int actual) {
				this.actual = actual;
			}
		};
		
		calculator = new TotalsCalculator();
	}

	/**
	 * Test method for {@link com.polaris.psi.resource.dto.util.TotalsCalculator#calculateTotals(com.polaris.psi.resource.dto.IBaseDto, java.util.List)}.
	 */
	@Test
	public void testCalculateRecommendedValues() {
		calculator.calculateTotals(dto, children);
		
		verify(mockDto1).getRecMinimum();
		verify(mockDto1).getRecMaximum();
		verify(mockDto1).getRecommended();

		verify(mockDto2).getRecMinimum();
		verify(mockDto2).getRecMaximum();
		verify(mockDto2).getRecommended();

		assertTrue(dto.getRecMaximum() == maximum * 2);
		assertTrue(dto.getRecMinimum() == minimum * 2);
		assertTrue(dto.getRecommended() == recommended * 2);
	}

}
