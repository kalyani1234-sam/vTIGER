package commonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtils {

	
	 
		public void maximize(WebDriver driver)

		{
			driver.manage().window().maximize();
		}
		public void impliciteWaits(WebDriver driver)

		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		public void handleDropdown(WebElement ref, String targetedElemnt)
		{
			Select se = new Select(ref);
			se.selectByVisibleText(targetedElemnt);
		}
		public void handleDropdownByValue(WebElement ref, String targetedElemnt)
		{
			Select se = new Select(ref);
			se.selectByValue(targetedElemnt);
		}
		public void mouseHover(WebDriver driver,WebElement element)
		{
			Actions act = new Actions(driver);
			
			act.moveToElement(element);
			act.perform();
			
			
		}
		public void click(WebDriver driver,WebElement element)
		{
			Actions act = new Actions(driver);
			
			act.click(element);
			act.perform();
			
			
		}
		//use this method to transfer control from parent to child and child to parent
		public void switchWindow(WebDriver driver,String childUrl)
		{
			//transfer control from parent window to child window
			Set<String> ids = driver.getWindowHandles();
			//System.out.println(ids);
			for (String title : ids) {
				 String actualUrl = driver.switchTo().window(title).getCurrentUrl();
				
				if (actualUrl.contains(childUrl)) {
					break;
				}
			}
		}
		
		public File screenShot(WebDriver driver,String screenshotName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File tempFile = ts.getScreenshotAs(OutputType.FILE);
			
			File destFile = new File("./Screenshots/"+screenshotName+".png");
			
			FileUtils.copyFile(tempFile, destFile);
			return destFile;
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
