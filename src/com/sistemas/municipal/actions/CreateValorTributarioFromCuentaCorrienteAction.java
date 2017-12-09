package com.sistemas.municipal.actions;

import java.util.Date;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import com.sistemas.municipal.models.*;

public class CreateValorTributarioFromCuentaCorrienteAction extends ViewBaseAction{
	public void execute() throws Exception{
		// Clase que pasa parámetros a la Cuenta Corriente para generar [Valor Tributario]
		Object valorOficina = getView().getValue("oficina.id");
		Object valorTipo = getView().getValue("tipovalor.id");
		Object valorTributo = getView().getValue("tributo.id");
		int valorInicio = (Integer) getView().getValue("ainiCuentaInicio");
		int valorFin = (Integer) getView().getValue("ainiCuentaFin");
		int valorAiniLote = (Integer) getView().getValue("ainiLote");
		int valorAiniValor = (Integer) getView().getValue("ainivalor");
		int valorNumeroLote = (Integer) getView().getValue("numeroLote");
		Date valorFechaInteres = (Date) getView().getValue("fechaInteres");
		
		CuentaCorriente cuentaCorriente = XPersistence.getManager().find(CuentaCorriente.class,getView().getValue("contribuyente.id"));
		cuentaCorriente.createValorTributario(valorOficina,valorTipo, valorTributo, valorInicio, valorFin, valorAiniLote,valorAiniValor,valorNumeroLote,valorFechaInteres);
		getView().refresh();
		addMessage("valortributario_created_from_cuentacorriente");
	}
}