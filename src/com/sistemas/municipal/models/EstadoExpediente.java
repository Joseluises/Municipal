package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class EstadoExpediente extends Deletable{
	@Column(length=45)
	@Required
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
