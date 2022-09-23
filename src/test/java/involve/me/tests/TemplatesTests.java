package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ProjectsPage;
import involve.me.pageobject.TemplatesPage;
import involve.me.utils.Utils;

public class TemplatesTests extends BaseTest{
	
	@Test(description = "Run a template preview")
	void tc_01_runTemplatePreview() {
		
		HomePage hp = new HomePage(driver);
		hp.acceptCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickTemplatePage();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseCatagory("Industry");
		tp.clickPreviewAtemplate("Content Types", "Quiz", "Big Five Personality Test");
		tp.runPreviewBigFivePTest(1, 2, 3, 4, 5, 1, 2, 3, 4, "checking", "auto", "cant@tellyou.com", 1);
		Assert.assertEquals(tp.getPreviewMessage(), "% open");
	}
	
	@Test(description = "use a template from preview end screen")
	void tc_02_useTemplateAfterPreview() {
		
		TemplatesPage tp = new TemplatesPage(driver);
		tp.navBack();
		tp.sleepFor(1500);
		tp.clickPreviewAtemplate("Industry", "Education", "Computer Skills Assessment");
		tp.runPreviewAsMobile("checking", "cant@tellyou.com" , 40, 120, 240, 360, "auto1", "auto2", "auto3", "auto4", "auto5");
		Assert.assertEquals(tp.getPreviewMobileMessage(), "We'll reach out soon.");
	}
	
	

}
