package OrganizationwithPropertyAndExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import POPUtilities.CreateOrgPopPage;
import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPUtilities.OrgInfoPopPage;
import POPUtilities.OrgPopPage;
import POPpages_GenericUtility.ExcelFileUtility;
import POPpages_GenericUtility.JavaUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;


public class CreateOrgwithIndustryAndTypeTest {
	
	@Test
	public void CreateOrgwithIndustryAndType_test() throws IOException, InterruptedException {
		
//		Property file 
		PropertyfileUtility putil=new PropertyfileUtility();
	    String browser = putil.fetchdatafromPropFile("Browser");
		String Url=putil.fetchdatafromPropFile("URL");
		String UserName=putil.fetchdatafromPropFile("Username");
		String Passsword=putil.fetchdatafromPropFile("Password");
		String timeout = putil.fetchdatafromPropFile("timeouts");
		
//      fetch random integer
	    JavaUtility jutil=new JavaUtility();
	    int Randomnum = jutil.fetchRandomInt();
		
//     	Fetch data from excel
	    ExcelFileUtility exutil=new ExcelFileUtility();
	    String orgname=exutil.FetchDatafromExcelFile("orgdata", 4, 3)+Randomnum;
        String Industry=exutil.FetchDatafromExcelFile("orgdata", 4, 4);
	    String Type=exutil.FetchDatafromExcelFile("orgdata", 4, 5);

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
		
//			fetch data from WebDriver utility
	       WebDriverUtility Wutil=new WebDriverUtility();
		
//		  maximize the window
		    Wutil.MaximizeTheWindow(driver);
		
//		  implictwait
		  Wutil.waitforElement_Implicit(driver, timeout);
		
//		  Navigate application to url
		     Wutil.navigateToAnAppln(driver, Url);
			
//          login to vtiger usig loginpoputility
		     LoginPopPage login=new LoginPopPage(driver);
		     login.login(UserName, Passsword);
		    
//		validate home page
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");
         }
		
//		identify organization tab and click on it
		 HomePopPage home=new HomePopPage(driver);
		 home.getOrgbtn();
		
//		identify plus icon and click on it
		OrgPopPage org=new OrgPopPage(driver);
		org.orgplusicon();
		
//		identify the organzation T.F  and PASS the value
		
		CreateOrgPopPage createorg=new CreateOrgPopPage(driver);
		createorg.getOrgnameTxtfld(orgname);
		
//		identify the industry dropdown from dd
	    WebElement ind_dd=createorg.getIndustrydropdown();
	    ind_dd.click();
		
//	     WebElement ind_dd = driver.findElement(By.name("industry"));
	      Wutil.selectDDByvisibleText(ind_dd, Industry);
		  
//	        identify the Type Dropdown and pass the value
	        WebElement type_dd=createorg.getTypedropdown();
	         type_dd.click();
	      
	   
//	    fetching dropdown from WebUtility select class for type dropdown
	       Wutil.selectDDByvalue(type_dd, Type);
   
//			identify the save button and click on it
	          createorg.getSavebtn();
			
//			verify org info page 
	        OrgInfoPopPage orginfo=new OrgInfoPopPage(driver);
	        String infheader = orginfo.getOrginfoheader();
			if (infheader.contains(orgname)) {
				System.out.println("successfully created org page");
			} 
			else {
				System.out.println("creating org fail");
            }
			
//			verify industry in orginfo page
             String ind_infheader = orginfo.getVerifyindustryinfo();
			 if (ind_infheader.contains(Industry)) {
				System.out.println("successfully created org page with industry");
				
			} else {
				System.out.println("creating org with industry fail");
            }
			 
//		verify type in orginfo page
             String typeinfheader = orginfo.getVerifytypeinfo();
			 if (typeinfheader.contains(Type)) {
				System.out.println("successfully created org page with Type");
				
			} else {
				System.out.println("creating org with type fail");
            }
			
//			identify org and click on it
		    home.getOrgbtn();

//			identify delete and delete the org
		driver.findElement(By.xpath("//a[contains(text(),'"+orgname+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();
			
			
//        	handle popup and click on ok		
			Wutil.handleAlertClickonOK(driver);

//			identify the admin icon and mouseover on this
			WebElement admin = home.getAdmintn();
			  
//         actions class for mouse over and signout
		      Wutil.mouseoveranEle_Actions(driver, admin);
			 
//			identify signout link and click on it
			 home.getSignoutbtn();
			 
//			 close the browser
			  Wutil.quitTheBrowsesr(driver);
	}

}
		
