package commonutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public String ReadPropertyFile(String propertyFileName, String propertyKey) {
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
