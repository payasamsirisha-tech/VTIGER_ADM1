package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPopPage {
	
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement createorgHeader;
	
	@FindBy(name = "accountname")
	private WebElement orgnameTxtfld;
	
	@FindBy(id = "phone")
	private WebElement orgphnnumTxtfld;
	
	@FindBy(name = "industry")
	private WebElement industrydropdown;
	
	@FindBy(name = "accounttype")
	private WebElement typedropdown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
//	initialization
	public CreateOrgPopPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

//    source-declaration
	public String getCreateorgHeader() {
		return createorgHeader.getText();
	}


	public void getOrgnameTxtfld(String orgname) {
		 orgnameTxtfld.sendKeys(orgname);
	}
	
	public void getOrgphnnumTxtfld(String phnnum) {
		 orgphnnumTxtfld.sendKeys(phnnum);

	}
	public WebElement getIndustrydropdown() {
		return industrydropdown;
	}


	public WebElement getTypedropdown() {
		return typedropdown;
	}


	public void getSavebtn() {
		 savebtn.click();
	}

}
