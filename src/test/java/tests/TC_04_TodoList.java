package tests;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_04_TodoList extends PageFactoryInitializer{
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_04_GoToEditState() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        todoListPage()
                .goToEditState();
    }
}
