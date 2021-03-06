package com.sistemas.municipal.models;
import java.math.*;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Views({
	@View(members=
			"generales["+
			"ainiCuenta,periodo;"+
			"tributo,fechaEmision,fechaVencimiento;"+
			"predioUrbano;"+
			"];"+
			"importes["+
	        "insoluto,ged,reajuste,interes,total;"+
			"];"+
	        "estadocuenta"),
		@View(name="DetalleCuentaVista1",	members="tributo,ainiCuenta,periodo"),
		@View(name="DetalleCuentaVista2",	members="tributo,ainiCuenta,periodo;insoluto")
})
@Tabs({
	@Tab(properties="tributo.descripcion;ainiCuenta,periodo;insoluto,estadocuenta.descripcion"),
	@Tab(name="DetalleCuentaTabular1", baseCondition="parent.id=1")
})
@Entity
public class DetalleCuentaCorriente extends Deletable{
	
	@ManyToOne
	private CuentaCorriente parent;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private Tributo tributo;
	
	@Column(length=4)
	@Required
	private int ainiCuenta;
	
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
//	@Required
	@ReadOnly
	private BigDecimal reajuste;

	@Column(length=9)
//	@Required
	@ReadOnly
	private BigDecimal interes;
	
	@Column(length=9)
	@DefaultValueCalculator(CurrentDateCalculator.class)	
	@ReadOnly
	private Date fechaEmision;
	
	@Column(length=9)
	private Date fechaVencimiento;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("PredioUrbanoVista1")
	@NoCreate @NoModify
	private PredioUrbano predioUrbano;
	
	@Depends("insoluto,ged,reajuste,interes")
	public BigDecimal getTotal(){
		return getInsoluto().add(getGed()).add(getReajuste().add(interes)); 
	}	

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion", orderByKey=true, condition="${id} = 1")
	@NoCreate @NoModify
	private EstadoCuenta estadocuenta;

	public CuentaCorriente getParent() {
		return parent;
	}

	public void setParent(CuentaCorriente parent) {
		this.parent = parent;
	}

	public Tributo getTributo() {
		return tributo;
	}

	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}

	public int getAiniCuenta() {
		return ainiCuenta;
	}

	public void setAiniCuenta(int ainiCuenta) {
		this.ainiCuenta = ainiCuenta;
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

	public PredioUrbano getPredioUrbano() {
		return predioUrbano;
	}

	public void setPredioUrbano(PredioUrbano predioUrbano) {
		this.predioUrbano = predioUrbano;
	}

	public EstadoCuenta getEstadocuenta() {
		return estadocuenta;
	}

	public void setEstadocuenta(EstadoCuenta estadocuenta) {
		this.estadocuenta = estadocuenta;
	}
}
