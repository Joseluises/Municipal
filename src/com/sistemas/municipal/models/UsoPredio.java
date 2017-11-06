package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Views({
	@View(members=
			"generales["+
			"codigo;"+
			"aini,descripcion;"+
			"]"),
		@View(name="UsoPredioSimple1",	members="codigo,aini,descripcion")
})		
@Entity
@Tab(properties="codigo,aini,descripcion")
public class UsoPredio extends Deletable{
	
	@Column(length=6)
	@ReadOnly
	private int codigo;
	
	@Required
	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@ReadOnly
	private int aini;
	
	@Column(length=45)
	@Required
	private String descripcion;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
