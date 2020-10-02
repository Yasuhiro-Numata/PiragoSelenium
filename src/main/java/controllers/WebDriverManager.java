package controllers;


import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {

    public static ThreadLocal<WebDriver> remoteWebDriver = new ThreadLocal<WebDriver>();
    static Logger log;

    static {
        log = Logger.getLogger(WebDriverManager.class);
    }

    public static WebDriver getDriver() {
        log.debug("Getting instance of remote driver");
        return remoteWebDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        log.debug("Setting the driver");
        remoteWebDriver.set(driver);
    }

    public static String getBrowserInfo() {
        log.debug("Getting browser info");
        Capabilities cap = ((RemoteWebDriver) WebDriverManager.getDriver()).getCapabilities();
        String b = cap.getBrowserName();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion();
        return String.format("Browser %s version:%s System %s", b, v, os);
    }

    public static String getBrowserName() {
        log.debug("Getting browser info");
        Capabilities cap = ((RemoteWebDriver) WebDriverManager.getDriver()).getCapabilities();
        return cap.getBrowserName();
    }
}
