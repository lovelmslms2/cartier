package cartier.cartierTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class CarListPage {
	private AppiumDriver driver;
	private WebDriverWait wait;

	public CarListPage(AppiumDriver driver, WebDriverWait wait) {
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

	public void goToVAD(int index) {
		List<WebElement> carList=driver.findElements(By.className("UIATableCell"));
		carList.get(index).click();
	}

	public void selectCarType(String... carType) {
//		String brandPath="//UIAApplication[1]/UIAWindow[2]/UIAButton[2]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.name("不限品牌")));
		driver.findElement(By.name("不限品牌")).click();
		for(int i=0;i<carType.length;i++){
			driver.findElement(By.name(carType[i])).click();
			sleep();
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.name("In progress")));
	}

	public void checkCarTypeFilter(String s) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(s)));
//		String brandPath="//UIAApplication[1]/UIAWindow[2]/UIAButton[2]";
//		assertEquals(driver.findElement(By.xpath(brandPath)).getAttribute("name"),s);
	}

	public void selectPriceRange(String s) {
//		String pricePath="//UIAApplication[1]/UIAWindow[2]/UIAButton[3]";
//		driver.findElement(By.xpath(pricePath)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.name("不限价格")));
		driver.findElement(By.name("不限价格")).click();
		driver.findElement(By.name(s)).click();
		driver.findElement(By.name("确定")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.name("In progress")));
	}

	public void gotoSearchPage() {
		driver.findElement(By.name("搜索")).click();
	}

	public void search(String s) {
//		String searchPath="//UIAApplication[1]/UIAWindow[2]/UIASearchBar[1]";
//		driver.findElement(By.xpath(searchPath)).sendKeys(s);
		driver.findElement(By.className("UIASearchBar")).sendKeys(s);
		driver.findElement(By.name("Search")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.name("In progress")));
	}

}
