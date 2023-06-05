package pages;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.PipedOutputStream;

public class ProductsPage extends BaseTest {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView") private WebElement productPageTitle;

    public static ProductsPage action(){
        return new ProductsPage();
    }


    public String getTitle(){
       return getText(productPageTitle);

    }


}
