package com.sistemas.municipal.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;

import com.sistemas.municipal.actions.OnChangeArancelViaAction;

@Entity
@Views({
	@View(members=
			"anexo,fechaRecepcion,condicionPropiedad,porcentajeCondominio;"+
			"Predio["+
			"predio;"+
			"];"+
			"Caracteristica["+
			"estadoPredio,fechaAdquisicion,frontis;"+
			"tipoPredio;"+
			"usoPredio,tipoAfectacion;"+
			"ubicacionParque;"+
			"areaConstruida,otraInstalacion,areaM2,montoArancel;"+
			"];" +
			"niveles{prediourbanodetalle};"+
			"foto{foto};"+
			"totales["+
			"valorConstruccion,valorTerreno,valorOtraInstalacion,autovaluo,totalAutovaluo;"+
			"]"),
		@View(name="PredioUrbanoVista1",	members="")
})
@Tab(properties="anexo")
public class PredioUrbano extends Deletable {
	@ManyToOne
	private HojaResumen parentHojaResumen;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaRecepcion;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaAdquisicion;
	
	@Column(length=3)
	private BigDecimal porcentajeCondominio;

	@Column(length=9)
	private BigDecimal frontis;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@NoCreate
	@NoModify
	@NoFrame
//	@OnChange(OnChangeArancelViaAction.class)	
	@ReferenceView("PredioVista1")
	private Predio predio;
	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	@Column(length = 3)
	private int anexo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion")	
//	@DescriptionsList(orderByKey=true)
	@NoCreate @NoModify
	private CondicionPropiedad condicionPropiedad;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(orderByKey=true)
	@NoCreate @NoModify
	private TipoAfectacion tipoAfectacion;
	
