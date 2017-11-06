package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
public class FichaCatastral extends Deletable{
	@Column(length=6)
	@ReadOnly(forViews="DEFAULT")
	@SearchKey
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
	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@DescriptionsList("pro")
	@NoFrame
	private TipoPuerta tipoPuerta;
	public TipoPuerta getTipoPuerta() {
		return tipoPuerta;
	}

	public void setTipoPuerta(TipoPuerta tipoPuerta) {
		this.tipoPuerta = tipoPuerta;
	}

	@PrePersist
	public void generarCodigo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " +
						getClass().getSimpleName() +
						 "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null?1:ultimoCodigo + 1;
	}
}
