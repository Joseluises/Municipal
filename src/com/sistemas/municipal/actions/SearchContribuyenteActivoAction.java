package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class SearchContribuyenteActivoAction extends ReferenceSearchAction{
	@Override
	public void execute() throws Exception{
		super.execute();
//		int contribuyenteNumber = getPreviousView().getValueInt("contribuyente.id");
		int estadoContribuyente = 1;
		if(estadoContribuyente > 0){
			getTab().setBaseCondition("${estadoContribuyente.id} = " + estadoContribuyente); // Condición en la lista de referencia
		}
	}
}