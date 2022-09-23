package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.EditorPage;
import involve.me.pageobject.EditorSideContentMenu;
import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ProjectsPage;
import involve.me.pageobject.SettingsPage;
import involve.me.pageobject.TemplatesPage;
import involve.me.utils.Utils;

public class EditorTests extends BaseTest{
	
	@Test(description = "Use editor to build a survry from scratch")
	void tc_01_buildASurveyFromScratch() {
		
		HomePage hp = new HomePage(driver);
		hp.acceptCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		ProjectsPage pp = new ProjectsPage(driver);
		pp.addNewWorkspace("New Office");
		pp.createProjectFromScratch("New Office");
		EditorPage ep = new EditorPage(driver);
		ep.chooseProjectType("Answer-based Outcomes");
		ep.editProjectName();
		SettingsPage sp = new SettingsPage(driver);
		sp.renameAndReturnToEditor("Employees");
		ep.dragAndDrop("Rating");
		ep.fillSideMenuRating("Would you recommend this site ", "0-3 - Never \r\n" + "4-8 - Maybe\r\n" + "8-10 - Sure ", "Please answer honestly ");
		ep.changeAndEditOutComeSettings("Score-based Outcomes", "7", "Custom Score Calculation");
		ep.editOutComePages(2, "Heading");
		EditorSideContentMenu escm = new EditorSideContentMenu(driver);
		escm.fillSideMenuHeading("Sorry to hear that :(", "Please tell us what to improve");
		ep.editOutComePages(3, "Heading");
		escm.fillSideMenuHeading("Thanks we are always here for you :)", "Pass it on ");
		Assert.assertEquals(ep.getEndTestText(),"Thanks we are always here for you :)");
	}
	
	@Test(description = "Click desgin preview and try it in tuntime")
	void tc_02_TryProjectInDesginPreview() {
		
		EditorPage ep = new EditorPage(driver);
		ep.tryDesginPreviewRating(2, 7, 10);
		Assert.assertEquals(ep.getEndTestText(),"Thanks we are always here for you :)");
		ep.clickSaveAndExit();
		
	}
	
	@Test(description = "Build and edit a template")
	void tc_03_buildASurveyFromTemplate() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		pp.createProjectFromTemp("My Workspace");
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseCatagory("Industry");
		tp.chooseSubCatagory("Events");
		tp.chooseATemplate("COVID-19 Restaurant Table Reservation");
		EditorPage ep = new EditorPage(driver);
		ep.chooseProjectNameAndType("Restaurant Reservation", "Thank You page");
		ep.changeProjectDesign("C:\\Users\\Taliran-PC\\eclipse-workspace\\Final_Project\\ScreenShots\\Background-Replacement.jpg"
				, "TEXT", "#1C1C1C", "ELEMENTS", "#1C1C1C", "HIGHLIGHTS", "#1C1C1C","Background", "#FFFFFF");
		ep.clickSaveAndExit();
		pp.moveToWorkspace("My Workspace");
		Assert.assertEquals(ep.returnProjectNameIfExist("Restaurant Reservation"), "Restaurant Reservation");
	}
	 
	@Test(description = "Add multiple pages in editor")
	void tc_04_addMultiplePages() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		pp.moveToWorkspace("New Office");
		pp.createProjectFromScratch("New Office");
		EditorPage ep = new EditorPage(driver);
		int before = ep.countPagesInEditor();
		ep.chooseProjectNameAndType("Add pages in editor ", "Thank You page");
		ep.addMultiplePages(3);
		int after = ep.countPagesInEditor();
		Assert.assertEquals(after, before+3);
	}
	
	@Test(description = "Delete multiple pages in editor")
	void tc_05_deleteMultiplePages() {
		
		EditorPage ep = new EditorPage(driver);
		ep.editProjectName();
		SettingsPage sp = new SettingsPage(driver);
		sp.renameAndReturnToEditor("Delete multiple pages");
		int before = ep.countPagesInEditor();
		ep.deleteMultiplePages(3, 1);
		int after = ep.countPagesInEditor();
		Assert.assertEquals(after, before-3);
	}
	
	@Test(description = "Add project timer")
	void tc_06_addProjectTimer() {
	
		EditorPage ep = new EditorPage(driver);
		ep.addProjectTimer("5", "10", "Custom Web Address", "https://google.co.il");
		Assert.assertTrue(ep.assertTimerDisplayed());
	}
	
	/*
	 * activate it only with another user and password - the user and password are in configuration.properties file
	 */
	
//	@Test(description = "Chat with support")
//	void tc_07_helpChatWithUS() {
//		
//		EditorPage ep = new EditorPage(driver);
//		ep.chatWithSupport("Chat with us", "Hello i would like to know the prices");
//		Assert.assertTrue(ep.isTextDisplayed("Hello i would like to know the prices"));
//	}
//	
}
