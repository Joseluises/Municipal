package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class AddCtasValorTribAction extends GoAddElementsToCollectionAction{
	@Override
	public void execute() throws Exception{
		super.execute();
		int codigoContrib = getPreviousView().getValueInt("contribuyentes.id");
		getTab().setBaseCondition("${contribuyente.id} = " +codigoContrib);
	}
}