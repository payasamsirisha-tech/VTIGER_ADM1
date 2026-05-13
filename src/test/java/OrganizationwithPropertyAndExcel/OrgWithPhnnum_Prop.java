package OrganizationwithPropertyAndExcel;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPUtilities.CreateOrgPopPage;
import POPUtilities.OrgInfoPopPage;
import POPUtilities.OrgPopPage;
import POPpages_GenericUtility.ExcelFileUtility;
import POPpages_GenericUtility.JavaUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;

public class OrgWithPhnnum_Prop {

	@Test
	
	public void OrgusingProperty_test() throws IOException, InterruptedException {
//		Property file 
		
		PropertyfileUtility P=new PropertyfileUtility();
	    String browser = P.fetchdatafromPropFile("Browser");
		
		String Url=P.fetchdatafromPropFile("URL");
		String UserName=P.fetchdatafromPropFile("Username");
		String Passsword=P.fetchdatafromPropFile("Password");
		String timeout = P.fetchdatafromPropFile("timeouts");
//		String Phnnum="987808765";
//		String orgname="hello87";
		
//	    fetch random integer
	    JavaUtility jutil=new JavaUtility();
    int Randomnum = jutil.fetchRandomInt();
		
//  	Fetch data from excel
	    ExcelFileUtility exutil=new ExcelFileUtility();
	    String name=exutil.FetchDatafromExcelFile("orgdata", 1, 3)+Randomnum;
	    String phnum=exutil.FetchDatafromExcelFile("orgdata", 7, 4);
		
//		open browese and enter url
		
		WebDriver driver=null;
         if (browser.equals("Chrome")) {
			driver=new ChromeDriver();
		}else if (browser.equals("edge")) {
			
			driver=new EdgeDriver();	
		}
		else if (browser.equals("Firefox")) {
			driver=new FirefoxDriver();
			}
		else
			driver=new ChromeDriver();
		
//		fetch data from WebDriver utility
		WebDriverUtility Wutil=new WebDriverUtility();
		
//		maximize the window
		Wutil.MaximizeTheWindow(driver);
		
//		implictwait
		Wutil.waitforElement_Implicit(driver, timeout);
		
//		Navigate application to url
		Wutil.navigateToAnAppln(driver, Url);
		
		
//		identify username usingligingpoppage and pass usernametxtfld
		LoginPopPage l=new LoginPopPage(driver);
		l.login(UserName, Passsword);
		
//		validate home page
		
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");

		}
//      click on org
		HomePopPage home=new HomePopPage(driver);
		home.getOrgbtn();
		
//		driver.findElement(By.linkText("Organizations")).click();
		
//		click on plus btn
       	OrgPopPage org=new OrgPopPage(driver);
		org.orgplusicon();
		
//	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		CreateOrgPopPage createorg=new CreateOrgPopPage(driver);
		
		createorg.getOrgnameTxtfld(name);
		
		
//		driver.findElement(By.name("accountname")).sendKeys(name);
		
		createorg.getOrgphnnumTxtfld(phnum);
		
//		driver.findElement(By.id("phone")).sendKeys(phnum);
		createorg.getSavebtn();
		
			
//		verify org info page 
		
		OrgInfoPopPage orginfo=new OrgInfoPopPage(driver);
	
		 String orginfoheader = orginfo.getOrginfoheader();
		
		if (orginfoheader.contains(name)) {
			System.out.println("successfully created org page");
			
		} else {
			System.out.println("creating org fail");

		}
		
//		verify org info page with phnnum
		
         String phninfoheader = orginfo.getVerifyorgphninfo();
		if (phninfoheader.contains(phnum)) {
			System.out.println("successfully created org page with phn num");
			
		} else {
			System.out.println("creating org with phn num fail");

		}
//		identify orgtab and click on it
		home.getOrgbtn();

//		identify delete and delete the org
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();
		 Wutil.handleAlertClickonOK(driver);

	
//		identify the admin icon and mouseover on this
		  WebElement admin = home.getAdmintn();
		  
//       actions class for mouse over and signout
		Wutil.clickAnEle_Actions(driver, admin);
		 
//		identify signout link and click on it
		home.getSignoutbtn();
		 
//		 close the browser
		Wutil.quitTheBrowsesr(driver);
	}
}
