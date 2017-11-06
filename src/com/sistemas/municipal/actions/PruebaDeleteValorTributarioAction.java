package com.sistemas.municipal.actions;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.sistemas.municipal.models.*;

public class PruebaDeleteValorTributarioAction extends ViewBaseAction{
	@Override
	public void execute() throws Exception{
//		addMessage("no se preocupe! He limpiado solamente la pantalla");
//		getView().clear();
		if(getView().getValue("id")==null){
			addError("no_delete_not_exists");
			return;
		}
		ValorTributario valorTributario = XPersistence.getManager().find(ValorTributario.class,getView().getValue("id"));
		valorTributario.setDeleted(true);
		addMessage("object_deleted","ValorTributario");
		getView().clear();
	}
}