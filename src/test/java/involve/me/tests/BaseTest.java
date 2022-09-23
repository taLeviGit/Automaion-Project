package involve.me.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import involve.me.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	
	//Initialize the driver
	@Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browser)
    {
        switch (browser)
        {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
        		Utils u = new Utils();
        		driver.get(u.readProperty("MainUrl"));
        		driver.manage().deleteAllCookies();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
        		u = new Utils();
        		driver.get(u.readProperty("MainUrl"));
        		driver.manage().deleteAllCookies();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
        		u = new Utils();
        		driver.get(u.readProperty("MainUrl"));
        		driver.manage().deleteAllCookies();
                break;
            default: throw new IllegalArgumentException("no such browser " + browser);
        }
    }
	
	/*
	 * This method will run after each test,
	 * it will take screen shot only for tests that failed
	 */
	@AfterMethod
	public void failedTest(ITestResult result) {
	  //check if the test failed
		if (result.getStatus() == ITestResult.FAILURE ){
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//result.getname() method will give you current test case name. 
			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
		}
	}
	
	//Close the browser 
	
	@AfterClass
	public void tearDown() throws InterruptedException{
		Thread.sleep(4000);
		driver.quit();
		/*
		 * Activate only when running a single test **NOT AS SUITE**
		 */
//		try {
//			Runtime.getRuntime().exec("taskkill.exe /F /IM chromedriver.exe /T");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
