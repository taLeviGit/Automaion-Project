package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ResetPasswordPage;
import involve.me.utils.Utils;

public class ResetPasswordTests extends BaseTest{
	
	@Test (description = "login failed due to incorrect username")
	void tc_01_resetPasswordWrongEmail(){
		
		HomePage hp = new HomePage(driver);
		hp.declineCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		lp.clickForgotPassword();
		ResetPasswordPage rpp = new ResetPasswordPage(driver);
		rpp.resetPassword("Trytoreset@gmail.com");
		Assert.assertTrue(rpp.isMsgCorrect("We can't find a user with that e-mail address."));
		
	}
	
	@Test (description = "Reset password with correct email - with configuration file as data provider ")
	void tc_02_resetPasswordSuccess(){
		
		ResetPasswordPage rpp = new ResetPasswordPage(driver);
		Utils u = new Utils();
		rpp.resetPassword(u.readProperty("email"));
		Assert.assertTrue(rpp.isSuccessMsgCorrect("A reset link has been sent to the email address, if it has been used to register for an account."));
		
	}

}
