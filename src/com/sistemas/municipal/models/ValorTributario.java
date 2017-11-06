package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Views({ @View(members = "generales[" +
				"ainivalor,numerovalor,tipovalor,fechaEmision,fechaInteres;"+
				"ainiLote,numeroLote,tipoGeneracion;" + 
				"tributo,oficina;"+
				"ainiCuentaInicio,periodoInicio,ainiCuentaFin,periodoFin;" +
				"];" + 
				"contribuyente;"+
				"detalleCuentaCorriente{cuentacorrientedetalle},detalleValorTributario{valortributariodetalle}"),
		@View(name = "ValorTributarioSimple1", members = "ainivalor,numerovalor"),
		@View(name = "ValorTributarioSimple2", members = "ainivalor,numerovalor,fechaEmision,tipovalor,oficina,tributo") })
@Tabs({ @Tab(baseCondition = "${deleted}=false", properties = "tipovalor.descripcion,ainivalor,numerovalor,fechaEmision,contribuyente.apellidoNombre,ainiLote,numeroLote"),
		@Tab(name = "Deleted", baseCondition = "${deleted}=true", properties = "tipovalor.descripcion,ainivalor,numerovalor,fechaEmision,contribuyente.apellidoPaterno,contribuyente.apellidoMaterno,contribuyente.nombre,ainiLote,numeroLote") 
})
@Entity
public class ValorTributario extends Deletable {
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ainivalor;

	// @SearchKey
	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	// @ReadOnly // (forViews="DEFAULT")
	private int ainiCuentaInicio;

	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ainiCuentaFin;

	@Column(length = 2)
	private int periodoInicio;

	@Column(length = 2)
	private int periodoFin;

	// @SearchKey
	@Column(length = 6)
	@ReadOnly(forViews = "DEFAULT")
	private int numerovalor;

	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@ReadOnly // (forViews="DEFAULT")
	private int ainiLote;

	@Column(length = 6)
	private int numeroLote;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	@ReadOnly
	private Date fechaEmision;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fechaInteres;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private TipoValor tipovalor;

	// Referencias
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("ContribuyenteVista1")
	@NoCreate
	@NoModify
	// @OnChangeSearch(OnChangeSearchContribuyenteAction.class) // Filtra datos
	// digitados por el usuario con [@SearchKey]
	// @SearchAction("ValorTributario.searchContribuyenteActivo") // Filtra a
	// los contribuyentes activos [estadoContribuyente], se configura en el
	// controller
	private Contribuyente contribuyente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private Tributo tributo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private Oficina oficina;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private TipoGeneracion tipoGeneracion;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	// @Condition("${detalleCuentaCorriente.parent_id} =
	// ${this.contribuyente.codigo}")
	// @NewAction("ValorTributario.añadirCuenta")
	// @SearchListCondition(value="${id}=2")
	private Collection<ValorTributarioDetalle> valortributariodetalle = new ArrayList<ValorTributarioDetalle>();

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Collection<CuentaCorrienteDetalle> cuentacorrientedetalle = new ArrayList<CuentaCorrienteDetalle>();

	@PrePersist
	public void calcularNumeroValor() throws Exception {
		Query query = XPersistence.getManager().createQuery("select max(i.numerovalor) from "
				+ getClass().getSimpleName() + " i where i.ainivalor = :ainivalor and i.tipovalor = :tipovalor");
		query.setParameter("ainivalor", ainivalor);
		query.setParameter("tipovalor", tipovalor);
		Integer ultimoNumero = (Integer) query.getSingleResult();
		this.numerovalor = ultimoNumero == null ? 1 : ultimoNumero + 1;
	}

	public static ValorTributario BuscarPorCodigo(int id) throws NoResultException {
		Query query = org.openxava.jpa.XPersistence.getManager().createQuery("FROM ValorTributario o WHERE o.id = :id");
		query.setParameter("id", new Integer(id));
		return (ValorTributario) query.getSingleResult();
	}

	@Override
	public String toString() {
		return ainivalor + "/" + numerovalor;
	}

	public int getAinivalor() {
		return ainivalor;
	}

	public void setAinivalor(int ainivalor) {
		this.ainivalor = ainivalor;
	}

	public int getAiniCuentaInicio() {
		return ainiCuentaInicio;
	}

	public void setAiniCuentaInicio(int ainiCuentaInicio) {
		this.ainiCuentaInicio = ainiCuentaInicio;
	}

	public int getAiniCuentaFin() {
		return ainiCuentaFin;
	}

	public void setAiniCuentaFin(int ainiCuentaFin) {
		this.ainiCuentaFin = ainiCuentaFin;
	}

	public int getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(int periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public int getPeriodoFin() {
		return periodoFin;
	}

	public void setPeriodoFin(int periodoFin) {
		this.periodoFin = periodoFin;
	}

	public int getNumerovalor() {
		return numerovalor;
	}

	public void setNumerovalor(int numerovalor) {
		this.numerovalor = numerovalor;
	}

	public int getAiniLote() {
		return ainiLote;
	}

	public void setAiniLote(int ainiLote) {
		this.ainiLote = ainiLote;
	}

	public int getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaInteres() {
		return fechaInteres;
	}

	public void setFechaInteres(Date fechaInteres) {
		this.fechaInteres = fechaInteres;
	}

	public TipoValor getTipovalor() {
		return tipovalor;
	}

	public void setTipovalor(TipoValor tipovalor) {
		this.tipovalor = tipovalor;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Tributo getTributo() {
		return tributo;
	}

	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public TipoGeneracion getTipoGeneracion() {
		return tipoGeneracion;
	}

	public void setTipoGeneracion(TipoGeneracion tipoGeneracion) {
		this.tipoGeneracion = tipoGeneracion;
	}

	public Collection<ValorTributarioDetalle> getValortributariodetalle() {
		return valortributariodetalle;
	}

	public void setValortributariodetalle(Collection<ValorTributarioDetalle> valortributariodetalle) {
		this.valortributariodetalle = valortributariodetalle;
	}

	public Collection<CuentaCorrienteDetalle> getCuentacorrientedetalle() {
		return cuentacorrientedetalle;
	}

	public void setCuentacorrientedetalle(Collection<CuentaCorrienteDetalle> cuentacorrientedetalle) {
		this.cuentacorrientedetalle = cuentacorrientedetalle;
	}
}
