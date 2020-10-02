package tests;

import model.Account;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TC_05_Quotation extends PageFactoryInitializer{
    private Account account = testDataModel.getAccount();

    @Test
    public void TC_05_ExportPDFFile() throws InterruptedException {
        openUrlDefault();
        loginPage()
                .login(account.getUserName(), account.getPass());
        quotationPage()
                .exportPDFFile();
    }
}
