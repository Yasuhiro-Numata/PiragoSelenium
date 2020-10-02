package tests;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_06_Operation extends PageFactoryInitializer{
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_06_Scroll() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        operationPage()
                .scroll();
    }
}
