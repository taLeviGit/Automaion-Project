package involve.me.pageobject;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConnectingPage extends BasePage{
	
	//Messages
	@FindBy(css = ".alert.alert-danger")
	private WebElement errorMsg;
		
	//Decline cookies
	@FindBy(css="[role='alert']")
	private WebElement cookies;

	@FindBy(css="#cookiescript_buttons #cookiescript_reject")
	private WebElement declineCookiesBtn;
	
	@FindBy(css="#cookiescript_buttons #cookiescript_accept")
	private WebElement acceptCookiesBtn;
	
	//Constructor
	
	public ConnectingPage(WebDriver driver) {
		super(driver);
	}

	/* Usage - All pages
	 * Waiting for Elements Appear
	 */
	
	public void waitForElementToBeClickable(WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	public void waitForListToLoad(List<WebElement> el) {
		wait.until(ExpectedConditions.visibilityOfAllElements(el));
	}
	
	public void waitForFrameToBeAvb(WebElement el) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(el));
	}
	
	public void waitForElementToAppear(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	
	public void waitForElementToDisappear(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	
	public void waitForAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForRefresh(ExpectedCondition<?> condition) {
		wait.until(ExpectedConditions.refreshed(condition));
	}
	
	/* Usage - EditorPage
	 * Loops 
	 */
	
	public void pickItemInList( List<WebElement> listName,char iteratorS ,int numOfAnswer) {

		List<WebElement> list = listName;

		for( iteratorS = 0 ;iteratorS<list.size();iteratorS++) {
			moveTO(list.get(numOfAnswer));
			clickOnce(list.get(numOfAnswer));
			sleepFor(1650);
		}
	}
	
	/* Usage - All pages
	 * Switch between tabs/windows
	 */

	public void switchWindow(String mainWindow) {

		mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String win : windows) {
			driver.switchTo().window(win);
		}

	}

	/* Usage - All pages
	 * Decline/Accept cookies
	 */
	public void declineCookies() {
		sleepFor(1500);
		clickOn(declineCookiesBtn);
	}
	
	public void acceptCookies() {
		sleepFor(1500);
		clickOn(acceptCookiesBtn);
	}

	//Validations

	/* Usage - LoginPage & ResetPasswordPage
	 * Validate if the message is correct and return true|false
	 */
	
	public boolean isMsgCorrect(String errorMessage) {
		if(getText(errorMsg).equalsIgnoreCase(errorMessage)) {
			return true;
		}else {
			return false;
		}
	}
}
