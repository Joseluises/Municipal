package com.sistemas.municipal.actions;
 
import java.util.*;

import javax.inject.*;

import org.openxava.actions.*;
import org.openxava.tab.*;
 
public class ListarCuentaCorrienteDeContribuyenteAction extends BaseAction
    implements IChangeModuleAction{                             
 
    private int row;   
    @Inject
    private Tab tab;
 
    @Override
	public void execute() throws Exception {
        Map claveContribuyente = (Map) tab.getTableModel().getObjectAt(row);
        int codigoContribuyente = ((Integer) claveContribuyente.get("id")).intValue();
        Tab tabCuentaCorriente = (Tab)
        		getContext().get("Municipal", getNextModule(), "xava_tab");            
        tabCuentaCorriente.setBaseCondition("${contribuyente.codigo} = "+codigoContribuyente);
    }
 
    public int getRow() {                                                    
        return row;
    }
    public void setRow(int row) {                                            
        this.row = row;
    }
 
    public Tab getTab() {
        return tab;
    }
    public void setTab(Tab tab) {
        this.tab = tab;
    }
 
    @Override
	public String getNextModule() {                
        return "CuentaCorriente";
    }
 
    @Override
	public boolean hasReinitNextModule() {                               
        return true;
    }
 }