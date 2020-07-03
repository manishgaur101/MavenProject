package browserfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import constants.FilePath;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	private WebDriver driver;
	private static BrowserFactory DriverInstance = null;
	
	private BrowserFactory(){
		getBrowser();
	}
	
	public static BrowserFactory getInstanceOfBrowserFactory(){
		if(DriverInstance==null){
			DriverInstance = new BrowserFactory();
        }
        return DriverInstance;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	

/*	public static WebDriver getBrowser(){
		//WebDriver driver = null;
		driver = null;
		String browserName = null;
		String url = null;
		try {
		File file = new File(FilePath.ENV_PROPERTIES);
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		browserName = prop.getProperty("browserName");
		url = prop.getProperty("applicationURL");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(browserName.toUpperCase()){
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

}*/
	
	private void getBrowser(){
		//WebDriver driver = null;
		driver = null;
		String browserName = null;
		String url = null;
		try {
		File file = new File(FilePath.ENV_PROPERTIES);
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		browserName = prop.getProperty("browserName");
		url = prop.getProperty("applicationURL");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(browserName.toUpperCase()){
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//return driver;

}


}
