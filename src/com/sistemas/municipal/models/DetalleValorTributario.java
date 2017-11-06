package com.sistemas.municipal.models;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class DetalleValorTributario extends Deletable{
	@ManyToOne
	private ValorTributario parent;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion", orderByKey=true)
	@NoCreate @NoModify
	private Tributo tributo;
	
	@Column(length=4)
	@Required
	private int aini;
	
	@Column(length=2)
	@Required
	private int periodo;
	
	@Column(length=9)
	@Required
	private BigDecimal insoluto;
	
	@Column(length=9)
	@Required
	private BigDecimal ged;

	@Column(length=9)
	@Required
	private BigDecimal reajuste;

	@Column(length=9)
	@Required
	private BigDecimal interes;

	public ValorTributario getParent() {
		return parent;
	}

	public void setParent(ValorTributario parent) {
		this.parent = parent;
	}

	public Tributo getTributo() {
		return tributo;
	}

	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getInsoluto() {
		return insoluto;
	}

	public void setInsoluto(BigDecimal insoluto) {
		this.insoluto = insoluto;
	}

	public BigDecimal getGed() {
		return ged;
	}

	public void setGed(BigDecimal ged) {
		this.ged = ged;
	}

	public BigDecimal getReajuste() {
		return reajuste;
	}

	public void setReajuste(BigDecimal reajuste) {
		this.reajuste = reajuste;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
}
