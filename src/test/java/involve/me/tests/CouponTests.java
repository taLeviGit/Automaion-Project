package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobject.CouponsPage;
import involve.me.pageobject.HomePage;
import involve.me.pageobject.LoginPage;
import involve.me.pageobject.ProjectsPage;
import involve.me.utils.Utils;

public class CouponTests extends BaseTest{
	
	@Test(description = "Create a single type coupon")
	void tc_01_addNewSingleCoupon() {
		
		HomePage hp = new HomePage(driver);
		hp.acceptCookies();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		Utils u = new Utils();
		lp.login(u.readProperty("Email"),u.readProperty("Password"));
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickCouponPage();
		CouponsPage cp = new CouponsPage(driver);
		int before = cp.countCoupons();
		cp.addSingleCoupon("coupon1", "wix", "Single - One Coupon Code For All Users", "1231546321", "10");
		int after = cp.countCoupons();
		Assert.assertEquals(after, before+1);
	}
	
	@Test(description = "Create a multiple type coupon")
	void tc_02_addNewmultipleCoupon() {
		
		CouponsPage cp = new CouponsPage(driver);
		int before = cp.countCoupons();
		cp.addMultipleCoupon("coupon2", "wix","Multiple - Unique Coupon Code For Each User", "1231546321");
		cp.addMultipleCoupon("coupon3", "wix","Multiple - Unique Coupon Code For Each User", "1231546321");
		int after = cp.countCoupons();
		Assert.assertEquals(after, before+2);
	}
	
	@Test(description = "Delete a coupon")
	void tc_03_deleteCoupon() {
		
		CouponsPage cp = new CouponsPage(driver);
		int before = cp.countCoupons();
		cp.deleteCoupon("coupon2", "Delete");
		int after = cp.countCoupons();
		Assert.assertEquals(after, before-1);
	}
	
	@Test(description = "Increase limit for a single type coupon")
	void tc_04_increaseCouponLimit() {
		
		CouponsPage cp = new CouponsPage(driver);
		int before = cp.countCouponUses("coupon1");
		cp.increarsLimitOrAddCodes("coupon1", "Increase Limit", "8");
		int after = cp.countCouponUses("coupon1");
		Assert.assertEquals(after, before+8);
	}
	
	@Test(description = "Add new codes to multiple type coupon")
	void tc_05_addCodesToCoupon() {
		
		CouponsPage cp = new CouponsPage(driver);
		int before = cp.countCouponUses("coupon3");
		cp.increarsLimitOrAddCodes("coupon3","Add codes", "dasdsadasdasdsadsadsada");
		int after = cp.countCouponUses("coupon3");
		Assert.assertEquals(after, before+1);
	}
}
