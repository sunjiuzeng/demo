package com.demo.elasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropUtil {

	private final static Logger logger = LoggerFactory.getLogger(PropUtil.class);

	private static Properties prop = null;

	static {
		
		prop = new Properties();
		
		InputStream in = null;
		
		try {
			in = ResourceUtils.getURL("classpath:es.properties").openStream();
//			in = PropUtil.class.getResourceAsStream("../../es.properties");
			prop.load(in);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static String getValue(String key) {
		logger.debug("string key is : {}", key);
		
		String value = null;
		
		value = prop.containsKey(key) ? prop.getProperty(key).trim() : "";
		
		return value;
	}
	
	public static int getIntValue(String key) {
		logger.debug("string key is : {}", key);
		
		String value = null;
		
		value = prop.containsKey(key) ? prop.getProperty(key).trim() : "";
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher match=pattern.matcher(value);
		
		if(match.matches()){
             return Integer.parseInt(value);
        }else {
			return -1;
		}
		
	}
	
	public static boolean getBooleanValue(String key) {
		logger.debug("string key is : {}", key);
		
		String value = null;
		
		value = prop.containsKey(key) ? prop.getProperty(key).trim() : "";
		
		if(value.equalsIgnoreCase("true")){
             return true;
        }else{
			return false;
		}		
	}

}
