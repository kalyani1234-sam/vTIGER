package vTigerCRM;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonUtils.ExcelUtils;
import commonUtils.JavaUtils;
import commonUtils.ListernerImplimentation;
import commonUtils.PropertyFileUtils;
import commonUtils.WebDriverUtils;

@Listeners(ListernerImplimentation.class)
public class Organisation {

	JavaUtils jUtil= new JavaUtils();
	PropertyFileUtils ps = new PropertyFileUtils();
	WebDriverUtils ws = new WebDriverUtils();
	ExcelUtils ex = new ExcelUtils();
	@Test
	public void organisationTest() throws IOException, InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ws.maximize(driver);
		
		ws.impliciteWaits(driver);
		
		//fetching data from property file to launch url
		driver.get(ps.getDataFrmPropertyFile1("url"));
		
		//fetching data from property file to launch username
		String userName= ps.getDataFrmPropertyFile1("username");
		
		//fetching data from property file to launch password
		String pass=ps.getDataFrmPropertyFile1("password");
		
		//find element called username
		driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys(userName);
		
		//find element called password
		driver.findElement(By.cssSelector("input[name='user_password']")).sendKeys(pass);
		
		//to click on submit button
		driver.findElement(By.id("submitButton")).submit();
		
		//to click on organisations on homepage
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//to click on plus icon
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		
		
		
		//to fill data in organisation name and calling javautils method to generate random number
		driver.findElement(By.cssSelector("input[name='accountname']")).sendKeys(ex.getDataFRomExcelVTIGER("Organisations", 0, 1)+jUtil.getRandomNumber());
		
		//to select radio button called group
		 driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		 
		//to select dropdown and pass the value to selected one by using select class
		 WebElement sel = driver.findElement(By.cssSelector("select[name='assigned_group_id']"));
		 
		 
		 //reading data from vtigerData excel sheet that is support group 
		 String val = ex.getDataFRomExcelVTIGER("Organisations", 1, 1);
		 
		 ws.handleDropdown(sel, val);
		 //to click on  save button 
		 driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
	
		 
		 Thread.sleep(2000);
		 //to mouse hover on img for clicking on sign out
		WebElement admin = driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		
		ws.mouseHover(driver, admin);
		
	 driver.findElement(By.linkText("Sign Out")).click();
		
		
		
		
	}
}
