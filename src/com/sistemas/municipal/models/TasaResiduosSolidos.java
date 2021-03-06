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
			"usoPredio,sector;"+
			"montoTasa;"+
			"];"),
		@View(name="TasaResiduosSolidosVista1",	members="aini")
})			
@Entity
public class TasaResiduosSolidos extends Deletable {
	@Column(length=4)
	@Required
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	private UsoPredio usoPredio;
	
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

	public UsoPredio getUsoPredio() {
		return usoPredio;
	}

	public void setUsoPredio(UsoPredio usoPredio) {
		this.usoPredio = usoPredio;
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
