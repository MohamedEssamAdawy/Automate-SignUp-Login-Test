package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class TestNGListenerReporter extends TestListenerAdapter {
    // Make sure to enable use default reporters in Listeners tab in Run\Debug Configuration with IntelliJ

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getDriver();
        var camera = (TakesScreenshot) webDriver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File screenshotName =new File("resources/screenshots/" + iTestResult.getName()
                +"_"+ timeStamp + ".png");
        try {
            Files.move(screenshot,screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("Failure ScreenShot Added");
        Reporter.log("<br><img src='"+screenshotName.getAbsolutePath()+"' height='500' width='890'/><br>");
        Reporter.log("Fail TestCase @ " + timeStamp);
        super.onTestFailure(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        Reporter.log("Success TestCase @ " + timeStamp);
        super.onTestSuccess(iTestResult);
    }
}
