package com.sistemas.municipal.actions;

import java.math.*;
import org.openxava.actions.OnChangePropertyBaseAction;

public class OnChangeValorTerrenoAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		Object nmontoarancel	 = getView().getValue("montoArancel");
		BigDecimal naream2		 = (BigDecimal) getView().getValue("areaM2");
		BigDecimal nValorTerreno = new BigDecimal("0.0");
		 if(naream2==null || nmontoarancel==null) {
			getView().setValue("valorTerreno", 0.00);
			return;
		}else {
			nValorTerreno = ((BigDecimal) nmontoarancel).multiply(naream2);
			getView().setValue("valorTerreno", nValorTerreno);
		}
	}
}