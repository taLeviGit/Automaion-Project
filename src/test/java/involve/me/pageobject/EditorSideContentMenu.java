package involve.me.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditorSideContentMenu extends ConnectingPage{

	//Side-content menu heading

	@FindBy(css = "#accordeon-headline-text textarea")
	private WebElement accordeonheadlineField;

	@FindBy(css = "#accordeon-subheadline-text textarea")
	private WebElement accordeonsubheadlineField;

	//Side-content multiple choice

	@FindBy(css = "[class='c-question-container']")
	private WebElement dropZoneMultipleChoice;

	@FindBy(css = "#accordeon-question-text textarea")
	private WebElement accordeonmultipleQField;

	@FindBy(css = "#accordeon-question-subtext textarea")
	private WebElement accordeonmultiplesubQField;

	@FindBy(css = "#accordeon-question-hint-text textarea")
	private WebElement accordeonmultipleHintField;

	@FindBy(css = "[class='form-control full-width dropdown-click-behaviour']")
	private WebElement behaviourSelectorField;

	@FindBy(css = "[class='swal-button swal-button--cancel']")
	private WebElement tryForFreeBtn;

	@FindBy(css = "[class='e-answer-text-container'] input")
	private List<WebElement> answersList;

	@FindBy(css = "[class='e-answer-settings-container'] [type='checkbox']")
	private List<WebElement> checkBoxCorrectAnswerList;

	@FindBy(css = "[class='e-answer-logic-jump'] select")
	private List<WebElement> answerLogicList;

	@FindBy(css = "[class='e-page-management']")
	private List<WebElement> pageList;

	@FindBy(css = "#project-title")
	private WebElement renameOutComeTitle;

	@FindBy(css = "[class='swal-button swal-button--confirm']")
	private WebElement confirmOutComeTitle;

	//Side-content menu image+text

	@FindBy(css = ".dropzone.dropzone-content-item.dz-clickable")
	private WebElement uploadPicBtn;

	@FindBy(css = "[class='c-rating-container']")
	private WebElement firstImage;

	@FindBy(css = "[class='note-editable card-block'] p")
	private WebElement textAreaField;

	//Side-content menu rating

	@FindBy(css = "#accordeon-rating-content-title textarea")
	private WebElement accordeonQuestionField;

	@FindBy(css = "#accordeon-rating-content-subtitle textarea")
	private WebElement accordeonQuestionSubField;

	@FindBy(css = "#accordeon-question-hint-text textarea")
	private WebElement accordeonQuestionHintField;

	@FindBy(css = "[class='settings-group  edit-block-checkbox'] [class='btn btn-toggle']")
	private WebElement answerIsRequierdBtn;

	@FindBy(css = "[class='save-close btn btn-primary bg-black']")
	private WebElement saveAndCloseSide;

	@FindBy(css = "[class='content-item has-rating']")
	private WebElement dropZoneRatingBtn;

	@FindBy(css = "[class='c-headline-container']")
	private WebElement dropZoneHeadingBtn;

	//Side-content design 

	@FindBy(css = "#tab-design-settings")
	private WebElement designBtn;

	@FindBy(css = ".btn.v-toggle-project.d-block")
	private WebElement projectDesignBtn;

	@FindBy(css = "#accordeon-project-colors button")
	private WebElement transparentBtn;

	@FindBy(xpath = "/html/body/input[1]")
	private WebElement changeBackPicBtn2;

	@FindBy(css = "#accordeon-project-image-upload [class='btn btn-danger btn-sm']")
	private WebElement removeBack;

	@FindBy(css = "#accordeon-project-colors [class='settings-group']")
	private List<WebElement> colorSectionList;

	//Hidden fields

	@FindBy(css = "[class='select-field-add']")
	private WebElement addhiddenFieldsBtn;

	@FindBy(css = "#number-hidden-fields")
	private WebElement HiddenFieldsNumber;

	//Contact-form

	@FindBy(css = "[class='c-data-collection-container']")
	private WebElement contactFormItem;

	@FindBy(css = "#accordeon-content-texts textarea")
	private WebElement accordeonContactField;

	@FindBy(css = "#accordeon-content-texts-sub textarea")
	private WebElement accordeonContactSubField;

	//Content-Element 

	@FindBy(css = "#content-item-tab")
	private WebElement contentItemBtn;

	//Constructor 

	public EditorSideContentMenu(WebDriver driver) {
		super(driver);
	}

	//Edit the side menu of image+text option
	public void fillSideMenuImageAndText(String text,String filePath) {

		sleepFor(1000);
		waitForElementToAppear(firstImage);
		waitForElementToBeClickable(uploadPicBtn);
		clickOn(uploadPicBtn);
		uploadPicBtn.sendKeys(filePath);
		waitForElementToAppear(textAreaField);
		enterText(textAreaField, text);
		waitForElementToBeClickable(saveAndCloseSide);
		clickOn(saveAndCloseSide);
	}

	//Edit the side menu of Multiple Choice option  
	public void fillSideMenuMultipleChoice(String text , String subText, String hint,String behaviourSelectorValue,
			String asnswer1,String asnswer2,String asnswer3,String asnswer4,String AnswerLogicType,int pageNumber,String newOutComeTitle,int pageNumber1,
			String newOutComeTitle1) {

		sleepFor(1000);
		clickOn(dropZoneMultipleChoice);
		waitForElementToAppear(accordeonmultipleQField);
		moveTO(accordeonmultipleQField);
		deleteTextFromField(accordeonmultipleQField);
		enterText(accordeonmultipleQField , text);
		waitForElementToAppear(accordeonmultiplesubQField);
		enterText(accordeonmultiplesubQField, subText);
		waitForElementToAppear(accordeonmultipleHintField);
		enterText(accordeonmultipleHintField, hint);
		selectByValue(behaviourSelectorField, behaviourSelectorValue);
		waitForElementToAppear(tryForFreeBtn);
		clickOn(tryForFreeBtn);
		List<WebElement> answer = answersList;
		deleteTextFromField(answer.get(0));
		enterText(answer.get(0), asnswer1);
		enterText(answer.get(1), asnswer2);
		enterText(answer.get(2), asnswer3);
		enterText(answer.get(3), asnswer4);
		waitForListToLoad(checkBoxCorrectAnswerList);
		List<WebElement> checkBox = checkBoxCorrectAnswerList;
		clickOn(checkBox.get(0));
		waitForListToLoad(answerLogicList);
		List<WebElement> answerLogic = answerLogicList;
		for (WebElement el : answerLogic) {
			selectByValue(el, AnswerLogicType);
		}
		changePageName(pageNumber, newOutComeTitle);
		changePageName(pageNumber1, newOutComeTitle1);
	}

	//Edit the side menu of Rating option  
	public void fillSideMenuRating(String text , String subText, String hint ) {

		sleepFor(1000);
		clickOn(dropZoneRatingBtn);
		waitForElementToAppear(accordeonQuestionField);
		moveTO(accordeonQuestionField);
		deleteTextFromField(accordeonQuestionField);
		enterText(accordeonQuestionField , text);
		waitForElementToAppear(accordeonQuestionSubField);
		enterText(accordeonQuestionSubField, subText);
		waitForElementToAppear(accordeonQuestionHintField);
		enterText(accordeonQuestionHintField, hint);
		waitForElementToBeClickable(answerIsRequierdBtn);
		clickOn(answerIsRequierdBtn);
		waitForElementToBeClickable(saveAndCloseSide);
		clickOn(saveAndCloseSide);
	}

	//Edit the side menu of Heading option
	public void fillSideMenuHeading(String text,String subText) {

		sleepFor(1000);
		clickOn(dropZoneHeadingBtn);
		waitForElementToAppear(accordeonheadlineField);
		moveTO(accordeonheadlineField);
		deleteTextFromField(accordeonheadlineField);
		enterText(accordeonheadlineField, text);
		waitForElementToAppear(accordeonsubheadlineField);
		deleteTextFromField(accordeonsubheadlineField);
		enterText(accordeonsubheadlineField, subText);
		waitForElementToBeClickable(saveAndCloseSide);
		clickOn(saveAndCloseSide);
		sleepFor(1500);
	}

	//Edit the side menu of Contact-Form option
	public void fillSideMenuContactForm(String text,String subText) {

		waitForElementToAppear(contactFormItem);
		clickOn(contactFormItem);
		waitForElementToAppear(accordeonContactField);
		moveTO(accordeonContactField);
		deleteTextFromField(accordeonContactField);
		enterText(accordeonContactField , text);
		enterText(accordeonContactSubField, subText);
		clickOn(saveAndCloseSide);
	}

	//change project design
	public void changeProjectDesign(String filePath,String fieldName ,String colorHex,
			String fieldName1 ,String colorHex1,String fieldName2 ,String colorHex2,String fieldName3 ,String colorHex3) {

		waitForElementToBeClickable(designBtn);
		clickOn(designBtn);
		waitForElementToBeClickable(projectDesignBtn);
		clickOn(projectDesignBtn);
		waitForElementToBeClickable(transparentBtn);
		clickOn(transparentBtn);
		changeColorfields(fieldName, colorHex);
		changeColorfields(fieldName1, colorHex1);
		changeColorfields(fieldName2, colorHex2);
		changeColorfields(fieldName3, colorHex3);
		waitForElementToBeClickable(removeBack);
		clickOn(removeBack);
		sleepFor(2000);
		changeBackPicBtn2.sendKeys(filePath);
		sleepFor(4500);
		clickOn(saveAndCloseSide);
	}

	//change the color of a filed on side-content 
	public void changeColorfields(String fieldName ,String colorInHex) {

		List<WebElement> colorSection = colorSectionList;
		for(WebElement el : colorSection) {
			WebElement name = el.findElement(By.cssSelector("#accordeon-project-colors [class='settings-group'] label"));
			if(getText(name).equalsIgnoreCase(fieldName)) {
				WebElement field = el.findElement(By.cssSelector("#accordeon-project-colors [class='settings-group'] [class='e-color-preview-box']"));
				clickOn(field);
				sleepFor(500);
				WebElement change = el.findElement(By.cssSelector("#accordeon-project-colors [class='settings-group'] [class='vc-input__input']"));
				clickOn(change);
				sleepFor(500);
				enterText(change, colorInHex);
				sleepFor(500);
				clickOn(field);
				break;
			}
		}
	}

	//Edit background and transparent design
	public void editBackgroundColorAndDesign(String fieldName ,String colorHex) {

		waitForElementToBeClickable(designBtn);
		clickOn(designBtn);
		waitForElementToBeClickable(projectDesignBtn);
		clickOn(projectDesignBtn);
		waitForElementToBeClickable(transparentBtn);
		clickOn(transparentBtn);
		changeColorfields(fieldName, colorHex);
		clickOn(saveAndCloseSide);
		clikcContentElement();

	}

	//Add hidden fields
	public void addHiddenfields(String fieldName) {

		selectByText(addhiddenFieldsBtn, fieldName);
		clickOn(saveAndCloseSide);
	}

	//click content-element
	public void clikcContentElement() {

		waitForElementToAppear(contentItemBtn);
		clickOn(contentItemBtn);
	}

	public void changePageName(int pageNumber,String newOutComeTitle) {
		List<WebElement> pages = pageList ;
		for (int i = 0 ;i<pages.size();i++) {
			moveTO(pages.get(pageNumber-1));
			clickOnce(pages.get(pageNumber-1).findElement(By.cssSelector("[class='e-page-management'] [title='Page properties']")));
		}
		deleteTextFromField(renameOutComeTitle);
		enterText(renameOutComeTitle, newOutComeTitle);
		waitForElementToBeClickable(confirmOutComeTitle);
		clickOn(confirmOutComeTitle);
	}

	//Validations

	public int numOfHiddenFields() {

		String before = getText(HiddenFieldsNumber);
		int num = Integer.parseInt(before);
		return num;
	}
}
