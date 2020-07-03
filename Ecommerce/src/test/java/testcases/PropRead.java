package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import constants.FilePath;

public class PropRead {

	public static void main(String[] args) {
		try {
		File file = new File(FilePath.ENV_PROPERTIES);
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		
			prop.load(fis);
			String value = prop.getProperty("applicationURL");
			System.out.println(value);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
