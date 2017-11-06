package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"descripcion,codigoPostal;"+
			"]"),
		@View(name="DistritoVista1",	members="codigo, descripcion")
})			
public class Distrito extends Deletable{
	@Column(length = 3)
	@ReadOnly
	private int codigo;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(length=50)
	@Required
	private String descripcion;
	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Column(length=3)
	@Required
	private String codigoPostal;
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@PrePersist
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}
}
