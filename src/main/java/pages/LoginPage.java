package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public class LoginPage {
    private WebDriver driver;
    private final By userNameField = By.xpath("//input[@name = 'username']");
    private final By passwordField = By.xpath("//input[@name = 'password']");
    private final By loginButton = By.className("loginbtn");
    private final By cookiesButton = By.className("cc-btn");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Set Email Address")
    public boolean setEmailField(String emailAddress) {
        // Check for Empty or Null String
        if (emailAddress == null || emailAddress.length() == 0)
            return false;
        driver.findElement(userNameField).sendKeys(emailAddress);

        // Check that it is a valid email address using regex
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return Pattern.matches(regex,emailAddress);
    }
    @Step("Set Password")
    public boolean setPasswordField(String password) {
        if (password == null || password.length() == 0)
            return false;
        driver.findElement(passwordField).sendKeys(password);
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{1,8}$";
        return Pattern.matches(regex, password);
    }
    @Step("Login Now!")
    public AccountPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new AccountPage(driver);
    }
    @Step("Hide CookiesBar")
    public void clickCookiesButtonIfExist(){
        WebElement cookiesBar = driver.findElement(cookiesButton);
        if(cookiesBar.isDisplayed()){
            driver.findElement(cookiesButton).click();
        }
    }
}
