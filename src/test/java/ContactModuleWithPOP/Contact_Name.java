package ContactModuleWithPOP;

import java.awt.Desktop.Action;
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

import POPUtilities.ContactPopPage;
import POPUtilities.ContactinfoPopPage;
import POPUtilities.CreateContactPopPage;
import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPpages_GenericUtility.ExcelFileUtility;
import POPpages_GenericUtility.JavaUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;

public class Contact_Name {
	 
	@Test
	
	public void name_test() throws IOException, InterruptedException {
		
//		Property file 
		
		PropertyfileUtility putil=new PropertyfileUtility();
	    String browser = putil.fetchdatafromPropFile("Browser");
		String Url=putil.fetchdatafromPropFile("URL");
		String UserName=putil.fetchdatafromPropFile("Username");
		String Passsword=putil.fetchdatafromPropFile("Password");
		String timeout = putil.fetchdatafromPropFile("timeouts");
		
		
//        fetch random integer
           JavaUtility jutil=new JavaUtility();
      	    int Randomnum = jutil.fetchRandomInt();
	
//     	Fetch data from excel
	    ExcelFileUtility exutil=new ExcelFileUtility();
	    String Contactname=exutil.FetchDatafromExcelFile("ConData", 1, 3)+Randomnum;
		
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
		
//     	fetch data from webdriver utility
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.navigateToAnAppln(driver, Url);
		wutil.MaximizeTheWindow(driver);
		wutil.waitforElement_Implicit(driver, timeout);
	
//		identify username,password and submibtn using logi poppage
		LoginPopPage login=new LoginPopPage(driver);
		login.login(UserName, Passsword);
		
//		validate home page
		
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");

		}
//		identify the contact tab and click on it using homepopup
		HomePopPage home=new HomePopPage(driver);
		home.getContactbtn();
	
//		identify plus icon and click on it
		ContactPopPage contact=new ContactPopPage(driver);
		contact.getContactplusbtn();
	
//		identify the Contact name TF and pass the value
		CreateContactPopPage create=new CreateContactPopPage(driver);
		create.getLastnametxtfld(Contactname);
	
//		identify save btn and click on it
		create.getContactsavebtn();
		
//		verify contactname in contact info page
		ContactinfoPopPage contactinfo=new ContactinfoPopPage(driver);
		String contactinfoheader = contactinfo.getContactheaderinfo();
        if (contactinfoheader.contains(Contactname)) {
			System.out.println("successfully created contact page");
			
		} else {
			System.out.println("creating contact fail");
          }
		
//		identify contact and click on it
		home.getContactbtn();

		//identify added contact and delete
		driver.findElement(By.xpath("//a[contains(text(),'"+Contactname+"') and @title='Contacts']/../..//a[contains(text(),'del')]")).click();
		 
		Thread.sleep(1000);
		 
//		 Handle alrear popand and click on ok btn
		 wutil.handleAlertClickonOK(driver);
		
		
//		identify the admin icon and mouseover on this
		WebElement admin = home.getAdmintn();
		
//       actions class for mouse over and signout
         
		 wutil.clickAnEle_Actions(driver, admin);
		 
		 
//		identify signout link and click on it
		home.getSignoutbtn();
		 
//		 close the browser
		 wutil.quitTheBrowsesr(driver);
	}		
		
	}
