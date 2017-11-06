package com.sistemas.municipal.actions;

import java.util.List;
import javax.persistence.Query;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

public class OnChangeArancelViaAction extends OnChangePropertyBaseAction{
	@Override
	public void execute() throws Exception{
		Object predio = getView().getValue("predio.id");
		Integer aini= (Integer) getView().getRoot().getValue("ainiHr");
		System.out.println (aini);
		if(getNewValue()==null || predio==null){
			getView().setValue("montoArancel", 0.00);
			return;
		}else{
			Query consulta = XPersistence.getManager().createQuery("SELECT montoViaPar from ArancelDetalle where aini=:aini and parentarancel_id=:predio"); 
	        consulta.setParameter("aini", aini);    
	        consulta.setParameter("predio", predio);
	        List results = consulta.getResultList();
	        if(!results.isEmpty()) getView().setValue("montoArancel", results.get(0)); 
	        	else getView().setValue("montoArancel", 0.00); 
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