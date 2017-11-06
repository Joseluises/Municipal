package com.sistemas.municipal.tests;

import org.openxava.actions.ViewBaseAction;

public class ChangeActiveYearAction extends ViewBaseAction {
	
	private Integer activeYear;
	
	@Override
	public void execute() throws Exception {
		Integer year = (Integer) getView().getValue("year");		
		setActiveYear(year);	
		addMessage("active_year_set - MUNICIPAL", year);
	}

	public Integer getActiveYear() {
		return activeYear;
	}

	public void setActiveYear(Integer activeYear) {
		this.activeYear = activeYear;
	}

}
