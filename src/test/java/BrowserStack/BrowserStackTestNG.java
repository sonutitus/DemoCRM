package BrowserStack;

import com.browserstack.local.Local;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BrowserStackTestNG {
    public WebDriver driver;
    private Local l;

    @BeforeMethod(alwaysRun=true)
    @org.testng.annotations.Parameters(value={"config", "environment"})
    public void setUp(String config, String environment) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject configs = (JSONObject) parser.parse(new FileReader("src\\main\\java\\com\\crm\\qa\\config" + config));
        JSONObject envs = (JSONObject) configs.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) configs.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) configs.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) configs.get("key");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
          capabilities.setCapability("app", app);
        }

        if(capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true"){
            l = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            l.start(options);
        }

        driver = new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@"+configs.get("server")+"/wd/hub"), capabilities);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
        if(l != null) l.stop();
    }
}
