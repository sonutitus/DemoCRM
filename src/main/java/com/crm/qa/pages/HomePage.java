package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;


public class HomePage extends TestBase {
	
	

	//PagesObjects -Object Repositories
	
	@FindBy(xpath="//span[@class='user-display']") WebElement userDisplay;
	@FindBy(xpath="//div[@class='header item']") WebElement logoDisplay;
	@FindBy(xpath="//span[text()='Calendar']") WebElement calendar;

	//Initializing the page objects
		public HomePage() throws Exception {
			
			PageFactory.initElements(driver, this);
			// TODO Auto-generated constructor stub
		}
		
		
		public String validateHomePageTitle() {
			
			return driver.getTitle();
		}
		
		public boolean userNameDispalyed() {
			
			return userDisplay.isDisplayed();
		}
		
		public boolean logoDispalyed() {
			
			return logoDisplay.isDisplayed();
		}
		
		public boolean emailDisplayed() {
			
			calendar.click();
			boolean emailDis=driver.findElement(By.xpath("//label[contains(text(),'"+prop.getProperty("username")+"')]")).isDisplayed();
			
			return emailDis;
		}
}

