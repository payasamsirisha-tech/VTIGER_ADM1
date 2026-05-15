package Contact_Module;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class contact_orgnametest {
	@Test
	
	public void contactwithorg_test() throws InterruptedException {
		String browser = "Chrome";
		String lastname="qsp";
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
		
		driver.get("http://localhost:8080");
			
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
     	driver.findElement(By.name("user_password")).sendKeys("password");
     	
		driver.findElement(By.id("submitButton")).click();
		
		if (driver.getCurrentUrl().contains("action=index&module=Home")) {
			System.out.println("Navigated to home page");
			
		} else {
			System.out.println("Login Test Fail");

		}
//		identify the contact and click on it
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
		
//		identify plus icon and click
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		identify the last name TF and pass the value
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		
//	identify plus org name and select the ord
		
	driver.findElement(By.xpath("//img[@title='Select' and @Tabindex]"))	.click();	
	

//	identify for parant window
	String parantwindow = driver.getWindowHandle();
	
//		identify the all widows
	
	Set<String> allwindow = driver.getWindowHandles();
	
    for (String all : allwindow) {
    	
		driver.switchTo().window(all);
		
		if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) { 
			
//		identify search 
			
			
//			enter orgname  and enter
			
			
//		
			System.out.println("controle in child page");
			
			driver.findElement(By.xpath("//a[text()='siri']")).click();
			break;
		}
		else {
			System.out.println("controle in parent page");
			
		}
	}
	driver.switchTo().window(parantwindow);
		
	//identify save btn and click

	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//verify contact
	WebElement infoheader = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));

	if (infoheader.getText().contains(lastname)) {
	System.out.println("successfully created contact page");
	} else {
	System.out.println("creating contact fail");

	}
	//identify contact and click

	driver.findElement(By.linkText("Contacts")).click();

	//identify added contact and delete
	driver.findElement(By.xpath("//a[contains(text(),'"+lastname+"') and @title='Contacts']/../..//a[contains(text(),'del')]")).click();

	driver.switchTo().alert().accept();

	//identify the admin and click on this

	driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	Thread.sleep(2000);
	
	//actions class for mouse over and signout
	Actions actobj=new Actions(driver);
	
	actobj.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))).perform();
	}

	}

		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      

   
		
		
		
		
		

