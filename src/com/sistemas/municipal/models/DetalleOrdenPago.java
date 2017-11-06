package com.sistemas.municipal.models;

import java.math.*;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
public class DetalleOrdenPago extends Deletable{
	
	@ManyToOne
	private OrdenPago parent;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("Simple")
	@NoCreate @NoModify
	private Contribuyente contribuyente;
	
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
	
	@Column(length=9)
	private BigDecimal abonado;
	
	@Column(length=9)
	@DefaultValueCalculator(CurrentDateCalculator.class)	
	@ReadOnly
	private Date fechaEmision;
	
	@Column(length=9)
	private Date fechaVencimiento;
	
	@Depends("insoluto,ged,reajuste,interes")
	public BigDecimal getTotal(){
		return getInsoluto().add(getGed()).add(getReajuste().add(interes)); 
	}	

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion", orderByKey=true)
	@NoCreate @NoModify
	private EstadoCuenta estadocuenta;

	public OrdenPago getParent() {
		return parent;
	}

	public void setParent(OrdenPago parent) {
		this.parent = parent;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
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

	public BigDecimal getAbonado() {
		return abonado;
	}

	public void setAbonado(BigDecimal abonado) {
		this.abonado = abonado;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public EstadoCuenta getEstadocuenta() {
		return estadocuenta;
	}

	public void setEstadocuenta(EstadoCuenta estadocuenta) {
		this.estadocuenta = estadocuenta;
	}
}
