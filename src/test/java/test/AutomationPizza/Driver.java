package test.AutomationPizza;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	public static WebDriver driver;
	public Functions function;
	public Locators locator;
	public static ExtentTest logger;
	public static Repositories repository;
	static String path = System.getProperty("user.dir");
	
	public Driver(WebDriver driver) {
		this.driver = driver;
		this.logger = logger;
	}



	@BeforeTest
	static  WebDriver setup() throws ClassNotFoundException, IOException
	{
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		 FileUtils.copyFile(screenshot, new File("//Users//anandyadav//eclipse-workspace//MavenProject//report//homePageScreenshot.png"));
       return driver;
	}
	
	
	
	//This method is to capture the screenshot and return the path of the screenshot.
		public static String getScreenShot(WebDriver driver) throws IOException, InterruptedException {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			Thread.sleep(1000);
			File source = ts.getScreenshotAs(OutputType.FILE);
	// after execution, you could see a folder "FailedTestsScreenshots" under src folder
			String destination = System.getProperty("user.dir") + "/Report/Screenshots/" + dateName + ".png";
			File finalDestination = new File(destination);
			Files.copy(source, finalDestination);
			return destination;
		}
		/*
		 * @Author :Anand
		 * This method is use to check and highlight element on page
		 * @csslocator
		 */
		void HighElement(ExtentTest logger,WebElement charbotimage) throws IOException, InterruptedException {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			Thread.sleep(1000);
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File(System.getProperty("user.dir") + "/Report/Screenshots/" + dateName + ".png");
		
			FileUtils.copyFile(source, destination);
	        //Create object of a JavascriptExecutor interface
	JavascriptExecutor js = (JavascriptExecutor) driver;
	        //use executeScript() method and pass the arguments
	        //Here i pass values based on css style. Yellow background color with solid red color border.
	js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", charbotimage);
	System.out.println(destination.getAbsolutePath());
	logger.addScreenCaptureFromPath(destination.getAbsolutePath());
	js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 0px solid red;');", charbotimage);
			
		}
}
