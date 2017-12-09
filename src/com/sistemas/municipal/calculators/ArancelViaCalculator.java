package com.sistemas.municipal.calculators;

import java.math.BigDecimal;
import java.sql.*;
 
import org.openxava.calculators.*;
import org.openxava.util.*;
 
public class ArancelViaCalculator implements IJDBCCalculator { // 1
 
 private IConnectionProvider provider;
 private int aini;
 private int parentvia_id;
 
 
 public void setConnectionProvider(IConnectionProvider provider) {
	 this.provider = provider;
 }
 
public Object calculate() throws Exception {
 Connection con = provider.getConnection();
 try {
	 PreparedStatement ps = con.prepareStatement("SELECT montoarancel FROM DetalleVia WHERE aini = ? AND parentvia_id = ? ");
	 ps.setInt(1, getAini());
	 ps.setInt(2, getParentvia_id());
	 ResultSet rs = ps.executeQuery();
	 rs.next();
	 BigDecimal result = new BigDecimal(rs.getInt(1));
	 ps.close();
	 return result;
 } finally {
	 con.close();
 }
 }

	public IConnectionProvider getProvider() {
		return provider;
	}
	
	public void setProvider(IConnectionProvider provider) {
		this.provider = provider;
	}
	
	public int getAini() {
		return aini;
	}
	
	public void setAini(int aini) {
		this.aini = aini;
	}
	
	public int getParentvia_id() {
		return parentvia_id;
	}
	
	public void setParentvia_id(int parentvia_id) {
		this.parentvia_id = parentvia_id;
	}
}
