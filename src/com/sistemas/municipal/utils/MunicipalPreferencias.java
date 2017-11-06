package com.sistemas.municipal.utils;

import java.io.*;
import java.math.*;
import java.util.*;

import org.apache.commons.logging.*;
import org.openxava.util.*;

public class MunicipalPreferencias {
	private final static String FILE_PROPERTIES="municipal.properties";
	private static Log log = LogFactory.getLog(MunicipalPreferencias.class);
	private static Properties properties;
	
	private static Properties getProperties(){
		if(properties == null){
			PropertiesReader reader = new PropertiesReader(MunicipalPreferencias.class, FILE_PROPERTIES);
			try{
				properties = reader.get();
			}
			catch (IOException ex){
				log.error(XavaResources.getString("properties_file_error", FILE_PROPERTIES), ex);
				properties = new Properties();
			}
		}
		return properties;
	}
	
	public static BigDecimal getDefaultMontoUit(){
		return new BigDecimal(
				getProperties().getProperty("defaultMontoUit"));
	}
}