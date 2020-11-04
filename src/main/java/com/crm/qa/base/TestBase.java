package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListeners;

public class TestBase {

	public static Logger logger;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListeners eventListeners;
	
	public static final String AUTOMATE_USERNAME = "sonutitus1";
    public static final String AUTOMATE_ACCESS_KEY = "sZWKzU8EDVVyjF26PrN9";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	public TestBase() throws Exception{
		
		try {
			prop =new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initilization() throws Exception {
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\\\chrome\\\\chromedriver.exe");
//			driver=new ChromeDriver();
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "8");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "latest");
			caps.setCapability("project", "LoginForm");
			caps.setCapability("build", "Build_4");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.14.0");



		  driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		  wait = new WebDriverWait(driver, 30);
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListeners = new WebEventListeners();
		e_driver.register(eventListeners);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
}
