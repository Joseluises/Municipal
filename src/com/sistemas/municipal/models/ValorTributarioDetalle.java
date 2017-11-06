package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;


@Entity
public class ValorTributarioDetalle extends Deletable{
	@ManyToOne
	private ValorTributario parent;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("DetalleCuentaVista2")
	@NoCreate @NoModify
	private CuentaCorrienteDetalle detalleCuentaCorriente;

	public ValorTributario getParent() {
		return parent;
	}

	public void setParent(ValorTributario parent) {
		this.parent = parent;
	}

	public CuentaCorrienteDetalle getDetalleCuentaCorriente() {
		return detalleCuentaCorriente;
	}

	public void setDetalleCuentaCorriente(CuentaCorrienteDetalle detalleCuentaCorriente) {
		this.detalleCuentaCorriente = detalleCuentaCorriente;
	}
}
