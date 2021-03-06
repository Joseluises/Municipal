package com.sistemas.municipal.actions;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.sistemas.municipal.models.*;

public class CopiarHojaResumenAction extends ViewBaseAction{ // getView
	public void execute() throws Exception{
		Object id = getView().getValue("id");
		if(id==null) {
			addError("hojaresumen_no_seleccionada");
			return;
		}
		HojaResumen hojaResumen = XPersistence.getManager().find(HojaResumen.class, getView().getValue("id"));
		hojaResumen.crearHojaResumen();
		getView().refresh();
		addMessage("hojaResumen_copiada", hojaResumen.getAiniHr());
	}
}