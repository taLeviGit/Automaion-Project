package involve.me.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CouponsPage extends ConnectingPage{

	// Buttons 

	@FindBy(css = "[class='flex px-4 md:px-6'] button")
	private WebElement createNewCouponBtn;

	@FindBy(css = ".relative.px-4.py-2.h-10")
	private WebElement createBtn;

	@FindBy(css = "[type='submit']")
	private WebElement dropdownSubmitBtn;

	//Fields

	@FindBy(css = "[name='coupon-list-name']")
	private WebElement couponNameField;

	@FindBy(css = "[name='coupon-code']")
	private WebElement couponCodeField;

	@FindBy(css = "[name='coupon-usage-limit']")
	private WebElement couponLimitField;

	@FindBy(css = "[placeholder='Paste or type codes here']")
	private WebElement couponCodeTextField;

	@FindBy(css = "[name='coupon-platform']")
	private WebElement couponPlatformField;

	@FindBy(css = "[name='coupon-type']")
	private WebElement CouponTypeField;

	//Lists

	@FindBy(css = "[name='coupon-platform'] option")
	private List<WebElement> selectPlatformNameList;

	@FindBy(css = "[name='coupon-type'] option")
	private List<WebElement> selectCouponTypeList;

	@FindBy(css = "[class='hover:bg-gray-50']")
	private List<WebElement> couponList;

	//constructor

	public CouponsPage(WebDriver driver) {
		super(driver);
	}

	//Add single type coupon
	public void addSingleCoupon(String couponName,String platformName,String couponType,String couponCode,String couponLimit) {

		waitForElementToBeClickable(createNewCouponBtn);
		clickOn(createNewCouponBtn);
		waitForElementToAppear(couponNameField);
		enterText(couponNameField, couponName);
		selectCouponPlatformList(platformName);
		selectCouponTypeFromList(couponType);
		waitForElementToAppear(couponCodeField);
		enterText(couponCodeField, couponCode);
		enterText(couponLimitField, couponLimit);
		clickOn(createBtn);
		sleepFor(1200);
	}

	//Add Multiple type coupon
	public void addMultipleCoupon(String couponName,String platformName,String couponType,String couponCode) {

		waitForElementToBeClickable(createNewCouponBtn);
		clickOn(createNewCouponBtn);
		waitForElementToAppear(couponNameField);
		enterText(couponNameField, couponName);
		selectCouponPlatformList(platformName);
		selectCouponTypeFromList(couponType);
		waitForElementToAppear(couponCodeTextField);
		enterText(couponCodeTextField, couponCode);
		clickOn(createBtn);
		sleepFor(1200);
	}

	//Select platform from coupon platform field
	public void selectCouponPlatformList(String platformName) {

		int indexNumber = 0;
		waitForListToLoad(selectPlatformNameList);
		List<WebElement> options = selectPlatformNameList;
		for(WebElement el :options ) {
			if(getText(el).equalsIgnoreCase(platformName)) {
				indexNumber = options.indexOf(el);
			}
		}
		moveTO(couponPlatformField);
		clickOnce(couponPlatformField);
		for(int i=0;i<indexNumber;i++) {
			couponPlatformField.sendKeys(Keys.ARROW_DOWN);
		}
		couponPlatformField.sendKeys(Keys.ENTER);
	}

	//Select type from coupon type field
	public void selectCouponTypeFromList(String couponType) {

		int indexNumber = 0;
		waitForListToLoad(selectCouponTypeList);
		List<WebElement> options = selectCouponTypeList;
		for(WebElement el :options ) {
			if(getText(el).equalsIgnoreCase(couponType)) {
				indexNumber = options.indexOf(el);
			}
		}
		moveTO(CouponTypeField);
		clickOnce(CouponTypeField);
		for(int i=0;i<indexNumber;i++) {
			CouponTypeField.sendKeys(Keys.ARROW_DOWN);
		}
		CouponTypeField.sendKeys(Keys.ENTER);
	}
	
	//Delete a coupon
	public void deleteCoupon(String couponName,String delete_Increase_AddCoupon) {
		waitForListToLoad(couponList);
		List<WebElement> options = couponList;
		for (WebElement el : options) {
			WebElement name = el.findElement(By.cssSelector("[class='hover:bg-gray-50'] [class='px-6 py-2 text-sm font-medium text-gray-900 whitespace-nowrap']"));
			if(getText(name).equalsIgnoreCase(couponName)) {
				WebElement dropDown = el.findElement(By.cssSelector("[class='relative dropdown'] svg"));
				waitForElementToBeClickable(dropDown);
				clickOn(dropDown);
				List<WebElement> deleteOrIncrease = el.findElements(By.xpath("//*[@class=\"relative dropdown\"]// ul // button"));
				for (WebElement webElement : deleteOrIncrease) {
					if(getText(webElement).equalsIgnoreCase(delete_Increase_AddCoupon)) {
						clickOn(webElement);
						break;

					}

				}
			}
		}
		clickOn(dropdownSubmitBtn);
	}
	
	//increarsLimit Or AddCodes for coupon 
	public void increarsLimitOrAddCodes(String couponName,String increase_addCodes,String addCouponText_NumberofcouponsToAdd) {
		waitForListToLoad(couponList);
		List<WebElement> options = couponList;
		for (WebElement el : options) {
			WebElement name = el.findElement(By.cssSelector("[class='hover:bg-gray-50'] [class='px-6 py-2 text-sm font-medium text-gray-900 whitespace-nowrap']"));
			if(getText(name).equalsIgnoreCase(couponName)) {
				WebElement dropDown = el.findElement(By.cssSelector("[class='relative dropdown'] svg"));
				waitForElementToBeClickable(dropDown);
				clickOn(dropDown);
				List<WebElement> deleteOrIncrease = el.findElements(By.xpath("//*[@class=\"relative dropdown\"]// ul // button"));
				for (WebElement webElement : deleteOrIncrease) {
					if(getText(webElement).equalsIgnoreCase("Increase Limit")) {
						clickOn(webElement);
						waitForElementToAppear(couponLimitField);
						clickOn(couponLimitField);
						int num = Integer.parseInt(addCouponText_NumberofcouponsToAdd);
						for(int i =0;i<num-1;i++) {
							couponLimitField.sendKeys(Keys.ARROW_UP);
						}
						clickOn(createBtn);
					}
					else if(getText(webElement).equalsIgnoreCase("Add codes")) {
						clickOn(webElement);
						enterText(couponCodeTextField, addCouponText_NumberofcouponsToAdd);
						clickOn(createBtn);
					}
				}
				break;
			}
		}
	}
	
	//Validations 
	
	//Count the number of coupons
	public int countCoupons() {
		
		sleepFor(1200);
		List<WebElement> options = couponList;
		return options.size();
	}
	
	//Count the number of coupons uses
	public int countCouponUses(String couponName) {
		
		sleepFor(1200);
		int num = 0;
		List<WebElement> options = couponList;
		for (WebElement el : options) {
			WebElement name = el.findElement(By.cssSelector("[class='hover:bg-gray-50'] [class='px-6 py-2 text-sm font-medium text-gray-900 whitespace-nowrap']"));
			if(getText(name).equalsIgnoreCase(couponName)) {
				WebElement uses = el.findElement(By.cssSelector("[class='px-6 py-2 text-sm text-gray-600 whitespace-nowrap']"));
				String before = getText(uses);
				String onlyNum = before.replaceAll("/", "");
				onlyNum.replaceFirst("^0+(?!$)", "");
				num = Integer.parseInt(onlyNum);
			}
		}
		return num;
	}
}
