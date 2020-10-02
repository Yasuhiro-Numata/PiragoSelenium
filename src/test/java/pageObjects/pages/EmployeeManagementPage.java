package pageObjects.pages;

import controllers.BaseMethod;
import controllers.InitMethod;
import controllers.WebDriverManager;
import model.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.Log;

public class EmployeeManagementPage extends BaseMethod {
    private Account account = testDataModel.getAccount();

    @FindBy(linkText = "解説動画はコチラ≫")
    public WebElement txtVideo;

    @FindBy(css = "div:nth-of-type(3) > label")
    public WebElement videoList;

    @FindBy(xpath = "/html/body/div[3]/div[1]/div[3]/table/tbody/tr[1]//span[@title='削除']")
    public WebElement iconDelete;


    public EmployeeManagementPage checkDisplayVideoList() throws InterruptedException {
        assertIsDisplayed(txtVideo, InitMethod.SLEEP_1000_MS);
        click(txtVideo);
        Thread.sleep(InitMethod.SLEEP_2000_MS);
        switchToWindowByTitle(WebDriverManager.getDriver(), "検索結果|SESクラウド");
        assertIsDisplayed(videoList, InitMethod.SLEEP_3000_MS);
        WebElement frameElement=getWebDriver().findElement(By.xpath("/html/body/div[4]/div[1]//iframe[@src='https://www.youtube.com/embed/qKNoDQZSUs8']"));
        getWebDriver().switchTo().frame(frameElement);
        getWebDriver().findElement(By.cssSelector(".ytp-button.ytp-large-play-button")).click();
        Thread.sleep(InitMethod.SLEEP_5000_MS);
        WebElement video = getWebDriver().findElement(By.cssSelector(".ytp-progress-bar-container > div[role='slider']"));
        String duration = video.getAttribute("aria-valuenow");
        System.out.println("------------------duration: "+duration);
        return this;
    }

    public EmployeeManagementPage deleteProject() throws Exception {
        Thread.sleep(InitMethod.SLEEP_3000_MS);
        WebElement valueTable = getWebDriver().findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/table/tbody"));
        String beforeDelete = valueTable.getAttribute("innerHTML");
        assertIsDisplayed(iconDelete, InitMethod.SLEEP_1000_MS);
        click(iconDelete);
        Thread.sleep(InitMethod.SLEEP_5000_MS);
        String alertMessage= getWebDriver().switchTo().alert().getText();
        assertEquals("削除してよろしいですか？", alertMessage);
        alertAccept();
        Thread.sleep(InitMethod.SLEEP_3000_MS);
        String afterDelete = valueTable.getAttribute("innerHTML");
        Assert.assertNotEquals(beforeDelete, afterDelete);
        return this;
    }
}
