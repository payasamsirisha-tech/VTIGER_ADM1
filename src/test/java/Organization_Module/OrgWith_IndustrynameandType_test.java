package Organization_Module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class OrgWith_IndustrynameandType_test {
	
	@Test
	public void orgIndustry_test() throws InterruptedException {
		
		String browser = "Chrome";
		String orgname = "chinna";
		
		
//		launch the browser
		
		WebDriver driver=null;
		
		if(browser.equals("Chrome"))
		{
			driver= new ChromeDriver();
		}
		else if (browser.equals("edge")) {
			
			driver=new EdgeDriver();	
		}
		else if (browser.equals("Firefox")) {
			driver=new FirefoxDriver();
			}
		else
			driver=new ChromeDriver();
		
//		maximize
		driver.manage().window().maximize();
		
//		implictly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
//		open browser
		driver.get("http://localhost:8888/index.php?action=index&module=Home");
			
//		identify username T.F and pass the value
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
//		identify password T.F and pass the value
	driver.findElement(By.name("user_password")).sendKeys("password");
		
//		identify login button and click on it
		driver.findElement(By.id("submitButton")).click();
		
//		validate home page
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");

		}
		
//		identify organization and click on it
		
		driver.findElement(By.linkText("Organizations")).click();
		
//		identify plus icon and click on it
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		identify the organzation name and send the value
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		
//		identify the industry dropdown and pass the name
		
	WebElement industry = driver.findElement(By.name("industry"));
		
	      Select selectobj=new Select(industry);
	        selectobj.selectByContainsVisibleText("Healthcare ");
	        
//	        identify the Type Dropdown and pass the value
	        
	      WebElement Type= driver.findElement(By.name("accounttype"));
	      
//	      creating another select class of type dropdown
	      
	      Select selectobj2=new Select(Type);
	      
	      selectobj2.selectByContainsVisibleText("Customer");
	      
//			identify the save button and click on it
			
			driver.findElement(By.name("button")).click();
//		VERIFY WITH INDUSTRY ID 
			
			
			
			
			
			
//			 VERIFY WITH TYOE ID
			
			
//			verify org info page 
			WebElement infheader = driver.findElement(By.xpath("//span[contains(text(), 'Organization Information')]"));
			
			if (infheader.getText().contains(orgname)) {
				System.out.println("successfully created org page");
				
			} else {
				System.out.println("creating org fail");

			}
			
//			identify org and click on it
		
			driver.findElement(By.linkText("Organizations")).click();

//			identify delete and delete the org
			
			driver.findElement(By.xpath("//a[contains(text(),'"+orgname+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();
			
	        driver.switchTo().alert().accept();

			//identify the admi and click 
			
			
	  driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
			
	  Thread.sleep(20000);
//			actions class for mouse over and signout
	  
	          Actions actobj=new Actions(driver);
	           actobj.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))).perform();
	           
	  Thread.sleep(2000);
			
			driver.quit();
	
	}

}
