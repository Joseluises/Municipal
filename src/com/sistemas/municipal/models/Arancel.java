package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"via;"+
			"]"),
		@View(name="ViaVista1",	members="codigo;via")
})			
@Tab(properties="codigo,via")
public class Arancel extends Deletable{
	@Column(length=6)
	@ReadOnly(forViews="DEFAULT")
	@SearchKey
	private int codigo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false) 
	@SearchListCondition("${distrito} = 30")
	@ReferenceView("ViaVista2")
	@NoCreate @NoModify @NoFrame
	private Via via;
		
	@OneToMany(mappedBy="parentArancel", cascade=CascadeType.ALL)
	@ListProperties("aini, viaInicial, viaFinal, montoViaPar, montoViaInpar")
	private Collection<ArancelDetalle> arancelDetalle = new ArrayList<ArancelDetalle>();
	
	@PrePersist
	public void generarCodigo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " +
						getClass().getSimpleName() +
						 "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null?1:ultimoCodigo + 1;
	}
	
	private static List<Arancel> buscarPorArancel(Object predio, int aini) {
		Query query = XPersistence.getManager().createQuery("FROM ArancelDetalle WHERE aini=:aini AND parentarancel_id=:predio");
		query.setParameter("predio", predio);
		query.setParameter("aini", aini);
		return query.getResultList();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Collection<ArancelDetalle> getArancelDetalle() {
		return arancelDetalle;
	}

	public void setArancelDetalle(Collection<ArancelDetalle> arancelDetalle) {
		this.arancelDetalle = arancelDetalle;
	}
}
