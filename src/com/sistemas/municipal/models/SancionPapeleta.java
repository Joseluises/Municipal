package com.sistemas.municipal.models;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Views({
	@View(members=
			"codigomulta;"+
			"descripcion,porcentajeuit"),
		@View(name="SancionPapeleta",	members="codigomulta;descripcion")
})		
@Entity
public class SancionPapeleta extends Deletable{
	@Column(length=6)
	@Required
	private String codigomulta;

	@Required
	@Stereotype("MEMO")
	private String descripcion;
	
	@Column(length=9)
	private BigDecimal porcentajeuit;

	public String getCodigomulta() {
		return codigomulta;
	}

	public void setCodigomulta(String codigomulta) {
		this.codigomulta = codigomulta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPorcentajeuit() {
		return porcentajeuit;
	}

	public void setPorcentajeuit(BigDecimal porcentajeuit) {
		this.porcentajeuit = porcentajeuit;
	}
}
