package cartier.cartierTest.pageObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.LogUtil;

import io.appium.java_client.AppiumDriver;

public class MyInfoPage {
	private AppiumDriver driver;
	private WebDriverWait wait;
	String collectPath="//UIAApplication[1]/UIAWindow[2]/UIASegmentedControl[1]/UIAButton[2]";
	String sellPath="//UIAApplication[1]/UIAWindow[2]/UIASegmentedControl[1]/UIAButton[1]";
	
	public MyInfoPage(AppiumDriver driver,WebDriverWait wait){
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
	
	public void showCollection() {	
		driver.findElement(By.xpath(collectPath)).click();
		sleep();
	}
	
	public void showSell() {	
		driver.findElement(By.xpath(sellPath)).click();
		sleep();
	}

	public int getCollectCount() {
		List<WebElement> collectList;
//    	showCollection();	
		collectList=driver.findElements(By.className("UIATableCell"));
		return collectList.size();
	}
	
	public int getMyAdCount() {
		List<WebElement> myAdList;	
		myAdList=driver.findElements(By.className("UIATableCell"));
		return myAdList.size();
	}
	
    public void goToVAD(int index, boolean isMyCar){
    	List<WebElement> carList;
    	
        try {
        	carList=driver.findElements(By.className("UIATableCell"));
        	carList.get(index).click();
        }catch (RuntimeException e){
        	showSell();
        	showCollection();
        	if(isMyCar){
        		showSell();
        	}
        	sleep();
        	carList=driver.findElements(By.className("UIATableCell"));
        	carList.get(index).click();
        }
    }

	public String getTitle(int index) {
		List<WebElement> collectList;
    	collectList=driver.findElements(By.className("UIATableCell"));
    	String title=collectList.get(index).getAttribute("name");
    	LogUtil.addData("标题", title);
    	if (title.endsWith("...")){
    		title = title.replace("...", "");
    	}            
		return title;
	}

	public void editUserName() {
		String usernamePath="//UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]";
		String username="cartier112233";
		String oldUsername=driver.findElement(By.xpath(usernamePath)).getAttribute("name");
		
		driver.findElement(By.name("编辑")).click();
		sleep();
		driver.findElement(By.name(oldUsername)).click();
		sleep();
		driver.findElement(By.id("请输入新用户名")).sendKeys(username);
		driver.findElement(By.name("保存")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("修改成功")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("我的店铺")));
		driver.findElement(By.name("我的店铺")).click();
		sleep();
		String newUserName=driver.findElement(By.xpath(usernamePath)).getAttribute("name");
		assertEquals(username,newUserName);
	}

	public void checkUpgrade() {
		sleep();
		String updatePath="//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]";
		driver.findElement(By.xpath(updatePath)).click();
		sleep();
		assertNotNull(driver.findElement(By.name("提示")));
		driver.findElement(By.name("知道了")).click();
		sleep();
	}

	public void feedBack() {
		driver.findElement(By.name("用户反馈")).click();
		sleep();
		driver.findElement(By.name("设置")).click();
		sleep();
	}

	public void aboutUs() {
		driver.findElement(By.name("关于我们")).click();
		sleep();
		driver.findElement(By.name("about.jpg")).click();
		sleep();		
	}

	public void signOut() {
		driver.findElement(By.name("退出登录")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("请输入手机号")));
	}
	
	public void gotoSetting(){
		String settingPath="//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]";
		driver.findElement(By.xpath(settingPath)).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("设置")));
	}

}
