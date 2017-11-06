package com.sistemas.municipal.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;

@Views({
	@View(members=
			"ainiRecibo,numeroRecibo,fechaRecibo;"+
	        "tipoRecibo;"+
	        "formaPago;"+
			"contribuyente;"+
			"cuentacorriente{cajadetallecuenta}"+
	        "rubro{cajadetallerubro}"),
	@View(name="Simple1",	members="ainiRecibo,numeroRecibo")
})	

@Tab(properties="ainiRecibo,numeroRecibo,fechaRecibo,tipoRecibo.descripcion")
@Entity
public class Caja extends Deletable{
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@ReadOnly	
	private int ainiRecibo;
	
	@Column(length=6)
	@ReadOnly(forViews="DEFAULT")
	private int numeroRecibo;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@ReadOnly
	private Date fechaRecibo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private TipoRecibo tipoRecibo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("ContribuyenteVista1")
	@NoCreate @NoModify
	private Contribuyente contribuyente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private FormaPago formaPago;
	
	@OneToMany(mappedBy="parentCajaDetalleCuenta", cascade=CascadeType.ALL)
//	@ListProperties("nropiso,antiguedad,categoria,materialNivel.descripcion, conservacionNivel.descripcion")
	private Collection<CajaDetalleCuenta> cajadetallecuenta = new ArrayList<CajaDetalleCuenta>();
	
	@OneToMany(mappedBy="parentCajaDetalleRubro", cascade=CascadeType.ALL)
//	@ListProperties("nropiso,antiguedad,categoria,materialNivel.descripcion, conservacionNivel.descripcion")
	private Collection<CajaDetalleRubro> cajadetallerubro = new ArrayList<CajaDetalleRubro>();
	
	@PrePersist
	public void calcularNumeroRecibo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("select max(i.numeroRecibo) from " +
						getClass().getSimpleName() +
						" i where i.ainiRecibo = :ainiRecibo");
		query.setParameter("ainiRecibo", ainiRecibo);
		Integer ultimoNumero = (Integer) query.getSingleResult();
		this.numeroRecibo= ultimoNumero == null?1:ultimoNumero + 1;
	}

	public int getAiniRecibo() {
		return ainiRecibo;
	}

	public void setAiniRecibo(int ainiRecibo) {
		this.ainiRecibo = ainiRecibo;
	}

	public int getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(int numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public TipoRecibo getTipoRecibo() {
		return tipoRecibo;
	}

	public void setTipoRecibo(TipoRecibo tipoRecibo) {
		this.tipoRecibo = tipoRecibo;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Collection<CajaDetalleCuenta> getCajadetallecuenta() {
		return cajadetallecuenta;
	}

	public void setCajadetallecuenta(Collection<CajaDetalleCuenta> cajadetallecuenta) {
		this.cajadetallecuenta = cajadetallecuenta;
	}

	public Collection<CajaDetalleRubro> getCajadetallerubro() {
		return cajadetallerubro;
	}

	public void setCajadetallerubro(Collection<CajaDetalleRubro> cajadetallerubro) {
		this.cajadetallerubro = cajadetallerubro;
	}
}
