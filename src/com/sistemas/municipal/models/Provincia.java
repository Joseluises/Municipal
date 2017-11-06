package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Required;

@Entity
public class Provincia extends Deletable{
	@Column(length=50)
	@Required
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Departamento departamento;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
