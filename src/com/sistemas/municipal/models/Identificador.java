package com.sistemas.municipal.models;

import javax.persistence.*;
import org.openxava.annotations.*;

@MappedSuperclass
public class Identificador {
	@Id @Hidden
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}