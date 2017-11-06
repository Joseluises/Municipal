package com.sistemas.municipal.models;

import javax.persistence.*;

@Entity
public class ValorTributarioRecepcion extends Deletable {
	@ManyToOne
	private ValorTributario parent;

}
