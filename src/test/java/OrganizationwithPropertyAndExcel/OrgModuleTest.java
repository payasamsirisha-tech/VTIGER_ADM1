package OrganizationwithPropertyAndExcel;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
/ This is org module
	
public class OrgModuleTest extends BaseClass {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void orgname_test() throws InterruptedException, EncryptedDocumentException, IOException {

//   fetch random integer
//		ListenersUtility.Listeners.test.log(Status.INFO, "Fetching random intiger");
		
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random intiger");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//	Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from excel");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String name = exutil.FetchDatafromExcelFile("orgdata", 1, 3) + Randomnum;

//		validate home page
		UtilityObjectClass.getTest().log(Status.INFO, "Verify Home page");
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validating home page");
//		soft.assertEquals(driver.getCurrentUrl(), driver.getCurrentUrl());

//		identify the organzation tab 
		UtilityObjectClass.getTest().log(Status.INFO, "identfy the org tab and click on it");
		HomePopPage home = new HomePopPage(driver);
		home.getOrgbtn();

//		click on plus btn
		OrgPopPage org = new OrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identift org page and click on it");
		org.orgplusicon();

//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		CreateOrgPopPage createorg = new CreateOrgPopPage(driver);
		createorg.getOrgnameTxtfld(name);
		UtilityObjectClass.getTest().log(Status.INFO, "create org with orgname and click on save");
		createorg.getSavebtn();

//		verify orginfopage
		OrgInfoPopPage orginfo = new OrgInfoPopPage(driver);
		String orginfoheader = orginfo.getVerifyOrgname();
		UtilityObjectClass.getTest().log(Status.PASS, "verify org with created orgname");
		Assert.assertTrue(orginfoheader.contains(name), "verifying orginfopage");

//		identify orn tab and click on it
		home.getOrgbtn();
		UtilityObjectClass.getTest().log(Status.INFO, "identify the org tab and click on it");

//     	identify delete and delete the org
		UtilityObjectClass.getTest().log(Status.INFO, "identify the delete btn and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + name + "')and @title='Organizations']/../..//a[contains(text(),'del')]"))
				.click();
		Thread.sleep(1000);

//		handle alert popup
		UtilityObjectClass.getTest().log(Status.INFO, "Handle Alret popup and click on it");
		wutil.handleAlertClickonOK(driver);

//close excel
		UtilityObjectClass.getTest().log(Status.INFO, "close the excel");
		exutil.closeExcel();

		soft.assertAll();

	}

