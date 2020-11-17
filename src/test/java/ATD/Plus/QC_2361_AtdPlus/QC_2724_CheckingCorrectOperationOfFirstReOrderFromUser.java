package ATD.Plus.QC_2361_AtdPlus;

import AWS.Order_aws;
import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_2724_CheckingCorrectOperationOfFirstReOrderFromUser {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }


    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the correct operation of the first re-order from the user")
    public void testCheckingCorrectOperationOfFirstReOrderFromUser() {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .choosesDateFromOneMonthAndExpectedDayEarlierThenCurrentOne(1)
                .selectExpectedGroupField("AUTODOC PLUS (Services)")
                .clickSearchBtn()
                .findOrderWithGeneratedInvoice()
                .transitionToReorderNumber();
        switchTo().window(1);
        new Order_aws().checkPresenceTransactionCodBloc()
                .checkCurrentStatusInOrder("Abgeschlossen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
