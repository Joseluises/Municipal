package com.sistemas.municipal.models;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
public class EjecutorCoactivo extends Deletable{
	@Column(length=50)
	@Required
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
