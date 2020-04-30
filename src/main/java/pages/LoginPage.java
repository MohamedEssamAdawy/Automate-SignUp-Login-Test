package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        // Check that it is a valid email address
        return emailAddress.contains("@");
    }
    @Step("Set Password")
    public boolean setPasswordField(String password) {
        // Check for Empty or Null String
        if (password == null || password.length() == 0)
            return false;
        driver.findElement(passwordField).sendKeys(password);
        boolean isSmallLetter = false;
        boolean isCapitalLetter = false;
        // Check that the password length limited to 8 Char
        if (password.length() > 8)
            return false;
        // Check that the password contains at least one capital and small letter
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                isSmallLetter = true;
            } else if (Character.isUpperCase(password.charAt(i))) {
                isCapitalLetter = true;
            }
        }
        return isCapitalLetter && isSmallLetter;
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
