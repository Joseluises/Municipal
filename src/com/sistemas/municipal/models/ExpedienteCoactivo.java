package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Views({
	@View(members=
			"ainiExpediente,numeroExpediente,fechaExpediente;"+
			"ejecutorCoactivo,auxiliarCoactivo;"+
			"estadoExpediente;"+		
			"valorTributario"),
		@View(name="Simple1",	members="ainiExpediente,numeroExpediente")
})		
@Tab(properties="ainiExpediente,numeroExpediente,fechaExpediente,valorTributario.contribuyente.apellido,valorTributario.contribuyente.nombre")
@Entity
public class ExpedienteCoactivo extends Deletable{
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@ReadOnly	
	private int ainiExpediente;
	
	@Column(length=6)
	@ReadOnly(forViews="DEFAULT")
	private int numeroExpediente;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@ReadOnly
	private Date fechaExpediente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@NoCreate @NoModify
	@ReferenceView("Simple2")
	private ValorTributario valorTributario;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private AuxiliarCoactivo auxiliarCoactivo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private EjecutorCoactivo ejecutorCoactivo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate @NoModify
	private EstadoExpediente estadoExpediente;

	@PrePersist
	public void calcularNumeroExpediente() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("select max(i.numeroExpediente) from " +
						getClass().getSimpleName() +
						" i where i.ainiExpediente = :ainiExpediente");
		query.setParameter("ainiExpediente", ainiExpediente);
		Integer ultimoNumero = (Integer) query.getSingleResult();
		this.numeroExpediente = ultimoNumero == null?1:ultimoNumero + 1;
	}

	public int getAiniExpediente() {
		return ainiExpediente;
	}

	public void setAiniExpediente(int ainiExpediente) {
		this.ainiExpediente = ainiExpediente;
	}

	public int getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(int numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public Date getFechaExpediente() {
		return fechaExpediente;
	}

	public void setFechaExpediente(Date fechaExpediente) {
		this.fechaExpediente = fechaExpediente;
	}

	public ValorTributario getValorTributario() {
		return valorTributario;
	}

	public void setValorTributario(ValorTributario valorTributario) {
		this.valorTributario = valorTributario;
	}

	public AuxiliarCoactivo getAuxiliarCoactivo() {
		return auxiliarCoactivo;
	}

	public void setAuxiliarCoactivo(AuxiliarCoactivo auxiliarCoactivo) {
		this.auxiliarCoactivo = auxiliarCoactivo;
	}

	public EjecutorCoactivo getEjecutorCoactivo() {
		return ejecutorCoactivo;
	}

	public void setEjecutorCoactivo(EjecutorCoactivo ejecutorCoactivo) {
		this.ejecutorCoactivo = ejecutorCoactivo;
	}

	public EstadoExpediente getEstadoExpediente() {
		return estadoExpediente;
	}

	public void setEstadoExpediente(EstadoExpediente estadoExpediente) {
		this.estadoExpediente = estadoExpediente;
	}
}
