package cartier.cartierTest.cartier;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.pageObject.CarListPage;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.FirstPage;
import cartier.cartierTest.pageObject.MyInfoPage;
import cartier.cartierTest.pageObject.ViewAdPage;

public class CollectionTest extends MyTestCase {
	private FirstPage firstPage;
	private MyInfoPage myInfoPage;
    private CarListPage carListPage;
    private ViewAdPage viewAdPage;
	private Common common;
	
	@BeforeMethod
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    firstPage=new FirstPage(driver,wait);
	    myInfoPage=new MyInfoPage(driver,wait);
	    viewAdPage=new ViewAdPage(driver,wait);
	    carListPage=new CarListPage(driver,wait);
		common.checkInFirstPage();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
		
    public void addToCollection(int carListType, int count) {
        firstPage.gotoGrid(carListType);
        for (int i = 0; i < count; i++) {          
            carListPage.goToVAD(i);
            viewAdPage.collect();
            driver.findElement(By.name("Back")).click();
        }
        driver.findElement(By.name("首页")).click();
    }
    
    public void deleteAllCollect() {
        common.gotoMyInfoPage();
        myInfoPage.showCollection();
        while(myInfoPage.getCollectCount()>0){     	
        	delCollectFromCollectionList(0);
        	myInfoPage.showCollection();
        }      
    }
    
    private void delCollectFromCollectionList(int index) {
    	myInfoPage.goToVAD(index,false);
        viewAdPage.rmCollect();
        driver.findElement(By.name("我的店铺")).click();
	}

	@Test
    public void testAddCollectInfo() {
        String title, price, distance, carAge;      
        int k;
        deleteAllCollect();
        for (int i = 0; i < 3; i++) {
            common.gotoFirstPage();
            common.checkInFirstPage();
            firstPage.gotoGrid(i);
            k=(int)(Math.random()*30);
            carListPage.goToVAD(k);
            viewAdPage.collect();
            title = viewAdPage.getTitle();
//            location = viewAdPage.getLocation();
            price = viewAdPage.getOriginalPrice();
            distance = viewAdPage.getDistance();
            carAge = viewAdPage.getTitle();
            driver.findElement(By.name("Back")).click();
            driver.findElement(By.name("首页")).click();
            common.checkInFirstPage();
            common.gotoMyInfoPage();
            common.checkInMyInfo();
            myInfoPage.showCollection();
            myInfoPage.goToVAD(0,false);
            assertEquals(title, viewAdPage.getTitle());
//            Assert.assertEquals(location, viewAdPage.getLocation());
            assertEquals(price, viewAdPage.getOriginalPrice());
            assertEquals(distance, viewAdPage.getDistance());
            assertEquals(carAge, viewAdPage.getTitle());
            viewAdPage.rmCollect();
            driver.findElement(By.name("Back")).click();
        }
    }

	@Test
    public void testAddCollectCount() {
        deleteAllCollect();
        common.gotoFirstPage();
        for (int i = 0; i < 3; i++){
        	addToCollection(i, 2);
        }         
        common.gotoMyInfoPage();
        myInfoPage.showCollection();
        assertEquals(6, myInfoPage.getCollectCount());
    }
    
	@Test
    public void testDeleteCollect() {
        deleteAllCollect();
        common.gotoFirstPage();
        for (int i = 0; i < 3; i++){
        	addToCollection(i, 1);
        }         
        common.gotoMyInfoPage();
        myInfoPage.showCollection();
        assertEquals(3, myInfoPage.getCollectCount());
        delCollectFromCollectionList(0);
        myInfoPage.showCollection();
        assertEquals(2, myInfoPage.getCollectCount());
    }
    
	@Test
    public void testDeleteAllCollect() {
        deleteAllCollect();
        common.gotoFirstPage();
        for (int i = 0; i < 3; i++){
            addToCollection(i, 1);
        }
        deleteAllCollect();
        assertEquals(0, myInfoPage.getCollectCount());
    }
    
	@Test
    public void testLastCollectShowsOnTop() {
        String title="";
        deleteAllCollect();
        common.gotoFirstPage();
        for (int i = 0; i < 3; i++) {            
            firstPage.gotoGrid(i);
            carListPage.goToVAD(0);
            viewAdPage.collect();
            title = viewAdPage.getTitle();
            driver.findElement(By.name("Back")).click();
            driver.findElement(By.name("首页")).click();
        }      
        common.gotoMyInfoPage();
        myInfoPage.showCollection();
        assertTrue(title.contains(myInfoPage.getTitle(0)));
        driver.findElement(By.name("首页")).click();
        deleteAllCollect();
    }
}







