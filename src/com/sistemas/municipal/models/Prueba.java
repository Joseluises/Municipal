package com.sistemas.municipal.models;

import javax.persistence.*;
import org.openxava.annotations.*;

import com.sistemas.municipal.validators.*;

@EntityValidator(
		value=PruebaDeliveredToBeInPruebaValidator.class,
		properties= {
				@PropertyValue(name="descripcion"),
				@PropertyValue(name="delivered")
		}
)
@Entity
public class Prueba extends Deletable{
	@Column(length=45)
	private String descripcion;
	
	private boolean delivered;
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
}
