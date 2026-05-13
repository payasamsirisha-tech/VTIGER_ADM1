package POPUtilities;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPopPage {
	
//	Declarization
	
	@FindBy(linkText = "Organizations")
	private WebElement orgheader;
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement orgplusicon;
	
	
	
//	intialization
	public OrgPopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	  }

//	utilization
	
	public String orgheader() {
		return orgheader.getText();
	}

	/**@author Lakshmi
	 * This method is used to click on orgpluicon
	 */
	
   public void orgplusicon() {
		orgplusicon.click();
	}

  
}
