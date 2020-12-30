package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import Common.DataBase;
import ATD.Listing_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_121_FiltersSorting_TestSideFilterInTileMode {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @DataProvider(name = "routesLKWsearch", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list28");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter in tile mode")
    public void testSideFilterInTileMode(String route) throws Exception {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute());
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox())
                    .waitUntilPreloaderDisappearAndSleep(3000)
                    .clickShowListingInTileModeButton()
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingInTileMode(characteristic, listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter in tile mode LKW")
    public void testSideFilterInTileModeLKW() throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list"));
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterCheckboxLKW700());
        listingPage.clickFilterButton(listingPage.langeFilterCheckboxLKW700())
                .waitUntilPreloaderDisappearAndSleep(3000)
                .clickShowListingInTileModeButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingInTileMode(characteristic, listingPage.langeProductAttributeTecdocRouteLKW());
    }

    @Test(dataProvider = "routesLKWsearch", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter in tile mode LKW search")
    public void testSideFilterInTileModeLKWsearch(String route) throws Exception {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute());
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox())
                .waitUntilPreloaderDisappearAndSleep(3000)
                .clickShowListingInTileModeButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingInTileMode(characteristic, listingPage.langeProductAttributeTecdocRoute());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
