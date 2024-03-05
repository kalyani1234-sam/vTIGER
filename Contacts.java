package vTigerCRM;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonUtils.ExcelUtils;
import commonUtils.JavaUtils;
import commonUtils.ListernerImplimentation;
import commonUtils.PropertyFileUtils;
import commonUtils.WebDriverUtils;

@Listeners(ListernerImplimentation.class)
public class Contacts {

	JavaUtils jUtil = new JavaUtils();
	PropertyFileUtils pUtil = new PropertyFileUtils();
	WebDriverUtils webDriverUtils = new WebDriverUtils();
	ExcelUtils excelUtils = new ExcelUtils();

	
	
	
	@Test
	public void contactsTest() throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();

		webDriverUtils.maximize(driver);

		webDriverUtils.impliciteWaits(driver);

		// fetching data from property file to launch url
		driver.get(pUtil.getDataFrmPropertyFile1("url"));
		
		String actualUrl = driver.getCurrentUrl();
		System.out.println(actualUrl);
		String expectedUrl = "http://localhost:8888/index.php?action=Login&module=Users";
		
		Assert.assertEquals(actualUrl, expectedUrl);;

		// fetching data from property file to launch username
		String userName = pUtil.getDataFrmPropertyFile1("username");

		// fetching data from property file to launch password
		String pass = pUtil.getDataFrmPropertyFile1("password");

		// find element called username
		driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys(userName);

		// find element called password
		driver.findElement(By.cssSelector("input[name='user_password']")).sendKeys(pass);

		// to click on submit button
		driver.findElement(By.id("submitButton")).submit();

		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		String fName = excelUtils.getDataFRomExcelVTIGER("Contacts", 0, 1);
		String lName = excelUtils.getDataFRomExcelVTIGER("Contacts", 1, 1);
		// read data for dropdown called team selling
		String assingTo = excelUtils.getDataFRomExcelVTIGER("Contacts", 2, 1);
		// String msTitle = excelUtils.getDataFRomExcelVTIGER("Contacts", 4, 1);
		String orgName = excelUtils.getDataFRomExcelVTIGER("Contacts", 3, 1);
		// WebElement titledr =
		// driver.findElement(By.cssSelector("select[name='salutationtype']"));

		// webDriverUtils.handleDropdownByValue(titledr, msTitle);

		driver.findElement(By.name("firstname")).sendKeys(fName);

		 driver.findElement(By.name("lastname")).sendKeys(lName + jUtil.getRandomNumber());

		WebElement notifyCheckBox = driver.findElement(By.name("notify_owner"));
		
		Assert.assertTrue(notifyCheckBox.isSelected());
		
		
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();// to click on radio button
		WebElement drdwn = driver.findElement(By.name("assigned_group_id"));// select drop down

		webDriverUtils.handleDropdown(drdwn, assingTo); // call webdriver method using select class

		// click on select(+) button in organisation textfield
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();

		String childURL = "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
		webDriverUtils.switchWindow(driver, childURL);

		// enter organisation name in search tf in new window
		driver.findElement(By.id("search_txt")).sendKeys(orgName);

		driver.findElement(By.name("search")).click();// click on search now button
		
		

		driver.findElement(By.linkText("Infosys415")).click();
		

		// need to transfer control from child window to parent window
		String parentUrl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		webDriverUtils.switchWindow(driver, parentUrl);
		// to click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		webDriverUtils.screenShot(driver, "savedcontName");

		Thread.sleep(2000);
		// to mouse hover img for sign out
		WebElement admin = driver.findElement(By.xpath("(//td[@class='small'])[2]"));

		webDriverUtils.mouseHover(driver, admin);
		// to click on sign out
		driver.findElement(By.linkText("Sign Out")).click();

	}

}
