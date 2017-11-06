package com.sistemas.municipal.models;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import org.openxava.annotations.*;

		
@Views({
	@View(members=
			"id;"+
			"contribuyente;"+
			"detalleordenpago;"+		
			 "totalInsoluto"),
		@View(name="OrdenPago",	members="contribuyente")
})		
@Entity
public class OrdenPago extends Deletable{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("Simple")
	@NoCreate @NoModify
//	@SearchAction("CuentaCorriente.searchContribuyente")
	private Contribuyente contribuyente;
		
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	@ListProperties("tributo.descripcion,aini,periodo,insoluto,ged,reajuste,interes")
	@NoCreate @NoModify
	private Collection<DetalleOrdenPago> detalleordenpago = new ArrayList<DetalleOrdenPago>();
	
	@Stereotype("MONEY")
	public BigDecimal getTotalInsoluto(){
		BigDecimal result = new BigDecimal("0.00");
		for(DetalleOrdenPago detalleordenpago: getDetalleordenpago())
		{
			result = result.add(detalleordenpago.getInsoluto());
		}
		return result;
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Collection<DetalleOrdenPago> getDetalleordenpago() {
		return detalleordenpago;
	}

	public void setDetalleordenpago(Collection<DetalleOrdenPago> detalleordenpago) {
		this.detalleordenpago = detalleordenpago;
	}
}	
	
	