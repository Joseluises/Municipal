package com.sistemas.municipal.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.View;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;

@View(members="aini,numero,fecha;"+
		"data{"+
		 "contribuyente;"+
		"detalles"+
		"}"
		)
@Entity
abstract public class DocumentoAdministrativo extends Deletable{
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@Column(length = 6)
	private int numero;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Contribuyente contribuyente;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	@ListProperties("infraccionAdministrativa.id")
	private Collection<DetalleAdministrativo> detalles = new ArrayList<DetalleAdministrativo>();

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Collection<DetalleAdministrativo> getDetalles() {
		return detalles;
	}

	public void setDetalles(Collection<DetalleAdministrativo> detalles) {
		this.detalles = detalles;
	}
}
