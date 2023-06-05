package tests;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class test1 extends BaseTest {

    By errorMessage = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    By usernameTxtField = AppiumBy.accessibilityId("test-Username");
    By passwordTxtField = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");


    @Test
    public void invalidLoginTest(){
        LoginPage.action().
                enterUsername("fodase").
                enterPassword("fodase").
                clickLoginButton();


        Assert.assertEquals(LoginPage.action().getErrorText(),"Username and password do not match any user in this service.");

    }

    @Test
    public void invalidPasswordTest(){

        LoginPage.action().
                enterUsername("standard_user").
                enterPassword("fodase").
                clickLoginButton();

        Assert.assertEquals(LoginPage.action().getErrorText(),"Username and password do not match any user in this service.");




    }

    @Test
    public void successfulLoginTest(){
        LoginPage.action().
                enterUsername("standard_user").
                enterPassword("secret_sauce").
                clickLoginButton();

        Assert.assertEquals(ProductsPage.action().getTitle(),"PRODUCTS");


    }
}
