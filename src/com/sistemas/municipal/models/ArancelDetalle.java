package com.sistemas.municipal.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Query;

import org.openxava.annotations.*;
import org.openxava.jpa.XPersistence;

@Entity
@Views({@View(members="generales["+
		"aini;"+
		"viaInicial,viaFinal;"+
		"montoViaPar,montoViaInpar;"+
		"]"),
	@View(name="ArancelDetalleVista1",members="aini")
})			
public class ArancelDetalle extends Deletable{
	
	@ManyToOne
	private Arancel parentArancel;
	
	@Column(length=4)
	@Required
	private int aini;
	
	@Column(length=4)
	@Required
	private int viaInicial;
	
	@Column(length=4)
	@Required
	private int viaFinal;
			
	@Column(length=6)
	private BigDecimal montoViaPar;
	
	@Column(length=6)
	private BigDecimal montoViaInpar;
	
	public static ArancelDetalle buscarArancel(Object predio, int aini){
		Query query = XPersistence.getManager().createQuery("FROM ArancelDetalle WHERE aini=:aini AND parentarancel_id=:predio");
		query.setParameter("aini", aini);		
		query.setParameter("predio", predio);
		return (ArancelDetalle) query.getSingleResult();
	}

	public Arancel getParentArancel() {
		return parentArancel;
	}

	public void setParentArancel(Arancel parentArancel) {
		this.parentArancel = parentArancel;
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public int getViaInicial() {
		return viaInicial;
	}

	public void setViaInicial(int viaInicial) {
		this.viaInicial = viaInicial;
	}

	public int getViaFinal() {
		return viaFinal;
	}

	public void setViaFinal(int viaFinal) {
		this.viaFinal = viaFinal;
	}

	public BigDecimal getMontoViaPar() {
		return montoViaPar;
	}

	public void setMontoViaPar(BigDecimal montoViaPar) {
		this.montoViaPar = montoViaPar;
	}

	public BigDecimal getMontoViaInpar() {
		return montoViaInpar;
	}

	public void setMontoViaInpar(BigDecimal montoViaInpar) {
		this.montoViaInpar = montoViaInpar;
	}
}
