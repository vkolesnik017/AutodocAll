package ATD.PrivateRoom.QC_868_DepositTabFunctionalInPR;

import ATD.Main_page_Logic;
import Common.SetUp;
import AWS.Customer_view_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_925_DepositTapElementPR {

    private String mail = "QC_868_depositTestATD@mailinator.com";
    private String customerId = "15579051";
    private Float  sumDepositInAws, sumDepositInDepositPage;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks for elements of the Deposit tab")
    public void testDepositTabElementPR(String route) throws SQLException {
        sumDepositInAws = new Customer_view_aws().openCustomerPersonalArea(customerId)
                .checkPresenceCustomerDepositTable()
                .getDepositBalanceAfterLastCrediting();
        openPage(route);
        sumDepositInDepositPage = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToDepositPage()
                .checkForTextInBlockTopTitle("Mein AUTODOC")
                .checkPresenceClientID()
                .checkPresenceHeaderBlockAndElementInside()
                .checkPresenceTitlePage()
                .checkPresenceColumnAndDataInsideTableWithDepositTransaction()
                .getDepositBalance();
        Assert.assertEquals(sumDepositInAws, sumDepositInDepositPage);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}