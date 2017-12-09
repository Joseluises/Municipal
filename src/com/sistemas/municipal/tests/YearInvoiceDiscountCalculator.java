package com.sistemas.municipal.tests;

import java.math.BigDecimal;

import org.openxava.calculators.ICalculator;
/**
 * @author Javier Paniza
 */
public class YearInvoiceDiscountCalculator implements ICalculator {
	
	private int year;

	public Object calculate() throws Exception {		
		if (year < 2002) return new BigDecimal("1200.00");
		if (year > 2004) return new BigDecimal("200.00");
		return new BigDecimal("400.00");
	}

	public int getYear() {
		return year;
	}

	public void setYear(int i) {
		year = i;
	}

}
