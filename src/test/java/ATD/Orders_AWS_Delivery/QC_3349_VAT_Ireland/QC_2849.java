package ATD.Orders_AWS_Delivery.QC_3349_VAT_Ireland;

import AWS.PageVAT_aws;
import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2849 {

    private String vatForIE, orderId;
    private String mail = "QC_2849autotest@autodoc.si";
    private String customerId = "19045900";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        vatForIE = new PageVAT_aws().getVatForIE();
        close();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check VAT for Ireland when creating an order with ABC")
    public void testCheckVatForIrelandWhenCreatingOrderWithAWS() {
        orderId = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(customerId)
                .chooseSkinInSelector("autodoc.de (DE)")
                .chooseDeliveryCountry("Ireland")
                .selectedPaymentMethod("Vorkasse")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("50-307044-50")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("50-307044-50")
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .getOrderIdOfOrder();

        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderId)
                .checkTextContainingVatPercentageInEmail(vatForIE);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
