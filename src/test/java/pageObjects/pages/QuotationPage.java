package pageObjects.pages;

import controllers.BaseMethod;
import controllers.InitMethod;
import controllers.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class QuotationPage extends BaseMethod {

    @FindBy(css = ".last")
    public WebElement txtContractManagement;

    @FindBy(linkText = "見積書")
    public WebElement txtQuotation;

    @FindBy(css = "tr:nth-of-type(1) [alt='　見積書出力\\/編集']")
    public WebElement compilation;

    @FindBy(xpath = "/html/body/div[3]/div[@class='container']/div[1]//div[@class='col-xs-3']/button[2]")
    public WebElement btnPDF;


    public QuotationPage exportPDFFile() throws InterruptedException {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(txtContractManagement).build().perform();
        click(txtQuotation);
        Thread.sleep(InitMethod.SLEEP_2000_MS);
        click(compilation);
        switchToWindowByTitle(WebDriverManager.getDriver(), "検索結果|SESクラウド");
        Thread.sleep(InitMethod.SLEEP_2000_MS);
        assertIsDisplayed(btnPDF, InitMethod.SLEEP_3000_MS);
        click(btnPDF);
        return this;
    }
}
