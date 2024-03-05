package commonUtils;

import org.testng.ITestContext;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListernerImplimentation implements ITestListener {

	ExtentReports rep;
	JavaUtils javaUtils;
	WebDriverUtils wUtils;
	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("testscript execution is started ");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"testscript execution is started",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("test script execution is passed");
		String methodName = result.getMethod().getMethodName();
		/*with help of this class we provide info in emailable html report
		Reporter.log(methodName+"testscript execution is passed",true);*/
		
		
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// System.out.println("test script execution is failed");
		// it will print exception with reporter class method
		String exceptionName = result.getThrowable().toString();
		String methodName = result.getMethod().getMethodName();
		//Reporter.log(methodName + "  testscript execution is failed   " + "\n " + "exeception occured" + exceptionName,
				//true);
		wUtils = new WebDriverUtils();
		wUtils.screenShot(driver, "contact");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("test script execution is skipped");
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName+"testscript execution is skipped",true);
	}

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("test script execution  started");
		
		//Reporter.log("testscript execution is started",true);
		
		/*USE ExtentSparkReporter CLASS JUST TO CONFIGURE EXTENT REPORT NOT GENERATING REPORT
		1.CREATE OBJECT OF ExtentSparkReporter CLASS AND PASS THE PATH AS AN ARGUMENT IN 
		ExtentSparkReporter CONSTRUCTOR
		
		*/
		javaUtils= new JavaUtils();
		ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("./ExtentSparkReport/report"+javaUtils.getRandomNumber()+".html");
		extentSparkReporter.config().setDocumentTitle("vTigerCRM");
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setReportName("kalyani");
		
		/*create objetc of ExtentReports class to generate extent report
		 * call  attachReporter(extentSparkReporter) method 
		 * 
		 * */
		
		rep= new ExtentReports();
		rep.attachReporter(extentSparkReporter);
		rep.setSystemInfo("os", "mac");
		rep.setSystemInfo("browser", "safari");
		rep.setSystemInfo("safariVersion", "121");
		rep.setSystemInfo("Author", "Kalyani-daund");
	
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("to finish execution");
		
		//Reporter.log("testscript execution is finished",true);
		rep.flush(); //mandatory to call this method to generate report
	}
	
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	

}
