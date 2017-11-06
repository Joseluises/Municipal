
package com.sistemas.municipal.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoFrame;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;

@Entity
@Views({
	@View(members=
			"generales["+
			"fechaRecepcion;"+
			"]"),
})			

@Tab(properties="codigoPredio")
public class PredioRustico extends Deletable {
	@ManyToOne
	private HojaResumen parentHojaResumen;
						
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("ContribuyenteVista1")
	@NoCreate
	@NoModify
	@NoFrame
	private Contribuyente contribuyente;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaRecepcion;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaAdquisicion;

	public HojaResumen getParentHojaResumen() {
		return parentHojaResumen;
	}

	public void setParentHojaResumen(HojaResumen parentHojaResumen) {
		this.parentHojaResumen = parentHojaResumen;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
}


