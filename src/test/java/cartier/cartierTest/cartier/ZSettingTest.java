package cartier.cartierTest.cartier;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.MyInfoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZSettingTest extends MyTestCase {
	private MyInfoPage myInfoPage;
	private Common common;
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    myInfoPage = new MyInfoPage(driver,wait);		
		common.checkInFirstPage();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
    public void testChangeUserName(){
        common.gotoMyInfoPage();
        common.checkInMyInfo();
        myInfoPage.editUserName();
    }

	@Test
    public void testCheckUpdate(){
        common.gotoMyInfoPage();
        myInfoPage.gotoSetting();
        myInfoPage.checkUpgrade();
    }

//	@Test
//    public void testFeedBack(){
//        common.gotoMyInfoPage();
//        myInfoPage.gotoSetting();
//        myInfoPage.feedBack();      
//    }

	@Test
    public void testAboutUs(){
        common.gotoMyInfoPage();
        myInfoPage.gotoSetting();
        myInfoPage.aboutUs();
    }

	@Test
    public void testZSignOut(){
        common.gotoMyInfoPage();
        myInfoPage.gotoSetting();
        myInfoPage.signOut();
    }

}
