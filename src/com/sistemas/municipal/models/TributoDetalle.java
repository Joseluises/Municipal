package com.sistemas.municipal.models;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
@Views({
	@View(members=
			"generales["+
			"ainiTributo,periodoTributo;"+
			"fechaVencimiento,fechaProrroga;"+
			"];"+
			"importes["+
			"ipm,tim;"+
			"bloquearIpm,bloquearTim;"+
			"]"),
		@View(name="TributoDetalleSimple1",	members="ainiTributo, periodoTributo")
})		
@Entity
public class TributoDetalle extends Deletable{
	@ManyToOne
	private Tributo parent;
	
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@SearchKey
	private int ainiTributo;
	
	@Column(length=2)
	@SearchKey
	private int periodoTributo;
	
	private Date fechaVencimiento;
	
	private Date fechaProrroga;
	
	@Column(length=12)
	private BigDecimal ipm;
	
	@Column(length=12)
	private BigDecimal tim;

	private boolean bloquearIpm;
	
	private boolean bloquearTim;

	public Tributo getParent() {
		return parent;
	}

	public void setParent(Tributo parent) {
		this.parent = parent;
	}

	public int getAiniTributo() {
		return ainiTributo;
	}

	public void setAiniTributo(int ainiTributo) {
		this.ainiTributo = ainiTributo;
	}

	public int getPeriodoTributo() {
		return periodoTributo;
	}

	public void setPeriodoTributo(int periodoTributo) {
		this.periodoTributo = periodoTributo;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaProrroga() {
		return fechaProrroga;
	}

	public void setFechaProrroga(Date fechaProrroga) {
		this.fechaProrroga = fechaProrroga;
	}

	public BigDecimal getIpm() {
		return ipm;
	}

	public void setIpm(BigDecimal ipm) {
		this.ipm = ipm;
	}

	public BigDecimal getTim() {
		return tim;
	}

	public void setTim(BigDecimal tim) {
		this.tim = tim;
	}

	public boolean isBloquearIpm() {
		return bloquearIpm;
	}

	public void setBloquearIpm(boolean bloquearIpm) {
		this.bloquearIpm = bloquearIpm;
	}

	public boolean isBloquearTim() {
		return bloquearTim;
	}

	public void setBloquearTim(boolean bloquearTim) {
		this.bloquearTim = bloquearTim;
	}
}
