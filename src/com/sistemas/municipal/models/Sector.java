package com.sistemas.municipal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import org.openxava.annotations.*;
import org.openxava.jpa.XPersistence;

@Entity
@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"descripcion;"+
			"]"),
		@View(name="SectorVista1",	members="codigo,descripcion")
})			
public class Sector extends Deletable {
	@Column(length = 3)
	@ReadOnly
	private int codigo;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Column(length = 50)
	@Required
	private String descripcion;
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