package com.sistemas.municipal.calculators;
import org.openxava.calculators.*;
import com.sistemas.municipal.models.*;

import static org.openxava.jpa.XPersistence.*;

public class PorcentajeUitPapeletaAdministrativa implements ICalculator{
	private int codigoMulta;
	
	public Object calculate() throws Exception{
		SancionPapeleta sancionpapeleta = getManager().find(SancionPapeleta.class, codigoMulta);
		return sancionpapeleta.getPorcentajeuit();
	}

	public int getCodigoMulta() {
		return codigoMulta;
	}

	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}
}