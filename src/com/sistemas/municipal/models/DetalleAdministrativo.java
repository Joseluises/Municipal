package com.sistemas.municipal.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class DetalleAdministrativo extends Deletable{
	@ManyToOne
	private DocumentoAdministrativo parent;

	private int cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
//	@ReferenceView("ProductoSimple")
	private InfraccionAdministrativa infraccionesAdministrativas;

	public DocumentoAdministrativo getParent() {
		return parent;
	}

	public void setParent(DocumentoAdministrativo parent) {
		this.parent = parent;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public InfraccionAdministrativa getInfraccionesAdministrativas() {
		return infraccionesAdministrativas;
	}

	public void setInfraccionesAdministrativas(InfraccionAdministrativa infraccionesAdministrativas) {
		this.infraccionesAdministrativas = infraccionesAdministrativas;
	}
}