	@Test(groups = "Regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void CreateOrgwithIndustryAndType_test() throws IOException, InterruptedException {

//      fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching random intiger");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//     	Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "Fetching data from excel");

		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.FetchDatafromExcelFile("orgdata", 4, 3) + Randomnum;
		String Industry = exutil.FetchDatafromExcelFile("orgdata", 4, 4);
		String Type = exutil.FetchDatafromExcelFile("orgdata", 4, 5);

//		validate home page
		SoftAssert soft = new SoftAssert();
		UtilityObjectClass.getTest().log(Status.INFO, "verify home page");
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validating home page");

//		identify organization tab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identift the org tab and click on it");
		HomePopPage home = new HomePopPage(driver);
		home.getOrgbtn();

//		identify plus icon and click on it
		OrgPopPage org = new OrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify org plus icon and click on it");
		org.orgplusicon();

//		identify the organzation T.F  and PASS the value
		CreateOrgPopPage createorg = new CreateOrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify org TF and pass the org name");
		createorg.getOrgnameTxtfld(orgname);

//		identify the industry dropdown from dd
		WebElement ind_dd = createorg.getIndustrydropdown();
		UtilityObjectClass.getTest().log(Status.INFO, "identify industrt dropdown and click on it");
		ind_dd.click();

//	     WebElement ind_dd = driver.findElement(By.name("industry"));
		wutil.selectDDByvisibleText(ind_dd, Industry);
		UtilityObjectClass.getTest().log(Status.INFO, "pass the industry name by excel file");

//	        identify the Type Dropdown and pass the value
		UtilityObjectClass.getTest().log(Status.INFO, "identify type dropdown and click on it");
		WebElement type_dd = createorg.getTypedropdown();
		type_dd.click();

//	    fetching dropdown from WebUtility select class for type dropdown
		UtilityObjectClass.getTest().log(Status.INFO, "pass the type name from excel file");
		wutil.selectDDByvalue(type_dd, Type);

//			identify the save button and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify the save btn and click on it");
		createorg.getSavebtn();

//			verify org info page 
		OrgInfoPopPage orginfo = new OrgInfoPopPage(driver);
		String infheader = orginfo.getOrginfoheader();
		UtilityObjectClass.getTest().log(Status.PASS, "verify orginfo page ");
		Assert.assertTrue(infheader.contains(orgname), "verifying orginfo page");

//			verify industry in orginfo page
		String ind_infheader = orginfo.getVerifyindustryinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify industry in orginfo page");
		Assert.assertTrue(ind_infheader.contains(Industry), "verifying industry in orginfo page");

//		verify type in orginfo page
		String typeinfheader = orginfo.getVerifytypeinfo();
		UtilityObjectClass.getTest().log(Status.PASS, "Fverify type in org info page");
		Assert.assertTrue(typeinfheader.contains(Type), "verify type in orginfo page");

//			identify org and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify home and click on it");
		home.getOrgbtn();

//			identify delete and delete the org
		UtilityObjectClass.getTest().log(Status.INFO, "identify the delete btn and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + orgname + "')and @title='Organizations']/../..//a[contains(text(),'del')]"))
				.click();
		Thread.sleep(1000);

//        	handle popup and click on ok		
		UtilityObjectClass.getTest().log(Status.INFO, "identify popup and click on it");
		wutil.handleAlertClickonOK(driver);

//		close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "close the excel");
		exutil.closeExcel();
		soft.assertAll();
	}

	@Test(groups = "Regression", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void OrgPhnum_test() throws IOException, InterruptedException {

//	    fetch random integer
		UtilityObjectClass.getTest().log(Status.INFO, "fetching random integer");
		JavaUtility jutil = new JavaUtility();
		int Randomnum = jutil.fetchRandomInt();

//  	Fetch data from excel
		UtilityObjectClass.getTest().log(Status.INFO, "fetching data from excel file");
		ExcelFileUtility exutil = new ExcelFileUtility();
		String name = exutil.FetchDatafromExcelFile("orgdata", 1, 3) + Randomnum;
		String phnum = exutil.FetchDatafromExcelFile("orgdata", 7, 4);

//		validate home page
		SoftAssert soft = new SoftAssert();
		UtilityObjectClass.getTest().log(Status.INFO, "verify hime page");
		soft.assertTrue(driver.getCurrentUrl().contains("action=index&module=Home"), "validating home page");

		// click on org
		UtilityObjectClass.getTest().log(Status.INFO, "identify the org tab and click on it");
		HomePopPage home = new HomePopPage(driver);
		home.getOrgbtn();

//		click on plus btn
		UtilityObjectClass.getTest().log(Status.INFO, "identify the org plus icon and click on it");
		OrgPopPage org = new OrgPopPage(driver);
		org.orgplusicon();

//		identift the org name TF and enter the orgname
		CreateOrgPopPage createorg = new CreateOrgPopPage(driver);
		UtilityObjectClass.getTest().log(Status.INFO, "identify org TF and pass the value");
		createorg.getOrgnameTxtfld(name);

//		identify the orgphnnum TF and enter the phnnum
		UtilityObjectClass.getTest().log(Status.INFO, "identify phnnum and pass the value");
		createorg.getOrgphnnumTxtfld(phnum);

//		identify the save btn and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify save btn and click on save");
		createorg.getSavebtn();

//		verify org info page 
		OrgInfoPopPage orginfo = new OrgInfoPopPage(driver);
		String orginfoheader = orginfo.getOrginfoheader();
		UtilityObjectClass.getTest().log(Status.PASS, "verify org info header");
		Assert.assertTrue(orginfoheader.contains(name), "verify org info page ");
//		verify org info page with phnnum

		String phninfoheader = orginfo.getVerifyorgphninfo();
		UtilityObjectClass.getTest().log(Status.PASS, "verify org info page with phnnum");
		Assert.assertTrue(phninfoheader.contains(phnum), "verify org info page with phnnum");

//		identify orgtab and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify the org tab  and click on it");
		home.getOrgbtn();

//		identify delete and delete the org
		UtilityObjectClass.getTest().log(Status.INFO, "identify the delete btn and click on it");
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + name + "')and @title='Organizations']/../..//a[contains(text(),'del')]"))
				.click();
		Thread.sleep(1000);

//		identift the alert popup and click on it
		UtilityObjectClass.getTest().log(Status.INFO, "identify alertpopup and click on ok");
		wutil.handleAlertClickonOK(driver);
		Thread.sleep(1000);

//     close the excel
		UtilityObjectClass.getTest().log(Status.INFO, "close the excel");
		exutil.closeExcel();
		soft.assertAll();
	}

}
