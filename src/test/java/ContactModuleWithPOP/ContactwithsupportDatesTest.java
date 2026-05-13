package ContactModuleWithPOP;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.Test;

import POPUtilities.ContactPopPage;
import POPUtilities.ContactinfoPopPage;
import POPUtilities.CreateContactPopPage;
import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPpages_GenericUtility.ExcelFileUtility;
import POPpages_GenericUtility.JavaUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;

public class ContactwithsupportDatesTest {
	
 @Test
 public void createConWithSuppodate_test()throws FileNotFoundException, IOException, InterruptedException {
	 
//		Property file 
		PropertyfileUtility putil=new PropertyfileUtility();
	    String Browser = putil.fetchdatafromPropFile("Browser");
		String Url=putil.fetchdatafromPropFile("URL");
		String UserName=putil.fetchdatafromPropFile("Username");
		String Passsword=putil.fetchdatafromPropFile("Password");
		String timeout = putil.fetchdatafromPropFile("timeouts");
		
//         fetch random integer
           JavaUtility jutil=new JavaUtility();
           int Randomnum = jutil.fetchRandomInt();	
	    
// 	     Fetch data from excel
	      ExcelFileUtility exutil=new ExcelFileUtility();
         String Contactname=exutil.FetchDatafromExcelFile("ConData", 4, 3)+Randomnum;
		
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
			
//		identify username,password,signin using loginpoppage
		LoginPopPage login=new LoginPopPage(driver);
		login.login(UserName, Passsword);
		
//		  validate home page
		 
		  
		  if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
		 } else {
			System.out.println("Login Test Fail");
         }
//		identify the contact tab and click on it
		  HomePopPage home=new HomePopPage(driver);
		   home.getContactbtn();
		
//		  identify plus icon and click
		  ContactPopPage contact=new ContactPopPage(driver);
		  contact.getContactplusbtn();
		
//		   identify the org name TF and pass the value
		    CreateContactPopPage createcontact=new CreateContactPopPage(driver);
		    createcontact.getLastnametxtfld(Contactname);

		   
//		   fetch date from jutil   
	        String Currentdate= jutil.fetchCurrentDate();
	        
//		    identify start date T.f  and enter
	        createcontact.getSupportstartdatetxtfld(Currentdate);

//		   fetch date enddate from jutil
		   String jEnddate = jutil.fetchDateAfterGivenDate(30);
		  
//		    identify end date T.f  and enter
		    createcontact.getSupportenddatetxtfld(jEnddate);

//			identify save btn and click
			createcontact.getContactsavebtn();

//			verify contact name in contact info page
			ContactinfoPopPage contactinfo=new ContactinfoPopPage(driver);
			String contactinfoheader = contactinfo.getContactheaderinfo();

			if (contactinfoheader.contains(Contactname)) {
				System.out.println("successfully created contact page");
				
			} else {
				System.out.println("creating contact fail");
               }
			
//			verify contact name with support start date in contact info page
			String verifystartinfo=contactinfo.getStartdatecontactinfo();
			if (verifystartinfo.contains(Currentdate)) {
				System.out.println("successfully created contact page with supported start date");
				
			} else {
				System.out.println("creating contact supported start date fail");
               }
			
//			verify contact name with support end date in contact info page
			String verifyendinfo = contactinfo.getEnddatecontactctinfo();
			 if (verifyendinfo.contains(jEnddate)) {
				System.out.println("successfully created contact page with supported end date");
				
			} else {
				System.out.println("creating contact supported end date fail");
               }
			
//			identify contact tab and click
			 home.getContactbtn();

//          identify added contact and delete
			driver.findElement(By.xpath("//a[contains(text(),'"+Contactname+"') and @title='Contacts']/../..//a[contains(text(),'del')]")).click();
			Thread.sleep(1000);
			
//			handle popup
			wutil.handleAlertClickonOK(driver);
			
//			identify the admin icon and mouseover on this
			WebElement admin=home.getAdmintn();
			
//        actions class for mouse over and signout
			wutil.clickAnEle_Actions(driver, admin);
			 
//			identify signout link and click on it
			home.getSignoutbtn();
			 
//			 close the browser
			  wutil.quitTheBrowsesr(driver);
		}
}
