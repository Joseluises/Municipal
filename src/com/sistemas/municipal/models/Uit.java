package com.sistemas.municipal.models;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"aini,monto;"+
			"]"),
		@View(name="UitVista1",	members="codigo")
})			
@Entity
@Tab(properties="codigo,aini,monto")
public class Uit extends Deletable{
	@Column(length=6)
	@ReadOnly
	private int codigo;
	
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@ReadOnly
	private int aini;
	
	@Stereotype("MONEY")
	private BigDecimal monto;
	
	@PrePersist
	public void generarCodigo() throws Exception{
		Query query = XPersistence.getManager()
				.createQuery("SELECT max(i.codigo) from " +
						getClass().getSimpleName() +
						 "  i");
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null?1:ultimoCodigo + 1;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getAini() {
		return aini;
	}

	public void setAini(int aini) {
		this.aini = aini;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
}
