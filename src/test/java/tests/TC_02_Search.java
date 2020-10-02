package tests;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_02_Search extends PageFactoryInitializer {
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_01_SearchJava() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        headerPage()
                .searchJava();
    }

    @Test
    public void TC_02_DisplayOpportunityList(){
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        headerPage()
                .checkDisplayOpportunityList();
    }

    @Test
    public void TC_03_DisplayProjectList() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        headerPage()
                .checkDisplayProjectList();
    }

    
}
