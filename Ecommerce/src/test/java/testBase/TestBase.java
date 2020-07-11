package testBase;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentTest;

import browserfactory.BrowserFactory;
import constants.FilePath;
import reportutil.ExtenReport;
import testcases.ui.MongoConnect;

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
	
	
	/*@DataProvider(name="myData")
	public Object[][] dataProvider(Method method){
		ExcelReader excel = new ExcelReader(FilePath.Excel_File_Path, method.getName());
		return excel.getMapDataFromSpreadSheet();
		}*/
	
	@DataProvider(name="myData")
	public Object[][] dataProvider(Method method){
		MongoConnect mongo = new MongoConnect("ecommerce", "logindetails");
		//return mongo.getMapDataFromMongo();
		return mongo.getMapDataFromMongoDB();
		}
	
	@AfterTest
	public void tearDown(){
		ExtenReport.writeReport();
		driver.close();
	}
}
