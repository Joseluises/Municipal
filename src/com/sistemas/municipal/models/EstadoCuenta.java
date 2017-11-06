package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.Required;

@Entity
public class EstadoCuenta extends Deletable{
	@Column(length=50)
	@Required
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
