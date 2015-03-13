package cartier.cartierTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.pageObject.LoginPage;

public class MyTestCase extends TestCase {
	protected  AppiumDriver driver;
	protected  WebDriverWait wait;
	
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
		
		LoginPage loginPage=new LoginPage(driver,wait);
		loginPage.singInWith("18817360115", "2300687");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Override
    protected void runTest() throws Throwable {
//        String testMethodName = getClass().getName() + "" + getName();
        try {
            LogUtil.clearLog();
            super.runTest();
        } catch (TimeoutException e){ 
        	throw new TimeoutException("\n" + LogUtil.getLog().toString(), e);
        } catch (Throwable e) {
            // 测试用例失败的时候截图
//            poseForScreenshotNamed(testMethodName);
            throw new Exception("\n" + LogUtil.getLog().toString(), e);
        } finally {
        }
    }
	@Test
	public void testMyTest(){
		
	}
	

}
