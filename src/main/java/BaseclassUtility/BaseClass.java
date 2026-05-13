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

	@BeforeSuite
	public void connectToDB() throws SQLException {
		dbutil = new DatabaseUtility();
		dbutil.getconnectWithDB();
		Reporter.log("Connected to DB", true);
	}

	@BeforeTest
	public void configParallelExe() {
		Reporter.log("configure the parallel execution", true);
	}

	@BeforeClass
//	@Parameters("browser")
	public void LaunchtheBrowser() throws IOException {
		Reporter.log("Launching the browser", true);

	String browser=putil.fetchdatafromPropFile("browser");

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

	@BeforeMethod
	public void Login() throws IOException {
		String url = putil.fetchdatafromPropFile("url");
		String username = putil.fetchdatafromPropFile("username");
		String password = putil.fetchdatafromPropFile("password");
		String timeouts = putil.fetchdatafromPropFile("timeouts");
		wutil.MaximizeTheWindow(driver);
		wutil.waitforElement_Implicit(driver, timeouts);
		wutil.navigateToAnAppln(driver, url);
		LoginPopPage l = new LoginPopPage(driver);
		l.login(username, password);
		Reporter.log("logged into the application", true);
	}

	@AfterMethod
	public void Logout() {
		HomePopPage home = new HomePopPage(driver);
		wutil.mouseoveranEle_Actions(driver, home.getAdmintn());
		home.getSignoutbtn();
		Reporter.log("logged out into the application", true);
	}

	@AfterClass
	public void quitTheBrowser() {
		wutil.quitTheBrowsesr(driver);
		Reporter.log("Quitting the browser", true);
	}

	@AfterTest
	public void closeConfigParallelExe() {
		Reporter.log("configure the parallel execution", true);
	}

	@AfterSuite
	public void dissconnectTheDB() throws SQLException {
		dbutil.discconectWithDB();
	}

}
