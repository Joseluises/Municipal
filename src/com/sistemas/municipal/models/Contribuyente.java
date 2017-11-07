package com.sistemas.municipal.models;

import java.util.Date;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoFrame;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Required;
import org.openxava.annotations.RowStyle;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.annotations.Tabs;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.CurrentYearCalculator;
import org.openxava.jpa.XPersistence;


@Entity
@Views({ @View(members = "generales[" +
						"codigo;"+
						"apellidoNombre,tipoPersona;"+ 
						"tipoDocumento,numeroDocumento;" +
						"telefono, email;" +						
						"];"+ 
						"direccion[" + 
						"via;numero,interior,manzana,lote;"+
						"];"+						
						"Conyugue["+
						"apellidoNombreConyugue;"+
						"tipoDocumentoConyugue,numeroDocumentoConyugue;"+ 
						"]"),
		@View(name = "ContribuyenteVista1", members = "codigo; apellidoNombre"),
		@View(name = "ContribuyenteVista2", members = "codigo; apellidoNombre; tipoDocumento.descripcion, numeroDocumento") })
@Tabs({ @Tab(rowStyles = @RowStyle(style = "row-highlight", property = "tipoPersona.descripcion", value = "JURIDICA") , baseCondition = "${deleted}=false", properties = "codigo,apellidoNombre,tipoPersona.descripcion,",
				defaultOrder = "${id}"),
		@Tab(name = "Deleted", baseCondition = "${deleted}=true", properties = "codigo,apellidoNombre,tipoPersona.descripcion,",
				defaultOrder = "${id}") })
public class Contribuyente extends Deletable {
	@Column(length = 6)
	@ReadOnly
	private int codigo;

	@Column(length = 80)
	@Required
	private String apellidoNombre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	private TipoPersona tipoPersona;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("ViaVista1")
	@NoFrame
	private Via via;
	
	@Column(length = 6)
//	@Required
	private Integer numero;
	
	@Column(length = 6)
//	@Required
	private Integer interior;
	
	@Column(length = 3)
//	@Required
	private String  manzana;

	@Column(length = 3)
//	@Required
	private String lote;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList(descriptionProperties="id,descripcion")
	@NoCreate
	@NoModify
	private TipoDocumento tipoDocumento;

	@Column(length = 12)
	@Required
	private String numeroDocumento;

	@Stereotype("TELEPHONE")
	@Column(length = 10)
	private String telefono;

	@Stereotype("EMAIL")
	private String email;
	
	@Column(length = 80)
//	@Required
	private String apellidoNombreConyugue;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private TipoDocumento tipoDocumentoConyugue;
	
	@Column(length = 12)
//	@Required
	private String numeroDocumentoConyugue;

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

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getInterior() {
		return interior;
	}

	public void setInterior(Integer interior) {
		this.interior = interior;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellidoNombreConyugue() {
		return apellidoNombreConyugue;
	}

	public void setApellidoNombreConyugue(String apellidoNombreConyugue) {
		this.apellidoNombreConyugue = apellidoNombreConyugue;
	}

	public TipoDocumento getTipoDocumentoConyugue() {
		return tipoDocumentoConyugue;
	}

	public void setTipoDocumentoConyugue(TipoDocumento tipoDocumentoConyugue) {
		this.tipoDocumentoConyugue = tipoDocumentoConyugue;
	}

	public String getNumeroDocumentoConyugue() {
		return numeroDocumentoConyugue;
	}

	public void setNumeroDocumentoConyugue(String numeroDocumentoConyugue) {
		this.numeroDocumentoConyugue = numeroDocumentoConyugue;
	}
}