package pageObjects.pages;

import controllers.BaseMethod;
import controllers.InitMethod;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class OperationPage extends BaseMethod {

    @FindBy(css = ".current")
    public WebElement txtContractManagement;

    @FindBy(linkText = "稼働")
    public WebElement txtOperation;

    public OperationPage scroll() throws InterruptedException {
        for (int i = 1; i < 6; i++) {
            Actions action = new Actions(getWebDriver());
            action.moveToElement(txtContractManagement).build().perform();
            click(txtOperation);
            Thread.sleep(InitMethod.SLEEP_2000_MS);
            saveScreenShotFullPage("7-1-1", InitMethod.SCREENSHOT_PATH + "Testcase-7.1");
            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(2000,0)");
            Thread.sleep(InitMethod.SLEEP_2000_MS);
            saveScreenShotFullPage("7-1-2", InitMethod.SCREENSHOT_PATH + "Testcase-7.1");
        }
        return this;
    }

}
