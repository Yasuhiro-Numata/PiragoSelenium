package listeners;


import controllers.ApplicationConfigReader;
import controllers.InitMethod;
import controllers.WebDriverManager;
import controllers.localdriver.LocalDriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalWebDriverListener extends WebDriverListenerBase implements IInvokedMethodListener {

    static Logger log = Logger.getLogger(LocalWebDriverListener.class);
    String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

//    @BeforeMethod
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: listeners.LocalWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            browserName = browserName == null ? "chrome" : browserName;

            // get and set new instance of localdriver WebDriver
            System.out.println("=== Start with " + browserName + " Browser ===>");
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            WebDriverManager.setWebDriver(driver);
            log.info("===== Start test name " + testResult.getTestContext().getCurrentXmlTest().getName() + " =====");
            log.info("===== Start method name " + testResult.getMethod().getMethodName() + " =====");
        }
        log.debug("END: listeners.LocalWebDriverListener.beforeInvocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: listeners.LocalWebDriverListener.afterInvocation");
        if (method.isTestMethod()) {
            String browser = WebDriverManager.getBrowserInfo();
            try {
                // change the name of the test method that will appear in the report to one that will contain
                // also browser name, version and OS.
                // very handy when analysing results.
                BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
                log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("ex:\n" + ex.getMessage());
            } finally {

                // close the browser
                WebDriver driver = WebDriverManager.getDriver();

                try {
                    Thread.sleep(InitMethod.SLEEP_1000_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (testResult.getStatus() == ITestResult.FAILURE) {
                    try {
                    	// TODO: change path
                        saveFullPageScreenshot(testResult, driver);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                log.info("===== End test name " + testResult.getTestContext().getCurrentXmlTest().getName() + " =====\n");
                log.info("===== End method name " + testResult.getMethod().getMethodName() + " =====\n");
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        log.debug("END: listeners.LocalWebDriverListener.afterInvocation");
    }
}
