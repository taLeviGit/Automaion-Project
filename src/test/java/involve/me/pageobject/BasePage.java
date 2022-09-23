package involve.me.pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {


	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;

	public BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js=(JavascriptExecutor)driver; 
	}

	//Actions

	public void clickOn(WebElement el) {
		highlightElement(el, "blue", "grey");
		el.click();
	}

	public void enterText(WebElement el,String text) {
		highlightElement(el, "purple", "grey");
		el.clear();
		el.sendKeys(text);
	}
	
	public void sleepFor(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	//Navigation 

	public void navBack() {
		driver.navigate().back();
	}

	public void refreshPage() {
		driver.navigate().refresh();
		
	}

	public void scrollDown(int numofpixels) {
		js.executeScript("window.scrollBy(0," + numofpixels + ")");
	}

	public void goToElement(WebElement el) {
		js.executeScript("arguments[0].scrollIntoView()", el);
	}

	//Alerts

	public void alertSendTextAndClickOk(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}
	public void alertOK() {
		driver.switchTo().alert().accept();
	}

	public void alertDismiss() {
		driver.switchTo().alert().dismiss();
	}
	public String getAlertTxt() {
		return driver.switchTo().alert().getText();
	}

	//Select By * 
	
	public void selectByValue(WebElement el,String value) {
		highlightElement(el, "red", "yellow");
		Select s = new Select(el);
		s.selectByValue(value);
	}

	public void selectByText(WebElement el,String text) {
		highlightElement(el, "red", "yellow");
		Select from = new Select(el);
		sleepFor(500);
		from.selectByVisibleText(text);
		sleepFor(1000);
	}

	public void selectByIndex(WebElement el,int index) {
		highlightElement(el, "red", "yellow");
		Select from = new Select(el);
		from.selectByIndex(index);
	}

	//Mouse Actions

	public void moveTO(WebElement el) {
		highlightElement(el, "yellow", "grey");
		action.moveToElement(el).build().perform();
	}

	public void doubleClick(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.doubleClick(el).build().perform();
	}

	public void clickOnce(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.click(el).build().perform();
	}

	public void cliickAndHold(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.clickAndHold(el).build().perform();
	}

	public void release(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.release(el).build().perform();
	}
	
	public void dragAndDrop(WebElement elementToDrog ,WebElement elementToDrop) {
		highlightElement(elementToDrog, "yellow", "green");
		highlightElement(elementToDrop, "green", "yellow");
		action.dragAndDrop(elementToDrog, elementToDrop).build().perform();
	}
	
	public void dragAndDropByLocation(WebElement elementToDrog ,int xOffset ,int yOffset) {
		highlightElement(elementToDrog, "yellow", "green");
		action.dragAndDropBy(elementToDrog, xOffset, yOffset).build().perform();
	}

	public void clickAndHold(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.clickAndHold(el).build().perform();
	}
	
	public void deleteTextFromField(WebElement el) {
		highlightElement(el, "yellow", "green");
		action.click(el).build().perform();
		action.click(el).build().perform();
		action.click(el).build().perform();
		el.sendKeys(Keys.DELETE);
	}

	/*
	 * Keyboard Actions
	 */
	
	public void actionArrowdown() {
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
	}

	public void actionEnter() {
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void actionDelete() {
		action.sendKeys(Keys.DELETE).build().perform();
	}

	//Highlight

	protected void highlightElement(WebElement element, String color, String backColor) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:" + backColor + ";" + " border: 3px solid " + color + ";" + originalStyle;

		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);
	}

	//I-Frame

	public void switchToFrameByElement(WebElement el) {
		driver.switchTo().frame(el);
	}

	public void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}
	public void closeFrame() {
		driver.switchTo().defaultContent();
	}

	//Validation

	public String getUrlAsString() {
		sleepFor(1000);
		return driver.getCurrentUrl();
	}

	public String getText(WebElement el) {
		highlightElement(el, "orange", "grey");
		return el.getText();
	}

	//Move Between windows

	public void setMainWindow(String windowName){
		windowName = driver.getWindowHandle();
	}

	public void moveToAnotherWindowByIndex(int windowIndex){

		Set<String> list = driver.getWindowHandles();
		List<String> listNumber = new ArrayList<>(list);
		for(int i=0;i<listNumber.size();i++) {
			driver.switchTo().window(listNumber.get(windowIndex-1));
		}
	}
	
	public void closeOpendWindow() {
		driver.close();
	}
	
	public void returnToMainWindow(String nameOfPage) {
		driver.switchTo().window(nameOfPage);
	}

}
