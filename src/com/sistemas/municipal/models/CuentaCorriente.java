package com.sistemas.municipal.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import org.openxava.annotations.ListProperties;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.jpa.XPersistence;

@Views({
	@View(members=
			"contribuyente;"+
			"detallesctacte;"+
			"Totales["+
			"totalPagado,totalPendiente;"+
			"]"),
		@View(name="CuentaCorrienteSimple1",	members="id,contribuyente.id,contribuyente.apellidoPaterno")
})		
@Entity
@Tab(properties="contribuyente.codigo,contribuyente.apellidoNombre,"
		+ "				contribuyente.tipoDocumento.descripcion, contribuyente.numeroDocumento, contribuyente.tipoPersona.descripcion")
public class CuentaCorriente extends Deletable{
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("ContribuyenteVista2")
	@NoCreate @NoModify
	private Contribuyente contribuyente;
		
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	@ListProperties("tributo.descripcion,ainiCuenta,periodo,predioUrbano.id,fechaVencimiento,insoluto,ged,reajuste,interes,total,estadoCuenta.descripcion")
	private Collection<CuentaCorrienteDetalle> detallesctacte = new ArrayList<CuentaCorrienteDetalle>();
	
	@Stereotype("MONEY")
//	@Depends("articulo.id")
	public BigDecimal getTotalPendiente(){
		BigDecimal result = new BigDecimal("0.00");
		for(CuentaCorrienteDetalle detallesctacte: getDetallesctacte())
		{
			result = result.add(detallesctacte.getInsoluto().add(detallesctacte.getGed().add(detallesctacte.getReajuste().add(detallesctacte.getInteres()))));
		}
		return result;
	}
	
	@Stereotype("MONEY")
	public BigDecimal getTotalPagado(){
		BigDecimal result = new BigDecimal("0.00");
		for(CuentaCorrienteDetalle detallesctacte: getDetallesctacte())
		{
			result = result.add(detallesctacte.getInsoluto().add(detallesctacte.getGed().add(detallesctacte.getReajuste().add(detallesctacte.getInteres()))));
		}
		return result;
	}

	public static CuentaCorriente BuscarPorCodigo(int id) throws NoResultException{
		Query query = org.openxava.jpa.XPersistence.getManager().createQuery("FROM CuentaCorriente o WHERE o.id = :id");
		query.setParameter("id", new Integer(id));
		return (CuentaCorriente) query.getSingleResult();
	}

	public void createValorTributario(Object valorOficina, Object valorTipo, Object valorTributo, 
						int valorInicio, int valorFin, int valorAiniLote, int valorAiniValor, int valorNumeroLote, 
						Date valorFechaInteres) throws Exception{
		Oficina oficina = XPersistence.getManager().find(Oficina.class,valorOficina);
		TipoValor tipoValor = XPersistence.getManager().find(TipoValor.class,valorTipo);
		Tributo tributo = XPersistence.getManager().find(Tributo.class,valorTributo);
		if(oficina == null || oficina == null || oficina==null){
			return;			
		}
		ValorTributario valorTributario = new ValorTributario(); // Crea una instance de Valor tributario [Nuevo]
//		BeanUtils.copyProperties(valorTributario, this);
		valorTributario.setId(0);
		valorTributario.setDeleted(false);
		valorTributario.setAiniCuentaInicio(valorInicio);
		valorTributario.setAiniCuentaFin(valorFin);
		valorTributario.setAiniLote(valorAiniLote);
		valorTributario.setAinivalor(valorAiniValor);
		valorTributario.setFechaEmision(new Date());
		valorTributario.setFechaInteres(valorFechaInteres);
		valorTributario.setNumeroLote(valorNumeroLote);
//		valorTributario.setContribuyente(getContribuyente());
		valorTributario.setOficina(oficina);
		valorTributario.setTipovalor(tipoValor);
		valorTributario.setTributo(tributo);
		valorTributario.setValortributariodetalle(new ArrayList());
		XPersistence.getManager().persist(valorTributario);
//		this.valorTributario = valorTributario;
		copyDetailsToValorTributario(valorTributario);
	}	

	private void copyDetailsToValorTributario(ValorTributario valorTributario) throws Exception{
		ValorTributarioDetalle valorTributarioDetalle = new ValorTributarioDetalle();
		
		for(CuentaCorrienteDetalle detallecuenta: getDetallesctacte()){
			valorTributarioDetalle.setId(0);
//			valorTributarioDetalle.setParent(get);
			
		}
	}

	public Contribuyente getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Contribuyente contribuyente) {
		this.contribuyente = contribuyente;
	}

	public Collection<CuentaCorrienteDetalle> getDetallesctacte() {
		return detallesctacte;
	}

	public void setDetallesctacte(Collection<CuentaCorrienteDetalle> detallesctacte) {
		this.detallesctacte = detallesctacte;
	}
}
