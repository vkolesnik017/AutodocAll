package ATD.Orders_AWS_Delivery.QC_3511_CheckingThePercentageOfPaid_UnpaidOrders;

import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Common.CommonMethods.getExpectedCalendarData;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3513 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the percentage of paid/unpaid orders Braintree")
    public void testCheckingThePercentageOfPaid_UnpaidOrders_Braintree() {
        SearchOrders_page_aws searchOrdersPageAws = new SearchOrders_page_aws();
        String data = getExpectedCalendarData("yyyy-MM-dd", 0, 0);
        searchOrdersPageAws.openSearchPageWithExpectedData(data, data)
                .chooseProjectInSelector(searchOrdersPageAws.projectDesktopInSelector())
                .chooseProjectInSelector(searchOrdersPageAws.projectMobileInSelector())
                .choosePaymentsMethods("Braintree")
                .clickSearchBtn()
                .checkPercentageOfPaidOrders();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
