package OrganizationwithPropertyAndExcel;


import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Organization_NamewithPoppage {
	
	@Test
	public void orgname_test() throws InterruptedException, EncryptedDocumentException, IOException {
	
//		fetch data from the Property file 
		
		PropertyfileUtility putil=new PropertyfileUtility();
	    String browser = putil.fetchdatafromPropFile("Browser");
		String Url=putil.fetchdatafromPropFile("URL");
		String UserName=putil.fetchdatafromPropFile("Username");
		String Passsword=putil.fetchdatafromPropFile("Password");
		String timeout = putil.fetchdatafromPropFile("timeouts");
		
	   
//   fetch random integer
	    JavaUtility jutil=new JavaUtility();   
	    int Randomnum = jutil.fetchRandomInt();
	
//	Fetch data from excel
    ExcelFileUtility exutil=new ExcelFileUtility();
    String name=exutil.FetchDatafromExcelFile("orgdata", 1, 3)+Randomnum;
	    
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

//		identify login,username,password and login btn fetch from loinPOPpage and pass values all we are given in methos itself
		LoginPopPage login=new LoginPopPage(driver);
		login.login(UserName, Passsword);
		
//		validate home page
		
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
		}
		else {
			System.out.println("Login Test Fail");
         }
		 
//		identify the organzation tab 
		HomePopPage home=new HomePopPage(driver);
		home.getOrgbtn();
	
//		click on plus btn
		OrgPopPage org=new OrgPopPage(driver);
		org.orgplusicon();
		
//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		CreateOrgPopPage createorg=new CreateOrgPopPage(driver);
		createorg.getOrgnameTxtfld(name);
        createorg.getSavebtn();

//		verify orginfopage
		OrgInfoPopPage orginfo=new OrgInfoPopPage(driver);
		String orginfoheader = orginfo.getVerifyOrgname();
		if (orginfoheader.contains(name)) {
			System.out.println("successfully created org page");
			
		} else {
			System.out.println("creating org fail");
          }
//		identify orn tab and click on it
           home.getOrgbtn();
	
//     	identify delete and delete the org
		 driver.findElement(By.xpath("//a[contains(text(),'"+name+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();
		 Wutil.handleAlertClickonOK(driver);

//		identify the admin icon and mouseover on this
        WebElement admin= home.getAdmintn();
        
//       actions class for mouse over and signout
		Wutil.clickAnEle_Actions(driver, admin);
		 
//		identify signout link and click on it
		home.getSignoutbtn();
//		 driver.findElement(By.linkText("Sign Out")).click();
		 
//		 close the browser
		Wutil.quitTheBrowsesr(driver);
	}

}
