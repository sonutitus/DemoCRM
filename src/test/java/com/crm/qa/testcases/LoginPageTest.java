package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	public LoginPageTest() throws Exception {
		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		initilization();
		loginPage = new LoginPage();
		logger=Logger.getLogger("LoginPageTest");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=0)
	public void loginPageTitleTest() {
				
	    logger.info("Calling method validateLoginPageTitle() and storing in STRING title");
		String title = loginPage.validateLoginPageTitle();
		System.out.println("title is "+title);
		logger.info("After getting value in STRING title, now going to comapre using Assert");
		Assert.assertEquals(title, "Cogmento CRM");
		logger.info("Asserting finished");
	}
	
	@Test(priority=1)
	public void loginFuctionalityTest() throws Exception {
		
		logger.info("Calling method loginFuctionalityTest() fuctionality checking for login");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		logger.info("Fininshed checking functionality");
		
	}
}
