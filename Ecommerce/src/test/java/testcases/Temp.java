package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Temp {
	public static void signIn(WebDriver driver){
		driver.findElement(By.xpath(".//a[@title='Log in to your customer account']")).click();
		WebElement Email_address = driver.findElement(By.id("email_create"));
		Email_address.clear();
		Email_address.sendKeys("mgaur000@gmail.com");
		driver.findElement(By.id("SubmitCreate")).click();
		WebElement lastname = driver.findElement(By.id("customer_lastname"));
		lastname.clear();
		lastname.sendKeys("GAUR");
		WebElement password = driver.findElement(By.id("passwd"));
		password.clear();
		password.sendKeys("abc123");
		driver.findElement(By.id("firstname")).sendKeys("MANISH");
		driver.findElement(By.id("address1")).sendKeys("Test Line");
		driver.findElement(By.id("city")).sendKeys("DELHI");
		
	}
	@Test
	public void sanityTest(){
		String URL ="http://automationpractice.com/";
		System.setProperty("webdriver.chrome.driver","C://webdriver//chromedriver.exe");
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Report//ExtentReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		ExtentTest test = extent.createTest("Test Case 001 - Open Browser");

		WebDriver driver = new ChromeDriver();
		test.log(Status.PASS,"Browser opened Successfully");
		extent.flush();
		
		ExtentTest test2 = extent.createTest("Test Case 002 - Open Website");
		driver.get(URL);
		test2.log(Status.PASS,"Website opened successfully");
		extent.flush();
		
		
		driver.manage().window().maximize();
		//Login login = new Login(driver);
		//login.loginApp("mgaur000@gmail.com", "ABC123");
		
		
		
		ExtentTest test5 = extent.createTest("Test Case 005 - Add product in Cart");
		driver.findElement(By.linkText("Women")).click();
		
		List<WebElement> Products_Name =
			driver.findElements(By.xpath(".//h5[@itemprop='name']/a[@class='product-name']"));
		Actions actions = new Actions(driver);
		String Product_name = Products_Name.get(0).getText();
		actions.moveToElement(Products_Name.get(0)).perform();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@title='Add to cart']")).click();
		test5.log(Status.PASS,"Product added in Cart");
		extent.flush();
		
		driver.findElement(By.xpath(".//a[@title='Proceed to checkout']")).click();
		String cart_item_desc = driver.findElement(By.xpath(".//td[@class='cart_description']//a")).getText();
		System.out.println(cart_item_desc);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(cart_item_desc,Product_name , "Cart item is different");
		String total_amount = driver.findElement(By.id("total_price_container")).getText();
		driver.findElement(By.xpath(".//p//a[@title='Proceed to checkout']")).click();
		driver.findElement(By.xpath(".//div[@id='ordermsg']//textarea")).sendKeys("Contains Gift");
		driver.findElement(By.xpath(".//button/span[text()='Proceed to checkout']")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath(".//button[@type='submit']/span[contains(text(),'Proceed to checkout')]")).click();
		String final_amount = driver.findElement(By.xpath(".//td[@id='total_price_container']/span")).getText();
		//softAssert.assertEquals(final_amount,total_amount , "Final amount is not equal");
		driver.findElement(By.xpath(".//a[@title='Pay by bank wire']")).click();
		String bank_wire_amount = driver.findElement(By.id("amount")).getText();
		//Assert.assertEquals(bank_wire_amount,total_amount );
		driver.findElement(By.xpath(".//button[@type='submit']/span[.='I confirm my order']")).click();
		String final_text = driver.findElement(By.xpath(".//div[@id='center_column']/h1")).getText();
		driver.quit();
		System.out.println("Well Done");
		//softAssert.assertEquals(final_text,"ORDER CONFIRMATION" , "Order is not placed successfully");
		//softAssert.assertAll();
		
	}
}
