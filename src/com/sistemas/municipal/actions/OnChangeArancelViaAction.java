package com.sistemas.municipal.actions;

import java.util.List;
import javax.persistence.Query;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

public class OnChangeArancelViaAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		Object predio = getView().getValue("predio.id");
		Integer aini  = (Integer) getView().getRoot().getValue("ainiHr");
		if(getNewValue()==null || predio==null){
			getView().setValue("montoArancel", 0.00);
			return;
		}else{
			Query consulta = XPersistence.getManager().createQuery("SELECT montoViaPar FROM ArancelDetalle"
																	+ " WHERE aini=:aini AND parentarancel_id=:predio");
	        consulta.setParameter("aini", aini);
	        consulta.setParameter("predio", predio);
	        List results = consulta.getResultList();
	        if(!results.isEmpty()) getView().setValue("montoArancel", results.get(0));
	        	else getView().setValue("montoArancel", 0.00);
		}
    }
}