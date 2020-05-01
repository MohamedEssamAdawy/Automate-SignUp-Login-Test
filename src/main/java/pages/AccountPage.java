package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AccountPage {
    private WebDriver driver;
    private final By dropdown = By.className("dropdown-login");
    private final By logOutButton = By.xpath("//a[.='Logout']");
    private final By greetingMsg = By.cssSelector(".col-md-8 > h3");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get Greeting Message from Account Page")
    public String getGreetingMsg() {
        return driver.findElement(greetingMsg).getText();
    }

    @Step("LogOut Now!!!")
    public LoginPage selectLogOutFromDropDown() {
        WebElement dropdownElement = driver.findElement(dropdown);
        Actions action = new Actions(driver);
        action.moveToElement(dropdownElement).click().perform();
        WebElement logoutElement = driver.findElement(logOutButton);
        action.moveToElement(logoutElement).click().perform();
        return new LoginPage(driver);
    }
}
