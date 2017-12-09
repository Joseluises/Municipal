package com.sistemas.municipal.tests;

import org.openxava.tests.ModuleTestBase;

public class ContribuyenteTest extends ModuleTestBase{
	public ContribuyenteTest(String nombrePrueba) {
		super(nombrePrueba, "Municipal", "Contribuyente");
	}
	
	public void testCrearLeerActualizarBorrar() throws Exception{
		//login("admin","admin");
		//Crear
		execute("CRUD.new");
		setValue("apellidoNombre", "");
		setValue("tipoDocumento.descripcion","15448107");
	}
}