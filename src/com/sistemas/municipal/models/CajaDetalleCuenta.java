package com.sistemas.municipal.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CajaDetalleCuenta extends Deletable{
	@ManyToOne
	private Caja parentCajaDetalleCuenta;

	public Caja getParentCajaDetalleCuenta() {
		return parentCajaDetalleCuenta;
	}

	public void setParentCajaDetalleCuenta(Caja parentCajaDetalleCuenta) {
		this.parentCajaDetalleCuenta = parentCajaDetalleCuenta;
	}
}
