package ATD.Orders_AWS_Delivery.QC_2994_FilterByOrdersWithPaylinksInOrderSearch;

import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3001_CheckingSearchBy_GroupField_FilterLinkSurcharges_CustomerInfo {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the filter search operation Group field: Link Surcharges + Client Info in AWS")
    public void testCheckingSearchBy_GroupField_Filter() {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .fillingFieldCustomerInfo("jeniachekamasov@gmail.com")
                .clickSearchBtn()
                .clickOnRandomOrderID(4);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
