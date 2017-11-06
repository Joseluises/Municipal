package com.sistemas.municipal.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.openxava.annotations.Required;

@Entity
public class TrimestrePredial extends Deletable{
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
