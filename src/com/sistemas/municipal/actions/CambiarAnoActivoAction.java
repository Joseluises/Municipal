package com.sistemas.municipal.actions;

import javax.inject.Inject;

import org.openxava.actions.ViewBaseAction;

public class CambiarAnoActivoAction extends ViewBaseAction{
	@Inject 
	private int anoActivo;
	
	@Override
	public void execute() throws Exception{
		assert anoActivo == 2016;
		anoActivo = 2017;
	}
}
