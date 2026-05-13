package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPopPage {
	@FindBy(linkText = "Contacts")
	private WebElement contactheader;
	
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement Contactplusbtn;
	

	
//	intialization
	public ContactPopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//	declaration
	
	public String getContactheader() {
		return contactheader.getText();
	}

	public void getContactplusbtn() {
		 Contactplusbtn.click();;
	}

	
}
