package pageObjects.pages;

import controllers.BaseMethod;
import controllers.InitMethod;
import org.openqa.selenium.By;
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
            WebElement txtContractManagement = getWebDriver().findElement(By.cssSelector(".last"));
            action.moveToElement(txtContractManagement).build().perform();
            WebElement txtOperation = getWebDriver().findElement(By.linkText("稼働"));
            txtOperation.click();
            Thread.sleep(InitMethod.SLEEP_2000_MS);
            saveScreenShotFullPage("6-1-1_"+i, InitMethod.SCREENSHOT_PATH + "Testcase-6.1");
            WebElement horizontal_scroll = getWebDriver().findElement(By.cssSelector(".fixed_table #iter_operation_:nth-of-type(1) td:nth-of-type(19) [type]"));
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", horizontal_scroll);
            Thread.sleep(InitMethod.SLEEP_2000_MS);
            saveScreenShotFullPage("6-1-2_"+i, InitMethod.SCREENSHOT_PATH + "Testcase-6.1");
        }
        return this;
    }

}
