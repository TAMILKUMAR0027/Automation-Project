package com.utils;
import java.io.FileInputStream;
	import java.util.Properties;

	public class TestdataReader {

	    private static Properties prop;

	    public static Properties getProperties() {

	        if (prop == null) {
	            try {
	                FileInputStream fis = new FileInputStream("src/test/resources/ForgetPassworddata.properties");
	                prop = new Properties();
	                prop.load(fis);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return prop;
	    }
	}

