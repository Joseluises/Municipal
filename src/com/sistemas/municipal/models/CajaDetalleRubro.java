package com.sistemas.municipal.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CajaDetalleRubro extends Deletable{
	@ManyToOne
	private Caja parentCajaDetalleRubro;

	public Caja getParentCajaDetalleRubro() {
		return parentCajaDetalleRubro;
	}

	public void setParentCajaDetalleRubro(Caja parentCajaDetalleRubro) {
		this.parentCajaDetalleRubro = parentCajaDetalleRubro;
	}
}
