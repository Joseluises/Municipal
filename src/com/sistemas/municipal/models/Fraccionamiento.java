package com.sistemas.municipal.models;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Entity
public class Fraccionamiento extends Deletable {
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@SearchKey
	private int aini;

	@Column(length = 6)
	@ReadOnly(forViews = "DEFAULT")
	@SearchKey
	private int numero;

	private boolean conBeneficio;

	private Date fechaInicio;

	@Column(length = 9)
	@Required
	private BigDecimal sobretasa;

	@Column(length = 9)
	@Required
	private BigDecimal porcentajeCoutaInicial;

	@Column(length = 4)
	private int numeroCuotas;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("Simple")
	@NoCreate
	@NoModify
	private Contribuyente contribuyente;

	@PrePersist
	public void calcularNumero() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("select max(i.numero) from " + getClass().getSimpleName() + " i where i.aini = :aini");
		query.setParameter("aini", aini);
		Integer ultimoNumero = (Integer) query.getSingleResult();
		this.numero = ultimoNumero == null ? 1 : ultimoNumero + 1;
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isConBeneficio() {
		return conBeneficio;
	}

	public void setConBeneficio(boolean conBeneficio) {
		this.conBeneficio = conBeneficio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public BigDecimal getSobretasa() {
		return sobretasa;
	}

	public void setSobretasa(BigDecimal sobretasa) {
		this.sobretasa = sobretasa;
	}

	public BigDecimal getPorcentajeCoutaInicial() {
		return porcentajeCoutaInicial;
	}

	public void setPorcentajeCoutaInicial(BigDecimal porcentajeCoutaInicial) {
		this.porcentajeCoutaInicial = porcentajeCoutaInicial;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}
}