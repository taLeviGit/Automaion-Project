package involve.me.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ConnectingPage{

	//iFrame
	@FindBy(css="[frameborder='0']")
	private WebElement iframe;

	//Buttons 
	
	@FindBy(css=".dropdown")
	private WebElement prodcutsBtn;

	@FindBy(css=".row.expandable-cta button")
	private WebElement expandBtn;

	@FindBy(css="#features .inner.text-center a button")
	private WebElement seeAllTempBtn;

	@FindBy(css="[class='c-button btn']")
	private WebElement seeYourTempBtn;
	
	@FindBy(css=".other-link.login")
	private WebElement loginBtn;

	//Lists
	@FindBy(css="[class='multi-column-dropdown'] li a")
	private List<WebElement> listsOfProducts;

	@FindBy(css="[class='c-image-answer answer-behaviour square']")
	private List<WebElement> imgBtnlistQ1_Q5;

	@FindBy(tagName="iframe")
	private List<WebElement> iframelist;

	@FindBy(css="[class='answer-radio is-single']")
	private List<WebElement> imgBtnlistQ6;

	@FindBy(css="[class='answer-radio image-answer is-single']")
	private List<WebElement> imgBtnlistQ7;
	
	//Constructor
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//Actions 
	
	//click login
	public void clickLogin() {
		
		waitForElementToBeClickable(loginBtn);
		clickOn(loginBtn);
	}


	//Choose a product from lists of products
	public void ClickAProduct(String productName) {
		
		moveTO(prodcutsBtn);
		waitForElementToBeClickable(expandBtn);
		clickOn(expandBtn);
		sleepFor(1500);
		waitForListToLoad(listsOfProducts);
		List<WebElement> products = listsOfProducts;
		for(WebElement el : products) {
			if(getText(el).equalsIgnoreCase(productName)) {
				clickOn(el);
				break;
			}
		}
	}

	//click "See all templates" 
	public void clickSeeAllTemp() {
		
		sleepFor(1500);
		waitForElementToBeClickable(seeAllTempBtn);
		goToElement(seeAllTempBtn);
		sleepFor(2000);
		clickOn(seeAllTempBtn);
	}

	//Try the free demo
	public void tryFreeDemoQ1_Q7(int q1_pick1OR0,int q2_pick1OR0,int q3_pick1OR0,int q4_pick0TO3,
			int q5_pick1OR0,int q6_pick0TO3,int q7_pick0TO5){

		scrollDown(300);
		sleepFor(600);
		List<WebElement> iframes = iframelist;
		driver.switchTo().frame(iframes.get(0));
		waitForListToLoad(imgBtnlistQ1_Q5);
		clickOn(imgBtnlistQ1_Q5.get(q1_pick1OR0));
		sleepFor(1800);
		clickOn(imgBtnlistQ1_Q5.get(q2_pick1OR0));
		sleepFor(1800);
		clickOn(imgBtnlistQ1_Q5.get(q3_pick1OR0));
		sleepFor(1800);
		clickOn(imgBtnlistQ1_Q5.get(q4_pick0TO3));
		sleepFor(1800);
		clickOn(imgBtnlistQ1_Q5.get(q5_pick1OR0));
		sleepFor(1800);
		clickOn(imgBtnlistQ6.get(q6_pick0TO3));
		sleepFor(1800);
		clickOn(imgBtnlistQ7.get(q7_pick0TO5));
		waitForElementToBeClickable(seeYourTempBtn);
		clickOn(seeYourTempBtn);
	}
}
