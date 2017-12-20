package com.sistemas.municipal.actions;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.sistemas.municipal.models.*;

public class CalcularPredialAction extends ViewBaseAction{ // getView
	public void execute() throws Exception{
		Object autovaluo = getView().getValue("autovaluodeclarado");
		if(autovaluo == null) {
			addMessage("autovaluo 0");
		}
		
//		Object predio = getView().getValue("predio.id");
//		Integer aini = (Integer) getView().getValue("ainihr");
//		ArancelDetalle consulta = ArancelDetalle.buscarArancel(predio, aini);
		HojaResumen hojaResumen = XPersistence.getManager().find(HojaResumen.class, getView().getValue("id"));
		hojaResumen.calcularPredial();
		getView().refresh();
		addMessage("predial_calculado");
	}
}