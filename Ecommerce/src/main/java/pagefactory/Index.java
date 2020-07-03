package pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import commonutil.SeleniumUtil;

public class Index {

	WebDriver driver;
	SeleniumUtil selUtil = new SeleniumUtil();
	ExtentTest test = null;

	@FindBy(linkText = "Women")
	WebElement Women_tab;

	@FindBy(xpath = "//h5[@itemprop='name']/a[@class='product-name']")
	List<WebElement> Woman_items_lists;

	@FindBy(xpath = "//a[@title='Add to cart']")
	WebElement addToCart_button;

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	WebElement Proceed_to_checkout;

	public Index(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.test = test;

	}

	public void addItemIntoCart() {
		try {
			Women_tab.click();
			selUtil.moveToElement(driver, Woman_items_lists, selUtil, 0, test);
			selUtil.click(driver,addToCart_button,selUtil,"add to cart button", test);
			selUtil.click(driver,Proceed_to_checkout, selUtil,"Proceed to checkout button", test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Item did not add to cart");
			selUtil.takeScreenshotandAttachInReport(driver, test);

		}
	}

}
