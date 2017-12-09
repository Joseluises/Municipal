package com.sistemas.municipal.actions;

import java.math.*;
import java.util.List;
import org.openxava.actions.OnChangePropertyBaseAction;
import com.sistemas.municipal.models.DepreciacionNivel;
public class OnChangePorcentajeDepreciadoAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		Object clasif = getView().getValue("clasificacionNivel.id");
		Object mater = getView().getValue("materialNivel.id");
		Object antig = getView().getValue("antiguedad");
		Integer conser = (Integer) getView().getValue("conservacionNivel.id");
		if(clasif==null || mater==null || antig==null || conser==null){
			getView().setValue("porcentajeDepreciado", 0.00);
			return;
		}else{
			getView().setValue("porcentajeDepreciado", 1.00);
			List<DepreciacionNivel> results = DepreciacionNivel.buscarPorcDepreciacionNivel(clasif, mater,antig);
			for(DepreciacionNivel o:results){
				if(conser==null) getView().setValue("porcentajeDepreciado", 0.00);
				else switch(conser){
				case 1:
					getView().setValue("porcentajeDepreciado", o.getMuyBueno()); 
					break;
				case 2:
					getView().setValue("porcentajeDepreciado", o.getBueno());
					break;
				case 3:
					getView().setValue("porcentajeDepreciado", o.getRegular());
					break;
				case 4:
					getView().setValue("porcentajeDepreciado", o.getMalo());
					break;
				case 5:
					getView().setValue("porcentajeDepreciado", o.getMuyMalo());
					break;
				}	
			}
			BigDecimal nvalorUnitario = (BigDecimal) getView().getValue("valorUnitario");
			BigDecimal nporcentajeDepreciado = (BigDecimal) getView().getValue("porcentajeDepreciado");
			
			BigDecimal nmontoDepreciado = new BigDecimal("0.0");
			BigDecimal nvalorUnitarioDepreciado = new BigDecimal("0.0");

			
			nmontoDepreciado = nvalorUnitario.multiply(nporcentajeDepreciado).divide(new BigDecimal("100"));
			nvalorUnitarioDepreciado = nvalorUnitario.subtract(nmontoDepreciado);

			
			getView().setValue("montoDepreciado",nmontoDepreciado); 
			getView().setValue("valorUnitarioDepreciado",nvalorUnitarioDepreciado);
		}
    }
}