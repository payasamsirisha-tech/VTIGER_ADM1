package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactinfoPopPage {
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement Contactheaderinfo;
	
	@FindBy(id="mouseArea_Last Name")
	private WebElement verifyorgname;
	
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement contactorginfo;   
	
	
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement startdatecontactinfo;
	
	@FindBy(id = "mouseArea_Support End Date")
	private WebElement enddatecontactctinfo;
	
//	declaration
                                                                                                                                                                                           
  public   ContactinfoPopPage(WebDriver driver) {
	  
	  PageFactory.initElements(driver, this);
  }

//utilization
  
  public String getContactheaderinfo() {
       return Contactheaderinfo.getText();
   }

  public String getVerifyconname() {
	return verifyorgname.getText();
}

  public String getContactorginfo() {
	return contactorginfo.getText();
   }

  public String getStartdatecontactinfo() {
	return startdatecontactinfo.getText();
  }

  public String getEnddatecontactctinfo() {
	return enddatecontactctinfo.getText();
  }
 
}

// group
//run
//include
