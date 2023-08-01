package base;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ProductsPage;
import utils.TestUtils;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BasePage {
    static protected ThreadLocal <AppiumDriver> driver = new ThreadLocal<>();
    static protected ThreadLocal <Properties> prop = new ThreadLocal<>();
    static protected ThreadLocal <InputStream> inputStream = new ThreadLocal<>();
    static protected ThreadLocal <String> dateTime = new ThreadLocal<>();
    static Logger log = LogManager.getLogger(BasePage.class.getName());
    private static AppiumDriverLocalService server;

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
    }

    public InputStream getInputStream(){
        return inputStream.get();
    }

    public void setInputStream(InputStream is){
        inputStream.set(is);
    }

    public static Properties getProps() {
        return prop.get();
    }

    public static void setProps(Properties props) {
        prop.set(props);
    }


    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver driver) {
        BasePage.driver.set(driver);
    }

    public static String getDatetime(){
        return dateTime.get();
    }

    public static void setDateTime(String date){
        dateTime.set(date);
    }

    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService getAppiumService(){
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                        .usingPort(4723)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withLogFile(new File("ServerLogs\\server.log"))
                        .withTimeout(Duration.ofSeconds(600))
        );
    }

    @BeforeSuite
    public void beforeSuite(){
        server = getAppiumService();
        if(!server.isRunning()){
            server.start();
        }

    }

    @AfterSuite
    public void afterSuite(){
        server.stop();
    }


    @Parameters({"platformName","udid","systemPort"})
    @BeforeMethod
    public synchronized void beforeMethod(String platformName,String udid,@Optional String systemPort) throws IOException {
        Properties props = new Properties();
        InputStream inputStream;
        AppiumDriver driver;
        setDateTime(TestUtils.getDateTime());

        log.info("This is a info message");
        log.error("This is a error message");
        log.warn("This is a warn message");

        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        props.load(inputStream);
        setProps(props);

        HashMap<String,String> caps = new HashMap<>();
        caps.put(MobileCapabilityType.PLATFORM_NAME,platformName);
        caps.put(MobileCapabilityType.UDID,udid);
        caps.put("systemPort",systemPort);
        caps.put(MobileCapabilityType.AUTOMATION_NAME,getProps().getProperty("androidAutomationName"));
        //caps.put(MobileCapabilityType.APP,"D:\\Software Estudos APPIUM\\apps\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        caps.put("appPackage",getProps().getProperty("androidAppPackage"));
        caps.put("appActivity",getProps().getProperty("androidAppActivity"));
        caps.put("adbExecTimeout","6000000");
        UiAutomator2Options options = new UiAutomator2Options(caps);

        URL url = new URL(getProps().getProperty("appiumURL"));

        setDriver(new AndroidDriver(url,options));
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
        ((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("androidAppPackage"));
    }

    public WebElement scrollToElement(){
        return getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollIntoView(" + "new UiSelector().description(\"test-Price\"));"));

    }

    @AfterMethod
    public void afterMethod(){
        closeApp();
        getDriver().quit();
    }

}
