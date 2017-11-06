
package com.sistemas.municipal.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.*;

@Entity
@Tab(properties="contribuyente,prediourbano")
public class ContribuyentePredio extends Deletable{
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("ContribuyenteSimple1")
	@NoCreate
	@NoModify
	private Contribuyente contribuyente;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("PredioUrbanoVista1")
	@NoCreate
	@NoModify
	private PredioUrbano predioUrbano;

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public PredioUrbano getPredioUrbano() {
		return predioUrbano;
	}

	public void setPredioUrbano(PredioUrbano predioUrbano) {
		this.predioUrbano = predioUrbano;
	}
}