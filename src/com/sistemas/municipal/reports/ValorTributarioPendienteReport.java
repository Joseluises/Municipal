package com.sistemas.municipal.reports;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import com.sistemas.municipal.models.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;

public class ValorTributarioPendienteReport extends JasperReportBaseAction {

	private ValorTributario valorTributario;

	@Override
	public Map getParameters() throws Exception {
		Messages errors = MapFacade.validate("ValorTributario", getView().getAllValues());
		if (errors.contains())
			throw new ValidationException(errors);
		Map parameters = new HashMap();
		parameters.put("ainivalor", getValorTributario().getAinivalor());
		return parameters;
	}

	@Override
	protected JRDataSource getDataSource() throws Exception {
		return new JRBeanCollectionDataSource(getValorTributario().getValortributariodetalle());
	}

	@Override
	protected String getJRXML() {
		return "ValorTributarioPendiente.jrxml";
	}

	private ValorTributario getValorTributario() throws Exception {
		if (valorTributario == null) {
			int id = getView().getValueInt("id");
			valorTributario = ValorTributario.BuscarPorCodigo(id);
		}
		return valorTributario;
	}
}