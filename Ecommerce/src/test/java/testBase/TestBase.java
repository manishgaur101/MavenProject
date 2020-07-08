package testBase;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentTest;

import browserfactory.BrowserFactory;
import commonutil.ExcelReader;
import constants.FilePath;
import reportutil.ExtenReport;

public class TestBase {
	
	Logger log = Logger.getLogger(TestBase.class);
	public WebDriver driver = BrowserFactory.getInstanceOfBrowserFactory().getDriver();
	//public WebDriver driver = BrowserFactory.getBrowser();
	public static ExtentTest test;
	
	
	static {
		//DOMConfigurator.configure("D:\\Sujoy\\myworkspace\\GitProject\\Ecommerce\\src\\test\\resources\\Log4j.xml");
		DOMConfigurator.configure(FilePath.LOG4J_XML);
		System.setProperty("org.freemarker.loggerLibrary", "none");
		}
	
	
	@DataProvider(name="myData")
	public Object[][] dataProvider(Method method){
		ExcelReader excel = new ExcelReader(FilePath.Excel_File_Path, method.getName());
		return excel.getMapDataFromSpreadSheet();
		}
	
	@AfterTest
	public void tearDown(){
		ExtenReport.writeReport();
		driver.close();
	}
}
