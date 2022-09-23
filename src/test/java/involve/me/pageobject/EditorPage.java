package involve.me.pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class EditorPage extends EditorSideContentMenu{

	//Asserts

	@FindBy(css = "[class='pwt-content truncate']")
	private WebElement timerExist;

	@FindBy(css = "[class='intercom-block-paragraph e16pl8n50 intercom-1l8ns5d']")
	private List<WebElement> chatBoxText;

	@FindBy(css = "[class='nav-link project-name']")
	private WebElement editorProjectName;
	
	@FindBy(css = "[class='c-data-collection-container']")
	private WebElement contactFormItem;

	//I-frame

	@FindBy(css = "[name='intercom-messenger-frame']")
	private WebElement chatFrame;

	//Fields

	@FindBy(css = "[class='swal-content__input']")
	private WebElement newProjectField;

	@FindBy(css = ".nav-link.project-name")
	private WebElement projectNameField;

	@FindBy(css = "[placeholder='CHANGE']")
	private WebElement confirmChangeField;

	@FindBy(css = "[class='timer-group'] [maxlength='4']")
	private WebElement minuteField;

	@FindBy(css = "[class='timer-group'] [maxlength='2']")
	private WebElement secondField;

	@FindBy(css = "#accordeon-button-link input")
	private WebElement timerTextField;

	@FindBy(css = "[name='message']")
	private WebElement chatMessageField;

	@FindBy(css = "[class='e-headline is-shrinkable'] span")
	private WebElement validationHeaderText;
	
	@FindBy(css = "[placeholder='First Name']")
	private WebElement fisrtNameField;
	
	@FindBy(css = "[placeholder='Last Name']")
	private WebElement lastNameField;
	
	@FindBy(css = "[placeholder='Email*']")
	private WebElement emailNameField;

	//Lists

	@FindBy(css = "[class='col-selection']")
	private List<WebElement> projectTypeList;

	@FindBy(css = "[class='v-title']")
	private List<WebElement> imageList;

	@FindBy(xpath = "//*[@type='number']")
	private List<WebElement> numbersList;

	@FindBy(css = "[class='e-page-management']")
	private List<WebElement> pageList;

	@FindBy(css = "[class='rating-item']")
	private List<WebElement> starsList;

	@FindBy(xpath = "//*[@class='add-page-container']//i")
	private List<WebElement> addPageButtonList;

	@FindBy(css = "[class='button js-remove-page']")
	private List<WebElement> deletePageButtonList;

	@FindBy(css = "[class='dropdown-item']")
	private List<WebElement> helpDropDownList;

	@FindBy(css = ".relative.flex.flex-col.pt-2.px-3.pb-2")
	private List<WebElement> projectList;

	@FindBy(css = "[class='nav-item'] a")
	private List<WebElement> upperNavigationBtnsList;
	
	@FindBy(css = "[class='el-checkbox__inner']")
	private List<WebElement> checkBoxRuntimeBtn;
	
	@FindBy(css = "[class='c-answer answer-behaviour btn btn-secondary']")
	private List<WebElement> answerListRuntime;

	//Buttons

	@FindBy(css = ".e-close.nav-link")
	private WebElement saveAndExitBtn;

	@FindBy(css = ".nav-item svg")
	private WebElement editProjectNameField;

	@FindBy(css = "#Layer_1")
	private WebElement outcomeSettingsBtn;

	@FindBy(css = "[title='Outcome Range Settings']")
	private WebElement outcomeSettingsBtnAfterChange;

	@FindBy(css = "[class='button']")
	private WebElement changeProjectTypeBtn;

	@FindBy(css = "[class='fal fa-exchange']")
	private WebElement confirmChangeBtn;

	@FindBy(css = "[type='submit']")
	private WebElement submitBtn;

	@FindBy(css = "[class='first-row']")
	private WebElement formulaBtn;

	@FindBy(css = ".btn.btn-secondary.preview-btn")
	private WebElement desginPreviewBtn;

	@FindBy(css = ".c-button-group-button.e-try")
	private WebElement tryItBtn;

	@FindBy(xpath = "//*[@class='add-page-container']//i")
	private WebElement addPageBtn;

	@FindBy(css = "[class='swal-button swal-button--confirm swal-button--danger']")
	private WebElement confirmDeleteBtn;

	@FindBy(css = "#projectTimerNavLink")
	private WebElement projectTimerBtn;

	@FindBy(css = "[class='settings-group  edit-block-checkbox'] button")
	private WebElement projectTimerHandleBtn;

	@FindBy(css = "[class='swal-button swal-button--cancel']")
	private WebElement confirmTimerBtn;

	@FindBy(css = "[class='pwt-content truncate']")
	private WebElement timerBtn;

	@FindBy(css = "[class='save-close btn btn-primary bg-black']")
	private WebElement saveAndCloseSide;

	@FindBy(css = "[class='dropdown-toggle text-right nav-link']")
	private WebElement helpBtn;

	@FindBy(css = ".px-6.py-2.leading-5")
	private WebElement startChatBtn;

	@FindBy(css = ".h-10.px-6")
	private WebElement openChatBtn;

	@FindBy(css = "[class='intercom-18biwo e1prtmiu1'] svg")
	private WebElement sendUsMessageBtn;

	@FindBy(css = "[class='intercom-composer-send-button intercom-18z1pbu e50zdj18'] svg")
	private WebElement sendMessageBtn;

	@FindBy(css = "[class='nav-link e-hidden-fields']")
	private WebElement hiddenFieldsBtn;
	
	@FindBy(css = "[class='c-button btn']")
	private WebElement confirmRuntimeBtn;

	//Select 

	@FindBy(css = "[class='points-source-select']")
	private WebElement selectcalculationType;

	@FindBy(xpath = "//*[@value=\"finish\"]/parent::select")
	private WebElement selectTimerOutcome;


	//Constructor

	public EditorPage(WebDriver driver) {
		super(driver);
	}

	//Actions 

	//Choose element to drag and drop by name
	public void dragAndDrop(String imageName) {

		try {
			List<WebElement> listOfImages = imageList;
			for(WebElement el : listOfImages ) {
				WebElement text = el.findElement(By.cssSelector("[class='v-title'] p"));
				if(getText(text).equalsIgnoreCase(imageName)){
					WebElement drag = el.findElement(By.cssSelector("[class='v-title'] [class='svg replaced-svg']"));
					action.moveToElement(drag);
					action.keyDown(Keys.LEFT_CONTROL).click().build().perform();
					action.keyUp(Keys.LEFT_CONTROL).build().perform();
					break;
				}
			}
		}catch (Exception e) {
			System.out.println("Did not find the requested item");
		}

	}

	//Change outcome Type and Settings
	public void changeAndEditOutComeSettings(String TypeName,String upTonumberOutcome1,String calculationType) {

		sleepFor(1000);
		waitForElementToBeClickable(outcomeSettingsBtn);
		clickOn(outcomeSettingsBtn);
		waitForElementToBeClickable(changeProjectTypeBtn);
		clickOn(changeProjectTypeBtn);
		chooseProjectType(TypeName);
		waitForElementToAppear(confirmChangeField);
		enterText(confirmChangeField ,"CHANGE");
		waitForElementToBeClickable(confirmChangeBtn);
		clickOn(confirmChangeBtn);
		sleepFor(3500);
		waitForElementToBeClickable(outcomeSettingsBtnAfterChange);
		clickOn(outcomeSettingsBtnAfterChange);
		waitForListToLoad(numbersList);
		List<WebElement> numbers = numbersList;
		enterText(numbers.get(0), upTonumberOutcome1);
		selectByText(selectcalculationType,calculationType);
		clickOn(submitBtn);
	}
	
	public void editOutComeSettings(String upTonumberOutcome1,String calculationType) {

		waitForElementToBeClickable(outcomeSettingsBtnAfterChange);
		clickOn(outcomeSettingsBtnAfterChange);

		List<WebElement> numbers = numbersList;
		for(int i=0;i<numbers.size();i++) {
			enterText(numbers.get(0), upTonumberOutcome1);
		}
		selectByText(selectcalculationType,calculationType);
		waitForElementToBeClickable(submitBtn);
		clickOn(submitBtn);
	}

	//Simple project creation - enter  project name , choose type of project , then click save&exit
	public void simpleProjectCreation(String projectName,String TypeName){

		sleepFor(800);
		waitForElementToAppear(newProjectField);
		enterText(newProjectField, projectName);
		chooseProjectType(TypeName);
		sleepFor(1000);
		clickOn(saveAndExitBtn);
	}

	//Simple project creation - enter  project name , choose type of project , then click save&exit
	public void chooseProjectNameAndType(String projectName,String TypeName){

		sleepFor(800);
		waitForElementToAppear(newProjectField);
		enterText(newProjectField, projectName);
		chooseProjectType(TypeName);
	}

	//choose a project type by name (3 options)
	public void chooseProjectType(String TypeName) {

		waitForListToLoad(projectTypeList);
		List<WebElement> projectType = projectTypeList;
		for(WebElement el : projectType ) {
			WebElement projectTypeName = el.findElement(By.cssSelector("[class='project-type-select'] h4"));
			clickOn(projectTypeName);
			if(getText(projectTypeName).equalsIgnoreCase(TypeName)){
				WebElement startEdit = el.findElement(By.cssSelector("[class='modal-btn-start swal-button']"));
				clickOn(startEdit);
				break;
			}
		}
	}

	//Run a demo of the design "Rating" in runtime
	public void tryDesginPreviewRating(int tabNumber ,int firstAnswer ,int secondAnswer ) {

		waitForElementToBeClickable(desginPreviewBtn);
		clickOn(desginPreviewBtn);
		waitForElementToAppear(tryItBtn);
		clickOn(tryItBtn);
		String main = driver.getWindowHandle();
		moveToAnotherWindowByIndex(tabNumber);
		sleepFor(2500);
		pickAnswerInDemoRating(firstAnswer);
		sleepFor(2500);
		refreshPage();
		sleepFor(2500);
		pickAnswerInDemoRating(secondAnswer);
		closeOpendWindow();
		driver.switchTo().window(main);
		sleepFor(2500);
	}
	
	//Run a demo of the design "Multiple choose" in runtime
	public void tryDesginPreviewMultiplechoose(int tabNumber ,String firstName,String lastName,String email,int checkBoxNum,int numOfAnswer1,int numOfAnswer2) {

		waitForElementToBeClickable(desginPreviewBtn);
		clickOn(desginPreviewBtn);
		waitForElementToAppear(tryItBtn);
		clickOn(tryItBtn);
		String main = driver.getWindowHandle();
		moveToAnotherWindowByIndex(tabNumber);
		sleepFor(800);
		enterText(fisrtNameField, firstName);
		sleepFor(800);
		enterText(lastNameField, lastName);
		sleepFor(800);
		enterText(emailNameField, email);
		sleepFor(800);
		List<WebElement> checkbox = checkBoxRuntimeBtn;
		clickOn(checkbox.get(checkBoxNum-1));
		waitForElementToBeClickable(confirmRuntimeBtn);
		clickOn(confirmRuntimeBtn);
		pickAnswerInDemoMultipleChoose(numOfAnswer1);
		sleepFor(2500);
		refreshPage();
		sleepFor(800);
		enterText(fisrtNameField, firstName);
		sleepFor(800);
		enterText(lastNameField, lastName);
		sleepFor(800);
		enterText(emailNameField, email);
		sleepFor(800);
		List<WebElement> checkbox1 = checkBoxRuntimeBtn;
		clickOn(checkbox1.get(checkBoxNum-1));
		waitForElementToBeClickable(confirmRuntimeBtn);
		clickOn(confirmRuntimeBtn);
		pickAnswerInDemoMultipleChoose(numOfAnswer2);
		sleepFor(2500);
		closeOpendWindow();
		driver.switchTo().window(main);
		sleepFor(2000);
	}

	//pick answer between 1-10 in runtime "Rating" 
	public void pickAnswerInDemoRating(int numOfAnswer) {
		
		waitForListToLoad(starsList);
		List<WebElement> star = starsList;
		clickOn(star.get(numOfAnswer-1));
	}
	
	//pick answer between 1-10 in runtime "Multiple choose" 
		public void pickAnswerInDemoMultipleChoose(int numOfAnswer) {
			sleepFor(800);
			waitForListToLoad(answerListRuntime);
			List<WebElement> answer = answerListRuntime;
			clickOn(answer.get(numOfAnswer-1));
		}

	//Edit out-come pages
	public void editOutComePages(int pageNumber ,String imageName) {

		pickOutComePage(pageNumber-1);
		dragAndDrop(imageName);
	}

	//Edit project name in settings (continue in settings page)
	public void editProjectName(){

		sleepFor(800);
		waitForElementToAppear(projectNameField);
		moveTO(projectNameField);
		waitForElementToBeClickable(editProjectNameField);
		clickOn(editProjectNameField);
	}

	//Click save&Exit from project
	public void clickSaveAndExit(){

		waitForElementToAppear(saveAndExitBtn);
		waitForElementToBeClickable(saveAndExitBtn);
		clickOn(saveAndExitBtn);
	}

	//pick outcome page by number 1,2,3...
	public void pickOutComePage(int pageNumber) {

		List<WebElement> pages = pageList ;
		for (int i = 0; i < pages.size(); i++) {
			clickOn(pages.get(pageNumber));
		}
	}

	//Add multiple pages - number of pages can be set by user 
	public void addMultiplePages(int numOfPages) {

		waitForElementToBeClickable(addPageBtn);
		clickOn(addPageBtn);
		sleepFor(800);

		int count = 0;
		List<WebElement> pageList = addPageButtonList;
		for(int j=0;j<pageList.size();j++) {
			if(count < numOfPages-1){
				waitForElementToBeClickable(pageList.get(pageList.size()-1));
				clickOn(pageList.get(pageList.size()-1));
				count++;
				sleepFor(800);
			}
		}	
	}

	//Delete multiple pages - number of pages can be set by user 
	public void deleteMultiplePages(int numOfPages,int numOfOutcomePages) {

		int count = 0;
		List<WebElement> pageListToDelete = pageList;
		for(WebElement el : pageListToDelete) {
			if(count < numOfPages){
				clickOn(pageListToDelete.get(pageListToDelete.size()-numOfOutcomePages-1));
				sleepFor(800);
				WebElement deleteBtn = el.findElement(By.cssSelector("[class='e-page-management'] [title='Delete page']"));
				moveTO(deleteBtn);
				clickOnce(deleteBtn);
				clickOn(confirmDeleteBtn);
				count++;
				sleepFor(800);
			}
		}	
	}

	//Add and edit project timer
	public void addProjectTimer(String minutes ,String seconds,String timerOutcome,String selectText) {

		waitForElementToBeClickable(projectTimerBtn);
		clickOn(projectTimerBtn);
		waitForElementToBeClickable(projectTimerHandleBtn);
		clickOn(projectTimerHandleBtn);
		waitForElementToAppear(confirmTimerBtn);
		clickOn(confirmTimerBtn);
		sleepFor(1500);
		waitForElementToAppear(timerBtn);
		clickOn(timerBtn);
		waitForElementToAppear(minuteField);
		enterText(minuteField, minutes);
		waitForElementToAppear(secondField);
		enterText(secondField, seconds);
		sleepFor(1000);
		selectByText(selectTimerOutcome, timerOutcome);
		enterText(timerTextField, selectText);
		waitForElementToBeClickable(saveAndExitBtn);
		clickOn(saveAndCloseSide);
	}

	//Chat with support 
	public void chatWithSupport(String buttonName,String chatMessage) {

		waitForElementToBeClickable(helpBtn);
		clickOn(helpBtn);
		String main = driver.getWindowHandle();
		List<WebElement> buttons = helpDropDownList;
		for (WebElement el : buttons) {
			if(getText(el).equalsIgnoreCase(buttonName)) {
				clickOn(el);
				break;
			}
		}
		moveToAnotherWindowByIndex(2);
		waitForElementToAppear(startChatBtn);
		clickOn(startChatBtn);
		waitForElementToAppear(openChatBtn);
		clickOn(openChatBtn);
		waitForElementToAppear(chatFrame);
		switchToFrameByElement(chatFrame);
		sleepFor(500);
		goToElement(sendUsMessageBtn);
		sleepFor(500);
		waitForElementToBeClickable(sendUsMessageBtn);
		clickOn(sendUsMessageBtn);
		sleepFor(500);
		enterText(chatMessageField, chatMessage);
		sleepFor(500);
		waitForElementToAppear(sendMessageBtn);
		clickOn(sendMessageBtn);
		sleepFor(2500);
		closeFrame();
		closeOpendWindow();
		driver.switchTo().window(main);
		waitForElementToAppear(saveAndExitBtn);
		clickOn(saveAndExitBtn);
	}
	
	//Pick upper navigation buttons
	public void pickUpperNavgtionBtn(String buttonName) {

		List<WebElement> buttons = upperNavigationBtnsList;
		for (WebElement el : buttons) {
			if(getText(el).equalsIgnoreCase(buttonName)) {
				clickOn(el);
				break;
			}
		}
	}
	
	public void clickHiddenFields() {
		waitForElementToAppear(hiddenFieldsBtn);
		clickOn(hiddenFieldsBtn);
	}

	//Validations 

	//Get the text of End test tc_01 for comprising 
	public String getEndTestText() {

		return getText(validationHeaderText);
	}

	//Return the name of the project if exist
	public String returnProjectNameIfExist(String projectName) {

		sleepFor(1500);
		String project = "";
		try {
			List<WebElement> projects = projectList;
			for(WebElement el : projects) {
				WebElement name = el.findElement(By.cssSelector(".relative.flex.flex-col.pt-2.px-3.pb-2 h1"));
				if(getText(name).equalsIgnoreCase(projectName)){
					project = getText(name);
				}
			}return project;
		}catch(Exception e) {
			return "Project do not exist";
		}

	}

	//Count the number of pages in editor 
	public int countPagesInEditor(){

		List<WebElement> pages = pageList;
		return pages.size();

	}

	//Check if timer element is displayed 
	public boolean assertTimerDisplayed() {
		WebElement timer = timerExist;
		if(timer.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}

	//Check if chat box text appears after test
	public boolean isTextDisplayed(String textSent) {

		sleepFor(3000);
		waitForFrameToBeAvb(chatFrame);
		waitForListToLoad(chatBoxText);
		List<WebElement> textList = chatBoxText;
		for (WebElement el : textList) {
			if(getText(el).equalsIgnoreCase(textSent)) {
				return true;
			}
		}
		return false;
	}

	//Check if project name is correct 
	public boolean checkProjectName(String projectName) {
		if(getText(editorProjectName).equalsIgnoreCase(projectName)) {
			return true;
		}else {
			return false;
		}
	}
	
	//Check if element has been added to editor 
	public boolean checkIfElementDisplayed() {
		
		if(contactFormItem.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
}

