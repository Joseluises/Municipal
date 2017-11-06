package com.sistemas.municipal.models;

import javax.persistence.*;

import org.openxava.annotations.*;

@View(members=
		"tipovia,via")		
@Embeddable
public class DireccionArancel {
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private TipoVia tipovia;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Arancel via;
	
	public TipoVia getTipovia() {
		return tipovia;
	}

	public void setTipovia(TipoVia tipovia) {
		this.tipovia = tipovia;
	}

	public Arancel getVia() {
		return via;
	}

	public void setVia(Arancel via) {
		this.via = via;
	}
}
