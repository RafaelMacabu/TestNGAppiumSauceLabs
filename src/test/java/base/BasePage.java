package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.ProductsPage;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BasePage {
    static protected AppiumDriver driver;
    protected Properties prop;
    protected InputStream inputStream;
    protected static String dateTime;

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
    }

    public String getProps(String property) {
        return prop.getProperty(property);
    }



    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setDriver(AppiumDriver driver) {
        BasePage.driver = driver;
    }

    public static String getDatetime(){
        return dateTime;
    }

    @Parameters({"platformName","udid"})
    @BeforeMethod
    public void beforeMethod(String platformName,String udid) throws IOException {
        dateTime = TestUtils.getDateTime();
        prop = new Properties();
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        HashMap<String,String> caps = new HashMap<>();
        caps.put(MobileCapabilityType.PLATFORM_NAME,platformName);
        caps.put(MobileCapabilityType.UDID,udid);
        caps.put(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty("androidAutomationName"));
        //caps.put(MobileCapabilityType.APP,"D:\\Software Estudos APPIUM\\apps\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        caps.put("appPackage",prop.getProperty("androidAppPackage"));
        caps.put("appActivity",prop.getProperty("androidAppActivity"));
        caps.put("adbExecTimeout","6000000");
        UiAutomator2Options options = new UiAutomator2Options(caps);

        URL url = new URL(prop.getProperty("appiumURL"));

        driver = new AndroidDriver(url,options);
    }

    public void waitForVisibility(WebElement e){
        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));

    }

    public void click(WebElement e ){
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e,String text){
        waitForVisibility(e);
        e.sendKeys(text);
    }

    public String getText(WebElement e){
        waitForVisibility(e);
        return e.getText();
    }

    public void closeApp(){
        ((InteractsWithApps) getDriver()).terminateApp(prop.getProperty("androidAppPackage"));
    }

    public WebElement scrollToElement(){
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollIntoView(" + "new UiSelector().description(\"test-Price\"));"));

    }

    @AfterMethod
    public void afterMethod(){
        closeApp();
        driver.quit();
    }

}
