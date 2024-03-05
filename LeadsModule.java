package vTigerCRM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commonUtils.ExcelUtils;
import commonUtils.JavaUtils;
import commonUtils.PropertyFileUtils;
import commonUtils.WebDriverUtils;

public class LeadsModule {

	WebDriverUtils webDriverUtils= new WebDriverUtils();
	PropertyFileUtils pUtil = new PropertyFileUtils();
	ExcelUtils excelUtils= new ExcelUtils();
	JavaUtils javaUtils= new JavaUtils();
	@Test
	public  void leadModTest() throws IOException
	{
		WebDriver driver = new ChromeDriver();
		webDriverUtils.maximize(driver);
		
		webDriverUtils.impliciteWaits(driver);
		
		//fetching data from property file to launch url
		driver.get(pUtil.getDataFrmPropertyFile1("url"));
		
		//fetching data from property file to launch username
		String userName= pUtil.getDataFrmPropertyFile1("username");
		
		//fetching data from property file to launch password
		String pass=pUtil.getDataFrmPropertyFile1("password");
		
		//find element called username
		driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys(userName);
		
		//find element called password
		driver.findElement(By.cssSelector("input[name='user_password']")).sendKeys(pass);
		
		//to click on submit button
		driver.findElement(By.id("submitButton")).submit();
		//to click on leads major tab
		driver.findElement(By.linkText("Leads")).click();
		//to click on plus icon to create leads
		driver.findElement(By.cssSelector("img[alt='Create Lead...']")).click();
		
		//TO ACCESS ALL DATA FROM EXCEL AND CREATING VARIABLES FOR THEM
		String fName = excelUtils.getDataFRomExcelVTIGER("Leads", 0, 1);
		String lName = excelUtils.getDataFRomExcelVTIGER("Leads", 1, 1);
		String cName = excelUtils.getDataFRomExcelVTIGER("Leads", 2, 1);
		String AssignTo = excelUtils.getDataFRomExcelVTIGER("Leads", 3, 1);
		
		
		//TO ENTER FIRST NAME IN TEXTFIELD
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(fName);
		
		//TO ENTER LAST NAME IN TEXT FIELD
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(lName+javaUtils.getRandomNumber());
		
		//TO ENTER COMPANY NAME IN RESPECTIVE TEXTFIELD
		driver.findElement(By.cssSelector("input[name='company']")).sendKeys(cName);
		
		//TO CLICK ON GROUP RADIO BUTTON
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		//TO SELECT DROPDOWN with visible text support group
		
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		webDriverUtils.handleDropdown(dropdown, AssignTo);
		
		//TO CLICK ON SAVE BUTTON
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		//to take screenshot of lead data save page
		webDriverUtils.screenShot(driver, "leadSave1");
		
		WebElement soImg = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		webDriverUtils.mouseHover(driver, soImg);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		
		
		
		
	}
}
