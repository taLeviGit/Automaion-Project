package involve.me.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends ConnectingPage{
	
	//Fields
	
	@FindBy(css = "[name='email']")
	private WebElement emailField;
	
	//Buttons 
	
	@FindBy(css = ".btn-primary")
	private WebElement sendPassBtn;
	
	//Messages
	
	@FindBy(css = ".alert-success")
	private WebElement successMsg;
	
	//Constructor
	
	public ResetPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	//Actions 
	
	//Enter email and click reset password
	public void resetPassword(String email) {
		
		waitForElementToAppear(emailField);
		enterText(emailField, email);
		waitForElementToBeClickable(sendPassBtn);
		clickOn(sendPassBtn);
	}
	
	//Validations 
	
	//Get text of Message and return true|false
	public boolean isSuccessMsgCorrect(String Message) {
		if(getText(successMsg).equalsIgnoreCase(Message)) {
			return true;
		}else {
			return false;
		}
	}
}
