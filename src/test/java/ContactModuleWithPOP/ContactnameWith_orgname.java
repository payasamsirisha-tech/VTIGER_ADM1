package ContactModuleWithPOP;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import POPUtilities.ContactPopPage;
import POPUtilities.ContactinfoPopPage;
import POPUtilities.CreateContactPopPage;
import POPUtilities.CreateOrgPopPage;
import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPUtilities.OrgInfoPopPage;
import POPUtilities.OrgPopPage;
import POPpages_GenericUtility.ExcelFileUtility;
import POPpages_GenericUtility.JavaUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;

public class ContactnameWith_orgname {
	
	@Test
	public void CreateContactwithOrg_test() throws FileNotFoundException, IOException, InterruptedException{

//		fetching data fron Property file 
		PropertyfileUtility putil=new PropertyfileUtility();
	    String Browser = putil.fetchdatafromPropFile("Browser");
		String Url=putil.fetchdatafromPropFile("URL");
		String UserName=putil.fetchdatafromPropFile("Username");
		String Passsword=putil.fetchdatafromPropFile("Password");
		String timeout = putil.fetchdatafromPropFile("timeouts");
		
		
//    fetch random integer
          JavaUtility jutil=new JavaUtility();
          int Randomnum = jutil.fetchRandomInt();
		
//		Fetch data from excel
	    ExcelFileUtility exutil=new ExcelFileUtility();
        String Contactname=exutil.FetchDatafromExcelFile("Condata", 7, 3)+Randomnum;
	    String orgname=exutil.FetchDatafromExcelFile("Condata", 7, 4)+Randomnum;

		
//		open browese and enter url
		WebDriver driver=null;
		if (Browser.equals("Chrome")) {
			driver=new ChromeDriver();
			
		} else if (Browser.equals("Edge"))  {
			
			driver=new EdgeDriver();
		}
		else if (Browser.equals("Firefox")) {
			driver=new FirefoxDriver();
		}
		
		else {
			driver=new ChromeDriver();
          }
//     	fetch data from webdriver utility
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.navigateToAnAppln(driver, Url);
	    wutil.MaximizeTheWindow(driver);
		wutil.waitforElement_Implicit(driver, timeout);
		
//		identify username TF,password tf and submitbtn using loginpopup
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
		
//		identify plus icon and click on it usig orgPOppage
		OrgPopPage org=new OrgPopPage(driver);
		org.orgplusicon();
		
//		identify the organzation name and send the value
		CreateOrgPopPage createorg=new CreateOrgPopPage(driver);
		createorg.getOrgnameTxtfld(orgname);
	
		
//		identify the save button and click on it
	     createorg.getSavebtn();
		
//		verify org info page
	     OrgInfoPopPage orginfo=new OrgInfoPopPage(driver);
	     
		String infoheader =orginfo.getOrginfoheader();
		if (infoheader.contains(orgname)) {
			System.out.println("successfully created org page");
		} 
		   else {
			System.out.println("creating org fail");
          }
		
//		identify the contact tab  and click on it
		home.getContactbtn();

//		identify plus icon and click
		ContactPopPage contact=new ContactPopPage(driver);
		contact.getContactplusbtn();

		
//		identify the last name TF and pass the value
		CreateContactPopPage createcontact=new CreateContactPopPage(driver);
		createcontact.getLastnametxtfld(Contactname);
	
//		identify the orgplus icon and click on it
		createcontact.getorgcontactplusicon();


//		fetch the parant window id
		String parantwin = wutil.fetchWindowID(driver);
		
//		switch the control to child window
        wutil.SwitchToChildWindow_url(driver, "module=Account&action");
        createcontact.getOrgsearchTF(orgname);
         createcontact.getOrgsearhbtn();
	     driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	    wutil.SwitchtoParantwindow(driver, parantwin);
	 
 //       identify save btn and click on it
         createcontact.getContactsavebtn();
		
//      verify contact name in contact info page
          ContactinfoPopPage contactinfo=new ContactinfoPopPage(driver);
          String contactinfoheader = contactinfo.getContactheaderinfo();

		if (contactinfoheader.contains(Contactname)) {
		System.out.println("successfully created contact page");
		} else {
		System.out.println("creating contact fail");

		}
		
//     verify orgname in contact info page
		String orginfoheader = contactinfo.getContactorginfo();
         if (orginfoheader.contains(orgname)) {
		    System.out.println("successfully created contact page with org");
	      } 
         else {
		  System.out.println("creating contact fail with org");
            }
         
		//identify contact tab and click
            home.getContactbtn();
//         

		//identify added contact and delete
		driver.findElement(By.xpath("//a[contains(text(),'"+Contactname+"') and @title='Contacts']/../..//a[contains(text(),'del')]")).click();

//		handle popup and click on it
	    wutil.handleAlertClickonOK(driver);
		
//		identify org and click on it
	    home.getOrgbtn();


//		identify delete and delete the org
		driver.findElement(By.xpath("//a[contains(text(),'"+orgname+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();

//		using webdriver util and click 
		wutil.handleAlertClickonOK(driver);

//		identify the admin icon and mouseover on this
		WebElement admin = home.getAdmintn();
		  
//        actions class for mouse over and signout
		   wutil.mouseoveranEle_Actions(driver, admin);
		 
//		identify signout link and click on it
		 home.getSignoutbtn();
		 
//		 close the browser
		   wutil.quitTheBrowsesr(driver);
		}
     }
	 
	 
	 
	 
	 
	 
	 
	 
