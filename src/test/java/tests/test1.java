package tests;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class test1 extends BasePage {


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
