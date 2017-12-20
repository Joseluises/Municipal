package com.sistemas.municipal.actions;

import java.util.List;
import javax.persistence.Query;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import com.sistemas.municipal.models.ArancelDetalle;
import com.sistemas.municipal.models.DepreciacionNivel;

public class OnChangeArancelViaAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		Object predio = getView().getValue("predio.id");
		Integer aini  = (Integer) getView().getRoot().getValue("ainiHr");
		if(getNewValue()==null || predio==null){
			getView().setValue("montoArancel", 0.00);
			return;
		}else{
			ArancelDetalle consulta = ArancelDetalle.buscarArancel(predio, aini);
	        getView().setValue("montoArancel", consulta.getMontoViaInpar());
		}
    }
}