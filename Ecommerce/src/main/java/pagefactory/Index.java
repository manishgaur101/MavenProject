package pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import commonutil.SeleniumUtil;
import testBase.TestBase;

public class Index extends TestBase {

	SeleniumUtil selUtil = new SeleniumUtil();

	@FindBy(linkText = "Women")
	WebElement Women_tab;

	@FindBy(xpath = "//h5[@itemprop='name']/a[@class='product-name']")
	List<WebElement> Woman_items_lists;

	@FindBy(xpath = "//a[@title='Add to cart']")
	WebElement addToCart_button;

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	WebElement Proceed_to_checkout;

	public Index() {
		
		PageFactory.initElements(driver, this);

	}

	public void addItemIntoCart() {
		try {
			Women_tab.click();
			//selUtil.moveToElement(driver, Woman_items_lists, selUtil, 0, test);
			selUtil.moveToElement(Woman_items_lists, 0);
			/*selUtil.click(driver,addToCart_button,selUtil,"add to cart button", test);
			selUtil.click(driver,Proceed_to_checkout, selUtil,"Proceed to checkout button", test);*/
			selUtil.click(addToCart_button,"add to cart button");
			selUtil.click(Proceed_to_checkout,"Proceed to checkout button");
		} catch (Exception e) {
			test.log(Status.FAIL, "Item did not add to cart");
			selUtil.takeScreenshotandAttachInReport();

		}
	}

}
