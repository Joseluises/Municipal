package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class DeleteCuentaCorrienteAction 
		extends ViewBaseAction{
	@Override
	public void execute() throws Exception{
		addMessage("Desea Borrar Cuenta Corriente");
		getView().clear();
	}
}
