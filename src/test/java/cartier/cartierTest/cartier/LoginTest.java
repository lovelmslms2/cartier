package cartier.cartierTest.cartier;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends TestCase{
	private AppiumDriver driver;
	private LoginPage loginPage;
	private WebDriverWait wait;
	private Common common;

	@Before
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
		loginPage=new LoginPage(driver,wait);
		common=new Common(driver,wait);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testLoginWithWrongPassword() {		
		loginPage.singInWith("18817360115", "password");
		loginPage.checkErrorPasswordMessage();
	}
	
	@Test
	public void testLoginWithWrongPhoneNumber() {
		loginPage.typePhone("12345678911");
		loginPage.checkErrorPhoneMessage();
	}

	@Test
	public void testzLogInSuccess() {
        loginPage.singInWith("18817360115", "2300687");
        common.checkInFirstPage();
    }
}
