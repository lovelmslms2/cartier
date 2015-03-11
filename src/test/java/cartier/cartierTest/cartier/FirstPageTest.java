package cartier.cartierTest.cartier;

//import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.FirstPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstPageTest extends MyTestCase{
	private FirstPage firstPage;
	private Common common;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    firstPage=new FirstPage(driver,common);
		common.checkInFirstPage();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testGridViewShowing() {
        firstPage.checkGrid();
    }

	@Test
    public void testChangeLocation() throws Exception {
		int hot=(int)(Math.random()*4);
        firstPage.setLocation(hot);//选择热门城市
        common.checkInFirstPage();
        firstPage.checkGrid();
        firstPage.setLocationInList(10,3);//选择字母排序的城市列表
        firstPage.checkGrid();
    }

	@Test
    public void testUpButton(){
        for (int i = 0; i < firstPage.getGridCount(); i++) {
            if (firstPage.gotoGrid(i)) {
                common.tapUp();
                common.checkInFirstPage();
            }
        }
    }

	@Test
    public void testTabs(){
        common.checkInFirstPage();
        common.gotoPrivateMsg();
        common.checkInPrivateMsg();
        common.gotoMyInfoPage();
        common.checkInMyInfo();
        common.gotoSellCarPage();
        common.checkInSellCar();
        common.gotoFirstPage();
        common.checkInFirstPage();
    }
	
}
