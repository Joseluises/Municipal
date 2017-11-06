package com.sistemas.municipal.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.*;

import org.apache.commons.beanutils.*;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;
import org.openxava.util.*;

@Views({
	@View(members=
			"Generales["+
				"ainiHr,numeroHr,fechaDeclaracion,motivoDeclaracion;"+
				"contribuyente;"+
				"prediosDeclarados;"+
				"autovaluoDeclarado,impuestoAnual;"+
				"];"+
				"Predios{"+
				"prediourbano;"+
				"};"
			),
		@View(name="HojaResumenVista1",	members="codigoHr")
})
@Entity
@Tab(properties="ainiHr,numeroHr,contribuyente.codigo, contribuyente.apellidoNombre,motivoDeclaracion.descripcion,prediosDeclarados,autovaluoDeclarado,impuestoAnual")
//@EntityListeners(AccessTrackingListener.class)
public class HojaResumen extends Deletable {
	@Column(length = 4)
	@Required
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ainiHr;

	@Column(length = 6)
	@ReadOnly(forViews = "DEFAULT")
	private int numeroHr;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaDeclaracion;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")	
	@NoCreate
	@NoModify
	private MotivoDeclaracion motivoDeclaracion;
	
	@Column(length = 4)
	@ReadOnly(forViews = "DEFAULT")
	private int prediosDeclarados;
	public int getPrediosDeclarados() {
		int result = 0;
		for(PredioUrbano prediourbano: getPrediourbano())
		{
			result =result+1; 
		}
		return result;
	}

	public void setPrediosDeclarados(int prediosDeclarados) {
		this.prediosDeclarados = prediosDeclarados;
	}

	@ReadOnly
	@Column(length = 12)
	private BigDecimal autovaluoDeclarado;
	public BigDecimal getAutovaluoDeclarado() {
		BigDecimal result = new BigDecimal("0.00");
		for(PredioUrbano prediourbano: getPrediourbano())
		{
			result = result.add(prediourbano.getTotalAutovaluo());
		}
		return result==null?BigDecimal.ZERO:result;
	}

	public void setAutovaluoDeclarado(BigDecimal autovaluoDeclarado) {
		this.autovaluoDeclarado = autovaluoDeclarado;
	}

	@ReadOnly
	@Column(length = 12)
	private BigDecimal impuestoAnual;
	public BigDecimal getImpuestoAnual() {
		return impuestoAnual==null?BigDecimal.ZERO:impuestoAnual;
	}

	public void setImpuestoAnual(BigDecimal impuestoAnual) {
		this.impuestoAnual = impuestoAnual;
	}
	
	@ReadOnly
	@Column(length = 12)
	private BigDecimal impuestoTrimestral;
	public BigDecimal getImpuestoTrimestral() {
		return impuestoTrimestral==null?BigDecimal.ZERO:impuestoTrimestral;
	}

	public void setImpuestoTrimestral(BigDecimal impuestoTrimestral) {
		this.impuestoTrimestral = impuestoTrimestral;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("ContribuyenteVista1")
	@NoCreate
	@NoModify
	@NoFrame
	private Contribuyente contribuyente;
	
	@OneToMany(mappedBy="parentHojaResumen", cascade=CascadeType.ALL)
	@ListProperties("anexo,predio.via.sector.descripcion,predio.via.urbanizacion.descripcion,predio.via.tipo.descripcion,predio.via.descripcion;"
					+"predio.numero,predio.interior,predio.manzana,predio.lote, totalAutovaluo")
	private Collection<PredioUrbano> prediourbano = new ArrayList<PredioUrbano>();
	
	@PrePersist
	public void generarNumeroHr() throws Exception {
		Query query = XPersistence.getManager()
				.createQuery("select max(i.numeroHr) from " + getClass().getSimpleName() + " i where i.ainiHr = :ainiHr");
		query.setParameter("ainiHr", ainiHr);
		Integer ultimoNumero = (Integer) query.getSingleResult();
		this.numeroHr = ultimoNumero == null ? 1 : ultimoNumero + 1;
	}

	public int getAiniHr() {
		return ainiHr;
	}

	public void setAiniHr(int ainiHr) {
		this.ainiHr = ainiHr;
	}

	public int getNumeroHr() {
		return numeroHr;
	}

	public void setNumeroHr(int numeroHr) {
		this.numeroHr = numeroHr;
	}

	public Date getFechaDeclaracion() {
		return fechaDeclaracion;
	}

	public void setFechaDeclaracion(Date fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}

	public MotivoDeclaracion getMotivoDeclaracion() {
		return motivoDeclaracion;
	}

	public void setMotivoDeclaracion(MotivoDeclaracion motivoDeclaracion) {
		this.motivoDeclaracion = motivoDeclaracion;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Collection<PredioUrbano> getPrediourbano() {
		return prediourbano;
	}

	public void setPrediourbano(Collection<PredioUrbano> prediourbano) {
		this.prediourbano = prediourbano;
	}
	
	public void recalcularAutovaluoDeclarado(){
		setAutovaluoDeclarado(autovaluoDeclarado);
	}
	
	public String toString(){
		return ainiHr+ "/" +numeroHr;
	}
	
	public void crearHojaResumen() throws ValidationException{  // excepcion de aplicacion
		try{
		HojaResumen hojaResumen = new HojaResumen();
		BeanUtils.copyProperties(hojaResumen, this); // copiar todas las propiedades de hojaresumen
		hojaResumen.setId(0); // copiar con ID diferente
//		hojaResumen.setFechaDeclaracion(new Date());
		hojaResumen.setPrediourbano(new ArrayList());
		XPersistence.getManager().persist(hojaResumen);
		copiarDetalleHojaResumen(hojaResumen);
//		this.hojaResumen = HojaResumen;
		}catch (Exception ex){ // excepcion de sistema
			throw new SystemException("imposible_crear_hojaresumen",ex);
		}
	}
	
	private void copiarDetalleHojaResumen(HojaResumen hojaResumen) throws Exception{
		for(PredioUrbano prediourbano: getPrediourbano()){
			PredioUrbano hojaResumenDetalle = (prediourbano); // clonar cada elemento
			BeanUtils.cloneBean(prediourbano);
			hojaResumenDetalle.setId(0); // nueva entidad
			hojaResumenDetalle.setParentHojaResumen(hojaResumen); // nuevo padre
			XPersistence.getManager().persist(prediourbano); // persistente
		} 
	}
}