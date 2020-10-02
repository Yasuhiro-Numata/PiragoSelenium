package pageObjects.initializePageObjects;

import controllers.BaseMethod;
import controllers.InitMethod;
import controllers.WebDriverManager;
import org.openqa.selenium.support.PageFactory;
import pageObjects.pages.*;

public class PageFactoryInitializer extends InitMethod {

    public void openUrlDefault() {
        WebDriverManager.getDriver().get(InitMethod.appConfig.getWebsiteUrl());
        setMaxPageLoadTime();
        implicitlywait();
        try {
            WebDriverManager.getDriver().manage().window().maximize();
        } catch (Exception ex) {
            // do nothing
        }
    }
    /* Open defaulte URL */

    /* Open custom URL */
    public void openUrl(String url) {
        WebDriverManager.getDriver().get(url);
        implicitlywait();
        setMaxPageLoadTime();
        try {
            WebDriverManager.getDriver().manage().window().maximize();
        } catch (Exception ex) {
            // do nothing
        }
    }

    public LoginPage loginPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), LoginPage.class);
    }

    public HeaderPage headerPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), HeaderPage.class);
    }

    public EmployeeManagementPage employeeManagementPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), EmployeeManagementPage.class);
    }

    public TodoListPage todoListPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), TodoListPage.class);
    }

    public QuotationPage quotationPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), QuotationPage.class);
    }

    public OperationPage operationPage() {
        return PageFactory.initElements(WebDriverManager.getDriver(), OperationPage.class);
    }

}