package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class RegisterInvalidDataReader {
	
	 private static Properties prop;

	    public static Properties getRegisterDataProperties() {

	        if (prop == null) {
	            try {
	                FileInputStream fis = new FileInputStream("src/test/resources/InvalidRegisterData.properties");
	                prop = new Properties();
	                prop.load(fis);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return prop;
	    }
}
