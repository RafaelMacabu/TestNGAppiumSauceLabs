package tests;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 extends BaseTest {

    By errorMessage = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    By usernameTxtField = AppiumBy.accessibilityId("test-Username");
    By passwordTxtField = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");


    @Test
    public void invalidLoginTest(){

        getDriver().findElement(usernameTxtField).sendKeys("SACOSUADO");
        getDriver().findElement(passwordTxtField).sendKeys("SEXO");
        getDriver().findElement(loginButton).click();


        Assert.assertEquals(getDriver().findElement(errorMessage).getText(),"Username and password do not match any user in this service.");

    }

    @Test
    public void invalidPasswordTest(){

        getDriver().findElement(usernameTxtField).sendKeys("standard_user");
        getDriver().findElement(passwordTxtField).sendKeys("SEXO");
        getDriver().findElement(loginButton).click();

        Assert.assertEquals(getDriver().findElement(errorMessage).getText(),"Username and password do not match any user in this service.");




    }

    @Test
    public void successfulLoginTest(){
        getDriver().findElement(usernameTxtField).sendKeys("standard_user");
        getDriver().findElement(passwordTxtField).sendKeys("secret_sauce");
        getDriver().findElement(loginButton).click();

        WebElement productTitle = getDriver().findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView"));

        Assert.assertEquals(productTitle.getText(),"PRODUCTS");


    }
}
