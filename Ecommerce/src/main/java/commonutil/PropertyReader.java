package commonutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	/**
	 * Method to get the value of the Property File
	 * 
	 * @param propertyFileName
	 * @param 
	 * 		propertyKey - Key in String form
	 * @return
	 * 		String - value of the key from property file
	 */
	public String getValue(String propertyFileName, String propertyKey) {
		String keyValue = "";
		try {
			File file = new File(propertyFileName);
			FileInputStream inputStream = new FileInputStream(file);
			Properties prop = new Properties();
				prop.load(inputStream);
			 keyValue = prop.getProperty(propertyKey);
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keyValue;
	}

}
