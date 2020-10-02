package tests.TC_01_Login;

import model.Account;
import model.TestDataModel;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;
import pageObjects.pages.LoginPage;
import utils.JsonCacheUtils;
import utils.Log;

public class TC_01_Login extends PageFactoryInitializer{
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_01_Login(){
        openUrlDefault();
        loginPage().login(account.getUserName(), account.getPass());
    }

}
