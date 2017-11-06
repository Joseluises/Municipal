package com.sistemas.municipal.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Required;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentYearCalculator;

@Views({
	@View(members=
			"generales["+
			"aini;"+
			"ubicacionParque,sector;"+
			"montoTasa;"+
			"];"),
		@View(name="TasaParquesJardinesVista1",	members="aini")
})			

@Entity
public class TasaParquesJardines extends Deletable {
	@Column(length=4)
	@Required
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	private UbicacionParque ubicacionParque;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	private Sector sector;

	@Column(length=9)
	@Required
	public BigDecimal montoTasa;

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public UbicacionParque getUbicacionParque() {
		return ubicacionParque;
	}

	public void setUbicacionParque(UbicacionParque ubicacionParque) {
		this.ubicacionParque = ubicacionParque;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public BigDecimal getMontoTasa() {
		return montoTasa;
	}

	public void setMontoTasa(BigDecimal montoTasa) {
		this.montoTasa = montoTasa;
	}
}
