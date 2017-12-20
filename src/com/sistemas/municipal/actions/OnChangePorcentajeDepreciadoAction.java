package com.sistemas.municipal.actions;

import java.math.*;
import java.util.List;
import org.openxava.actions.OnChangePropertyBaseAction;
import com.sistemas.municipal.models.DepreciacionNivel;

public class OnChangePorcentajeDepreciadoAction extends OnChangePropertyBaseAction{
	public void execute() throws Exception{
		BigDecimal nmontoDepreciado = new BigDecimal("0.0");
		BigDecimal nvalorUnitarioDepreciado = new BigDecimal("0.0");

		Object  clasif	= getView().getValue("clasificacionNivel.id");
		Object  mater	= getView().getValue("materialNivel.id");
		Integer antig	= (Integer) getView().getValue("antiguedad");
		Object  conser  = getView().getValue("conservacionNivel.id");
		if(clasif==null || mater==null || antig==0 || conser==null){
			getView().setValue("porcentajeDepreciado", 0.00);
			return;
		}else{
			BigDecimal pDep = DepreciacionNivel.buscarPorcDepreciacionNivel(clasif, mater,antig, conser);
			BigDecimal vUni = (BigDecimal) getView().getValue("valorUnitario");
			getView().setValue("porcentajeDepreciado", pDep);
			getView().setValue("montoDepreciado", vUni.multiply(pDep).divide(new BigDecimal("100")));
			getView().setValue("valorUnitarioDepreciado", vUni.subtract(vUni.multiply(pDep).divide(new BigDecimal("100"))));
		}
    }
}