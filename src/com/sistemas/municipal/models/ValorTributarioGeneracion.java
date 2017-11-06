package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Views({ @View(members = "generales["+
						 "ainiLote,numeroLote;"+
						 "tipoValor;"+
						 "];" +
						 "cuentaCorriente["+
						 "ainiCuentaInicio,periodoInicio;"+
						 "ainiCuentaFin,periodoFin;"+
						 "]"),
@View(name = "ValorTributarioGeneracionSimple1", members = "ainivalor,numerovalor,fechaEmision,tipoValor,oficina,tributo") })
@Tabs({ @Tab(baseCondition = "${deleted}=false", properties = "tipoValor.descripcion,ainivalor,numerovalor"), 
@Tab(name = "Deleted", baseCondition = "${deleted}=true", properties = "tipoValor.descripcion,ainivalor,numerovalor") 
})
public class ValorTributarioGeneracion extends Deletable{
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@ReadOnly
	private Date fechaEmision;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInteres;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private TipoValor tipoValor;
	
	@Column(length=4)
	private int ainiLote;
	
	@Column(length=3)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int numeroLote;
	
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ainiCuentaInicio;

	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ainiCuentaFin;

	@Column(length = 2)
	private int periodoInicio;

	@Column(length = 2)
	private int periodoFin;

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaInteres() {
		return fechaInteres;
	}

	public void setFechaInteres(Date fechaInteres) {
		this.fechaInteres = fechaInteres;
	}

	public TipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(TipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	public int getAiniLote() {
		return ainiLote;
	}

	public void setAiniLote(int ainiLote) {
		this.ainiLote = ainiLote;
	}

	public int getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	public int getAiniCuentaInicio() {
		return ainiCuentaInicio;
	}

	public void setAiniCuentaInicio(int ainiCuentaInicio) {
		this.ainiCuentaInicio = ainiCuentaInicio;
	}

	public int getAiniCuentaFin() {
		return ainiCuentaFin;
	}

	public void setAiniCuentaFin(int ainiCuentaFin) {
		this.ainiCuentaFin = ainiCuentaFin;
	}

	public int getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(int periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public int getPeriodoFin() {
		return periodoFin;
	}

	public void setPeriodoFin(int periodoFin) {
		this.periodoFin = periodoFin;
	}
}
