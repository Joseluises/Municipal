package com.sistemas.municipal.calculators;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.openxava.calculators.IJDBCCalculator;
import org.openxava.util.IConnectionProvider;

	public class PorcentajeDepreciadoCalculator implements IJDBCCalculator{

		private int clasificacionnivel_id;
		private int materialnivel_id;
		
		private IConnectionProvider provider;
		
		public void setConnectionProvider(IConnectionProvider provider) {
			this.provider = provider;
		}
		
		public Object calculate() throws Exception {
//			Log log = LogFactory.getLog(PorcentajeDepreciadoCalculator.class);
//			log.info("FJA:Entering Calculate");
			Connection con = provider.getConnection();
			try {
				PreparedStatement ps = con.prepareStatement("select regular from DepreciacionNivel where clasificacionnivel_id = 1 and materialnivel_id = 1");
				ps.setInt(1, getClasificacionnivel_id());
				ps.setInt(2, getMaterialnivel_id());
				ResultSet rs = ps.executeQuery();
				rs.next();
				BigDecimal result = new BigDecimal(rs.getInt(1));
				ps.close();
				return result;
			}finally {
				con.close();
			}
		}

		public int getClasificacionnivel_id() {
			return clasificacionnivel_id;
		}

		public void setClasificacionnivel_id(int clasificacionnivel_id) {
			this.clasificacionnivel_id = clasificacionnivel_id;
		}

		public int getMaterialnivel_id() {
			return materialnivel_id;
		}

		public void setMaterialnivel_id(int materialnivel_id) {
			this.materialnivel_id = materialnivel_id;
		}

		public IConnectionProvider getProvider() {
			return provider;
		}

		public void setProvider(IConnectionProvider provider) {
			this.provider = provider;
		}
	}	
