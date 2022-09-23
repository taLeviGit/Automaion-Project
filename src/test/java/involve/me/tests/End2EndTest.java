package involve.me.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.EditorPage;
import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ProjectsPage;
import involve.me.pageobject.SettingsPage;
import involve.me.utils.Utils;

public class End2EndTest extends BaseTest{
	
	@Test(description = "Create a single type coupon")
	void tc_01_addNewSingleCoupon() {
		
		HomePage hp = new HomePage(driver);
		hp.declineCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		ProjectsPage pp = new ProjectsPage(driver);
		pp.addNewWorkspace("University");
		//Assert workspace name 
		Assert.assertEquals(pp.checkIfWorkspaceExist("University"),"University");
		pp.createProjectFromScratch("University");
		EditorPage ep = new EditorPage(driver);
		ep.chooseProjectNameAndType("Students", "Score-based Outcomes");
		//Assert project name 
		Assert.assertTrue(ep.checkProjectName("Students"));
		ep.pickUpperNavgtionBtn("Settings");
		SettingsPage sp = new SettingsPage(driver);
		sp.sendEmailtoParticipants();
		//Assert warning massage
		Assert.assertTrue(sp.getWarningMsg("Attention"));
		sp.clickEditProjectBtn();
		ep = new EditorPage(driver);
		int before = ep.numOfHiddenFields();
		ep.clickHiddenFields();
		ep.addHiddenfields("email");
		int after = ep.numOfHiddenFields();
		//Assert number of hidden fields
		Assert.assertEquals(after, before+1);
		ep.editBackgroundColorAndDesign("Background", "#E2F0E3");
		ep.dragAndDrop("Contact Form");
		ep.fillSideMenuContactForm("Are You Brave Enough", "Fill out this form for more details");
		//Assert drag and drop of contact-form
		Assert.assertTrue(ep.checkIfElementDisplayed());
		int beforeAdd = ep.countPagesInEditor();
		ep.addMultiplePages(1);
		int afterAdd = ep.countPagesInEditor();
		//Assert page has been added
		Assert.assertEquals(afterAdd,beforeAdd+1);
		ep.addProjectTimer("10", "01", "Custom Web Address", "https://involve.com");
		//Assert project timer is displayed 
		Assert.assertTrue(ep.assertTimerDisplayed());
		ep.sleepFor(1000);
		ep.dragAndDrop("Multiple Choice");
		ep.fillSideMenuMultipleChoice("Which is the Best Development Methodology For QA Testing", "Please take your time", "It is been used all over the world",
				"jump", "Agile", "Waterfall ", "Spiral", "Iterative development",
				"finish",3, "Wrong", 4, "Correct");
		ep.editOutComeSettings("0.98", "Custom Score Calculation");
		ep.editOutComePages(4, "Heading");
		ep.fillSideMenuHeading("You are Correct", "keep it up");
		ep.editOutComePages(3, "Heading");
		ep.fillSideMenuHeading("Worng answer", "Try again");
		ep.tryDesginPreviewMultiplechoose(2, "testing", "testing", "test@gmail.com", 2, 1, 2);
	}
	
}
