package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class AddCtacteValorTributarioAction extends GoAddElementsToCollectionAction{
	@Override
	public void execute() throws Exception{
		super.execute();
		int codigo = getPreviousView().getValueInt("contribuyente.id");
		getTab().setBaseCondition("${contribuyente.id} = " + codigo);
	}
}