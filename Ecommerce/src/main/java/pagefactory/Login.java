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

	SeleniumUtil selUtil = new SeleniumUtil();
	
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
	
		public Login(){
		PageFactory.initElements(driver, this);
	}
	
	public void loginApp(String userName, String password){
		try{
			
		selUtil.click(sign_in_button,"signin button");
		selUtil.sendKeys(email_text_box, "Email address", userName);
		selUtil.sendKeys(password_text_box, "Password", password);
		selUtil.takeScreenshotandAttachInReport();
		selUtil.click(submit_button, "Submit Button");
		test.log(Status.PASS, "Login Successful");
		
		}catch(Exception e){
			log.error(e);
			test.log(Status.FAIL, "Login to application failed");
			selUtil.takeScreenshotandAttachInReport();
		}
	}
	
	public void logoutApp(){
		try{
			
		selUtil.click(logout_button, "logout button");
		
		
		}catch(Exception e){
			log.error(e);
			test.log(Status.FAIL, "Logout from application failed");
			selUtil.takeScreenshotandAttachInReport();
		}
	}
}
