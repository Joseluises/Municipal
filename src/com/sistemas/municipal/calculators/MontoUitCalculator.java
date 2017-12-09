package com.sistemas.municipal.calculators;

import org.openxava.calculators.*;

import com.sistemas.municipal.utils.*;

public class MontoUitCalculator implements ICalculator{
	public Object calculate() throws Exception{
		return MunicipalPreferencias.getDefaultMontoUit();		
	}
}	