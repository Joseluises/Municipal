package com.sistemas.municipal.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.*;

@View(members = "distrito;" + 
				"tipo,via,numero,interior,manzana,lote")
@Embeddable
public class Direccion {
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private Distrito distrito;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private TipoVia tipo;

	@Column(length = 50)
	@Required
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Urbanizacion urbanizacion;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Sector sector;

	@Column(length = 10)
//	@Required
	private Integer numero;
	
	@Column(length = 10)
//	@Required
	private Integer interior;
	
	@Column(length = 4)
//	@Required
	private String  manzana;

	@Column(length = 4)
//	@Required
	private String lote;

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public TipoVia getTipo() {
		return tipo;
	}

	public void setTipo(TipoVia tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Urbanizacion getUrbanizacion() {
		return urbanizacion;
	}

	public void setUrbanizacion(Urbanizacion urbanizacion) {
		this.urbanizacion = urbanizacion;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getInterior() {
		return interior;
	}

	public void setInterior(Integer interior) {
		this.interior = interior;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
}
