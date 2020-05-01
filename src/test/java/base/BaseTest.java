package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.RegisterPage;
import utils.EventReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private EventFiringWebDriver driver;
    protected RegisterPage registerPage;

    public EventFiringWebDriver getDriver() {
        return driver;
    }

    @BeforeClass(description = "Set up the WebDriver")
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        // Use EventFiringWebDriver to use EventReporter class
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());

        //Implicit TimeOut to make sure that all elements will be available
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goHome();
    }

    @BeforeMethod(description = "Go to the home page before each method")
    public void goHome() {
        driver.get("https://www.phptravels.net/register");
        registerPage = new RegisterPage(driver);
    }

    @AfterClass(description = "Quit the Web Driver")
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite(description = "Open Allure reports")
    public void openReports() throws IOException, InterruptedException {
        // Open Allure Report
        String allureFolderPath = System.getProperty("user.dir");
        System.out.println(allureFolderPath);
        String generate = "cmd /c start \"\" allure serve " + allureFolderPath + "\\allure-results";
        Process runtimeProcess = Runtime.getRuntime().exec(generate);
        int processComplete = runtimeProcess.waitFor();
    }

    // Setup Chrome options
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // Start Chrome maximized
//        options.addArguments("headless");      // Run test cases without open the browser
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false); // Hide Automation Bar
        return options;
    }


    public static void main(String[] args) throws InterruptedException {
        BaseTest test = new BaseTest();
        test.setUp();
    }
}
