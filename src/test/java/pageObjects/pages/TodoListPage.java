package pageObjects.pages;

import controllers.BaseMethod;
import controllers.InitMethod;
import model.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TodoListPage extends BaseMethod {

    @FindBy(xpath = "/html/body/div[3]/div[7]/div[3]/table/tbody/tr[1]//input[@value='編集']")
    public WebElement btnEdit;

    public TodoListPage goToEditState() throws InterruptedException {
        Thread.sleep(InitMethod.SLEEP_2000_MS);
        WebElement content = getWebDriver().findElement(By.cssSelector("input[value='ステータス']"));
        String readonly = content.getAttribute("readonly");
        Assert.assertNotNull(readonly);

        WebElement priority = getWebDriver().findElement(By.xpath("/html/body/div[3]/div[7]/div[3]/table/tbody/tr[1]//select"));
        String attP = priority.getAttribute("style");
        System.out.println("----------------"+attP);

        assertIsDisplayed(btnEdit, InitMethod.SLEEP_1000_MS);
        click(btnEdit);
        Thread.sleep(InitMethod.SLEEP_3000_MS);

        String readonly1 = content.getAttribute("readonly");
        Assert.assertNull(readonly1);
        String attP1 = priority.getAttribute("style");
        System.out.println("----------------"+attP1);

        Assert.assertNotEquals(attP, attP1);
        return this;
    }
}
