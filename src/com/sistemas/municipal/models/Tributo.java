package com.sistemas.municipal.models;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Views({
	@View(members=
			"codigo;"+
			"descripcion;"+
			"partidapresupuestal;"+
			"detalletributo"),
		@View(name="Simple1",	members="aini,numero")
})		
@Entity
public class Tributo extends Deletable {
	@Column(length=6)
	@ReadOnly
	@SearchKey
	private int codigo;

	@Column(length=45)
	@Required
	private String descripcion;
	
	@Column(length=45)
	@Required
	private String partidapresupuestal;
	
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
//	@ReferenceView("TributoDetalleSimple1")
	@ListProperties("ainiTributo,periodoTributo,ipm,fechaVencimiento,fechaProrroga")
	private Collection<TributoDetalle> detalletributo = new ArrayList<TributoDetalle>();
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}

	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
	}

	public Collection<TributoDetalle> getDetalletributo() {
		return detalletributo;
	}

	public void setDetalletributo(Collection<TributoDetalle> detalletributo) {
		this.detalletributo = detalletributo;
	}

}
