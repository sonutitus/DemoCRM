package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homepage;
	
	public HomePageTest() throws Exception {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		
		initilization();
		loginPage = new LoginPage();
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		logger=Logger.getLogger("HomePageTest");
		PropertyConfigurator.configure("Log4j.properties");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=0)
	public void verifyHomePageTitleTest() {
		
		 logger.info("Inside verifyHomePageTitleTest");
		String homepageTitle = homepage.validateHomePageTitle();
		logger.info("Inside verifyHomePageTitleTest - HomePage Title Value Stored to String and Next step Asserting Starts...");
		Assert.assertEquals(homepageTitle, "Cogmento 1CRM", "Home page title not matched");
		logger.info("Inside verifyHomePageTitleTest - Assert Finished");
	}
	
	@Test(priority=1)
	public void verifyUsernameDisplayedTest() {
		logger.info("Inside verifyUsernameDisplayedTest - Asserting Starts...");
		Assert.assertTrue(homepage.userNameDispalyed());
		logger.info("Inside verifyUsernameDisplayedTest - Assert Finished");
	}
	
	@Test(priority=2)
	public void verifyuserEmailDisplayedTest() {
		
		logger.info("Inside verifyuserEmailDisplayedTest - Asserting Starts...");
		Assert.assertTrue(homepage.emailDisplayed());
		logger.info("Inside verifyuserEmailDisplayedTest - Assert Finished");
	}
}
