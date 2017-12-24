package com.sistemas.municipal.models;

import java.util.Date;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;

@Entity
@Views({ @View(members =
		"codigo;"+
		"Via[" +
		"via,numero,interior,manzana,lote;"+
		"]"),	
@View(name = "PredioVista1", members = "codigo;via,numero,interior,manzana,lote")})
public class Predio extends Deletable {
	@Column(length = 7)
	@ReadOnly
	private int codigo;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false) 
	@SearchListCondition("${distrito} = 30")
	@ReferenceView("ViaVista2")
	@NoCreate @NoModify @NoFrame
	private Via via;
	
	@Column(length = 5)
	private Integer numero;
	
	@Column(length = 3)
//	@Required
	private Integer interior;
	
	@Column(length = 3)
//	@Required
	private String  manzana;

	@Column(length = 3)
//	@Required
	private String lote;
	
	@PrePersist
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getInterior() {
		return interior;
	}

	public void setInterior(Integer interior) {
		this.interior = interior;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
}