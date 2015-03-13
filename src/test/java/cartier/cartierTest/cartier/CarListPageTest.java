package cartier.cartierTest.cartier;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.pageObject.CarListPage;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.FirstPage;
import cartier.cartierTest.pageObject.ViewAdPage;

public class CarListPageTest extends MyTestCase {
	private FirstPage firstPage;
    private CarListPage carListPage;
    private ViewAdPage viewAdPage;
	private Common common;

	@BeforeMethod
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    firstPage=new FirstPage(driver,wait);
	    carListPage=new CarListPage(driver,wait);
	    viewAdPage=new ViewAdPage(driver,wait);
		common.checkInFirstPage();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
    public void testCarTypeFilter() {
        for (int j = 0; j < 3; j++) {
            firstPage.gotoGrid(j);          
            carListPage.selectCarType(new String[]{"奥迪", "奥迪A6", "不限型号"});
            for (int i = 0; i < 6; i++) {
                try {
                    carListPage.goToVAD(i);
                    assertEquals(viewAdPage.getCarSeries(),"奥迪A6");
                    driver.findElement(By.name("Back")).click();
                } catch (Exception e) {
                    break;
                }
            }
            carListPage.checkCarTypeFilter("奥迪A6");
            driver.findElement(By.name("首页")).click();
            common.checkInFirstPage();
            firstPage.gotoGrid(j);
            carListPage.selectCarType(new String[]{"宝马", "不限车系"});
            for (int i = 0; i < 6; i++) {
                try {
                    carListPage.goToVAD(i);
                    assertEquals(viewAdPage.getCarBrand(),"宝马");
                    driver.findElement(By.name("Back")).click();
                } catch (Exception e) {
                    break;
                }
            }
            carListPage.checkCarTypeFilter("宝马");
            driver.findElement(By.name("首页")).click();
            common.checkInFirstPage();
        }
    }

	@Test
    public void testPriceFilter() {
        float priceValue;
        for (int j = 0; j < 3; j++) {
            firstPage.gotoGrid(j);
            carListPage.selectPriceRange("3万以下");
            for (int i = 0; i < 6; i++) {
            	carListPage.goToVAD(i);
                priceValue = viewAdPage.getPrice();
                assertTrue(priceValue <= 3);
                driver.findElement(By.name("Back")).click();
            }
            driver.findElement(By.name("首页")).click();
            common.checkInFirstPage();
        }
    }
    
	@Test
    public void testSearch(){
		String s="比亚迪";
        for (int j = 0; j < 3; j++) {
            firstPage.gotoGrid(j);
            carListPage.gotoSearchPage();
            carListPage.search(s);
            for (int i = 0; i < 6; i++) {
                carListPage.goToVAD(i);
                assertTrue(viewAdPage.getAllInfo().contains("比亚迪"));
                driver.findElement(By.name("Back")).click();
            }
            driver.findElement(By.name("Back")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
    				By.name("取消")));
            driver.findElement(By.name("取消")).click();
            driver.findElement(By.name("首页")).click();
            common.checkInFirstPage();
        }
    }
}
