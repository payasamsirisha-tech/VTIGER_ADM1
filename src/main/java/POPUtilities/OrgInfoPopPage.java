package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPopPage {
//	declaration
	
	@FindBy(xpath = "//span[contains(text(),'Organization Information')]")
	private WebElement orginfoheader;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement verifyOrgname;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement verifyorgphninfo;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement verifyindustryinfo;
	
	
	@FindBy(id = "dtlview_Type")
	private WebElement verifytypeinfo;

//	   initialization

	public OrgInfoPopPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//     utilization
	
      public String getOrginfoheader() {
		return orginfoheader.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifyorgphninfo() {
		return verifyorgphninfo.getText();
	}

	public String getVerifyindustryinfo() {
		return verifyindustryinfo.getText();
	}

	public String getVerifytypeinfo() {
		return verifytypeinfo.getText();
	}

	}
	
	

