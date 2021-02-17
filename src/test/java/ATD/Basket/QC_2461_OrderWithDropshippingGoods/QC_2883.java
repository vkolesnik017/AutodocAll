package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import AWS.Customer_view_aws;
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

public class QC_2883 {

    private final String mail = "qc_2883_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Create an order for a drop + legal entity in ABC")
    public void testCreationOrderForDropAndLegalEntity() {
        String customerID = "26096324";
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.be (BN)")
                .selectedPaymentMethod("HypoVereinsbank")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("13660609")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("HH371H1")
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkResponseInBlockLogsCompanyNumbers("success(200)");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
