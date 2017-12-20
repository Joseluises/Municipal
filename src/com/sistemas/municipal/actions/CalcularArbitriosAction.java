package com.sistemas.municipal.actions;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.sistemas.municipal.models.*;

public class CalcularArbitriosAction extends ViewBaseAction{ // getView
	public void execute() throws Exception{
		Object autovaluo = getView().getValue("autovaluodeclarado");
		if(autovaluo == null) {
			addMessage("autovaluo 0");
		}
		HojaResumen hojaResumen = XPersistence.getManager().find(HojaResumen.class, getView().getValue("id"));
		hojaResumen.calcularArbitrios();
		getView().refresh();
		addMessage("predial_calculado");
	}
}