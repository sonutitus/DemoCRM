package BrowserStack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackDemo {
	public static final String AUTOMATE_USERNAME = "sonutitus1";
	  public static final String AUTOMATE_ACCESS_KEY = "sZWKzU8EDVVyjF26PrN9";
	  public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	  public static void main(String[] args) throws Exception {
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("os", "Windows");
		  caps.setCapability("os_version", "7");
		  caps.setCapability("browser", "IE");
		  caps.setCapability("browser_version", "8.0");
		  caps.setCapability("project", "LoginForm");
		  caps.setCapability("build", "Build_2");
		  caps.setCapability("browserstack.local", "false");
		  caps.setCapability("browserstack.selenium_version", "3.5.2");

	    WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);
	    driver.get("https://www.google.com");
	    WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys("BrowserStack");
	    element.submit();
	    System.out.println(driver.getTitle());
	    driver.quit();
}
}
