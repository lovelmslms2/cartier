package cartier.cartierTest.pageObject;


import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cartier.cartierTest.LogUtil;

public class LoginPage {
	private AppiumDriver driver;
	private WebDriverWait wait;
	
	public LoginPage(AppiumDriver driver,WebDriverWait wait) throws Exception{
		this.driver = driver;
		this.wait=wait;
	}
	public void insertPhone(String phoneNumber){
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("请输入手机号")));
		driver.findElement(By.id("请输入手机号")).sendKeys(phoneNumber);
//		driver.findElementByAccessibilityId("请输入手机号").sendKeys(phoneNumber);
	}
	
	public void typePhone(String phoneNumber){
		insertPhone(phoneNumber);
		driver.findElementByAccessibilityId("下一步").click();
	}
	
	public void singInWith(String phoneNumber,String password){
		typePhone(phoneNumber);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("请输入验证码或密码")));
		driver.findElement(By.id("请输入验证码或密码")).sendKeys(password);
		driver.findElementByAccessibilityId("登录").click();
	}
	
	public void checkErrorPasswordMessage(){
		String message="验证码错误，计时结束后点击获取语音验证";
		
		driver.findElement(By.id("知道了")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id(message)));
	}
	
	public void checkErrorPhoneMessage(){
		LogUtil.addStep("检查”请输入正确的手机号“已显示");
		String message="请填写正确的手机号";

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id(message)));
	}

}
