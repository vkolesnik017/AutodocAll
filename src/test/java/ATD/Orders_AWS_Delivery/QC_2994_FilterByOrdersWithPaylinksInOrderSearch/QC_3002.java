package ATD.Orders_AWS_Delivery.QC_2994_FilterByOrdersWithPaylinksInOrderSearch;

import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3002 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + OrderId in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_OrderId() {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .fillingFieldOrderId("39571519")
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @DataProvider(name = "shopName")
    Object[] shopName() {
        return new Object[][]{
                {"pkwteile.at (AT)"},
                {"auto-doc.at (AT)"}
        };
    }

    @Test(dataProvider = "shopName")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + Project filter in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_ProjectFilter(String shopName) {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .chooseShopInSelector(shopName)
                .clickSearchBtn()
                .clickOnRandomOrderID(1);

    }

    @DataProvider(name = "countryName")
    Object[] dataCountry() {
        return new Object[][]{
                {"Deutschland"},
                {"Luxemburg"}
        };
    }

    @Test(dataProvider = "countryName")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + Country filter in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_CountryFilter(String countryName) {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .chooseCountry(countryName)
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @DataProvider(name = "paymentsMethod")
    Object[] dataPaymentsMethod() {
        return new Object[][]{
                {"HypoVereinsbank"},
                {"PayPal"}
        };
    }

    @Test(dataProvider = "paymentsMethod")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + Payments Filter in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_PaymentsFilter(String paymentsMethod) {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .choosePaymentsMethods(paymentsMethod)
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @DataProvider(name = "currency")
    Object[] dataCurrency() {
        return new Object[][]{
                {"EUR"},
                {"SEK"}
        };
    }

    @Test(dataProvider = "currency")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + Currency Filter in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_CurrencyFilter(String currency) {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .chooseCurrency(currency)
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @DataProvider(name = "assemblyWarehouse")
    Object[] dataAssemblyWarehouse() {
        return new Object[][]{
                {"Основной склад"},
                {"Склад PL"}
        };
    }

    @Test(dataProvider = "assemblyWarehouse")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the search work on the Group Field filter: Link Surcharges + Assembly Warehouse Filter in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_AssemblyWarehouseFilter(String assemblyWarehouse) {
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .selectExpectedGroupField("Линк Доплаты")
                .chooseAssemblyWarehouse(assemblyWarehouse)
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
