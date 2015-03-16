package cartier.cartierTest.pageObject;

import static org.junit.Assert.*;

import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.LogUtil;

public class FirstPage {
	private AppiumDriver driver;
	private WebDriverWait wait;
	private String location="上海";
	String areaPath="//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]";
	
	public FirstPage(AppiumDriver driver,WebDriverWait wait){
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
	
	public void checkGrid() {
		String gridName;
		int i=0;
		
		LogUtil.addData("location", location);
        LogUtil.addStep("检查九宫格显示的排序和字符");
		String[] gridTexts = new String[]{"个人车源", location + "二手车", "全国二手车", "我的收藏", 
				"车商聊天室", "过户转籍", "车辆估价", "违章查询", "限迁标准", "免费刷新20条","车商内网反馈"};
		List<WebElement> gridView=driver.findElements(By.className("UIACollectionCell"));
		for(String gridText:gridTexts){
			gridName=gridView.get(i).getAttribute("name");
			assertEquals(gridText,gridName);
			i++;
		}
	}

    public void setLocation(int index) {
    	List<WebElement> cityList;
    	
    	driver.findElement(By.xpath(areaPath)).click();
    	sleep();
    	cityList=driver.findElements(By.className("UIATableCell"));
    	this.location=cityList.get(index).getAttribute("name");  
    	cityList.get(index).click();
    	sleep();       	
    }

	public void setLocationInList(int i,int j) {
		List<WebElement> city2List;
		
		setLocation(i);
		city2List=driver.findElements(By.className("UIATableView")).get(1).findElements(By.className("UIATableCell"));
		this.location=city2List.get(j).getAttribute("name"); 
		city2List.get(j).click();		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("百姓聚车商")));
	}

    public int getGridCount() {
    	List<WebElement> gridView=driver.findElements(By.className("UIACollectionCell"));
    	return gridView.size();
    }


    public boolean gotoGrid(int index) {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("百姓聚车商")));
		LogUtil.addStep("点击第" + index + "个格子");
		List<WebElement> gridView=driver.findElements(By.className("UIACollectionCell"));
		String s=gridView.get(index).getAttribute("name");
		if(s!=null&&s!=""){
			gridView.get(index).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.name("In progress")));
			return true;
		}else{
			return false;
		}
	}

	public void gotoGrid(String s) {
		driver.findElement(By.name(s)).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.name("In progress")));
	}

}
