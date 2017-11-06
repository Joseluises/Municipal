package com.sistemas.municipal.tests;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;

import com.sistemas.municipal.models.Deletable;

@Entity
public class Section extends Deletable{
	@Column(length=4)
	@ReadOnly
	@DefaultValueCalculator(YearInvoiceDiscountCalculator.class)
	private int year;
	
	@Column(length=1)
	@Required
	private String section;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
}
