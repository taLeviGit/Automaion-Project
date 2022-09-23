package involve.me.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ListenerClass extends TestListenerAdapter {
	
	//In case of test failure add screenshot , page source , console log.
	@Override
	public void onTestFailure(ITestResult result) {
		Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
		if (webDriverAttribute instanceof WebDriver) {
			AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
			AllureAttachment.getPageSource((WebDriver) webDriverAttribute);
			AllureAttachment.attachConsoleLogs((WebDriver) webDriverAttribute);
		}
	}
}
