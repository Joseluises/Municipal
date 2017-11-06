package com.sistemas.municipal.models;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.openxava.annotations.CollectionView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

@Views({
	@View(extendsView="super.DEFAULT",
			members="papeletas{papeletas}"
	),
			@View(name="noContribuyenteNoPapeleta",
			members="aini,numero,fecha;"+	
			"detalles"
	)
})
@Entity
public class MultaAdministrativa extends DocumentoAdministrativo{
	@OneToMany(mappedBy="multaAdministrativa")
	@CollectionView("noContribuyenteNoMulta")
	private Collection<PapeletaAdministrativa> papeletas;

	public Collection<PapeletaAdministrativa> getPapeletas() {
		return papeletas;
	}

	public void setPapeletas(Collection<PapeletaAdministrativa> papeletas) {
		this.papeletas = papeletas;
	}
}
