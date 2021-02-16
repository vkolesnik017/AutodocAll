package ATD.Orders_AWS_Delivery.QC_2994_FilterByOrdersWithPaylinksInOrderSearch;

import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static AWS.SearchOrders_page_aws.randomAssemblyWarehouse;
import static AWS.SearchOrders_page_aws.randomPaymentsMethod;
import static Common.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3005 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "filtersForPKW", parallel = true)
    Object[] dataFiltersForPKW() {
        return new Object[][]{
                {"choosePaymentsMethods"},
                {"chooseCurrency"},
                {"chooseAssemblyWarehouse"},
                {"fillingFieldCustomerInfo"}
        };
    }

    @Test(dataProvider = "filtersForPKW")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the operation of the search using the Group Field filter: Link Surcharges + \n" +
            "+ date + project 'PKW' in AWS")
    public void testCheckingSearchByFilter_PKWProject(String filters) {
        String paymentName = randomPaymentsMethod();
        String assemblyWarehouse = randomAssemblyWarehouse();
        String data = getExpectedCalendarData("yyyy-MM-dd", 2, 5);
        new SearchOrders_page_aws().openSearchPageWithExpectedDataFrom(data)
                .selectExpectedGroupField("Линк Доплаты")
                .chooseProjectInSelector("pkw")
                .choosingAllExpectedFilter(filters, paymentName, "EUR", assemblyWarehouse, "jeniachekamasov@gmail.com")
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @Test(dataProvider = "filtersForPKW")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the operation of the search using the Group Field filter: Link Surcharges + \n" +
            "+ date + project 'ATD' in AWS")
    public void testCheckingSearchByFilter_ATDProject(String filters) {
        String paymentName = randomPaymentsMethod();
        String assemblyWarehouse = randomAssemblyWarehouse();
        String data = getExpectedCalendarData("yyyy-MM-dd", 2, 5);
        new SearchOrders_page_aws().openSearchPageWithExpectedDataFrom(data)
                .selectExpectedGroupField("Линк Доплаты")
                .chooseProjectInSelector("atd")
                .choosingAllExpectedFilter(filters, paymentName, "EUR", assemblyWarehouse, "jeniachekamasov@gmail.com")
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
