package cartier.cartierTest.pageObject;

import static org.junit.Assert.*;
import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cartier.cartierTest.LogUtil;

public class FirstPage {
	private AppiumDriver driver;
	private Common common;
	private String location="上海";
	String areaPath="//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]";
	
	public FirstPage(AppiumDriver driver,Common common){
		this.driver=driver;
		this.common=common;
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
				"车商聊天室", "过户转籍", "车辆估价", "违章查询", "限迁标准", "免费刷新20条","意见反馈"};
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
		common.checkInFirstPage();
	}

    public int getGridCount() {
    	List<WebElement> gridView=driver.findElements(By.className("UIACollectionCell"));
    	return gridView.size();
    }


    public boolean gotoGrid(int index) {
		common.checkInFirstPage();
		LogUtil.addStep("点击第" + index + "个格子");
		List<WebElement> gridView=driver.findElements(By.className("UIACollectionCell"));
		String s=gridView.get(index).getAttribute("name");
		if(s!=null&&s!=""){
			gridView.get(index).click();
			sleep();
			return true;
		}else{
			return false;
		}
	}

	public void gotoGrid(String s) {
		driver.findElement(By.name(s)).click();
		sleep();
	}

}
