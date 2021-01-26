package ATD.Search.QC_536_SearchLogic;

import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
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

public class QC_537 {

    Main_page_Logic mainPage = new Main_page_Logic();
    Listing_page_Logic listingPage = new Listing_page_Logic();
    private String brandName = "RIDEX";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list4");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "The test verifies that at the listing have only products of brand ridex after search by text RIDEX")
    public void testSearchByBrand(String route) {
        openPage(route);
        mainPage.useSearch(brandName);
        listingPage.checksProductTitlesContainExpectedTextGoingAllPagination(brandName);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list14");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products of brand ridex after search by text RIDEX")
    public void testSearchByBrandLKW(String route) {
        openPage(route);
        mainPage.useSearch(brandName);
        listingPage.checksProductTitlesContainExpectedTextGoingAllPagination(brandName);
    }

    @DataProvider(name = "routeMoto")
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog6");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products of brand ridex after search by text RIDEX")
    public void testSearchByBrandMoto(String route) {
        openPage(route);
        mainPage.useSearch(brandName);
        listingPage.checksProductTitlesContainExpectedTextGoingAllPagination(brandName);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
