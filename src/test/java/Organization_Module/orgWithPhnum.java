package Organization_Module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class orgWithPhnum{
@Test
	public void orgnameandPhnnum_test() throws InterruptedException {
		
		String browser="Chrome";
		
		String orgname="Divvi";
		
		String Phnnum="9876789054";
		
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
		
		driver.manage().window().maximize();
		
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("http://localhost:8888/index.php?action=index&module=Home");
			
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
     	driver.findElement(By.name("user_password")).sendKeys("password");
     	
		driver.findElement(By.id("submitButton")).click();
		
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");

		}
//       click on org
		driver.findElement(By.linkText("Organizations")).click();
		
//		click on plus btn
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		
		driver.findElement(By.id("phone")).sendKeys(Phnnum);
		driver.findElement(By.name("button")).click();
		
				
//		verify org info page 
		WebElement infoheader = driver.findElement(By.xpath("//span[contains(text(), 'Organization Information')]"));
		
		if (infoheader.getText().contains(orgname)) {
			System.out.println("successfully created org page");
			
		} else {
			System.out.println("creating org fail");

		}
		
		driver.findElement(By.linkText("Organizations")).click();

//		identify delete and delete the org
		
		driver.findElement(By.xpath("//a[contains(text(),'"+orgname+"')and @title='Organizations']/../..//a[contains(text(),'del')]")).click();
		
        driver.switchTo().alert().accept();

		//identify the admi and click 
		
		
  driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
  Thread.sleep(20000);
  
//  actions class for mouse over and signout
  Actions actobj=new Actions(driver);
          
         actobj.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))).perform();
           
  
		
		driver.quit();
		
		
		
	
		
	}
			
		
		
		
	}


