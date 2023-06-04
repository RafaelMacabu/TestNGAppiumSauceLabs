package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setDriver(AppiumDriver driver) {
        BaseTest.driver = driver;
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        HashMap<String,String> caps = new HashMap<>();
        caps.put(MobileCapabilityType.PLATFORM_NAME,"android");
        caps.put(MobileCapabilityType.UDID,"291d97f2");
        caps.put(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        //caps.put(MobileCapabilityType.APP,"D:\\Software Estudos APPIUM\\apps\\AndroidSauceLabsMobileSampleapp.apk");
        caps.put("appPackage","com.swaglabsmobileapp");
        caps.put("appActivity","com.swaglabsmobileapp.SplashActivity");
        UiAutomator2Options options = new UiAutomator2Options(caps);

        URL url = new URL("http://0.0.0.0:4723");

        driver = new AndroidDriver(url,options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
