package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Tab(properties = "codigo,descripcion")
public class Giro extends Deletable {
	@Column(length = 6)
	@ReadOnly
	private int codigo;

	@Required
	@Stereotype("MEMO")
	private String descripcion;

	@PrePersist
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}