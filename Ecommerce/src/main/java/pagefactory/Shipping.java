package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shipping {
	
	@FindBy(id="cgv")
	WebElement I_agree_check_BOX;
	
	@FindBy(xpath="//button[@type='submit']/span[contains(text(),'Proceed to checkout')]")
	WebElement Shipping_Proceed_to_Checkout_Button;
	
	public Shipping(WebDriver driver){
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickCheckBoxAndProceedToCheckout(){
		I_agree_check_BOX.click();
		Shipping_Proceed_to_Checkout_Button.click();
	}

}
