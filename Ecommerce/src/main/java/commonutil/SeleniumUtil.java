package commonutil;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import constants.FilePath;
import reportutil.ExtenReport;

public class SeleniumUtil {

	
	public void click(WebDriver driver,WebElement element,SeleniumUtil selUtil,String elementName,ExtentTest test){
		try{
		element.click();
		test.log(Status.PASS, "Clicked on "+elementName);
		}catch(Exception e){
			test.log(Status.FAIL, "Error in clicking on "+elementName);
			takeScreenshotandAttachInReport(driver, test);
			
		}
	}
	
	public void sendKeys(SeleniumUtil selUtil,WebDriver driver,WebElement element,String elementName,String data,ExtentTest test){
		try{
		element.clear();
		element.sendKeys(data);
		test.log(Status.PASS, elementName+" entered successfully");
		}
		catch(Exception e){
			test.log(Status.FAIL, elementName+" is not found");
			selUtil.takeScreenshotandAttachInReport(driver, test);
			
		}
		
		
		
	}
	public void moveToElement(WebDriver driver,List<WebElement> element,SeleniumUtil selUtil,int index,ExtentTest test){
		String elementName = "";
		try{
			
			elementName = selUtil.getText(element, index);
		Actions actions = new Actions(driver);
		actions.moveToElement(element.get(index)).perform();
		test.log(Status.PASS, "Mouse Hover on Element " +elementName);
		}
		catch(Exception e){
			test.log(Status.FAIL, "Mouse Hover on Element "+elementName+ "Failed");
			selUtil.takeScreenshotandAttachInReport(driver, test);
			
		}
	}
	
	public String getText(List<WebElement> element, int index){
		return element.get(index).getText();
	}
	
	public void takeScreenshotandAttachInReport(WebDriver driver,ExtentTest test){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String path = FilePath.SCREEN_SHOT+File.separator+ExtenReport.DateFormatter()+"_image.png";
			FileUtils.copyFile(scrFile, new File(path), true);
			test.log(Status.INFO, "attached Image",
			MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			test.log(Status.FAIL,"Failed Screenshot Caputed");
		}
		}
	
	public void scrollTillElement(WebDriver driver,WebElement element){
		
	}
}
