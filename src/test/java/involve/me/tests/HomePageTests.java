package involve.me.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.HomePage;
import involve.me.pageobject.TemplatesPage;


public class HomePageTests extends BaseTest{
	
	@Test (description = "Show all products available in the site")
	void tc_01_browaeAllProducts() {
		HomePage hp = new HomePage(driver);
		hp.declineCookies();
		hp.ClickAProduct("» Browse all");
		Assert.assertEquals(hp.getUrlAsString(),"https://www.involve.me/use-cases/");
	}
	
	@Test (description = "Try to use a templates without a User ")
	void tc_02_useAtemplateWithoutLogin() {
		HomePage hp = new HomePage(driver);
		hp.navBack();
		hp.clickSeeAllTemp();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseATemplate("Big Five Personality Test");
		Assert.assertEquals(tp.getUrlAsString(),"https://app.involve.me/login");
	}
	
	@Test (description = "Try the free demo in homepage ")
	void tc_03_tryDemoProject(){
		HomePage hp = new HomePage(driver);
		hp.navBack();
		hp.navBack();
		hp.tryFreeDemoQ1_Q7(0, 1, 1, 2, 1, 2, 4);
		hp.switchWindow("main");
		Assert.assertEquals(hp.getUrlAsString(),"https://app.involve.me/templates?type=quiz");
	}
}
