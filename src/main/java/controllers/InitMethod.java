/**
 *
 */
package controllers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import model.TestDataModel;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import utils.JsonCacheUtils;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class InitMethod {
    public static ApplicationConfigReader appConfig = new ApplicationConfigReader();
    public static TestDataModel testDataModel = JsonCacheUtils.get().getResult("src/main/resources/test_data.json", TestDataModel.class);
    public static String WebsiteURL = appConfig.getWebsiteUrl();
    //    public static String Browser = appConfig.getBrowser();
    public static int MaxPageLoadTime = appConfig.getMaxPageLoadTime();
    public static int ImplicitlyWait = appConfig.getImplicitlyWait();

    public static String FS = File.separator;

    public static String OSName = System.getProperty("os.name");
    public static String OSArchitecture = System.getProperty("os.arch");
    public static String OSVersion = System.getProperty("os.version");
    public static String OSBit = System.getProperty("sun.arch.data.model");

    public static String ProjectWorkingDirectory = System.getProperty("user.dir");

    public static String TestData = "./src/test/resources/TestData/";
    public static String ExcelFiles = "./src/test/resources/TestData/Excel Files/";
    public static String PropertiesFiles = "./src/test/resources/TestData/Properties Files/";
    public static String Reports = "./src/test/resources/Reports/";
    public static String Images = "./src/test/resources/Reports/Images/";
    public static String Videos = "./src/test/resources/Reports/Videos/";
    public static final long processTimestamp =  System.currentTimeMillis();
    public static String ReportZipFilePath = Reports + "data_" + processTimestamp + ".zip";
    public static String ReportRootPath = Reports + "reportData/";
    public static String ReportErrorCaptured = ReportRootPath + "errorCaptured/";
    public static String DiffScreenShotLogFile = ReportRootPath + "diffScreenShotLog.txt";
    public static String AutoTestLogFile = ReportRootPath + "autoTestResult.txt";
    public static String ReportTestNGPath = ReportRootPath + "surefire-reports";

    public static String AwsS3Region = "ap-northeast-1";
    public static String AwsS3BucketName = "agmiru-autotest-report";

    public static Robot re;
    public static Alert al;
    public static String robotImageName;
    public static Select se;
    public static String FileToUpload;
    public static Actions ac;
    public static ITestResult testResult;
    public static SoftAssert softAssert;
    public static ITestResult result;
    public static URI uri;

    public static final String OUTPUT_FOLDER = "./src/test/resources/Reports/";

    //Config for test
    public static final int WAIT_TIME_1 = 1;
    public static final int WAIT_TIME_5 = 5;
    public static final int WAIT_TIME_10 = 10;
    public static final int WAIT_TIME_15 = 15;
    public static final int WAIT_TIME_30 = 30;
    public static final int WAIT_TIME_300 = 300;
    
    public static final int SLEEP_500_MS = 500;
    public static final int SLEEP_1000_MS = 1000;
    public static final int SLEEP_2000_MS = 2000;
    public static final int SLEEP_3000_MS = 3000;
    public static final int SLEEP_5000_MS = 5000;

    public static final String SCREENSHOT_PATH = ReportRootPath + "screenshots" + File.separator;
    
//    public static final String PATH = System.getProperty("user.dir") + File.separator + "screenshots"+File.separator;

    String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    public String FILE_NAME = "ExtentReport" + date + ".html";
    public ExtentReports extent;
    public ISuite suite;
    public ISuiteResult res;
    public ExtentTest test;

    public static void implicitlywait() {
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(ImplicitlyWait, TimeUnit.SECONDS);
    }

    public static void setMaxPageLoadTime() {
        WebDriverManager.getDriver().manage().timeouts().pageLoadTimeout(MaxPageLoadTime, TimeUnit.SECONDS);
    }

    public static String BASE64_IMAGE = "data:image/png;base64,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"
    		+ "gg==";

}
