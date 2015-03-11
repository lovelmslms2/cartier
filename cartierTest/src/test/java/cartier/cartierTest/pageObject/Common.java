package cartier.cartierTest.pageObject;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.LogUtil;
import cartier.cartierTest.LogUtil.ActionType;

public class Common {
	private AppiumDriver driver;
	private WebDriverWait wait;
	
	public Common(AppiumDriver driver,WebDriverWait wait){
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
	
	public void gotoFirstPage() {
		driver.findElement(By.id("首页")).click();
		sleep();
	}
	
    public void checkInFirstPage() {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("百姓聚车商")));
    }

	public void gotoPrivateMsg() {
		driver.findElement(By.id("私聊")).click();
		sleep();
	}

	public void checkInPrivateMsg() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("会 话")));
	}

	public void gotoMyInfoPage() {
		driver.findElement(By.id("店铺")).click();
		sleep();
	}

	public void checkInMyInfo() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("我的店铺")));
	}

	public void gotoSellCarPage() {
		driver.findElement(By.id("发车")).click();
		sleep();
	}

	public void checkInSellCar() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("发布车辆")));
	}

	public void tapUp() {
		LogUtil.addOperate(ActionType.TAP_UP, 0, "", 0);
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("首页")));
		driver.findElement(By.name("首页")).click();
		sleep();
	}

}
