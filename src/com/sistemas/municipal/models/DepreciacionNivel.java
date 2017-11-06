package com.sistemas.municipal.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Query;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.jpa.XPersistence;


@Views({
	@View(members=
				"generales["+
				"clasificacionNivel,materialNivel;"+
				"desde,hasta;"+
				"muyBueno;"+
				"bueno;"+
				"regular;"+
				"malo;"+
				"muyMalo;"+
				"]"),
		@View(name="DepreciacionNivelVista1",	members="clasificacionNivel.descripcion")
})

@Entity
@Tab(properties="clasificacionNivel.descripcion,materialNivel.descripcion,desde,hasta,muyBueno,bueno,regular,malo,muyMalo", defaultOrder="${clasificacionNivel}, ${materialNivel}, ${desde}, ${hasta}")
public class DepreciacionNivel extends Deletable{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	private ClasificacionNivel clasificacionNivel;

	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	private MaterialNivel materialNivel;
	
	@Column(length=2)
	private int desde;
	
	@Column(length=3)
	@Required
	private int hasta;
	
	@Column(length=5)
	private BigDecimal muyBueno;
	
	@Column(length=5)
	@Required
	private BigDecimal bueno;

	@Column(length=5)
	@Required
	private BigDecimal regular;

	@Column(length=5)
	@Required
	private BigDecimal malo;

	@Column(length=5)
	private BigDecimal muyMalo;
	
	public static List<DepreciacionNivel> buscarPorcDepreciacionNivel(Object clasif, Object mater, Object antig){
		Query query = XPersistence.getManager().createQuery("FROM DepreciacionNivel where clasificacionnivel_id = :clasif and materialnivel_id = :mater and (desde <= :antig and hasta >= :antig)");
		query.setParameter ("clasif",clasif);
		query.setParameter("mater",mater);
		query.setParameter("antig",antig);
		return query.getResultList();
	}

	public ClasificacionNivel getClasificacionNivel() {
		return clasificacionNivel;
	}

	public void setClasificacionNivel(ClasificacionNivel clasificacionNivel) {
		this.clasificacionNivel = clasificacionNivel;
	}

	public MaterialNivel getMaterialNivel() {
		return materialNivel;
	}

	public void setMaterialNivel(MaterialNivel materialNivel) {
		this.materialNivel = materialNivel;
	}

	public int getDesde() {
		return desde;
	}

	public void setDesde(int desde) {
		this.desde = desde;
	}

	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}

	public BigDecimal getMuyBueno() {
		return muyBueno;
	}

	public void setMuyBueno(BigDecimal muyBueno) {
		this.muyBueno = muyBueno;
	}

	public BigDecimal getBueno() {
		return bueno;
	}

	public void setBueno(BigDecimal bueno) {
		this.bueno = bueno;
	}

	public BigDecimal getRegular() {
		return regular;
	}

	public void setRegular(BigDecimal regular) {
		this.regular = regular;
	}

	public BigDecimal getMalo() {
		return malo;
	}

	public void setMalo(BigDecimal malo) {
		this.malo = malo;
	}

	public BigDecimal getMuyMalo() {
		return muyMalo;
	}

	public void setMuyMalo(BigDecimal muyMalo) {
		this.muyMalo = muyMalo;
	}
}
