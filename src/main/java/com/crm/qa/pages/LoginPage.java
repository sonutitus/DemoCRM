package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//Page Factory or Object Reporsitories
	@FindBy(name="email") WebElement username;
	@FindBy(name="password") WebElement password;
	@FindBy(xpath="//div[text()='Login']") WebElement btnLogin;
	
	
	//Initializing the page objects
	public LoginPage() throws Exception {
		
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	public String validateLoginPageTitle() {
		
		return driver.getTitle();
	}
	
	public HomePage login(String un,String pwd) throws Exception {
		
//		username.sendKeys(un);
//		password.sendKeys(pwd);
//		btnLogin.click();
		
		
		wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(un);
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
		wait.until(ExpectedConditions.visibilityOf(btnLogin)).click();
		
		return new HomePage();
	}
	
	public void Set() {
		
		System.out.println("Set");
	}
}
