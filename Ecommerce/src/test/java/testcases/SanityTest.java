package testcases;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pagefactory.Login;
import reportutil.ExtenReport;
import testBase.TestBase;

public class SanityTest extends TestBase{
	
	//WebDriver driver = BrowserFactory.getBrowser();
	//ExtentTest test = null;
	Logger log = Logger.getLogger(SanityTest.class);
	int dataSet = 0;
	
	/*@DataProvider(name="myData")
	public Object[][] dataProvider(Method method){
		ExcelReader excel = new ExcelReader(FilePath.Excel_File_Path, method.getName());
		Map<String, String> map = excel.getRowData(2);
		
		Object[][] data = new Object[1][1];
		data[0][0] = map;
		
		
		return data;
		
	}*/
	
	@Test(priority=1,dataProvider="myData")
	public void Sheet1(Map<String, String> testData){
		dataSet++;
		log.info("Data set="+dataSet);
		if(!testData.get("DataToRun").equalsIgnoreCase("Y")){
			throw new SkipException("DataToRun for row number "+dataSet+" is No or Blank, "
					+ "Hence skipping execution for this data set");
		}
		log.info("Executing Data set number = "+dataSet);	
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Login app = new Login(driver,test);
		app.loginApp(testData.get("UserName"), testData.get("Password"));
		app.logoutApp();
		log.info("Login test case executed successfully");
		
	}
	
	
	/*
	@Test(priority=2,dependsOnMethods={"sanityTest"})
	public void addItemIntoCart(){
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Index index = new Index(driver,test);
		index.addItemIntoCart();
		test.log(Status.PASS, "Item added to cart Successfully");
		log.info("Item added to cart Successfully");
	}
	
	@Test(priority=3)
	public void SummaryProceedToCheckout(){
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Summary summary = new Summary(driver);
		summary.ClickOnProceedToCheckoutButton();
	}
	
	@Test(priority=4)
	public void commentandProceed(){
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Address address = new Address(driver);
		address.enterCommentsAndProceedToCheckout();
		
	}
	
	@Test(priority=5)
	public void checkBoxAndProceed(){
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Shipping shipping = new Shipping(driver);
		shipping.clickCheckBoxAndProceedToCheckout();
		
	}
	
	@Test(priority=6)
	public void ClickOnPayByBankWire(){
		test = ExtenReport.getExtentTest(new Object(){}.getClass().getEnclosingMethod().getName());
		Payment payment = new Payment(driver);
		payment.Click_PayByBankWireandCompareAmount();
		payment.printOrderDetails();
		
	}
*/
	
}
