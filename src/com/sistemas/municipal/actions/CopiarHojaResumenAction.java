package com.sistemas.municipal.actions;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.sistemas.municipal.models.*;

public class CopiarHojaResumenAction extends ViewBaseAction{ // getView
	public void execute() throws Exception{
		HojaResumen hojaResumen = XPersistence.getManager().find(HojaResumen.class, getView().getValue("id")); //JPA
		hojaResumen.crearHojaResumen(); // Entidad
		getView().refresh();
		addMessage("hojaresumen_copiada", hojaResumen.getAiniHr());
	}
}