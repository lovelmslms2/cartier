package cartier.cartierTest.cartier;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.pageObject.CarListPage;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.FirstPage;
import cartier.cartierTest.pageObject.ViewAdPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewAdPageTest extends MyTestCase{
	private FirstPage firstPage;
	private CarListPage carListPage;
	private ViewAdPage viewAdPage;
	private Common common;
	String returnPath="//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]";

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    firstPage=new FirstPage(driver,common);
	    carListPage=new CarListPage(driver,wait);
	    viewAdPage=new ViewAdPage(driver,wait);
		common.checkInFirstPage();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

    public void gotoVAD(int carListType, int index) {
        firstPage.gotoGrid(carListType);
        carListPage.goToVAD(index);
    }
    
	@Test
    public void testCheckContact() {
        for (int i = 0; i < 3; i++) {
            gotoVAD(i, 0);
            viewAdPage.checkContact();
            driver.findElement(By.xpath(returnPath)).click();
            driver.findElement(By.name("首页")).click();
        }
    }
	
	@Test
    public void testShare() {
        for (int i = 0; i < 3; i++) {
            gotoVAD(i, 0);
            viewAdPage.share();
            driver.findElement(By.xpath(returnPath)).click();
            driver.findElement(By.name("首页")).click();
        }
    }
	
	@Test
    public void testCollect() {
        for (int i = 0; i < 3; i++) {
            gotoVAD(i, 0);
            viewAdPage.collect();
            viewAdPage.rmCollect();
            driver.findElement(By.xpath(returnPath)).click();
            driver.findElement(By.name("首页")).click();
        }
    }

}
