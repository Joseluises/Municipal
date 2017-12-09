package com.sistemas.municipal.calculators;
import org.openxava.calculators.ICalculator;
import com.sistemas.municipal.models.*;  // ACTIVA TODOS LOS MODELOS PARA CREAR FUNCION CON LOS CAMPOS

import groovy.util.logging.Log;

import static org.openxava.jpa.XPersistence.*;

import org.apache.commons.logging.LogFactory;  

public class NombreContribuyenteCalculator implements ICalculator{
	private int codigoid;
	
	public Object calculate() throws Exception{
		System.out.println("Ejecutando Calculador");
		Contribuyente contrib = getManager().find(Contribuyente.class, codigoid);
		return contrib.getApellidoNombre();
//		addMessage("hola ", getView().getValue("codigo"));
	}

	public int getCodigoid() {
		return codigoid;
	}

	public void setCodigoid(int codigoid) {
		this.codigoid = codigoid;
	}
}
