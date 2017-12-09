package com.sistemas.municipal.actions;
import com.sistemas.municipal.models.*;

import java.math.*;

import org.openxava.actions.OnChangePropertyBaseAction;

public class OnChangeValorUnitarioAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		String categoria = (String) getView().getValue("categorias");
		Integer aini= (Integer) getView().getRoot().getValue("ainiHr");
//		System.out.println(categoria);
		if(getNewValue()==null || aini==0){  
			getView().setValue("valorUnitario", 0.00);
			return;
		}else{
			BigDecimal total = new BigDecimal("0.0");
			for(int i=0; i<categoria.length();i++){
//				String caracter = categoria.substring(i,i+1);
//				System.out.println(caracter);
				ValorUnitarioEdificacion consulta = ValorUnitarioEdificacion.buscarMontoCategoriaNivel(aini, categoria.substring(i,i+1));
				switch (i){
					case 0: total = total.add(consulta.getMuros());;break;
					case 1: total = total.add(consulta.getTechos());;break;
					case 2: total = total.add(consulta.getPisos());;break;
					case 3: total = total.add(consulta.getPuertas());;break;
					case 4: total = total.add(consulta.getRevestimientos());;break;
					case 5: total = total.add(consulta.getBanos());;break;
					case 6: total = total.add(consulta.getInstalaciones());break;
				}
		}
			getView().setValue("valorUnitario",total);
	  }
	}
}