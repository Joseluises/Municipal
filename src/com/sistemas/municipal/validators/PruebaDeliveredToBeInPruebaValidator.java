package com.sistemas.municipal.validators;

import org.openxava.validators.*;
import org.openxava.util.*;

public class PruebaDeliveredToBeInPruebaValidator implements IValidator{
	private String descripcion;
	private boolean delivered;
	
	@Override
	public void validate(Messages errors) throws Exception{
		if(!delivered){
			errors.add("order_must_be_delivered", descripcion, delivered);
		}
	}

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
