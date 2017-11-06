package com.sistemas.municipal.reports;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openxava.actions.JasperReportBaseAction;
import org.openxava.model.MapFacade;
import org.openxava.util.Messages;
import org.openxava.validators.ValidationException;

import com.sistemas.municipal.models.*;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CuentaCorrientePendienteReport extends JasperReportBaseAction{
	
	private CuentaCorriente cuentaCorriente;
	
	@Override
	public Map getParameters() throws Exception{
		Messages errors = MapFacade.validate("CuentaCorriente", getView().getAllValues());
		if(errors.contains()) throw new ValidationException(errors);
		Map parameters = new HashMap();
		parameters.put("codigo", getCuentaCorriente().getContribuyente().getCodigo());
//		parameters.put("nombres", getCuentaCorriente().getContribuyente().getApellidoPaterno()+" "+getCuentaCorriente().getContribuyente().getApellidoMaterno()+" "+getCuentaCorriente().getContribuyente().getNombre().trim());
		parameters.put("documento",getCuentaCorriente().getContribuyente().getTipoDocumento().getDescripcion()+"-"+getCuentaCorriente().getContribuyente().getNumeroDocumento());
//		parameters.put("dptoprov", getCuentaCorriente().getContribuyente().getDireccionFiscal().getDepartamento().getDescripcion().trim()+" "+getCuentaCorriente().getContribuyente().getDireccionFiscal().getProvincia().getDescripcion());		
//		parameters.put("direccionfiscal", getCuentaCorriente().getContribuyente().getDireccionFiscal().getTipovia().getDescripcion()+" "+getCuentaCorriente().getContribuyente().getDireccionFiscal().getVia().getDescripcion()+" "+getCuentaCorriente().getContribuyente().getDireccionFiscal().getNumero());
		parameters.put("totalPendiente", getCuentaCorriente().getTotalPendiente());
		return parameters;
	}
	
	@Override
	protected JRDataSource getDataSource() throws Exception{
		return new JRBeanCollectionDataSource(getCuentaCorriente().getDetallesctacte());
	}
	
	@Override
	protected String getJRXML(){
		return "CuentaCorrientePendiente.jrxml";
	}
	
	private CuentaCorriente getCuentaCorriente() throws Exception{
		if(cuentaCorriente == null){
			int id = getView().getValueInt("contribuyente.id");
			cuentaCorriente = CuentaCorriente.BuscarPorCodigo(id);
		}
		return cuentaCorriente;
	}
}