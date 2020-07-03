package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Payment {
	
	@FindBy(xpath="//td[@id='total_price_container']/span")
	WebElement total_amount;
	
	@FindBy(xpath="//a[@title='Pay by bank wire']")
	WebElement Pay_by_bank_wire;
	
	@FindBy(id="amount")
	WebElement bank_wire_Payment_amount;
	
	@FindBy(xpath="//button[@type='submit']/span[.='I confirm my order']")
	WebElement I_confirm_my_order_button;
	
	@FindBy(xpath="//div[@id='center_column']/h1")
	WebElement Order_amount;
	
	public Payment(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void Click_PayByBankWireandCompareAmount(){
		String total_amt = total_amount.getText();
		Pay_by_bank_wire.click();
		Assert.assertEquals(bank_wire_Payment_amount.getText(), total_amt);
		I_confirm_my_order_button.click();
	}
	
	public void printOrderDetails(){
		System.out.println(Order_amount.getText());
	}
}
