package POPpages_GenericUtility;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Lakshmi
 * This methods are using to fetch all dreiver methods using Webutility
 */
public class WebDriverUtility {
/**@Author Lakshmi
 * This method is used to navigate the url
 * @param driver
 * @param url
 */
	/** 
	 * This method is used to navigate the application
	 * @param driver
	 * @param url
	 */
	public void navigateToAnAppln(WebDriver driver,String url) {
		driver.get(url);
	}
	
	/**
	 * This method is used to fetch the title
	 * @param driver
	 * @return
	 */
	public String fetchTheTitle(WebDriver driver) {
		return driver.getTitle();
	}
	/**
	 * This method is used to fetch the current url
	 * 
	 * @param driver
	 * @return
	 */
	
	public String fetchTheUrl(WebDriver driver) {
		return driver.getCurrentUrl();
		
	}
	
	/**
	 * This method is used to fetch the source code of the url
	 * @param driver
	 * @return
	 */
	public String fetchThePageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	/**
	 * This method is used to close the url
	 * 
	 * @param driver
	 */
	public void closeTheBrowser(WebDriver driver) {
		  driver.close();
	}
	
	/**
	 * This method is used to quit the browser
	 * @param driver
	 */
	public void quitTheBrowsesr(WebDriver driver) {
		 driver.quit();
		
	}
	
	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
	public void MaximizeTheWindow(WebDriver driver) {
		 driver.manage().window().maximize();
	}
	
	/** 
	 * This method is used to minimize the window
	 * 
	 * @param driver
	 */
	public void MinimizeTheWindow(WebDriver driver) {
		 driver.manage().window().minimize();
	}
	/**
	 * This method is used to fullscreen the window
	 * 
	 * @param driver
	 */
	public void fullTheWindow(WebDriver driver) {
		 driver.manage().window().fullscreen();
        }
	
	/**
	 * This method is used to set the size of the window
	 * @param driver
	 * @param width
	 * @param height
	 */
	public void SetWindowSize(WebDriver driver,int width,int height) {
		 driver.manage().window().setSize(new Dimension(width, height));
	}
	
	
	/**
	 * This method is used to set the position of the window
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void SetWindowPosition(WebDriver driver,int x,int y) {
		 driver.manage().window().setPosition(new Point(x, y));
	  }
	
	/**
	 * This method is used to get the window size
	 * @param driver
	 * @return
	 */
	public Dimension getWindowSize(WebDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		return dim;
	}
	
	/**
	 * This method is used to get the position of the window
	 * @param driver
	 * @return
	 */
	public Point getWindowPosition(WebDriver driver) {
	 Point P = driver.manage().window().getPosition();
		return P;
	}
	
/**
 * This method is used to navigate the previous window(back)
 * @param driver
 */
	public void NavigateToPreviou_WebPage(WebDriver driver) {
		 driver.navigate().back();
	}
	
	/**
	 * This method is used to forward the window
	 * @param driver
	 */
	public void NavigateToNext_WebPage(WebDriver driver) {
		 driver.navigate().forward();
	}
	
	/**
	 * This method is used to refresh the window
	 * @param driver
	 */
	public void Refresh_WebPage(WebDriver driver) {
		 driver.navigate().refresh();
	}
	
	/**
	 * This method is used to navigate the window using tostringurl
	 * @param driver
	 * @param url
	 */
	public void navigateToApp_toStringUrl(WebDriver driver,String url) {
		 driver.navigate().to(url);
	}
	
	/**
	 * This method is used to navigate the url using tourl
	 * @param driver
	 * @param url
	 * @throws MalformedURLException
	 */
	public void navigateToApp_toURL(WebDriver driver,String url) throws MalformedURLException{
		 driver.navigate().to(new URL(url));
	}
	
	
	/**
	 * This method is used to get the windowhandle
	 * @param driver
	 * @return
	 */
	public String fetchWindowID(WebDriver driver) {
		String win = driver.getWindowHandle();
		return win;
	}
	
	/**
	 * This method is used to fetch all window handles
	 * @param driver
	 * @return
	 */
	public Set<String> fetchAllWindowID(WebDriver driver) {
		Set<String> wins = driver.getWindowHandles();
		return wins;
	}
	
