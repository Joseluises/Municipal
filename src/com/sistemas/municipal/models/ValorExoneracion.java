package com.sistemas.municipal.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;

@Entity
@Views({
	@View(members=
			"generales["+
			"codigo, aini;"+
			"funx;"+
			"tipoAfectacion;"+
			"porcentajeExonerado;"+
			"montoExonerado"+
			"]"),
		@View(name="ValorExoneracionVista1",	members="codigo")
})			
@Tab(properties="codigo, aini, funx, tipoAfectacion.descripcion, porcentajeExonerado, montoExonerado")
public class ValorExoneracion extends Deletable{
	@Column(length=3)
	@ReadOnly
	private int codigo;
	
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int aini;
	
	@Required
	private Funx funx;
	public enum Funx {CONTRIBUYENTE, PREDIO}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion")	
	@NoCreate @NoModify
	private TipoAfectacion tipoAfectacion;

	@Column(length=3)
	private BigDecimal porcentajeExonerado;
	
	@Column(length=10)
	private BigDecimal montoExonerado;
	
	@PrePersist
	public void generarCodigo() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " + getClass().getSimpleName() + "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public Funx getFunx() {
		return funx;
	}

	public void setFunx(Funx funx) {
		this.funx = funx;
	}

	public TipoAfectacion getTipoAfectacion() {
		return tipoAfectacion;
	}

	public void setTipoAfectacion(TipoAfectacion tipoAfectacion) {
		this.tipoAfectacion = tipoAfectacion;
	}

	public BigDecimal getPorcentajeExonerado() {
		return porcentajeExonerado;
	}

	public void setPorcentajeExonerado(BigDecimal porcentajeExonerado) {
		this.porcentajeExonerado = porcentajeExonerado;
	}

	public BigDecimal getMontoExonerado() {
		return montoExonerado;
	}

	public void setMontoExonerado(BigDecimal montoExonerado) {
		this.montoExonerado = montoExonerado;
	}
}
