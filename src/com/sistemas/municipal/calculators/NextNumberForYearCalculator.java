package com.sistemas.municipal.calculators;

import javax.persistence.Query;

import org.openxava.jpa.XPersistence;

public class NextNumberForYearCalculator {
	
	public Object calculate() throws Exception{
		Integer year = null;
		Query query = XPersistence.getManager().createQuery("select max(i.number) from DocumentoAdministrativo i "+
				"where i.year =:year");
		query.setParameter("year", year);
		Integer lastNumber = (Integer) query.getSingleResult();
		return lastNumber == null?1:lastNumber +1;
	}
}
