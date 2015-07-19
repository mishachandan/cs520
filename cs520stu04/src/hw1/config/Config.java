package hw1.config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static String applicationURL;
	public static String viewFilePath		;
	
	public static void readPath(String propertiesFullPath) throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(new FileInputStream(propertiesFullPath));
		applicationURL = properties.getProperty("applicationURL");
		viewFilePath = properties.getProperty("viewPath");
	}

	public static String getApplicationURL() {
		return applicationURL;
	}

	public static String getViewFilePath() {
		return viewFilePath;
	}	

}
