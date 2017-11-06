package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"abreviatura;"+
			"descripcion;"+
			"]"),
		@View(name="TipoPuertaVista1",	members="codigo,abreviatura,descripcion")
})
public class TipoPuerta extends Deletable{
	@Column(length=6)
	@ReadOnly(forViews="DEFAULT")
	@SearchKey
	private int codigo;
	
	@Column(length=1)
	@Required
	private String abreviatura;
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	@Column(length=45)
	@Required
	private String descripcion;

	@PrePersist
	public void generarCodigo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " +
						getClass().getSimpleName() +
						 "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null?1:ultimoCodigo + 1;
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
