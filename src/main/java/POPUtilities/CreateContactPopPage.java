package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPopPage {
	
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement createconheader;
	
	@FindBy(name = "lastname")
	private WebElement Lastnametxtfld;
	
	@FindBy(xpath ="//img[@alt='Select' and @tabindex]")
	private WebElement orgcontactplusicon;
	
	@FindBy(id = "jscal_field_support_end_date")
	private WebElement supportenddatetxtfld;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement supportstartdatetxtfld;
	
	@FindBy(id = "search_txt")
	private WebElement orgsearchTF;
	
	@FindBy(name = "search")
	private WebElement orgsearhbtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement contactsavebtn;
	
	
	public CreateContactPopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//   utilization
	
	public void getLastnametxtfld(String contactname) {
		Lastnametxtfld.sendKeys(contactname);
	}


	public void getorgcontactplusicon() {
		 orgcontactplusicon.click();
	}


	public void getSupportenddatetxtfld(String enddate) {
		 supportenddatetxtfld.clear();
		 supportenddatetxtfld.sendKeys(enddate);
	}


	public void getSupportstartdatetxtfld(String startdate) {
		 supportstartdatetxtfld.clear();
		 supportstartdatetxtfld.sendKeys(startdate);
	}


	public String getCreateconheader() {
		return createconheader.getText();
	}


	public void getOrgsearchTF(String orgname) {
		 orgsearchTF.sendKeys(orgname);
	}


	public void getOrgsearhbtn() {
		 orgsearhbtn.click();
	}


	public void getContactsavebtn() {
		 contactsavebtn.click();
	}
	
	

}
