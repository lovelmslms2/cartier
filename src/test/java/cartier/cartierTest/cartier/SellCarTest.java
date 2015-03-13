package cartier.cartierTest.cartier;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cartier.cartierTest.MyTestCase;
import cartier.cartierTest.model.Car;
import cartier.cartierTest.pageObject.Common;
import cartier.cartierTest.pageObject.MyInfoPage;
import cartier.cartierTest.pageObject.SellCarPage;
import cartier.cartierTest.pageObject.ViewAdPage;

public class SellCarTest extends MyTestCase{
	private ViewAdPage viewAdPage;
	private MyInfoPage myInfoPage;
	private SellCarPage sellCarPage;
	private Common common;

	@BeforeMethod
	@Override
	public void setUp() throws Exception {
		super.setUp();
	    common=new Common(driver,wait);
	    viewAdPage=new ViewAdPage(driver,wait);
	    myInfoPage = new MyInfoPage(driver,wait);
	    sellCarPage=new SellCarPage(driver,wait);
	    common.checkInFirstPage();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
    public void deleteAllAds(){
        common.gotoMyInfoPage();
        common.checkInMyInfo();
        while (myInfoPage.getMyAdCount() > 0) {
            myInfoPage.goToVAD(0, true);
            viewAdPage.deleteMyAd();
        }
    }

	@Test
    public void testPublishCar() {
        deleteAllAds();
        common.gotoSellCarPage();
        publishCar();
        common.checkInMyInfo();
        Assert.assertEquals(1,myInfoPage.getMyAdCount());
        myInfoPage.goToVAD(0,true);
        viewAdPage.checkInfo(createNewAd());
        viewAdPage.deleteMyAd();
    }
	
	@Test
    public void testEditMyAd(){
        deleteAllAds();
        common.gotoSellCarPage();
        publishCar();
        common.checkInMyInfo();
        myInfoPage.goToVAD(0,true);
        viewAdPage.checkAddToCollectionNotDisplayed();
        viewAdPage.EditMyAd();
        Car editCar = editCar();
        sellCarPage.inputCarInfo(editCar,2);
        if (editCar.getDetail() != null){
        	sellCarPage.inputCarDetail(editCar.getDetail());
        }           
        sellCarPage.publishCar(false);
        viewAdPage.checkInfo(editCar);
        viewAdPage.deleteMyAd();
    }

	private Car editCar() {
		Car car = new Car();
        car.setPinpai("别克");
        car.setChexi("别克世纪");
        car.setXinghao("2006款 世纪 2.2 MT 经典型 11座");
        car.setDistance("120");
//        car.setDate("2012年06月");
        car.setPrice("999");
        car.setGuohufei("不包含");
        car.setPailiang("0.8");
        car.setBiansu("手自一体");
        car.setRanyou("柴油");
        car.setPaifang("国三");
        car.setXingshi("丢失");
        car.setDengji("丢失");
        car.setFapiao("丢失");
        car.setWeixiu("丢失");
        car.setShigu("有");
        car.setYongtu("公用");
        car.setGuohu("不能");
        car.setAnjie("不能");
        car.setJibie("其他");
        car.setColor("红");
        car.setGouzhi("丢失");
        car.setDetail("好车，欢迎咨询啊");
        return car;
	}

	private Car createNewAd() {
        Car car = new Car();
        car.setPinpai("本田");
        car.setChexi("本田飞度");
        car.setXinghao("2011款 1.3 AT 舒适版");
        car.setDistance("100");
//        car.setDate("2012年06月");
        car.setPrice("38.8");
        car.setGuohufei("包含");
        car.setPailiang("0.8");
        car.setBiansu("自动");
        car.setRanyou("97#");
        car.setPaifang("欧洲III号");
        car.setXingshi("齐全");
        car.setDengji("齐全");
        car.setFapiao("齐全");
        car.setWeixiu("齐全");
        car.setShigu("无");
        car.setYongtu("商用");
        car.setGuohu("能");
        car.setAnjie("能");
        car.setJibie("中型车");
        car.setColor("黑");
        car.setGouzhi("齐全");
        car.setDetail("本广告为测试广告，马上删除，请勿回复谢谢，给您造成不便请谅解。");
        return car;
	}

	private void publishCar() {
		Car car = createNewAd();
        sellCarPage.inputCarInfo(car,1);
        if (car.getDetail() != null){
        	sellCarPage.inputCarDetail(car.getDetail());
        }        
        sellCarPage.publishCar(true);
	}
}
