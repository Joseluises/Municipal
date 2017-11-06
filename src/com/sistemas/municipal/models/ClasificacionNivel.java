package com.sistemas.municipal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Query;

import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.jpa.XPersistence;

@Entity
public class ClasificacionNivel extends Deletable{
	@Column(length = 3)
	@ReadOnly
	private int codigo;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(length=45)
	@Required
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}
}
