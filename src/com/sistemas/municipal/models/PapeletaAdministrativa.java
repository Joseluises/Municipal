package com.sistemas.municipal.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

@Views({
	@View(extendsView="super.DEFAULT",
			members="multa administrativa{multaAdministrativa}"
	),
			@View(name="noContribuyenteNoMulta",
			members="aini,numero,fecha;"+	
			"detalles"
	)
})
@Entity
public class PapeletaAdministrativa extends DocumentoAdministrativo{
	@ManyToOne(fetch=FetchType.LAZY)
	@ReferenceView("noContribuyenteNoPapeleta")
	private MultaAdministrativa multaAdministrativa;

	public MultaAdministrativa getMultaAdministrativa() {
		return multaAdministrativa;
	}

	public void setMultaAdministrativa(MultaAdministrativa multaAdministrativa) {
		this.multaAdministrativa = multaAdministrativa;
	}
}
