package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Views({
	@View(members=
			"general["+
			"codigo;"+
			"distrito;"+
			"sector,urbanizacion;"+
			"tipo.descripcion,descripcion;"+
			"]"),
		@View(name="ViaVista1",	members="codigo;distrito;sector,urbanizacion,tipo;descripcion"),
		@View(name="ViaVista2",	members="codigo,sector,urbanizacion,tipo,descripcion;"),
		@View(name="ViaVista3",	members="codigo;sector,urbanizacion,tipo;descripcion;")
})			
@Tab(properties="codigo,distrito.sector,urbanizacion,descripcion,tipo,descripcion")
public class Via extends Deletable{
	@Column(length = 3)
	@ReadOnly
	private int codigo;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoFrame
	@NoCreate
	@NoModify
	private Distrito distrito;
	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	private TipoVia tipo;
	public TipoVia getTipo() {
		return tipo;
	}

	public void setTipo(TipoVia tipo) {
		this.tipo = tipo;
	}

	@Column(length = 62)
	@Required
	private String descripcion;
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoFrame
	private Urbanizacion urbanizacion;
	public Urbanizacion getUrbanizacion() {
		return urbanizacion;
	}

	public void setUrbanizacion(Urbanizacion urbanizacion) {
		this.urbanizacion = urbanizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoFrame
	private Sector sector;
	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	@PrePersist
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}
}
