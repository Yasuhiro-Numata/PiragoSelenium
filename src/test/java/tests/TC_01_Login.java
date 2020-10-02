package tests;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_01_Login extends PageFactoryInitializer{
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_01_Login(){
        openUrlDefault();
        loginPage().login(account.getUserName(), account.getPass());
    }
}
