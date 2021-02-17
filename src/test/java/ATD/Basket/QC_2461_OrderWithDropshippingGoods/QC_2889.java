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

public class QC_2889 {

    private final String mail = "qc_2889_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Creation of a combined order drop + regular product + legal entity in AWS")
    public void testCreationCombinedOrder() {
        String customerID = "26080355";
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.be (BN)")
                .selectedPaymentMethod("HypoVereinsbank")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("13660609")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("HH371H1")
                .addProduct("n501")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("N501")
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .checkVatStatusInOrder("Mit MwSt 21%", "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkPresenceVatInSellingPrice("13660609", "inkl.21% VAT")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 21%", "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkPresenceVatInSellingPrice("13660609", "inkl.21% VAT")
                .clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkResponseInBlockLogsCompanyNumbers("success(200)");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
