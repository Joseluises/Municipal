package com.sistemas.municipal.actions;

import java.util.List;
import javax.persistence.Query;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

public class OnChangeAntiguedadAction extends OnChangePropertyBaseAction{
	@Override
	public void execute() throws Exception{
		Integer anoantiguedad = (Integer) getView().getValue("anoantiguedad");
		if(getNewValue()==null || anoantiguedad==0){
			getView().setValue("montoArancel", 0.00);
			return;
		}else{
			getView().setValue("antiguedad", 5.00); 
		}
    }
}     	
//System.out.println ("El precio es de " + precio + " euros");
//getView().setValue("montoArancel", results.get(0));
//getView().setValue("montoArancel", 0.00);
//if(getNewValue()==null) getView().setValue("montoArancel", 0);
//else getView().setValue("montoArancel",120.30);
//addMessage("property_changed");
//addMessage("property_changed");
//	addMessage("hola ", getView().getValue("codigo"));