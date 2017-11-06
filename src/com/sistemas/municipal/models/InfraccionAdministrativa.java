package com.sistemas.municipal.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;

@Entity
public class InfraccionAdministrativa extends Deletable{
	@Column(length = 7)
	private String codigo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList(orderByKey=true)
	@NoCreate @NoModify
	private Oficina oficina;
	
	@Column(length=9)
	private BigDecimal porcentajeUit;
	
	@Column(length=9)
	@Required
	public BigDecimal monto;
	
	@Stereotype("MEMO")
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList(orderByKey=true)
	private MedidaComplementariaAdministrativa medidaComplementariaAdministrativa;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public BigDecimal getPorcentajeUit() {
		return porcentajeUit;
	}

	public void setPorcentajeUit(BigDecimal porcentajeUit) {
		this.porcentajeUit = porcentajeUit;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MedidaComplementariaAdministrativa getMedidaComplementariaAdministrativa() {
		return medidaComplementariaAdministrativa;
	}

	public void setMedidaComplementariaAdministrativa(
			MedidaComplementariaAdministrativa medidaComplementariaAdministrativa) {
		this.medidaComplementariaAdministrativa = medidaComplementariaAdministrativa;
	}
}
