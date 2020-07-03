package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Address {
	
	@FindBy(xpath="//div[@id='ordermsg']//textarea")
	WebElement comment_textarea;
	
	@FindBy(xpath ="//button/span[text()='Proceed to checkout']")
	WebElement address_proceed_to_checkout;
	
	public Address(WebDriver driver){
		PageFactory.initElements(driver, this);
		
	}

	public void enterCommentsAndProceedToCheckout(){
		comment_textarea.clear();
		comment_textarea.sendKeys("Handle with Care");
		address_proceed_to_checkout.click();
		
	}
}
