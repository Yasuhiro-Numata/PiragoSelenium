package tests.TC_02_Header;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_02_Search extends PageFactoryInitializer {
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_01_SearchByKeyWord() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        headerPage()
                .searchJava();

    }
}
