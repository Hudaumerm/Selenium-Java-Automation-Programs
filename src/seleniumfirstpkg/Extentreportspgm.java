package seleniumfirstpkg;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public  class Extentreportspgm {

	org.openqa.selenium.WebDriver driver;
	String baseUrl="https://www.google.com";
	ExtentHtmlReporter reporter;
	ExtentTest test;
	ExtentReports extent;
	
	@BeforeTest
	public void setup(){
		reporter=new ExtentHtmlReporter("./Report/test1.html");
		reporter.config().setDocumentTitle("Automation testing");
		reporter.config().setReportName("Function Test");
		reporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("hostname", "chrome");
		extent.setSystemInfo("os", "windows");
		extent.setSystemInfo("tester name", "huda");
		extent.setSystemInfo("browser", "chrome");

		driver=new ChromeDriver();
		
		
		
	}
	
	@BeforeMethod
	public void bmthd() {
		driver.get(baseUrl);
	}
	
	@Test
	public void titleVerification() {
		test=extent.createTest("titleverification");
		String exp=driver.getTitle();
		String act="Google";
//		assert.assertTrue(true, act);
		
	}
	
	@AfterTest
	public void close() {
		extent.flush();
	}
	
	@AfterMethod
	public void browserclose(ITestResult result) throws IOException {
		
		if (result.getStatus()== ITestResult.FAILURE) {
			test.log(Status.FAIL, "test case failed is="+result.getName());
			test.log(Status.FAIL, "test case failed is="+result.getThrowable());
			
			String screenshotpath=Extentreportspgm.ScreenShotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
			

		}
		
		else if (result.getStatus()== ITestResult.SKIP) {
			test.log(Status.SKIP, "test case failed is="+result.getName());

		}
		if (result.getStatus()== ITestResult.SUCCESS) {
			test.log(Status.PASS, "test case failed is="+result.getName());

		}
	}
	
	public static String ScreenShotMethod(WebDriver driver,String sname) throws IOException {
		File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest="./Screenshot"+sname+".jpeg";
		FileHandler.copy(ss, new File(dest));
		return dest;
	}
	
}
