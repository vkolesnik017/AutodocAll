package ATD.Orders_AWS_Delivery.QC_2336_BlackList;

import AWS.OrderAdd_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2358 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Order hitting status 65 (Blacklist) when ordering from AWS")
    public void testCheckingOrderHittingStatusBL_whenOrderingFromAWS() {
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID("15211254")
                .chooseSkinInSelector("autodoc.de (DE)")
                .selectedPaymentMethod("Vorkasse")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("z306")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("Z306")
                .clickSaveOrderBtnAndCheckThatThisAccountInBlackList()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .reSaveOrder()
                .checkAndClosePopUpBlackList()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
