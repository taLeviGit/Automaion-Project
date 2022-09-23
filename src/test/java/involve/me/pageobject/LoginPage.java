package involve.me.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ConnectingPage{

	//Fields

	@FindBy(css = "[name='email']")
	private WebElement emailField;
	
	@FindBy(css = "[name='password']")
	private WebElement passwordField;

	//Buttons 
	
	@FindBy(css = ".btn-lg")
	private WebElement loginBtn;
	
	@FindBy(css = "[href='https://app.involve.me/password/reset']")
	private WebElement forgotPassBtn;
	
	//Constructor
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	//Actions

	//Attempt to login
	public void login(String email,String password) {
		
		waitForElementToAppear(emailField);
		enterText(emailField, email);
		waitForElementToAppear(passwordField);
		enterText(passwordField, password);
		waitForElementToBeClickable(loginBtn);
		clickOn(loginBtn);
	}
	
	//Click forgot password 
	public void clickForgotPassword() {
		
		waitForElementToBeClickable(forgotPassBtn);
		clickOn(forgotPassBtn);
	}
	
	//Validations
	
	//Return Constraint message from the email field
	public String getLoginConstraintErrorEmail() {
		
		sleepFor(1000);
		waitForElementToBeClickable(emailField);
		String msg = emailField.getAttribute("validationMessage");
		return msg;		
	}
	
	//Return Constraint message from the password field
	public String getLoginConstraintErrorPassword() {
		
		sleepFor(1000);
		waitForElementToBeClickable(passwordField);
		String msg = passwordField.getAttribute("validationMessage");
		return msg;		
	}
	
}
