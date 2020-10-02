package pageObjects.pages;

import controllers.BaseMethod;
import model.SearchConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import controllers.InitMethod;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class HeaderPage extends BaseMethod {
    private SearchConditions searchConditions = testDataModel.getSearchConditions();

    @FindBy(linkText = "ホーム")
    public WebElement txtHome;

    @FindBy(css = "#all_search_ipt")
    public WebElement tbxSearch;

    @FindBy(css = "[onsubmit] #form_all_search_button")
    public WebElement iconSearch;

    @FindBy(css = ".container > div:nth-of-type(5)")
    public WebElement caseList;

    @FindBy(css = ".container > div:nth-of-type(6)")
    public WebElement keyMember;

    @FindBy(xpath = "//div[@id='header']/div[1]/table//tr/td[3]/input[1]")
    public WebElement iconOpportunity;

    @FindBy(xpath = "/html/body/div[3]/div[@class='container']/div[1]")
    public WebElement opportunityList;

    @FindBy(xpath = "//nav[@id='nav']/ul/li[2]")
    public WebElement txtMatching;

    @FindBy(linkText = "案件マッチング")
    public WebElement txtMatchingProject;

    @FindBy(css = ".col-lg-10.col-sm-10 > div:nth-of-type(1)")
    public WebElement projectList;

    public void searchJava() throws InterruptedException {
        assertIsDisplayed(tbxSearch, InitMethod.SLEEP_1000_MS);
        clearAndType(tbxSearch, searchConditions.getJava());
        assertIsDisplayed(iconSearch, InitMethod.SLEEP_1000_MS);
        click(iconSearch);
        assertIsDisplayed(caseList, InitMethod.SLEEP_3000_MS);
        assertIsDisplayed(keyMember, InitMethod.SLEEP_3000_MS);
        saveScreenShotFullPage("2-1-1-SearchJavaResult", InitMethod.SCREENSHOT_PATH + "Testcase-2.1");
    }

    public void checkDisplayOpportunityList(){
        assertIsDisplayed(iconOpportunity, InitMethod.SLEEP_1000_MS);
        click(iconOpportunity);
        assertIsDisplayed(opportunityList, InitMethod.SLEEP_3000_MS);
    }

    public void checkDisplayProjectList() throws InterruptedException {
        Thread.sleep(InitMethod.SLEEP_2000_MS);
        Actions action = new Actions(getWebDriver());
        action.moveToElement(txtMatching).build().perform();
        click(txtMatchingProject);
        Thread.sleep(InitMethod.SLEEP_3000_MS);
        assertIsDisplayed(projectList, InitMethod.SLEEP_3000_MS);
    }


}
