package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.EditorPage;
import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ProjectsPage;
import involve.me.pageobject.SettingsPage;
import involve.me.pageobject.TemplatesPage;
import involve.me.utils.Utils;

public class ProjectsTests extends BaseTest{
	
	@Test(description = "Create a new workspace")
	void tc_01_createNewWorkspace() {
		
		HomePage hp = new HomePage(driver);
		hp.declineCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		ProjectsPage pp = new ProjectsPage(driver);
		pp.addNewWorkspace("NewProjects");
		Assert.assertEquals(pp.checkIfWorkspaceExist("NewProjects"),"NewProjects");
	}
	
	@Test(description = "Rename a workspace")
	void tc_02_renameWorkspace() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		pp.renameWorkspace("ProjectPage");
		Assert.assertEquals(pp.checkIfWorkspaceExist("ProjectPage"),"ProjectPage");
	}
	
	@Test(description = "Delete a workspace")
	void tc_03_deleteWorkSapace() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.countWorkspaces();
		pp.deleteWorkspace("ProjectPage");
		int after = pp.countWorkspaces();
		Assert.assertEquals(after,(before-1));
	}
	
	@Test(description = "Create a new project from a template")
	void tc_04_newProjectFromTemplate() {
		 
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.countProjectsInWorkSpace("My Workspace");
		pp.createProjectFromTemp("My Workspace");
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseATemplate("Big Five Personality Test");
		EditorPage ep = new EditorPage(driver);
		ep.simpleProjectCreation("Templates","Answer-based Outcomes");
		int after = pp.countProjectsInWorkSpace("My Workspace");
		Assert.assertEquals(after , before+1);
	}
	
	@Test(description = "Create a new project from scratch")
	void tc_05_newProjectScratch() {
	
		ProjectsPage pp = new ProjectsPage(driver);
		pp.addNewWorkspace("ProjectPage");
		pp.sleepFor(1200);
		int before = pp.countProjectsInWorkSpace("ProjectPage");
		pp.createProjectFromScratch("ProjectPage");
		EditorPage ep = new EditorPage(driver);
		ep.simpleProjectCreation("Project From Scratch","Score-based Outcomes");
		int after = pp.countProjectsInWorkSpace("ProjectPage");
		Assert.assertEquals(after , before+1);
	}
	
	@Test(description = "Duplicate existing project")
	void tc_06_duplicateProject() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.countProjectsInWorkSpace("My Workspace");
		pp.moveToWorkspace("My Workspace");
		pp.duplicateProject("Templates");
		EditorPage ep = new EditorPage(driver);
		ep.editProjectName();
		SettingsPage sp = new SettingsPage(driver);
		sp.renameAndReturnToEditor("After Editing");
		ep.clickSaveAndExit();
		int after = pp.countProjectsInWorkSpace("My Workspace");
		Assert.assertEquals(after , before+1);
	}
	
	@Test(description = "Delete existing project")
	void tc_07_deleteProject() {
	
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.countProjectsInWorkSpace("My Workspace");
		pp.deleteProject("After Editing");
		int after = pp.countProjectsInWorkSpace("My Workspace");
		Assert.assertEquals(after , before-1);
	}
	
	@Test(description = "move existing project to another workspace")
	void tc_08_moveProjectWorkspace() {
		
		ProjectsPage pp = new ProjectsPage(driver);
		pp.addNewWorkspace("Moving Project");
		int before = pp.countProjectsInWorkSpace("Moving Project");
		pp.moveToWorkspace("ProjectPage");
		pp.moveProjectToWorkspcae("Project From Scratch");
		pp.chooseDropDowneOption("Moving Project");
		pp.moveToWorkspace("Moving Project");
		int after = pp.countProjectsInWorkSpace("Moving Project");
		Assert.assertEquals(after, before+1);
	}
	
}
