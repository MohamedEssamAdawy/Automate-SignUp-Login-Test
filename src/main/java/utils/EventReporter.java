package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class EventReporter implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    // Using afterClickOn event to save the Http Response for the sign up api.
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        if(webElement.getText().equals("SIGN UP")) {
            try {
                // Using timeStamp to create a new file with unique name for every testcase
                String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
                URL obj = new URL(webDriver.getCurrentUrl());
                URLConnection conn = obj.openConnection();
                String logData = "";
                String logFilePath = "resources/signUpResponse/signUp" +timeStamp  + ".txt";
                // Get all headers from the response
                Map<String, List<String>> map = conn.getHeaderFields();
                // Get each header and it's value and add it to logData String
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if(entry.getKey() == null){
                        logData += "Key : " + "Status" + " ,Value : " + entry.getValue() + "\n";
                    }else{
                        logData += "Key : " + entry.getKey() + " ,Value : " + entry.getValue() + "\n";
                    }
                }
                // Export logData String to txt file to be checked later
                Files.write(Paths.get(logFilePath), logData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {
    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}
