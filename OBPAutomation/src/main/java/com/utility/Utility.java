package com.utility;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Utility {
	
	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;
	public static ExtentTest test;
	public static ExtentTest parentTest;
	String concatenate =".";
	
	
	public void setUp() {
		reporter = new ExtentHtmlReporter(AppConfig.Path_ExtentReport +"\\Extent_"+ getcurrentdateandtime() + ".html");
		//reporter = new ExtentHtmlReporter("./reports/Extent_"+ getcurrentdateandtime() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		reporter.config().setDocumentTitle("seleniumautomation");
		reporter.config().setReportName("Testautomation");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.STANDARD);
		
		extent.setSystemInfo("Environment", "AutomationTesting");
		extent.setSystemInfo("HostAddress", "Google");
		extent.setSystemInfo("Browser", "GoogleChrome");
		extent.setSystemInfo("Application_URL", "automation");
	}
	
	
	public static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}
	
	
	public void passFailScreenshot(WebDriver driver,String name) throws IOException {
		String screenshotName=AppScreenshots.getScreenshot(driver,name);
		//String screenshotName=concatenate+AppScreenshots.getScreenshot(name);//Removing Concatenate it works in runnable jar file
 	//	String scenario_name=concatenate + ScreenShot.CaptureScreenShot(driver, scenario_name);
		screenCapture("Seleniumautomation",screenshotName);
	}

	
	public static Object screenCapture(String logdetails, String imagepath) throws IOException {
		test.log(Status.INFO, logdetails,MediaEntityBuilder.createScreenCaptureFromPath(imagepath).build());
		return test;
	}
			

}
