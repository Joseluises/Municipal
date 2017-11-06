package com.sistemas.municipal.actions;

import java.util.*;

import org.openxava.actions.*;

import javassist.tools.rmi.*;

public class VerContribuyenteCuentaCorrienteAction extends ViewBaseAction{
	@Override
	public void execute() throws Exception{
		try{
			Object codigo = getView().getValue("contribuyente.codigo");
			Map key = new HashMap();
			key.put("codigo",codigo);
			showNewView();
			getView().setModelName("Cliente");
			getView().setValues(key);
			getView().findObject();
			getView().setKeyEditable(false);
			getView().setEditable(false);
			setControllers("Return");
		}
		catch(ObjectNotFoundException ex){
			getView().clear();
			addError("object_not_found");
		}
		catch(Exception ex){
			ex.printStackTrace();
			addError("system_error");
		}
	}

}
