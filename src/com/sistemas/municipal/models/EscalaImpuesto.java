package com.sistemas.municipal.models;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Views({ @View(members = "aini;" + "inicioAutovaluo,finAutovaluo;" + "tasaImpuesto;" + "impuestoAcumulado"),
		@View(name = "Simple1", members = "aini,tasaImpuesto") })
@Tab(properties = "aini,inicioAutovaluo,finAutovaluo,tasaImpuesto,impuestoAcumulado")
@Entity
public class EscalaImpuesto extends Deletable {
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;

	@Column(length = 12)
	private BigDecimal inicioAutovaluo;

	@Column(length = 12)
	private BigDecimal finAutovaluo;

	@Column(length = 12)
	private BigDecimal tasaImpuesto;

	@Column(length = 12)
	private BigDecimal impuestoAcumulado;

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public BigDecimal getInicioAutovaluo() {
		return inicioAutovaluo;
	}

	public void setInicioAutovaluo(BigDecimal inicioAutovaluo) {
		this.inicioAutovaluo = inicioAutovaluo;
	}

	public BigDecimal getFinAutovaluo() {
		return finAutovaluo;
	}

	public void setFinAutovaluo(BigDecimal finAutovaluo) {
		this.finAutovaluo = finAutovaluo;
	}

	public BigDecimal getTasaImpuesto() {
		return tasaImpuesto;
	}

	public void setTasaImpuesto(BigDecimal tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
	}

	public BigDecimal getImpuestoAcumulado() {
		return impuestoAcumulado;
	}

	public void setImpuestoAcumulado(BigDecimal impuestoAcumulado) {
		this.impuestoAcumulado = impuestoAcumulado;
	}
}