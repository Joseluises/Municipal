package com.sistemas.municipal.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;


@Views({
	@View(members=
				"generales["+
				"aini,categoria;"+
				"];"+		
				"valores["+
				"muros;"+	
				"techos;"+
				"pisos;"+
				"puertas;"+
				"revestimientos;"+
				"banos;"+
				"instalaciones;"+
				"]"),
		@View(name="ValorUnitarioVista1",	members="aini,categoria.descripcion")
})

@Entity
@Tab(properties="aini,categoria,muros,techos,pisos,puertas,revestimientos,banos,instalaciones", defaultOrder="${aini}, ${categoria}")
public class ValorUnitarioEdificacion extends Deletable{
	@Column(length=4)
	@Required
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	@Column(length = 1)
	@Required
	private String categoria;
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column(length=5)
	private BigDecimal muros;
	public BigDecimal getMuros() {
		return muros;
	}

	public void setMuros(BigDecimal muros) {
		this.muros = muros;
	}

	@Column(length=5)
	private BigDecimal pisos;
	public BigDecimal getPisos() {
		return pisos;
	}

	public void setPisos(BigDecimal pisos) {
		this.pisos = pisos;
	}

	@Column(length=5)
	private BigDecimal techos;
	public BigDecimal getTechos() {
		return techos;
	}

	public void setTechos(BigDecimal techos) {
		this.techos = techos;
	}

	@Column(length=5)
	private BigDecimal puertas;
	public BigDecimal getPuertas() {
		return puertas;
	}

	public void setPuertas(BigDecimal puertas) {
		this.puertas = puertas;
	}

	@Column(length=5)
	private BigDecimal revestimientos;
	public BigDecimal getRevestimientos() {
		return revestimientos;
	}

	public void setRevestimientos(BigDecimal revestimientos) {
		this.revestimientos = revestimientos;
	}

	@Column(length=5)
	private BigDecimal banos;
	public BigDecimal getBanos() {
		return banos;
	}

	public void setBanos(BigDecimal banos) {
		this.banos = banos;
	}

	@Column(length=5)
	private BigDecimal instalaciones;
	public BigDecimal getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(BigDecimal instalaciones) {
		this.instalaciones = instalaciones;
	}

	public static ValorUnitarioEdificacion buscarMontoCategoriaNivel(int aini, String categoria) throws NoResultException{
		Query query = XPersistence.getManager().createQuery("FROM ValorUnitarioEdificacion o WHERE o.aini=:aini and o.categoria=:categoria");
		query.setParameter("aini",aini);
		query.setParameter("categoria",categoria);
		return (ValorUnitarioEdificacion) query.getSingleResult();
	}
}
