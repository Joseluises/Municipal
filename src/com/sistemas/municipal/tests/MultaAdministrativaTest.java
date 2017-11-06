package com.sistemas.municipal.tests;

public class MultaAdministrativaTest extends DocumentoComercialTest{
	
	public MultaAdministrativaTest(String testName){
		super(testName,"MultaAdministrativa");
	}
	
	public void testAddPapeletas() throws Exception{
		assertListNotEmpty();
		execute("Mode.detailAndFirst");
		execute("Sections.change", "activeSection=1");
		assertCollectionRowCount("papeletas", 0);
		execute("Collection.add","viewObject=xava_view_section1_papeletas");
		execute("AddToCollection.add", "row=0");
		assertCollectionRowCount("papeletas", 1);
		checkRowCollection("papeletas", 0);
		execute("Collection.removeSelected","viewObject=xava_view_section1_papeletas");
		assertCollectionRowCount("papeletas", 0); 
	}
}
