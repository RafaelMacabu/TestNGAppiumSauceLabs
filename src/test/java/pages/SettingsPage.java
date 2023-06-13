package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {
    @AndroidFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutButton;

    public LoginPage clickLogoutBttn(){
        click(logoutButton);
        return new LoginPage();
    }
}
