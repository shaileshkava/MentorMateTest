package com.mentormanate.MentorMateTest.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	private static String propertyFile = "src/test/resources/Config/config.properties";
	
	private static ReadProperties readProperty;
	
	Properties prop;
	
	public static ReadProperties getReadProperty() {
		return readProperty;
	}
	
	public ReadProperties() {
		if(prop == null) {
			prop = new Properties();
			try {
				prop.load(new FileInputStream(new File(propertyFile)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getURL() {
		return prop.getProperty("url");
	}
	
	
	
}
