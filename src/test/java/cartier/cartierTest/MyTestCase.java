package cartier.cartierTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import cartier.cartierTest.pageObject.LoginPage;

public class MyTestCase {
	protected  AppiumDriver driver;
	protected  WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() throws Exception {
		File appDir = new File("src/test/java/cartier/cartierTest");
	    File app = new File(appDir, "cartierDev_new.app");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
	    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    wait=new WebDriverWait(driver,15);
		
		LoginPage loginPage=new LoginPage(driver,wait);
		loginPage.singInWith("18817360115", "2300687");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	


}
