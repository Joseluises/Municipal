package com.sistemas.municipal.actions;

import java.util.Map;

import javax.ejb.ObjectNotFoundException;

import org.openxava.actions.SearchByViewKeyAction;

public class searchExcludingDeletedAction extends SearchByViewKeyAction{
	private boolean isDeletable(){
		return getView().getMetaModel().containsMetaProperty("deleted");
	}
	
	@Override
	protected Map getValuesFromView() throws Exception{
		if(!isDeletable()){
			return super.getValuesFromView();
		}
		Map values = super.getValuesFromView();
		values.put("deleted", false);
		return values;
	}
	
	@Override
	protected Map getMemberNames() throws Exception{
		if(!isDeletable()){
			return super.getMemberNames();
		}
		Map members = super.getMemberNames();
		members.put("deleted", null);
		return members;
		
	}
	
	@Override
	protected void setValuesToView(Map values) throws Exception{
		if(!isDeletable() && (Boolean) values.get("deleted")){
			throw new ObjectNotFoundException();
		}else{
			super.setValuesToView(values);
		}
	}
	
}
