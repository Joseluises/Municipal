package com.sistemas.municipal.actions;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.view.*;

import com.sistemas.municipal.models.ValorTributario;

public class OnChangeSearchContribuyenteAction extends OnChangeSearchAction{
	@Override
	public void execute() throws Exception{
		// ValorTributario
		super.execute();  // Se ejecuta el modo estándar en los campos con [@SearchKey] cuando se digitan
		Map keyValues = getView().getKeyValuesWithValue(); // Obtener la clave de ValorTributario
		if(keyValues.isEmpty()) return;
		ValorTributario valorTributario = (ValorTributario) 
						MapFacade.findEntity(getView().getModelName(), keyValues); // Con la clave de ValorTributario se obtiene su entidad con [MapFacade]
		// Contribuyente
		View contribuyenteView = getView().getRoot().getSubview("contribuyente"); // Acceder a la vista del contribuyente
		int contribuyenteCodigo = contribuyenteView.getValueInt("codigo"); // Cliente de [Order]
		if(contribuyenteCodigo == 0){ // La acción pregunta si no hay un cliente llena los datos desde el cliente del [ValorTributario]
			contribuyenteView.setValue("codigo", valorTributario.getContribuyente().getCodigo());
			contribuyenteView.refresh();
		}else{
			if(contribuyenteCodigo != valorTributario.getContribuyente().getCodigo()){
				addError("valorTributario_contribuyente_not_match",valorTributario.getContribuyente().getCodigo(), valorTributario, contribuyenteCodigo);
				getView().clear();	
			}
		}
	}
}