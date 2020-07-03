package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Summary {
	WebDriver driver;
	
	@FindBy(xpath="//p//a[@title='Proceed to checkout']")
	WebElement Proceed_to_Checkout_button;
	
	public Summary(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickOnProceedToCheckoutButton(){
		try{
		Proceed_to_Checkout_button.click();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
