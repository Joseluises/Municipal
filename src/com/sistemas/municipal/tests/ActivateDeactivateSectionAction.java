package com.sistemas.municipal.tests;

import org.openxava.actions.*;


/**
 * @author Javier Paniza
 */
public class ActivateDeactivateSectionAction extends ViewBaseAction {

	private String section; 

	@Override
	public void execute() throws Exception {
		boolean currentState = getView().isSectionEditable(section);
		getView().setSectionEditable(section, !currentState); 	
	}

	public String getSection() {
		return section;
	}

	public void setSection(String newSection) {
		section = newSection;
	}

}
