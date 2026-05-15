package BaseclassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ListenersUtility.UtilityObjectClass;
import POPUtilities.HomePopPage;
import POPUtilities.LoginPopPage;
import POPpages_GenericUtility.DatabaseUtility;
import POPpages_GenericUtility.PropertyfileUtility;
import POPpages_GenericUtility.WebDriverUtility;


public class BaseClass {

	public DatabaseUtility dbutil;
	public PropertyfileUtility putil = new PropertyfileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver=null;

	@BeforeSuite(alwaysRun=true)
	public void connectToDB() throws SQLException {
		dbutil = new DatabaseUtility();
		dbutil.getconnectWithDB();
		Reporter.log("Connected to DB", true);
	}

	@BeforeTest(alwaysRun=true)
	public void configParallelExe() {
		Reporter.log("configure the parallel execution", true);
	}

	@BeforeClass(alwaysRun=true)
//	@Parameters("browser")
	public void LaunchtheBrowser() throws IOException {
		Reporter.log("Launching the browser", true);

//     String browser=putil.fetchdatafromPropFile("browser");
		
//     for maven we can given command line also so here we are just passing key if key is not given then it will take from putil so here we are giving both
     	
     String browser=System.getProperty("browser",putil.fetchdatafromPropFile("browser"));

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equals("edge")) {

			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else
			driver = new ChromeDriver();
		
		UtilityObjectClass.setDriver(driver);
		sdriver=driver;
	}

	@BeforeMethod(alwaysRun=true)
	public void Login() throws IOException {
//		String url = putil.fetchdatafromPropFile("url");
//		String username = putil.fetchdatafromPropFile("username");
//		String password = putil.fetchdatafromPropFile("password");
//		String timeouts = putil.fetchdatafromPropFile("timeouts");
		
		String url =System.getProperty("url", putil.fetchdatafromPropFile("url"));
		String username = System.getProperty("username", putil.fetchdatafromPropFile("username"));
		String password = System.getProperty("password", putil.fetchdatafromPropFile("password"));
	String timeouts =System.getProperty("timeouts", putil.fetchdatafromPropFile("timeouts"));
		
		wutil.MaximizeTheWindow(driver);
		wutil.waitforElement_Implicit(driver, timeouts);
		wutil.navigateToAnAppln(driver, url);
		LoginPopPage l = new LoginPopPage(driver);
		l.login(username, password);
		Reporter.log("logged into the application", true);
	}

	@AfterMethod(alwaysRun=true)
	public void Logout() {
		HomePopPage home = new HomePopPage(driver);
		wutil.mouseoveranEle_Actions(driver, home.getAdmintn());
		home.getSignoutbtn();
		Reporter.log("logged out into the application", true);
	}

	@AfterClass(alwaysRun=true)
	public void quitTheBrowser() {
		wutil.quitTheBrowsesr(driver);
		Reporter.log("Quitting the browser", true);
	}

	@AfterTest(alwaysRun=true)
	public void closeConfigParallelExe() {
		Reporter.log("configure the parallel execution", true);
	}

	@AfterSuite(alwaysRun=true)
	public void dissconnectTheDB() throws SQLException {
		dbutil.discconectWithDB();
	}

}
