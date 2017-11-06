package com.sistemas.municipal.models;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Entity
@Tab(properties="aini,categoria,muros,techos,pisos,puertas,revestimientos,banos,instalaciones")
public class CategoriaNivel extends Deletable{
	private static final Object B = null;

	@Column(length=4)
	@Required
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@Enumerated(EnumType.STRING)
	@Column(length=1)
	private Categoria categoria;
	public enum Categoria {A,B,C,D,E,F,G,H,I,J}
	
	@Column(length=5)
	private BigDecimal muros;

	@Column(length=5)
	private BigDecimal techos;

	@Column(length=5)
	private BigDecimal pisos;

	@Column(length=5)
	private BigDecimal puertas;

	@Column(length=5)
	private BigDecimal revestimientos;

	@Column(length=5)
	private BigDecimal banos;

	@Column(length=5)
	private BigDecimal instalaciones;
	
	public static List<CategoriaNivel> buscarMontoCategoriaNivel(Object ancon){
		Query query = XPersistence.getManager().createQuery("from CategoriaNivel where aini = :ancon and categoria='A'");
		query.setParameter("ancon",ancon);
		return query.getResultList();
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getMuros() {
		return muros;
	}

	public void setMuros(BigDecimal muros) {
		this.muros = muros;
	}

	public BigDecimal getTechos() {
		return techos;
	}

	public void setTechos(BigDecimal techos) {
		this.techos = techos;
	}

	public BigDecimal getPisos() {
		return pisos;
	}

	public void setPisos(BigDecimal pisos) {
		this.pisos = pisos;
	}

	public BigDecimal getPuertas() {
		return puertas;
	}

	public void setPuertas(BigDecimal puertas) {
		this.puertas = puertas;
	}

	public BigDecimal getRevestimientos() {
		return revestimientos;
	}

	public void setRevestimientos(BigDecimal revestimientos) {
		this.revestimientos = revestimientos;
	}

	public BigDecimal getBanos() {
		return banos;
	}

	public void setBanos(BigDecimal banos) {
		this.banos = banos;
	}

	public BigDecimal getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(BigDecimal instalaciones) {
		this.instalaciones = instalaciones;
	}
}
