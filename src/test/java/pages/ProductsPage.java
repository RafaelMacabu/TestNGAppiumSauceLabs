package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends MenuPage{
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement productPageTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private WebElement backpackTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private WebElement backpackPrice;
    public static ProductsPage action(){
        return new ProductsPage();
    }


    public String getTitle(){
       return getText(productPageTitle);

    }

    public String getSLBTitle(){
        return getText(backpackTitle);

    }

    public String getSLBPrice(){
        return getText(backpackPrice);

    }

    public ProductsDetailsPage clickSLBTitle(){
        click(backpackTitle);
        return new ProductsDetailsPage();
    }


}
