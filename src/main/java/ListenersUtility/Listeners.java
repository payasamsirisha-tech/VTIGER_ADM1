package ListenersUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.BaseClass;

public class Listeners implements ISuiteListener,ITestListener {
	public ExtentReports report;
	public static ExtentTest test;

	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite executionstart with adv-report configuration",true);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		//Configure the Report
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvancedReports/Vtiger"+time+".html");
		spark.config().setDocumentTitle("Reports Of CRM Vtiger");
		spark.config().setReportName("VTIGER Contact_Org Reports");
		spark.config().setTheme(Theme.DARK);
		
		 report = new ExtentReports();
		 report.attachReporter(spark);
		 report.setSystemInfo("Browser", "Chrome-147.0.7727.138 ");
		 report.setSystemInfo("Browser", "Edge-147.0.3912.98 ");
		 report.setSystemInfo("Browser", "FireFox-150.0.1 (64-bit)");
		 report.setSystemInfo("OS", "Windows-AMD Ryzen 7 7435HS (3.10 GHz)");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		
//		every time it replace with new method name to overcome this we use date &time
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		Reporter.log(testname + ": Test execution started", true);
		
//		create the test
		test=report.createTest(testname+time);
	 UtilityObjectClass.setTest(test);   
		
	test.log(Status.INFO, testname+":Test execution started");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
//		Reporter.log(testname + ": Test execution success", true);
		
		test.log(Status.PASS,testname+" : Test Execution successfull");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.log(Status.FAIL, testname+": Test ececution failur-screenshot");
		
//		Reporter.log(testname + ": Test execution Failure", true);
		
		TakesScreenshot tsobj=(TakesScreenshot)BaseClass.sdriver;
		
		String src=tsobj.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src,testname+"_"+time);
	}
		


	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		test.log(Status.WARNING, testname+" : Test Execution skipped");
//		Reporter.log(testname + ": Test execution skipped", true);
		
	}

	@Override
	public void onFinish(ISuite suite) {
		test.log(Status.INFO, "suite execution finished");
		report.flush();
//		Reporter.log("Suite execution Finished-Report Backup", true);
		
	}
	
}
	

