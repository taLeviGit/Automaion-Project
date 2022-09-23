package involve.me.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends ConnectingPage{
	
	//Assertions
	
	@FindBy(css="div.w-full.space-y-6.col-span-2 > div.block.rounded.mt-4.px-8.py-4.bg-orange-100.text-orange-800")
	private WebElement emailWarningMsg;
	
	//Fields
	
	@FindBy(css="[name='project-name']")
	private WebElement projectNameField;
	
	@FindBy(css="[data-intercom-target='project-settings-edit-btn']")
	private WebElement editProjectBtn;
	
	//Buttons
	
	@FindBy(css="#general-settings button")
	private WebElement updateGenreralSettingsBtn;
	
	@FindBy(xpath="//*[@id=\"email-notifications\"]/div/div[3]/button")
	private WebElement updateEmailSettingsBtn;
	
	@FindBy(css="[name='participant-notifications']")
	private WebElement sendEmailBtn;
	
	//Wait for action to happen
	
	@FindBy(css=".text-green-600.text-sm")
	private WebElement saveSuccessfullyGeneral;
	
	@FindBy(css="[id='email-notifications'] [class='space-y-2']")
	private WebElement saveSuccessfullyEmail;
	
	//Constructor
	
	public SettingsPage(WebDriver driver) {
		super(driver);
	}
	
	//Change the name of the project and return to editor page 
	public void renameAndReturnToEditor(String projectName) {
		
		sleepFor(800);
		enterText(projectNameField, projectName);
		waitForElementToBeClickable(updateGenreralSettingsBtn);
		clickOn(updateGenreralSettingsBtn);
		waitForElementToAppear(saveSuccessfullyGeneral);
		waitForElementToDisappear(saveSuccessfullyGeneral);
		clickOn(editProjectBtn);
	}
	
	//Click send email to participant check-box
	public void sendEmailtoParticipants() {
		
		scrollDown(600);
		waitForElementToAppear(sendEmailBtn);
		clickOn(sendEmailBtn);
		sleepFor(500);
		moveTO(updateEmailSettingsBtn);
		clickOnce(updateEmailSettingsBtn);
		waitForElementToAppear(saveSuccessfullyEmail);
		waitForElementToDisappear(saveSuccessfullyEmail);
	}
	
	//Click edit project button
	public void clickEditProjectBtn() {
		clickOn(editProjectBtn);
	}
	
	//Validations
	
	//Get warning message after clicking send email to participant
	public boolean getWarningMsg(String warningMsg) {
		
		if(getText(emailWarningMsg).contains(warningMsg)) {
			return true;
		}else {
			return false;
		}
	}
	
}
