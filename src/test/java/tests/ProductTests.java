package tests;

import base.BasePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsDetailsPage;
import pages.ProductsPage;

public class ProductTests extends BasePage {

    @Test
    public void validateProductOnProductsPage(){
        SoftAssert softAssert = new SoftAssert();
        ProductsPage productsPage = LoginPage.
                action().
                login("standard_user","secret_sauce");

        softAssert.assertEquals(productsPage.getSLBTitle(),"Sauce Labs Backpack");
        softAssert.assertEquals(productsPage.getSLBPrice(),"$29.995");

        softAssert.assertAll();


    }

    @Test
    public void validateProductOnProductDetailsPage(){
        SoftAssert softAssert = new SoftAssert();
        LoginPage.
                action().
                login("standard_user","secret_sauce");

        ProductsDetailsPage detailsPage = ProductsPage.action()
                .clickSLBTitle();

        softAssert.assertEquals(detailsPage.getSLBCartTitle(),"Sauce Labs Backpack");
        softAssert.assertEquals(detailsPage.getSLBCartDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        detailsPage.
                scrollToPrice();

        softAssert.assertEquals(detailsPage.getSLBPrice(),"$29.99");

        softAssert.assertAll();



    }
}
