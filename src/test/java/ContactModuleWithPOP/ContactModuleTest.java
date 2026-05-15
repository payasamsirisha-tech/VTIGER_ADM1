package ContactModuleWithPOP;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.BaseClass;
import ListenersUtility.UtilityObjectClass;
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


//@Listeners(ListenersUtility.Listeners.class)

/**
 * This is contact module
 */
public class ContactModuleTest extends BaseClass {
	@Test (groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)

	public void name_test() throws IOException, InterruptedException {

//        fetch random integer

//		ListenersUtility.Listeners.test.log(Status.INFO, "Fetching random intiger");
//	 test we given as static so static variable can't create multiple objects for batch pRller group executions so we given static variales in another clas and that class we are calling here

		UtilityObjectClass.getTest().log(Status.INFO, "Fetchimg random intiger");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//     	Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from excelfile");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String Contactname = exutil.FetchDatafromExcelFile("Condata", 1, 3) + Randomnum;

//		validate home page

		SoftAssert soft = new SoftAssert();
		UtilityObjectClass.getTest().log(Status.INFO, "verify home page");
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validate home page");

//		identify the contact tab and click on it using homepopup
		UtilityObjectClass.getTest().log(Status.INFO, "identify the contact tab and click on it");
		HomePopPage home = new HomePopPage(driver);
		home.getContactbtn();

//		identify plus icon and click on it
		ContactPopPage contact = new ContactPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify contact plus icon and click on it");
		contact.getContactplusbtn();

//		identify the Contact name TF and pass the value
		CreateContactPopPage create = new CreateContactPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify orgname TF and pass the org name");
		create.getLastnametxtfld(Contactname);

//		identify save btn and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify save btn and click on it");
		create.getContactsavebtn();

//		verify contactname in contact info page
		ContactinfoPopPage contactinfo = new ContactinfoPopPage(driver);
		String contactinfoheader = contactinfo.getContactheaderinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify contactname in contact info page");
		Assert.assertTrue(contactinfoheader.contains(Contactname), "verify contactname in contact info page");

//		identify contact and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify contact btn and click on it");
		home.getContactbtn();

		// identify added contact and delete
		UtilityObjectClass.getTest().log(Status.INFO, "identify delete btn and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + Contactname + "') and @title='Contacts']/../..//a[contains(text(),'del')]"))
				.click();
		Thread.sleep(1000);

//		 Handle alrear popand and click on ok btn
		UtilityObjectClass.getTest().log(Status.INFO, "identify alert popup  and click on it");
		wutil.handleAlertClickonOK(driver);

//		close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "close the excel");
		exutil.closeExcel();
		soft.assertAll();

	}

	@Test(groups = "Regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void CreateContactwithOrg_test() throws FileNotFoundException, IOException, InterruptedException {

//fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random integer");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//	Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "fetching data from excel file");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String Contactname = exutil.FetchDatafromExcelFile("Condata", 7, 3) + Randomnum;
		String orgname = exutil.FetchDatafromExcelFile("Condata", 7, 4) + Randomnum;

//	validate home page

		SoftAssert soft = new SoftAssert();
		UtilityObjectClass.getTest().log(Status.INFO, "verify home page");
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validate home page");

//	identify organization tab and click on it
		HomePopPage home = new HomePopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify orgtab  and click on it");

		home.getOrgbtn();

//	identify plus icon and click on it usig orgPOppage

		OrgPopPage org = new OrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify orgplus icon and click on it");
		org.orgplusicon();

//	identify the organzation name and send the value
		CreateOrgPopPage createorg = new CreateOrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify org TF and pass the orgname");
		createorg.getOrgnameTxtfld(orgname);

//	identify the save button and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify save btn and click on it");
		createorg.getSavebtn();

//	verify org info page
		OrgInfoPopPage orginfo = new OrgInfoPopPage(driver);

		String infoheader = orginfo.getOrginfoheader();
		UtilityObjectClass.getTest().log(Status.PASS, "erify org info page");
		Assert.assertTrue(infoheader.contains(orgname), "verify org info page");

//	identify the contact tab  and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify contact btn and click on it");
		home.getContactbtn();

//	identify plus icon and click
		UtilityObjectClass.getTest().log(Status.INFO, "identify the contact plus icon and click on it");
		ContactPopPage contact = new ContactPopPage(driver);
		contact.getContactplusbtn();

//	identify the last name TF and pass the value
		CreateContactPopPage createcontact = new CreateContactPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify contact TF and pass the value");
		createcontact.getLastnametxtfld(Contactname);

//	identify the orgplus icon and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify the contact plus icon and click on it");
		createcontact.getorgcontactplusicon();

//	fetch the parant window id
		UtilityObjectClass.getTest().log(Status.INFO, "fetct the parant window id");
		String parantwin = wutil.fetchWindowID(driver);

//	   switch the control to child window
		UtilityObjectClass.getTest().log(Status.INFO, "switch the control to child window");
		wutil.SwitchToChildWindow_url(driver, "module=Account&action");
		createcontact.getOrgsearchTF(orgname);
		createcontact.getOrgsearhbtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		wutil.SwitchtoParantwindow(driver, parantwin);

//       identify save btn and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify contatc save btn and click on it");
		createcontact.getContactsavebtn();

//  verify contact name in contact info page
		ContactinfoPopPage contactinfo = new ContactinfoPopPage(driver);
		String contactinfoheader = contactinfo.getContactheaderinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify contact info header");
		Assert.assertTrue(contactinfoheader.contains(Contactname));

// verify orgname in contact info page
		String orginfoheader = contactinfo.getContactorginfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify orgname in contact info  page");
		Assert.assertTrue(orginfoheader.contains(orgname), "verify orgname in contact info page");
		// identify contact tab and click
		home.getContactbtn();
//     

		// identify added contact and delete
		UtilityObjectClass.getTest().log(Status.INFO, "identify the delete btn for contactname  and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + Contactname + "') and @title='Contacts']/../..//a[contains(text(),'del')]"))
				.click();

		Thread.sleep(1000);
//	handle popup and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify alert popup and click on it");
		wutil.handleAlertClickonOK(driver);
		Thread.sleep(1000);
//	identify org and click on it
		home.getOrgbtn();

//	identify delete and delete the org
		UtilityObjectClass.getTest().log(Status.INFO, "identify delete btn for orn name  and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + orgname + "')and @title='Organizations']/../..//a[contains(text(),'del')]"))
				.click();

//	using webdriver util and click 
		UtilityObjectClass.getTest().log(Status.INFO, "identify alert popup and click on it");
		wutil.handleAlertClickonOK(driver);

		Thread.sleep(1000);
		UtilityObjectClass.getTest().log(Status.INFO, "close the browser");
		exutil.closeExcel();
		soft.assertAll();
	}

	@Test(groups = "Regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createConWithSuppodate_test() throws FileNotFoundException, IOException, InterruptedException {

//        fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "fetching random integer");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//	     Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from excel file");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String Contactname = exutil.FetchDatafromExcelFile("Condata", 4, 3) + Randomnum;

//		  validate home page

		SoftAssert soft = new SoftAssert();
		UtilityObjectClass.getTest().log(Status.INFO, "verify home page");
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validate home page");

		// identify the contact tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify the contactbtn and click on it");
		HomePopPage home = new HomePopPage(driver);
		home.getContactbtn();

//		  identify plus icon and click
		ContactPopPage contact = new ContactPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify contact plusicon  and click on it");
		contact.getContactplusbtn();

//		   identify the org name TF and pass the value
		CreateContactPopPage createcontact = new CreateContactPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify lastname TF and pass the value");
		createcontact.getLastnametxtfld(Contactname);

//		   fetch date from jutil   
		UtilityObjectClass.getTest().log(Status.INFO, "fetch current date from javautil");
		String Currentdate = jutil.fetchCurrentDate();

//		    identify start date T.f  and enter
		UtilityObjectClass.getTest().log(Status.INFO, "identify the start date TF and enter the current date");
		createcontact.getSupportstartdatetxtfld(Currentdate);

//		   fetch date enddate from jutil
		UtilityObjectClass.getTest().log(Status.INFO, "fetch the end date from javautil");
		String jEnddate = jutil.fetchDateAfterGivenDate(30);

//		    identify end date T.f  and enter
		UtilityObjectClass.getTest().log(Status.INFO, "identify the end date TF and enter the end date ");
		createcontact.getSupportenddatetxtfld(jEnddate);

//			identify save btn and click
		UtilityObjectClass.getTest().log(Status.INFO, "identify the contatc save btn and click on it");
		createcontact.getContactsavebtn();

//			verify contact name in contact info page
		ContactinfoPopPage contactinfo = new ContactinfoPopPage(driver);
		String contactinfoheader = contactinfo.getContactheaderinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verift contact name with contact info page");
		Assert.assertTrue(contactinfoheader.contains(Contactname), "verify contact name in contact info page");

//			verify contact name with support start date in contact info page

		String verifystartinfo = contactinfo.getStartdatecontactinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify contact name with suppoerted start date");
		Assert.assertTrue(verifystartinfo.contains(Currentdate),
				"verify contact name with support start date in contact info page");

//			verify contact name with support end date in contact info page
		String verifyendinfo = contactinfo.getEnddatecontactctinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify contact name with supported end date");
		Assert.assertTrue(verifyendinfo.contains(jEnddate),
				"verify contact name with support end date in contact info page");

//			identify contact tab and click
		UtilityObjectClass.getTest().log(Status.INFO, "identify the home btn and and click on it");
		home.getContactbtn();

//         identify added contact and delete
		UtilityObjectClass.getTest().log(Status.INFO, "identify the delete btn and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + Contactname + "') and @title='Contacts']/../..//a[contains(text(),'del')]"))
				.click();
		Thread.sleep(1000);

//	handle popup
		UtilityObjectClass.getTest().log(Status.INFO, "identify alert popup and click on it");
		wutil.handleAlertClickonOK(driver);
		Thread.sleep(1000);

		UtilityObjectClass.getTest().log(Status.INFO, "close the excel");
		exutil.closeExcel();
		soft.assertAll();

	}

}
