package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePopPage {
	
//	declaration
	@FindBy(partialLinkText = "Home")
	private WebElement homeheader;
	
	@FindBy(linkText = "Organizations")
	private WebElement orgbtnTab;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactbtnTab;
	
     @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
     private WebElement admnicon;	
	
	@FindBy(linkText = "Sign Out")
     private WebElement signoutlink;
	
//	intialization
	
	public HomePopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}

	
//	utilization
	
	public String getHomeheader() {
		return homeheader.getText();
	}
	
	public void getOrgbtn() {
		 orgbtnTab.click();
	}

	
	public void getContactbtn() {
		 contactbtnTab.click();;
	}

	public WebElement getAdmintn() {
		return admnicon;
	}

	public void getSignoutbtn() {
		 signoutlink.click();
	}
	

}
