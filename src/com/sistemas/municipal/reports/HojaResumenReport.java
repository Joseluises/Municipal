package com.sistemas.municipal.reports;

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

public class HojaResumenReport extends JasperReportBaseAction{
	
	private HojaResumen hojaResumen;
	
	@Override
	public Map getParameters() throws Exception{
		Messages errors = MapFacade.validate("HojaResumen", getView().getAllValues());
		if(errors.contains()) throw new ValidationException(errors);
		Map parameters = new HashMap<String, Integer>();
		parameters.put("aini", getHojaResumen().getAiniHr());
		parameters.put("codigo", getHojaResumen().getContribuyente().getCodigo());
		parameters.put("contribuyente", getHojaResumen().getContribuyente().getApellidoNombre());
		parameters.put("direccionfiscal", getHojaResumen().getContribuyente().getVia().getDistrito().getDescripcion()+" - "+
										  getHojaResumen().getContribuyente().getVia().getSector().getDescripcion()+" "+
										  getHojaResumen().getContribuyente().getVia().getUrbanizacion().getDescripcion());
		return parameters;
	}
	
	@Override
	protected JRDataSource getDataSource() throws Exception{
		return new JRBeanCollectionDataSource(getHojaResumen().getPrediourbano());
	}
	
	@Override
	protected String getJRXML(){
		return "HojaResumen.jrxml";
	}
	
	private HojaResumen getHojaResumen() throws Exception{
		if(hojaResumen == null){
			int id = getView().getValueInt("id");
			hojaResumen = HojaResumen.BuscarPorId(id);
		}
		return hojaResumen;
	}
}