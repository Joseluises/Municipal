package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Views({
	@View(members=
			"codigo;" +
			"Vencimiento[" +	
			"tributo; " +
			"aini,periodo,fechaVencimiento;" +
			"]"),
	@View(name="VencimientoTributo",	members="tributo.descripcion; aini,periodo;fechaVencimiento"),
})
@Tab(properties="codigo,tributo.descripcion,aini,periodo,fechaVencimiento", defaultOrder="${id}")
@Entity
public class VencimientoTributo extends Deletable{
	@Column(length=6)
	@ReadOnly
	private int codigo;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion", orderByKey=true)
	@NoCreate @NoModify
	private Tributo tributo;

	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@Column(length=2)
	private int periodo;

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaVencimiento;
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaLimite;
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInicio;

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaFin;
	
	@PrePersist
	public void generarCodigo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " +
						getClass().getSimpleName() +
						 "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null?1:ultimoCodigo + 1;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
