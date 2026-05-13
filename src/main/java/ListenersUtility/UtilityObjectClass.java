package ListenersUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityObjectClass {
	
//	for static variables we can't use in parallel execution y because it's not ceating obj fos static so wea are creating class for static and use getter and setter methods
	
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver>driver=new ThreadLocal<WebDriver>();

	
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void setTest(ExtentTest acttest) {
		test.set(acttest);
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver actdriver) {
		driver.set(actdriver);
	}

	
	
}
