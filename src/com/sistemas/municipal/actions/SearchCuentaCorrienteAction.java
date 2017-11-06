package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class SearchCuentaCorrienteAction 
		extends AddElementsToCollectionAction{
	
	@Override
	public void execute() throws Exception{
		super.execute();
		int contribuyenteCodigo = getPreviousView().getValueInt("contribuyente.id");
		getTab().setBaseCondition("${contribuyente.id} = "+contribuyenteCodigo);
	}
}