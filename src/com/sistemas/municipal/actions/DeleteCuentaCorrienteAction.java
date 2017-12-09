package com.sistemas.municipal.actions;

import org.openxava.actions.*;

public class DeleteCuentaCorrienteAction 
		extends ViewBaseAction{
	public void execute() throws Exception{
		addMessage("Desea Borrar Cuenta Corriente");
		getView().clear();
	}
}