	/**
	 * This methos is used to wait for element(Implicitwait)
	 * @param driver
	 * @param time
	 */
	public void waitforElement_Implicit(WebDriver driver,String time) {
	    long t=Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));
	}
	
	
	/**
	 * This method is used to wait for element to be visible(explicitwait)
	 * @param driver
	 * @param time
	 * @param ele
	 */
	public void waitforElementVisible(WebDriver driver,String time,WebElement ele) {
          long t=Long.parseLong(time);
	      WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(t));
		 wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	/**
	 * This method is used to wait for element to be clickable(explicitwait)
	 * @param driver
	 * @param time
	 * @param ele
	 */
	public void waitforElementToBeClickable(WebDriver driver,String time,WebElement ele) {
        long t=Long.parseLong(time);
	      WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(t));
		 wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	/**
	 * This method is used to wait for element to be visible(explicitwait)
	 * @param driver
	 * @param time
	 * @param title
	 */
	  public void waitforTitleTobevisible(WebDriver driver,String time,String title) {
        long t=Long.parseLong(time);
	      WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(t));
		 wait.until(ExpectedConditions.titleContains(title));
	}
	  
	  /**
	   * This method is used to select dropdown using index
	   * @param dropdown
	   * @param index
	   */
	  public void selectDDByindex(WebElement dropdown,int index) {
		  Select s=new Select(dropdown);
		  s.selectByIndex(0);
	 }
	  
	  /**
	   * This method is used to select the dropdown using value
	   * @param dropdown
	   * @param value
	   */
	  
	  public void selectDDByvalue(WebElement dropdown,String value) {
		  Select s=new Select(dropdown);
		  s.selectByValue(value);
	 }
	 
	  /**
	   * This method is used to select the dropdown using text visible
	   * @param dropdown
	   * @param text
	   */
	  public void selectDDByvisibleText(WebElement dropdown,String text) {
		  Select s=new Select(dropdown);
		  s.selectByVisibleText(text);
	 }
	  
	  /**
	   * This method is used to click an element using action class
	   * @param driver
	   * @param ele
	   */
	  public void clickAnEle_Actions(WebDriver driver,WebElement ele) {
		  Actions act=new Actions(driver);
		  act.click(ele).perform();
	  }
	  
	  /**
	   *  This method is used to move to an element using action class
	   * @param driver
	   * @param ele
	   */
	  public void mouseoveranEle_Actions(WebDriver driver,WebElement ele) {
		  Actions act=new Actions(driver);
		  act.moveToElement(ele).perform();
	  }
	  
	  /**
	   * This method is used to drag and drop an element using action class
	   * @param driver
	   * @param targetele
	   * @param targetLoc
	   */
	  public void DragandDropAnEle_Actions(WebDriver driver,WebElement targetele,WebElement targetLoc ) {
		  Actions act=new Actions(driver);
		  act.dragAndDrop(targetele, targetLoc).perform();
	  }
	  
	  /**
	   * This method is used to accept the popup  using alertpopup
	   * @param driver
	   */
	  public void handleAlertClickonOK(WebDriver driver) {
		  driver.switchTo().alert().accept();
	  }
	  
	  /**
	   * This method is used to dismiss the popup  using alertpopup
	   * @param driver
	   */
	  public void handleAlertClickonCancel(WebDriver driver) {
		  driver.switchTo().alert().dismiss();
	  }
	  
	 /**
	  *  This method is used to get the text from the popup  using alertpopup
	  * @param driver
	  * @return
	  */
	  public String handleAlertFetchTheText(WebDriver driver) {
		  String text = driver.switchTo().alert().getText();
		  return text;
	  }
	  
	  /**
	   * This method is used to enter the text in popup using alertpopup
	   * @param driver
	   * @param text
	   */
	  public void handleAlertEnterText(WebDriver driver,String text) {
		  driver.switchTo().alert().sendKeys(text);
	  }
	  
	  /**
	   * This method is used to switch to child window using exp.url
	   * @param driver
	   * @param expurl
	   */
	  public void SwitchToChildWindow_url(WebDriver driver,String expurl) {
		  Set<String> wids = driver.getWindowHandles();
		  for(String s:wids) {
			   driver.switchTo().window(s);
			  if(driver.getCurrentUrl().contains(expurl)) {
				  break;
			  }
		  }
	    }
	  
	  /**
	   * This method is used to switch to child window using exp.title
	   * @param driver
	   * @param exptitle
	   */
	  public void SwitchToChildWindowtitle(WebDriver driver,String exptitle) {
		  Set<String> wids = driver.getWindowHandles();
		  for(String s:wids) {
			   driver.switchTo().window(s);
			  if(driver.getTitle().contains(exptitle)) {
				  break;
			  }
		  }
	    }
	  
	  /**
	   * This method is used to switch the window to parant window
	   * @param driver
	   * @param id
	   */
	  public void SwitchtoParantwindow(WebDriver driver,String id) {
		driver.switchTo().window(id);  
		  
		  }
	  }
	  

