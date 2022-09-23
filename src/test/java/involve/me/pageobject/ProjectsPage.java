package involve.me.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProjectsPage extends ConnectingPage{

	//Frame

	@FindBy(css = "[src='https://www.googletagmanager.com/ns.html?id=GTM-PQRRT9K']")
	private WebElement frame;

	//Buttons

	@FindBy(css = ".flex.text-lg.px-3 svg")
	private WebElement plusBtn;

	@FindBy(css = "#confirm-create-button")
	private WebElement createWorkpaceBtn;

	@FindBy(css = ".relative.dropdown.mr-3 svg")
	private WebElement dropDownBtn;

	@FindBy(css = "[class='block pr-4 pl-4 w-full text-sm leading-loose text-left hover:bg-gray-100 hover:text-gray-800']")
	private WebElement renameBtn;

	@FindBy(xpath = "//*[@class='relative dropdown mr-3']//li/button[@title]")
	private WebElement deleteListBtn;

	@FindBy(css = ".hidden.px-3.py-2 svg")
	private WebElement newProjectBtn;

	@FindBy(xpath = ".//a[contains(@href,'templates')]")
	private WebElement fromTemplateBtn;

	@FindBy(xpath = ".//a[contains(@href,'create?workspace')]")
	private WebElement fromScratchBtn;

	@FindBy(css = "#confirm-delete-button")
	private WebElement confirmBtn;

	@FindBy(css = "[data-intercom-target='dashboard-navigation-templates']")
	private WebElement templatePageBtn;

	@FindBy(css = "[data-intercom-target='dashboard-navigation-coupons']")
	private WebElement couponPageBtn;

	//Fields

	@FindBy(css = "[class='p-6 space-y-6'] input")
	private WebElement workspaceNameField;

	@FindBy(css = "[class='p-6'] input")
	private WebElement renameWSField;

	@FindBy(css = "[class='space-y-2'] input")
	private WebElement deleteWSField;

	@FindBy(css = "[aria-describedby]")
	private WebElement selectfield;

	//Lists

	@FindBy(css = ".mr-3.truncate")
	private List<WebElement> workspaceList;

	@FindBy(css = ".relative.flex.flex-col.pt-2.px-3.pb-2")
	private List<WebElement> projectList;

	@FindBy(css = ".flex.justify-between.font-medium.px-3.py-2")
	private List<WebElement> numberOfProjectList;

	@FindBy(css = "[class='w-full rounded shadow-sm disabled:cursor-not-allowed disabled:bg-gray-100 border-slate-300 focus:border-teal-600 focus:ring-teal-600'] option")
	private List<WebElement> chooseWorkspaceList;

	//Constructor

	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

	//Actions 

	//Add new workspace
	@Step("WorkSpace added: {workspaceName}")
	public void addNewWorkspace(String workspaceName) {

		waitForElementToBeClickable(plusBtn);
		clickOn(plusBtn);
		waitForElementToAppear(workspaceNameField);
		enterText(workspaceNameField, workspaceName);
		waitForElementToBeClickable(createWorkpaceBtn);
		clickOn(createWorkpaceBtn);
		sleepFor(1000);
	}

	//Rename existing workspace
	@Step("WorkSpace renamed: {workspaceName}")
	public void renameWorkspace(String workspaceName) {

		sleepFor(1200);
		moveTO(dropDownBtn);
		waitForElementToBeClickable(dropDownBtn);
		clickOn(dropDownBtn);
		waitForElementToBeClickable(renameBtn);
		clickOn(renameBtn);
		waitForElementToAppear(renameWSField);
		enterText(renameWSField, workspaceName);
		waitForElementToBeClickable(createWorkpaceBtn);
		clickOn(createWorkpaceBtn);
	}

	//Delete workspace
	@Step("WorkSpace deleted: {workspaceName}")
	public void deleteWorkspace(String workspaceName) {

		sleepFor(1200);
		moveTO(dropDownBtn);
		waitForElementToBeClickable(dropDownBtn);
		clickOn(dropDownBtn);
		waitForElementToBeClickable(deleteListBtn);
		clickOn(deleteListBtn);
		waitForElementToAppear(deleteWSField);
		enterText(deleteWSField, workspaceName);
		waitForElementToBeClickable(createWorkpaceBtn);
		clickOn(createWorkpaceBtn);
	}

	// Pick a workspace and create a new project from template 
	@Step("A new Project created from template in workspace: {workspaceName}")
	public void createProjectFromTemp(String workspaceName) {

		sleepFor(1000);
		moveToWorkspace(workspaceName);
		waitForElementToBeClickable(newProjectBtn);
		clickOn(newProjectBtn);
		waitForElementToBeClickable(fromTemplateBtn);
		clickOn(fromTemplateBtn);
	}

	// Pick a workspace and create a new project from scratch
	@Step("A new Project created from scratch in workspace: {chooseWorkspaceToCreate}")
	public void createProjectFromScratch(String chooseWorkspaceToCreate) {

		sleepFor(1000);
		moveToWorkspace(chooseWorkspaceToCreate);
		waitForElementToBeClickable(newProjectBtn);
		clickOn(newProjectBtn);
		waitForElementToBeClickable(fromScratchBtn);
		clickOn(fromScratchBtn);
	}

	//Move to a workspace by it's name 
	@Step("Moving to workspace: {workspaceName}")
	public void moveToWorkspace(String workspaceName) {

		sleepFor(1500);
		waitForListToLoad(workspaceList);
		List<WebElement> workspaces = workspaceList;
		for(WebElement el : workspaces ) {
			if(getText(el).equalsIgnoreCase(workspaceName)){
				clickOn(el);
				break;
			}
		}
		sleepFor(1000);
	}

	// Duplicate existing project
	@Step("{projectName} has been duplicated")
	public void duplicateProject(String projectName) {

		waitForListToLoad(projectList);
		List<WebElement> projects = projectList;
		for(WebElement el : projects ) {
			WebElement name = el.findElement(By.cssSelector("[class='mb-3'] h1"));
			if(getText(name).equalsIgnoreCase(projectName)) {
				WebElement dropDown = el.findElement(By.cssSelector("[class='relative dropdown'] [href='#'] svg"));
				clickOn(dropDown);
				WebElement duplicate = el.findElement(By.xpath(".//a[contains(@href,'duplicate')]"));
				clickOn(duplicate);
				break;
			}
		}
	}

	// Delete existing project
	@Step("{projectName} has been deleted")
	public void deleteProject(String projectName) {

		waitForListToLoad(projectList);
		List<WebElement> projects = projectList;
		for(WebElement el : projects ) {
			WebElement name = el.findElement(By.cssSelector("[class='mb-3'] h1"));
			if(getText(name).equalsIgnoreCase(projectName)) {
				WebElement dropDown = el.findElement(By.cssSelector("[class='relative dropdown'] [href='#'] svg"));
				clickOn(dropDown);
				WebElement delete = el.findElement(By.cssSelector("[class='pl-4 pr-4 w-full block leading-loose text-left text-sm text-red-600 hover:bg-red-600 hover:text-white']"));
				clickOn(delete);
				break;
			}
		}
		waitForElementToBeClickable(confirmBtn);
		clickOn(confirmBtn);
		sleepFor(6000);
		refreshPage();
	}

	// Move a project between workspaces
	@Step("{projectName} moved to workspace")
	public void moveProjectToWorkspcae(String projectName) {

		waitForListToLoad(projectList);
		List<WebElement> projects = projectList;
		for(WebElement el : projects ) {
			WebElement name = el.findElement(By.cssSelector("[class='mb-3'] h1"));
			if(getText(name).equalsIgnoreCase(projectName)) {
				WebElement dropDown = el.findElement(By.cssSelector("[class='relative dropdown'] [href='#'] svg"));
				clickOn(dropDown);
				WebElement moveto = el.findElement(By.cssSelector("[class='pl-4 pr-4 w-full block leading-loose text-left text-sm hover:bg-gray-100 hover:text-gray-800']"));
				clickOn(moveto);
				break;
			}
		}
	}

	//Click edit a project by it's name
	@Step("{projectName} has been edited")
	public void clickEditproject(String projectName) {

		waitForListToLoad(projectList);
		List<WebElement> projects = projectList;
		for(WebElement el : projects ) {
			WebElement name = el.findElement(By.cssSelector("[class='mb-3'] h1"));
			if(getText(name).equalsIgnoreCase(projectName)) {
				WebElement edit = el.findElement(By.cssSelector("[data-icon='pen']"));
				clickOn(edit);
				break;
			}
		}
	}

	//Choose the project name in the drop down (connects to move a project between workspaces)
	public void chooseDropDowneOption(String moveToWorkspaceName) {

		int indexNumber =0;
		moveTO(selectfield);
		clickOnce(selectfield);
		sleepFor(2000);
		waitForListToLoad(chooseWorkspaceList);
		List<WebElement> options = chooseWorkspaceList;
		for(WebElement el :options ) {
			if(getText(el).equalsIgnoreCase(moveToWorkspaceName)) {
				indexNumber = options.indexOf(el);
			}
		}
		moveTO(selectfield);
		clickOnce(selectfield);
		for(int i=0;i<indexNumber;i++) {
			selectfield.sendKeys(Keys.ARROW_DOWN);
		}
		selectfield.sendKeys(Keys.ENTER);
		clickOn(createWorkpaceBtn);
	}

	//Click template page 

	public void clickTemplatePage() {

		waitForElementToBeClickable(templatePageBtn);
		clickOn(templatePageBtn);
	}

	//Click template page
	public void clickCouponPage() {

		waitForElementToBeClickable(couponPageBtn);
		clickOn(couponPageBtn);
	}

	//Validations

	//return the name of list if it exist
	public String checkIfWorkspaceExist(String workspaceName) {

		sleepFor(1500);
		String listName = "";
		try {
			waitForListToLoad(workspaceList);
			List<WebElement> workspaces = workspaceList;
			for(WebElement el : workspaces ) {
				if(getText(el).equalsIgnoreCase(workspaceName)){
					listName = getText(el);
				}
			}return listName;
		}catch(Exception e) {
			return "Workspaece do not exist";
		}

	}

	//Return the number of workspace currently exist
	public int countWorkspaces() {

		sleepFor(1500);
		waitForListToLoad(workspaceList);
		List<WebElement> workspaces = workspaceList;
		return workspaces.size();
	}

	//Return the number of projects currently exist 
	public int countProjectsInWorkSpace(String workspaceName) {

		int num = 0;
		sleepFor(1500);
		waitForListToLoad(numberOfProjectList);
		List<WebElement> workspaces = numberOfProjectList;
		for(WebElement el : workspaces) {
			WebElement text = el.findElement(By.cssSelector(".flex.justify-between.font-medium.px-3.py-2 span"));
			if(getText(text).equalsIgnoreCase(workspaceName)) {
				String countAsString = getText(el);
				String onlyNum = countAsString.replaceAll("[^0-9]", ""); 
				num = Integer.parseInt(onlyNum);
			}
		}return num;
	}
}
