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
        WebElement checkList1 = getWebDriver().findElement(By.xpath("/html/body/div[3]/div[7]/div[3]/table/tbody/tr[1]"));
        String content = checkList1.getAttribute("readonly");
        String priority = checkList1.getAttribute("style");
        assertIsDisplayed(btnEdit, InitMethod.SLEEP_1000_MS);
        click(btnEdit);
        Thread.sleep(InitMethod.SLEEP_3000_MS);
        WebElement checkList2 = getWebDriver().findElement(By.xpath("/html/body/div[3]/div[7]/div[3]/table/tbody/tr[1]"));
        String content1 = checkList2.getAttribute("readonly");
        String priority1 = checkList2.getAttribute("style");
        Assert.assertNotEquals(content, content1);
        Assert.assertNotEquals(priority, priority1);
        return this;
    }
}
