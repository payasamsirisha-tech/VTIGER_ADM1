package POPUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPopPage {
//	declaration
	
	@FindBy(linkText = "vtiger")
	private WebElement vtigerHeader;
	
	@FindBy(name =  "user_name")
  private WebElement usenameTxtfld;
  
  @FindBy(name ="user_password")
  private WebElement passwordTxtfld;
  
  @FindBy(id = "submitButton")
  private WebElement loginbtn;
  
//  intialization
  

  public LoginPopPage(WebDriver driver) {
	  PageFactory.initElements(driver, this);
  }
  
//utilization

  public String getVtigerHeader() {
	return vtigerHeader.getText();
}
 
  
  public void getUsenameTxtfld(String UserName) {
        usenameTxtfld.sendKeys(UserName);
  }

  public void getPasswordTxtfld(String Passsword) {
	 passwordTxtfld.sendKeys(Passsword);
  }
  public void getLoginbtn() {
		 loginbtn.click();
	  }
 

//  Businesslogic
  public void login(String UserName,String Passsword)
  {
      usenameTxtfld.sendKeys(UserName);
      passwordTxtfld.sendKeys(Passsword);
      loginbtn.click();
  }
  
 }


