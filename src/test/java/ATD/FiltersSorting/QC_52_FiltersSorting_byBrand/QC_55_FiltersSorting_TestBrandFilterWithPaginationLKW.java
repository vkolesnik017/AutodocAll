package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_55_FiltersSorting_TestBrandFilterWithPaginationLKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search23");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list5");
    }

    @DataProvider(name = "routesLKWnoCar", parallel = true)
    Object[] dataProviderLKWnoCar() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search8");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination (LKW listing)")
    public void checkBrandFilterPaginationLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        new Listing_page_Logic().checkBrandFilterWithSixBrandsPagination();
    }

    @Test(dataProvider = "routes", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination")
    public void checkBrandFilterPaginationWithoutCar(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        new Listing_page_Logic().checkBrandFilterWithTwoBrandsPagination();
    }

    @Test(dataProvider = "routesLKWnoCar", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination")
    public void checkBrandFilterPaginationWithoutCarLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        new Listing_page_Logic().checkBrandFilterWithTwoBrandsPaginationLKW();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
