package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class MenuPage extends BasePage {
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement privateSettingsButton;

    public static MenuPage action(){
        return new MenuPage();
    }

    public SettingsPage clickSettingsBttn(){
        click(privateSettingsButton);
        return new SettingsPage();
    }
}
