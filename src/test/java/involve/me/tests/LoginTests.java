package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.utils.Excel;
import involve.me.utils.Utils;


public class LoginTests extends BaseTest{

	@Test(description = "Turn to login page")
	void tc_01_trnToLgnPg() {
		HomePage hp = new HomePage(driver);
		hp.acceptCookies();
		hp.clickLogin();
	}

	@Test (dataProvider = "loginWrongUserExcel", description = "login failed due to incorrect username - with excel data provider ")
	void tc_02_unsuccessfulLogin(String email,String password){

		LoginPage lp = new LoginPage(driver);
		lp = new LoginPage(driver);
		lp.login(email,password);
		Assert.assertTrue(lp.isMsgCorrect("These credentials do not match our records."));
	}

	@Test (dataProvider = "loginWrongPasswordExcel", description = "login failed due to incorrect password - with excel data provider ")
	void tc_03_unsuccessfulLogin(String email,String password){

		LoginPage lp = new LoginPage(driver);
		lp.login(email,password);
		Assert.assertTrue(lp.isMsgCorrect("These credentials do not match our records."));
	}

	@Test (dataProvider = "loginWrongPasswordAndUserExcel", description = "login failed due to incorrect password and user - with excel data provider ")
	void tc_04_unsuccessfulLogin(String email,String password){

		LoginPage lp = new LoginPage(driver);
		lp.login(email,password);
		Assert.assertTrue(lp.isMsgCorrect("These credentials do not match our records."));
	}

	@Test (description = "login failed due to missing/Wrong password or user")
	void tc_05_unsuccessfulLogin(){

		LoginPage lp = new LoginPage(driver);
		lp.login("","");
		Assert.assertEquals(lp.getLoginConstraintErrorEmail(), "Please fill out this field.");
		lp.login("", "df12@434c");
		Assert.assertEquals(lp.getLoginConstraintErrorEmail(), "Please fill out this field.");
		lp.login("mg12345@gmail.com", "");
		Assert.assertEquals(lp.getLoginConstraintErrorPassword(), "Please fill out this field.");
		lp.login("mg12345", "df12@434c");
		Assert.assertEquals(lp.getLoginConstraintErrorEmail(), "Please include an '@' in the email address. 'mg12345' is missing an '@'.");
	}

	@Test (description = "successful login - with configuration file as data provider ")
	void tc_06_successfulLogin(){

		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		Assert.assertEquals(lp.getUrlAsString(),"https://app.involve.me/projects");;
	}


	/*
	 * Data providers From Login Excel
	 */

	@DataProvider
	public Object[][] loginWrongUserExcel(){
		String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\involve\\me\\data\\Login.xlsx";
		System.out.println(excelPath);
		Object[][] table = Excel.getTableArray(excelPath, "LoginWrongUser");
		return table;
	}

	@DataProvider
	public Object[][] loginWrongPasswordExcel(){
		String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\involve\\me\\data\\Login.xlsx";
		System.out.println(excelPath);
		Object[][] table = Excel.getTableArray(excelPath, "LoginWrongPassword");
		return table;
	}

	@DataProvider
	public Object[][] loginWrongPasswordAndUserExcel(){
		String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\involve\\me\\data\\Login.xlsx";
		System.out.println(excelPath);
		Object[][] table = Excel.getTableArray(excelPath, "LoginWrongPasswordAndUser");
		return table;
	}

}