	@Column(length=9)
	@ReadOnly
//	@Depends("areaConstruida")
	private BigDecimal areaConstruida;
	public BigDecimal getAreaConstruida() {
		BigDecimal result = new BigDecimal("0.00");
		for(PredioUrbanoDetalle prediourbanodetalle: getPrediourbanodetalle())
		{
			result = result.add(prediourbanodetalle.getAreaConstruida());
		}
		return result;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	
	
	@Column(length=9)
	@Required
	private BigDecimal areaM2;
	
	@Column(length=9)
	@ReadOnly
	@Required
	public BigDecimal montoArancel;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(descriptionProperties="id,descripcion")
//	@DescriptionsList(orderByKey=true)
	@NoCreate @NoModify
	private EstadoPredio estadoPredio;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
//	@DescriptionsList(orderByKey=true)
	@DescriptionsList(descriptionProperties="id,descripcion")	
	@NoCreate @NoModify
	private TipoPredio tipoPredio;
	
	@ManyToOne(fetch=FetchType.LAZY)
//	@DescriptionsList(depends="ainiPu", condition="${ainiPu} = ? ", descriptionProperties="descripcion", orderByKey=true)
	@DescriptionsList(descriptionProperties="id,descripcion", orderByKey=true)
	@NoCreate @NoModify
	private UsoPredio usoPredio;
	
	@Column(length=9)
	private BigDecimal otraInstalacion;

	@ReadOnly
	@Column(length=9)
	private BigDecimal valorConstruccion;
	public BigDecimal getValorConstruccion() {
		BigDecimal result = new BigDecimal("0.00");
		for(PredioUrbanoDetalle prediourbanodetalle: getPrediourbanodetalle())
		{
			result = result.add(prediourbanodetalle.getValorConstruccion());
		}
		return result==null?BigDecimal.ZERO:result;
	}

	public void setValorConstruccion(BigDecimal valorConstruccion) {
		this.valorConstruccion = valorConstruccion;
	}

	@Column(length=9)
	@ReadOnly
	private BigDecimal valorOtraInstalacion;
	
	@ReadOnly
	@Column(length=9)
	@Depends("areaM2")
	private BigDecimal valorTerreno;
	public BigDecimal getValorTerreno() {
		return (areaM2).multiply(montoArancel)==null?BigDecimal.ZERO:(areaM2).multiply(montoArancel);
	}

	public void setValorTerreno(BigDecimal valorTerreno) {
		this.valorTerreno = valorTerreno;
	}
	
	@ReadOnly
	@Column(length=9)
	private BigDecimal autovaluo;
	
	@ReadOnly
	@Column(length=9)
	@Depends("valorTerreno")
	private BigDecimal totalAutovaluo;
	public BigDecimal getTotalAutovaluo() {
		return getValorTerreno().add(getValorConstruccion())==null?BigDecimal.ZERO:getValorTerreno().add(getValorConstruccion());
	}

	public void setTotalAutovaluo(BigDecimal totalAutovaluo) {
		this.totalAutovaluo = totalAutovaluo;
	}

	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(orderByKey=true)
	@NoCreate @NoModify
	private UbicacionParque ubicacionParque;
	
	@Stereotype("PHOTO")
	private byte[] foto;
	
	@OneToMany(mappedBy="parentPredioUrbano", cascade=CascadeType.ALL)
	@ListProperties("nropiso.descripcion,antiguedad,categorias,valorUnitario,incremento,montoDepreciado,valorUnitarioDepreciado,areaConstruida,valorAreaConstruida,valorAreaComun,valorConstruccion")
	private Collection<PredioUrbanoDetalle> prediourbanodetalle = new ArrayList<PredioUrbanoDetalle>();
	
	public HojaResumen getParentHojaResumen() {
		return parentHojaResumen;
	}

	public void setParentHojaResumen(HojaResumen parentHojaResumen) {
		this.parentHojaResumen = parentHojaResumen;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public BigDecimal getPorcentajeCondominio() {
		return porcentajeCondominio;
	}

	public void setPorcentajeCondominio(BigDecimal porcentajeCondominio) {
		this.porcentajeCondominio = porcentajeCondominio;
	}

	public BigDecimal getFrontis() {
		return frontis;
	}

	public void setFrontis(BigDecimal frontis) {
		this.frontis = frontis;
	}

	public int getAnexo() {
		return anexo;
	}

	public void setAnexo(int anexo) {
		this.anexo = anexo;
	}

	public CondicionPropiedad getCondicionPropiedad() {
		return condicionPropiedad;
	}

	public void setCondicionPropiedad(CondicionPropiedad condicionPropiedad) {
		this.condicionPropiedad = condicionPropiedad;
	}

	public TipoAfectacion getTipoAfectacion() {
		return tipoAfectacion;
	}

	public void setTipoAfectacion(TipoAfectacion tipoAfectacion) {
		this.tipoAfectacion = tipoAfectacion;
	}

	public BigDecimal getAreaM2() {
		return areaM2;
	}

	public void setAreaM2(BigDecimal areaM2) {
		this.areaM2 = areaM2;
	}

	public BigDecimal getMontoArancel() {
		return montoArancel;
	}

	public void setMontoArancel(BigDecimal montoArancel) {
		this.montoArancel = montoArancel;
	}

	public EstadoPredio getEstadoPredio() {
		return estadoPredio;
	}

	public void setEstadoPredio(EstadoPredio estadoPredio) {
		this.estadoPredio = estadoPredio;
	}

	public TipoPredio getTipoPredio() {
		return tipoPredio;
	}

	public void setTipoPredio(TipoPredio tipoPredio) {
		this.tipoPredio = tipoPredio;
	}

	public UsoPredio getUsoPredio() {
		return usoPredio;
	}

	public void setUsoPredio(UsoPredio usoPredio) {
		this.usoPredio = usoPredio;
	}

	public BigDecimal getOtraInstalacion() {
		return otraInstalacion;
	}

	public void setOtraInstalacion(BigDecimal otraInstalacion) {
		this.otraInstalacion = otraInstalacion;
	}

	public BigDecimal getValorOtraInstalacion() {
		return valorOtraInstalacion;
	}

	public void setValorOtraInstalacion(BigDecimal valorOtraInstalacion) {
		this.valorOtraInstalacion = valorOtraInstalacion;
	}

	public BigDecimal getAutovaluo() {
		return autovaluo;
	}

	public void setAutovaluo(BigDecimal autovaluo) {
		this.autovaluo = autovaluo;
	}

	public UbicacionParque getUbicacionParque() {
		return ubicacionParque;
	}

	public void setUbicacionParque(UbicacionParque ubicacionParque) {
		this.ubicacionParque = ubicacionParque;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Collection<PredioUrbanoDetalle> getPrediourbanodetalle() {
		return prediourbanodetalle;
	}

	public void setPrediourbanodetalle(Collection<PredioUrbanoDetalle> prediourbanodetalle) {
		this.prediourbanodetalle = prediourbanodetalle;
	}
}