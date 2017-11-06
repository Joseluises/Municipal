package com.sistemas.municipal.actions;
 
import java.util.Map;

import javax.inject.Inject;

import org.openxava.actions.BaseAction;
import org.openxava.actions.IChangeModuleAction;
import org.openxava.actions.IModuleContextAction;
import org.openxava.controller.ModuleContext;
import org.openxava.tab.Tab;
 
public class ListarHojaResumenPredioUrbanoAction extends BaseAction
    implements IChangeModuleAction,                                             
               IModuleContextAction {                                           
 
    private int row;                                                            
    @Inject 
    private Tab tab;
    private ModuleContext context;
 
    @Override
	public void execute() throws Exception {
        Map claveCliente = (Map) tab.getTableModel().getObjectAt(row);          
        int codigoCliente = ((Integer) claveCliente.get("codigo")).intValue();
        Tab tabFacturas = (Tab)
            context.get("OpenXavaTest", getNextModule(), "xava_tab");           
        tabFacturas.setBaseCondition("${cliente.codigo} = "+codigoCliente);     
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
        return "FacturasDeCliente";
    }
 
    @Override
	public void setContext(ModuleContext context) {                            
        this.context = context;
    }
 
    @Override
	public boolean hasReinitNextModule() {                                     
        return true;
    }
 
}