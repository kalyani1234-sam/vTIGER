package commonUtils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	WebDriverUtils ws = new WebDriverUtils();
	PropertyFileUtils ps = new PropertyFileUtils();
	ExcelUtils ex = new ExcelUtils();
	JavaUtils jUtil = new JavaUtils();
	
	
	public WebDriver driver = new ChromeDriver();//intialize driver here only to avoid nullPointerExeception

	@BeforeSuite
	public void bs() {
		System.out.println("connect to dataBase");

	}

	@BeforeClass
	public void bc() throws IOException {
		// THIS @BeforeClass ANNOTATION USED TO LAUNCH BROWSER /APPLICATION

		ws.maximize(driver);

		ws.impliciteWaits(driver);
		driver.get(ps.getDataFrmPropertyFile1("url"));
	}

	@BeforeMethod
	public void bm() throws IOException {
		// @BeforeMethod USED TO LOGIN TO THE APPLICATION

		String userName = ps.getDataFrmPropertyFile1("username");

		// fetching data from property file to launch password
		String pass = ps.getDataFrmPropertyFile1("password");
		// find element called username
		driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys(userName);

		// find element called password
		driver.findElement(By.cssSelector("input[name='user_password']")).sendKeys(pass);

		// to click on submit button
		driver.findElement(By.id("submitButton")).submit();

	}

	@AfterMethod
	public void am() throws InterruptedException {
		//@AfterMethod IS USED TO SIGN OUT FROM THE APPLICATION
		 Thread.sleep(2000);
		WebElement admin = driver.findElement(By.xpath("(//td[@class='small'])[2]"));

		ws.mouseHover(driver, admin);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

	@AfterClass
	public void ac() {
		//@AfterClass IS USED TO CLOSE THE BROWSER
		driver.close();
	}

	@AfterSuite
	public void as() {
		System.out.println("Disconnect from dataBase");
	}
}
