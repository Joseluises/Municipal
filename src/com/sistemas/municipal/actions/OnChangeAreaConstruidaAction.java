package com.sistemas.municipal.actions;
import com.sistemas.municipal.models.*;

import java.math.*;

import org.openxava.actions.OnChangePropertyBaseAction;

public class OnChangeAreaConstruidaAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		BigDecimal nareaConstruida = (BigDecimal) getView().getValue("areaConstruida");
		BigDecimal nvalorUnitarioDepreciado= (BigDecimal) getView().getValue("valorUnitarioDepreciado");
		BigDecimal nporcentajeAreaComun= (BigDecimal) getView().getValue("porcentajeAreaComun");
		BigDecimal nvalorAreaConstruida = new BigDecimal("0.0");
		BigDecimal nvalorConstruccion = new BigDecimal("0.0");
		if(nareaConstruida==null || nvalorUnitarioDepreciado==null || nvalorAreaConstruida==null){
			getView().setValue("valorAreaConstruida", 0.00);
			return;
		}else{
			nvalorAreaConstruida = nareaConstruida.multiply(nvalorUnitarioDepreciado);
			getView().setValue("valorAreaConstruida",nvalorAreaConstruida);
			getView().setValue("valorConstruccion",nvalorAreaConstruida);
		}
	}	
}