package com.sistemas.municipal.tests;

public class PapeletaAdministrativaTest extends DocumentoComercialTest{
	
	public PapeletaAdministrativaTest(String testName){
		super(testName,"PapeletaAdministrativa");
	}
	
	public void testSetMulta() throws Exception{
		assertListNotEmpty();
		execute("Mode.detailAndFirst");
		execute("Sections.change", "activeSection=1");
		assertValue("multaAdministrativa.numero", "");
		assertValue("multaAdministrativa.aini", "");
		execute("Reference.search",	"keyProperty=invoice.year");
		String year = getValueInList(0, "aini");
		String number = getValueInList(0, "numero"); 
		execute("ReferenceSearch.choose", "row=0");
		assertValue("multaAdministrativa.aini", year); 
		assertValue("multaAdministrativa.numero", number);
	}
}
