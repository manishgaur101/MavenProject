package pagefactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import commonutil.SeleniumUtil;
import testBase.TestBase;

public class Login extends TestBase{

	ExtentTest test = null;
	SeleniumUtil selUtil = new SeleniumUtil();
	WebDriver driver;
	Logger log = Logger.getLogger(Login.class);
	@FindBy(xpath = "//a[@title='Log in to your customer account']")
	WebElement sign_in_button;
	
	@FindBy(id = "email")
	WebElement email_text_box;
	
	@FindBy(id = "passwd")
	WebElement password_text_box;
	
	@FindBy(name = "SubmitLogin")
	WebElement submit_button;
	
	@FindBy(xpath = "//a[@title='Log me out']")
	WebElement logout_button;
	
	public Login(WebDriver driver,ExtentTest test){
		
		PageFactory.initElements(driver, this);
		this.test = test;
		this.driver = driver;
	}
	
	public void loginApp(String userName, String password){
		try{
			
		selUtil.click(driver,sign_in_button,selUtil, "signin button", test);
		selUtil.sendKeys(selUtil,driver,email_text_box, "Email address", userName, test);
		selUtil.sendKeys(selUtil,driver,password_text_box, "Password", password, test);
		selUtil.takeScreenshotandAttachInReport(driver, test);
		selUtil.click(driver,submit_button,selUtil, "Submit Button", test);
		test.log(Status.PASS, "Login Successful");
		
		}catch(Exception e){
			log.error(e);
			test.log(Status.FAIL, "Login to application failed");
			selUtil.takeScreenshotandAttachInReport(driver, test);
		}
	}
	
	public void logoutApp(){
		try{
			
		selUtil.click(driver,logout_button,selUtil, "logout button", test);
		
		
		}catch(Exception e){
			log.error(e);
			test.log(Status.FAIL, "Logout from application failed");
			selUtil.takeScreenshotandAttachInReport(driver, test);
		}
	}
}
