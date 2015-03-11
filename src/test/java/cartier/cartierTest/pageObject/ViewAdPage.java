package cartier.cartierTest.pageObject;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.LogUtil;
import cartier.cartierTest.model.Car;

import io.appium.java_client.AppiumDriver;

public class ViewAdPage {
	private WebDriverWait wait;
	private AppiumDriver driver;
	
	public ViewAdPage(AppiumDriver driver,WebDriverWait wait){
		this.driver=driver;
		this.wait=wait;
	}
	
	public void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void collect() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fav")));
		driver.findElement(By.name("fav")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("知道了")));
		driver.findElement(By.id("知道了")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("faved")));
	}

	public void rmCollect() {
		driver.findElement(By.name("faved")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("知道了")));
		driver.findElement(By.id("知道了")).click();
	}

	public String getTitle() {
		String titlePath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[1]";
		return driver.findElement(By.xpath(titlePath)).getAttribute("name");
	}

//	public String getLocation() {
//		
//		return null;
//	}

	public String getDistance() {
		String distancePath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[19]";
		return driver.findElement(By.xpath(distancePath)).getAttribute("name");
	}

	public String getOriginalPrice() {
//		String pricePath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[5]";
		String pricePath="//UIAScrollView[1]/UIAStaticText[5]";
		String price=driver.findElement(By.xpath(pricePath)).getAttribute("name");
		LogUtil.addData("价格", price);
		return price;
	}

	public String getCarBrand() {
		String brandPath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[13]";
		String brand=driver.findElement(By.xpath(brandPath)).getAttribute("name");
		LogUtil.addData("品牌", brand);
		return brand;
	}

	public String getCarSeries() {
		String carSeriesPath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[15]";
		String carSeries=driver.findElement(By.xpath(carSeriesPath)).getAttribute("name");
		LogUtil.addData("车系列", carSeries);
		return carSeries;
	}

	public String getModel() {
		String modelPath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[17]";
		String model=driver.findElement(By.xpath(modelPath)).getAttribute("name");
		LogUtil.addData("车型", model);
		return model;
	}

	public String getDetail() {
		String detailPath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAStaticText[26]";
		String detail=driver.findElement(By.xpath(detailPath)).getAttribute("name");
		LogUtil.addData("车辆详情", detail);
		return detail;
	}
	
	public float getPrice() {
		String price = getOriginalPrice();
        if (price.contains("万"))
            price = price.substring(0, price.indexOf("万"));
        if (price.contains("-"))
            price = price.substring(0, price.indexOf("-"));
        if (price.contains("~"))
            price = price.substring(0, price.indexOf("~"));
        if (price.contains("、"))
            price = price.substring(0, price.indexOf("、"));
        if (price.contains(","))
            price = price.substring(0, price.indexOf(","));
        if (price.contains("。"))
            price = price.substring(0, price.indexOf("。"));
        if (price.contains("，"))
            price = price.substring(0, price.indexOf("，"));
        price = price.replace(" ", "");
        float priceValue = Float.valueOf(price);
        return priceValue;
	}

	public String getAllInfo() {
		String allInfo;
		allInfo=getCarBrand()+getCarSeries()+getModel()+getTitle()+getDetail();
		return allInfo;
	}

	public void checkContact() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("短信")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("私信")));
	}

	public void share() {
		driver.findElement(By.name("share")).click();
		sleep();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("微信好友")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("微信朋友圈")));
		driver.findElement(By.name("Cancel")).click();
	}

	public void deleteMyAd() {
		driver.findElement(By.name("删除")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("提示")));
		driver.findElement(By.name("确认")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("提示")));
		driver.findElement(By.name("知道了")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("我的店铺")));
	}

	public void checkInfo(Car car) {
		String path="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/";
		String pinpai=path+"UIAStaticText[13]";
		String chexi=path+"UIAStaticText[15]";
		String xinghao=path+"UIAStaticText[17]";
//		String distance=path+"UIAStaticText[19]";
		String price=path+"UIAStaticText[5]";
		String guohufei=path+"UIAStaticText[23]";
		String pailiang=path+"UIAStaticText[35]";
		String biansu=path+"UIAStaticText[37]";
		String ranyou=path+"UIAStaticText[57]";
		String paifang=path+"UIAStaticText[59]";
		String xingshi=path+"UIAStaticText[39]";
		String dengji=path+"UIAStaticText[41]";
		String fapiao=path+"UIAStaticText[43]";
		String weixiu=path+"UIAStaticText[45]";
		String shigu=path+"UIAStaticText[47]";
		String yongtu=path+"UIAStaticText[49]";
		String guohu=path+"UIAStaticText[51]";
		String anjie=path+"UIAStaticText[53]";
		String jibie=path+"UIAStaticText[55]";
		String color=path+"UIAStaticText[33]";
		String gouzhi=path+"UIAStaticText[61]";
		String detail=path+"UIAStaticText[26]";
		
	    equalOrNot(pinpai,car.getPinpai());
	    equalOrNot(chexi,car.getChexi());
	    equalOrNot(xinghao,car.getXinghao());
//	    equalOrNot(distance,car.getDistance()+"万公里");
	    equalOrNot(price,car.getPrice()+"万元");
	    equalOrNot(guohufei,car.getGuohufei());
	    equalOrNot(pailiang,car.getPailiang()+"L");
	    equalOrNot(biansu,car.getBiansu());
	    equalOrNot(ranyou,car.getRanyou());
	    equalOrNot(paifang,car.getPaifang());
	    equalOrNot(weixiu,car.getWeixiu());
	    equalOrNot(shigu,car.getShigu());
	    equalOrNot(xingshi,car.getXingshi());
	    equalOrNot(dengji,car.getDengji());
	    equalOrNot(fapiao,car.getFapiao());
	    equalOrNot(yongtu,car.getYongtu());
	    equalOrNot(guohu,car.getGuohu());
	    equalOrNot(anjie,car.getAnjie());
	    equalOrNot(jibie,car.getJibie());
	    equalOrNot(color,car.getColor());
	    equalOrNot(gouzhi,car.getGouzhi());
	    equalOrNot(detail,car.getDetail());
	}

	public void equalOrNot(String xpath, String s) {
		if(!driver.findElement(By.xpath(xpath)).getAttribute("value").equals(s)){
			fail("无法匹配："+driver.findElement(By.xpath(xpath)).getAttribute("value")+"and"+s);
		}
	}
	
	public void checkAddToCollectionNotDisplayed() {
		ExpectedConditions.invisibilityOfElementLocated(By.name("fav"));
	}

	public void EditMyAd() {
		driver.findElement(By.name("修改")).click();
		sleep();
	}

}
