package com.sistemas.municipal.models;

import java.math.BigDecimal;

import javax.persistence.*;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentYearCalculator;

import com.sistemas.municipal.actions.*;

@Entity
@Views({ @View(members ="Categorias["+
							"tipoNivel,categorias"+
						"];"+ 		
						"Datos de Nivel["+
							"nropiso;"+
							",antiguedad;"+
							"clasificacionNivel,materialNivel,conservacionNivel;"+
						"];"+
						"Datos del Valuo["+						
							"areaConstruida, porcentajeAreaComun;"+
							"valorUnitario,incremento;"+
							"porcentajeDepreciado,montoDepreciado,valorUnitarioDepreciado;"+
							"valorAreaConstruida;"+
							"valorAreaComun;"+
							"valorConstruccion;"+
						"]"),
		@View(name = "PredioUrbanoDetalleVista1", members = "clasificacionNivel.descripcion") })
public class PredioUrbanoDetalle extends Deletable {
	@ManyToOne
	private PredioUrbano parentPredioUrbano;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	private TipoNivel tipoNivel;
	
	@Column(length = 7)
	@Required
	@OnChange(OnChangeValorUnitarioAction.class)
	private String categorias;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	@OnChange(OnChangePorcentajeDepreciadoAction.class)
	private ClasificacionNivel clasificacionNivel;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	@OnChange(OnChangePorcentajeDepreciadoAction.class)
	private MaterialNivel materialNivel;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	@OnChange(OnChangePorcentajeDepreciadoAction.class)
	private ConservacionNivel conservacionNivel;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private Nivel nropiso;
	public Nivel getNropiso() {
		return nropiso;
	}

	public void setNropiso(Nivel nropiso) {
		this.nropiso = nropiso;
	}

	@Column(length = 3)
	@Required
//	@ReadOnly
	@OnChange(OnChangePorcentajeDepreciadoAction.class)
	private int antiguedad;

	@ReadOnly
	@Column(length = 9)
	private BigDecimal montoDepreciado;
	public BigDecimal getMontoDepreciado() {
		return montoDepreciado;
	}

	public void setMontoDepreciado(BigDecimal montoDepreciado) {
		this.montoDepreciado = montoDepreciado;
	}

	@ReadOnly
	@Column(length = 9)
	private BigDecimal porcentajeDepreciado;
	public BigDecimal getPorcentajeDepreciado() {
		return porcentajeDepreciado;
	}

	public void setPorcentajeDepreciado(BigDecimal porcentajeDepreciado) {
		this.porcentajeDepreciado = porcentajeDepreciado;
	}

	@ReadOnly
	@Column(length = 9)
	private BigDecimal valorUnitarioDepreciado;
	public BigDecimal getValorUnitarioDepreciado() {
		return valorUnitarioDepreciado;
	}

	public void setValorUnitarioDepreciado(BigDecimal valorUnitarioDepreciado) {
		this.valorUnitarioDepreciado = valorUnitarioDepreciado;
	}

	@Column(length = 9)
	@Required
	private BigDecimal areaConstruida;
	@OnChange(OnChangeAreaConstruidaAction.class)
	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	
	@Column(length = 5)
	private BigDecimal porcentajeAreaComun;
	public BigDecimal getPorcentajeAreaComun() {
		return porcentajeAreaComun;
	}

	public void setPorcentajeAreaComun(BigDecimal porcentajeAreaComun) {
		this.porcentajeAreaComun = porcentajeAreaComun;
	}

	@Column(length = 9)
	@ReadOnly
	private BigDecimal valorAreaComun;
	public BigDecimal getValorAreaComun() {
		return valorAreaComun;
	}

	public void setValorAreaComun(BigDecimal valorAreaComun) {
		this.valorAreaComun = valorAreaComun;
	}

	@ReadOnly
	@Column(length = 9)
	private BigDecimal valorAreaConstruida;
	public BigDecimal getValorAreaConstruida() {
		return valorAreaConstruida;
	}

	public void setValorAreaConstruida(BigDecimal valorAreaConstruida) {
		this.valorAreaConstruida = valorAreaConstruida;
	}
	
	@ReadOnly
	@Column(length = 9)
	private BigDecimal valorConstruccion;
	public BigDecimal getValorConstruccion() {
		return valorConstruccion;
	}
	public void setValorConstruccion(BigDecimal valorConstruccion) {
		this.valorConstruccion = valorConstruccion;
	}


	@ReadOnly
	@Column(length = 9)
	private BigDecimal valorUnitario;
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	@ReadOnly
	@Column(length = 5)
	private BigDecimal incremento;

	public PredioUrbano getParentPredioUrbano() {
		return parentPredioUrbano;
	}

	public void setParentPredioUrbano(PredioUrbano parentPredioUrbano) {
		this.parentPredioUrbano = parentPredioUrbano;
	}

	public TipoNivel getTipoNivel() {
		return tipoNivel;
	}

	public void setTipoNivel(TipoNivel tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
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

	public ConservacionNivel getConservacionNivel() {
		return conservacionNivel;
	}

	public void setConservacionNivel(ConservacionNivel conservacionNivel) {
		this.conservacionNivel = conservacionNivel;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public BigDecimal getIncremento() {
		return incremento;
	}

	public void setIncremento(BigDecimal incremento) {
		this.incremento = incremento;
	}
}