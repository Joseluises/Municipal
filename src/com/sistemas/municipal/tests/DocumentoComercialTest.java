package com.sistemas.municipal.tests;

import org.openxava.tests.ModuleTestBase;

abstract public class DocumentoComercialTest extends ModuleTestBase{
	private String numero;
	
	public DocumentoComercialTest(String testName, String moduleName){
		super(testName,"Municipal",moduleName);
	}
	
	public void testCreate() throws Exception{}
	
	private void addDetalleAdministrativo() throws Exception{
		assertCollectionRowCount("detalles",0);
		execute("Collection.new", "viewObject=xava_view_section0_details");
		
		setValue("detalles.infraccionAdministrativa.codigo","01-101");
		assertValue("detalles.infraccionAdministrativa.descripcion","Por falta de permiso1.");
		
		setValue("detalles.cantidad","2");
		execute("Collection.save","viewObject=xava_view_section0_details");
		
		assertNoErrors();
		assertCollectionRowCount("detalles",1);
		
		setValue("detalles.infraccionAdministrativa.codigo","01-102");
		assertValue("detalles.infraccionAdministrativa.descripcion","Por falta de permiso2.");
		setValue("detalles.cantidad","1");
		execute("Collection.save","viewObject=xava_view_section0_details");
		
		assertNoErrors();
		assertCollectionRowCount("detalles",2);
	}
	
	private void remove() throws Exception{}
	
	private void verifyCreated() throws Exception{}
	
	private void save() throws Exception{}
	
	private void setOtherProperties() throws Exception{}
	
	private void chooseContribuyente() throws Exception{}
	
	private void verifyDefaultValues() throws Exception{}
	
//	private String getCurrentYear(){}
	
//	private String getCurrentDate(){}
}
