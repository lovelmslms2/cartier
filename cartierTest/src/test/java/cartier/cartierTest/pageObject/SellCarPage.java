package cartier.cartierTest.pageObject;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.model.Car;

public class SellCarPage {
	private AppiumDriver driver;
	private WebDriverWait wait;
	
	public SellCarPage(AppiumDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait=wait;
	}

	public void sleep(){
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    //根据path定位，返回path
	private String editPath(int i) {
		String path="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[";
		return path+(i+1)+"]/UIATextField[1]";
	}
	//填写发车内容 k=1 发布；k=2 修改
	public void inputCarInfo(Car car,int k) {
		String btPatha="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[";
		String btPathb;
		
		if(k==1){
			btPathb="]/UIAButton[1]";
		}else{
			btPathb="]/UIAButton[2]";
		}
		for(int i=0;i<21;i++){
			//修改时定位不到排量
			if((k==2)&&(i==6)) continue;
			switch(i){
			case 0://品牌
				driver.findElement(By.xpath(editPath(i))).click();
				sleep();
				driver.findElement(By.name(car.getPinpai())).click();
				sleep();
				driver.findElement(By.name(car.getChexi())).click();
				sleep();
				driver.findElement(By.name(car.getXinghao())).click();
				sleep();
				break;
			case 1://行驶里程 由于无法定位，暂时不测
		
//				sleep();
//				driver.findElement(By.xpath(editPath(i))).clear();
//				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getDistance());
			    break;
			case 2://初次上牌 由于无法定位，暂时不测
//				detailList.get(i).findElement(By.xpath("datePath")).click();
//				sleep();
//				driver.findElement(By.name("完 成")).click();
//				sleep();
				break;
			case 3://价格
				driver.findElement(By.xpath(editPath(i))).clear();
				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getPrice());
				driver.scrollTo("变速箱");
			    break;
			case 4://过户费
			case 10://行驶证
			case 11://登记证
			case 12://购车发票
			case 13://维修记录
			case 14://重大事故
			case 16://能否过户
			case 17://能否按揭
			case 20://购置税
				driver.findElement(By.xpath(btPatha+(i+1)+btPathb)).click();
				break;
			case 6://排量
				driver.findElement(By.xpath(editPath(i))).clear();
				sleep();
				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getPailiang());
			    break;
			case 7://变速箱
				driver.findElement(By.xpath(editPath(i))).click();
				sleep();
				driver.findElement(By.name(car.getBiansu())).click();
				break;
			case 8://燃油类型
			    driver.scrollTo("燃油类型");
			    driver.findElement(By.xpath(editPath(i))).clear();
				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getRanyou());	
			    break;
			case 9://排放标准
				driver.findElement(By.xpath(editPath(i))).clear();
				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getPaifang());
			    break;
			case 15://车辆用途
				driver.scrollTo("车辆用途");
				sleep();
				driver.findElement(By.xpath(editPath(i))).clear();
				driver.findElement(By.xpath(editPath(i))).sendKeys(car.getYongtu());
			    break;
			case 18://级别
				driver.scrollTo("级别");
				driver.findElement(By.xpath(editPath(i))).click();
				sleep();
				driver.findElement(By.name(car.getJibie())).click();
			    break;
			case 19://车辆颜色
				driver.scrollTo("车辆颜色");
				driver.findElement(By.xpath(editPath(i))).click();
				sleep();
				driver.findElement(By.name(car.getColor())).click();
			    break;
			}
			sleep();
		}
	}

	public void publishCar(boolean isNew) {    		
        if (isNew){
        	driver.findElement(By.name("发布")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("正在发布")));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("发布成功")));	
        }else{
        	driver.findElement(By.name("编辑")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("正在发布")));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("修改成功")));
        }
        driver.findElement(By.name("知道了")).click();
	}

	public void inputCarDetail(String detail) {
		String desPath="//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATextView[1]";

		driver.findElement(By.xpath(desPath)).sendKeys(detail);
		sleep();
	}

}
