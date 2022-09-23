package involve.me.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatesPage extends ConnectingPage{

	//Assert

	@FindBy(css="div > div:nth-child(2) > div > span:nth-child(3)")
	WebElement previewEndMsg;
	
	@FindBy(css="div.e-subheadline > div > span")
	WebElement mobilePreviewEndMsg;

	//Fields

	@FindBy(css="[placeholder='First Name']")
	WebElement previewFirstNameField;

	@FindBy(css="[placeholder='Last Name']")
	WebElement previewLastNameField;

	@FindBy(css="[placeholder='Email*']")
	WebElement previewEmailField;

	@FindBy(css="[placeholder='First Name*']")
	WebElement previewFirstNameMobileField;
	
	@FindBy(css="[placeholder='Database*']")
	WebElement dataBasePreviewField;
	
	@FindBy(css="[placeholder='Word Processing*']")
	WebElement wordProcessingPreviewField;
	
	@FindBy(css="[placeholder='Spreadsheets*']")
	WebElement spreadsheetsPreviewField;
	
	@FindBy(css="[placeholder='Presentations*']")
	WebElement presentationsPreviewField;
	
	@FindBy(css="[placeholder='Graphics*']")
	WebElement graphicsPreviewField;
	
	//Buttons 

	@FindBy(css=".c-button.btn")
	WebElement multiTypePreviewBtn;

	@FindBy(css="[class='el-checkbox__inner']")
	WebElement checkBoxBtn;

	@FindBy(css="[title='mobile']")
	WebElement mobileFormBtn;

	//Lists

	@FindBy(css=".overflow-hidden.relative.w-full.bg-white.rounded.shadow.group")
	List<WebElement> templates;

	@FindBy(css=".px-5.py-2")
	List<WebElement> catagoryList;

	@FindBy(css="[class='flex justify-between items-center pl-8 pr-5 hover:bg-teal-50 w-full py-1.5 border-l-[3px] border-l-transparent']")
	List<WebElement> subCatagoryList;

	@FindBy(css=".c-image-answer.answer-behaviour.square")
	List<WebElement> previewImageList;

	@FindBy(css="[class='el-checkbox__inner']")
	List<WebElement> checkBoxList;

	@FindBy(css="[class='rangeslider__handle']")
	List<WebElement> sliderPreviewList;

	//Constructor

	public TemplatesPage(WebDriver driver) {
		super(driver);
	}

	//Actions

	//hover a template and click choose 
	public void chooseATemplate(String templateName) {
		waitForListToLoad(templates);
		List<WebElement> temp = templates;
		for(WebElement el : temp) {
			if(getText(el).equalsIgnoreCase(templateName)){
				clickOn(el);
				break;
			}
		}
	}
	//choose category from a list  
	public void chooseCatagory(String catagoryName) {

		List<WebElement> categories = catagoryList;

		for(WebElement el : categories) {
			WebElement name = el.findElement(By.cssSelector("[class='py-1']"));
			if(getText(name).equalsIgnoreCase(catagoryName)) {
				clickOn(el);
				break;
			}
		}
		sleepFor(1500);
	}

	//choose sub-category from a list  
	public void chooseSubCatagory(String subCatagoryName) {

		List<WebElement> categories = subCatagoryList;

		for(WebElement el : categories) {
			WebElement name = el.findElement(By.cssSelector("[class='text-gray-700']"));
			if(getText(name).equalsIgnoreCase(subCatagoryName)) {
				clickOn(el);
				break;
			}
		}
		sleepFor(1500);
	}

	//choose category . sub-category , and template by name 
	public void clickPreviewAtemplate(String catagoryName,String subCatagoryName,String templateName) {

		chooseCatagory(catagoryName);
		chooseSubCatagory(subCatagoryName);
		List<WebElement> temp = templates;
		for (WebElement el : temp) {
			if(getText(el).equalsIgnoreCase(templateName)) {
				moveTO(el);
				WebElement preview = el.findElement(By.cssSelector(".block.py-2.text-sm.text-gray-600"));
				clickOn(preview);
				break;
			}
		}
	}

	//Run a preview of "Big Five Personality Test"
	public void runPreviewBigFivePTest(int Q1_1_5,int Q2_1_5,int Q3_1_5,int Q4_1_5,int Q5_1_5
			,int Q6_1_5,int Q7_1_5,int Q8_1_5,int Q9_1_5,String firstName,String lastName
			,String email,int checkBoxToAccept_1_Or_2) {

		waitForElementToBeClickable(multiTypePreviewBtn);
		clickOn(multiTypePreviewBtn);
		waitForListToLoad(previewImageList);
		previewAnswer(previewImageList,Q1_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q2_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q3_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q4_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q5_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q6_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q7_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q8_1_5);
		sleepFor(1650);
		previewAnswer(previewImageList,Q9_1_5);
		waitForElementToAppear(previewFirstNameField);
		enterText(previewFirstNameField, firstName);
		enterText(previewLastNameField, lastName);
		enterText(previewEmailField, email);
		previewAnswer(checkBoxList, checkBoxToAccept_1_Or_2);
		waitForElementToBeClickable(multiTypePreviewBtn);
		clickOn(multiTypePreviewBtn);
	}

	public void runPreviewAsMobile(String firstName,String email,int slider1_pixelsToMove,
			int slider2_pixelsToMove,int slider3_pixelsToMove,int slider4_pixelsToMove,
			String dataBaseText,String wordProcessText,String spreadsheetText,String presentaionText,String graphicsText) {

		waitForElementToAppear(mobileFormBtn);
		clickOn(mobileFormBtn);
		waitForElementToAppear(multiTypePreviewBtn);
		clickOn(multiTypePreviewBtn);
		waitForElementToAppear(previewFirstNameMobileField);
		enterText(previewFirstNameMobileField, firstName);
		enterText(previewEmailField, email);
		clickOn(checkBoxBtn);
		clickOn(multiTypePreviewBtn);
		sleepFor(1200);
		waitForListToLoad(sliderPreviewList);
		previewSliderAnswer(sliderPreviewList, 1, slider1_pixelsToMove);
		previewSliderAnswer(sliderPreviewList, 2, slider2_pixelsToMove);
		previewSliderAnswer(sliderPreviewList, 3, slider3_pixelsToMove);
		previewSliderAnswer(sliderPreviewList, 4, slider4_pixelsToMove);
		clickOn(multiTypePreviewBtn);
		waitForElementToAppear(dataBasePreviewField);
		enterText(dataBasePreviewField, dataBaseText);
		enterText(wordProcessingPreviewField, wordProcessText);
		enterText(spreadsheetsPreviewField, spreadsheetText);
		enterText(presentationsPreviewField, presentaionText);
		enterText(graphicsPreviewField, graphicsText);
		clickOn(multiTypePreviewBtn);
	}

	//A loop to pick an answer in preview mode
	public void previewAnswer(List<WebElement> webElementList,int answerNumber) {

		List<WebElement> items = webElementList;
		clickOn(items.get(answerNumber-1));
	}

	public void previewSliderAnswer(List<WebElement> ListName, int sldierNumber,int pixelsToMove) {

		List<WebElement> sliders = ListName;
		action.clickAndHold(sliders.get(sldierNumber-1)).build().perform();
		action.moveByOffset(pixelsToMove, 0).build().perform();
		action.release().build().perform();

	}
	
	//Validations

	//Get text from preview of "Big Five Personality Test" end page 
	public String getPreviewMessage() {
		waitForElementToAppear(previewEndMsg);
		return getText(previewEndMsg);
	}
	
	//Get text from preview as mobile end page
	public String getPreviewMobileMessage() {
		sleepFor(1500);
		waitForElementToAppear(mobilePreviewEndMsg);
		return getText(mobilePreviewEndMsg);
	}
}


