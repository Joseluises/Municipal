package com.sistemas.municipal.calculators;

import java.math.BigDecimal;

import org.openxava.calculators.ICalculator;


/**
 * @author Luis Miguel Hernandez
 */
public class BigDecimalCalculator implements ICalculator {

	private Object value;
	
	public Object calculate() throws Exception {		
	    if (getValue()==null) return new BigDecimal(0);
		return 100.00;
	}

	public Object getValue() {
	  if (value==null) return new BigDecimal(0);
		return value.toString();
	}

	public void setValue(Object i){
		value = i;
	}

}
