package ATD.FiltersSorting.QC_185_FiltersSorting_byRating;


import ATD.DataBase;
import ATD.Listing_page_Logic;
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

public class QC_186_FiltersSorting_TestOutputWithFilterByRating {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list8");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks output with filter by rating")
    public void testOutputWithFilterByRating(String route) {
        openPage(route);
        new Listing_page_Logic().clickFourRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkFourStarsRatingInEveryProductOnListing()
                                .clickFiveRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkFiveStarsRatingInEveryProductOnListing()
                                .refreshPage()
                                .checkFiveStarsRatingInEveryProductOnListing()
                                .clickFiveRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkTwoUniqueRatingOnListing();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks output with filter by rating LKW")
    public void testOutputWithFilterByRatingLKW(String route) {
        openPage(route);
        new Listing_page_Logic().clickThreeRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkThreeStarsRatingInEveryProductOnListing()
                                .clickFiveRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkFiveStarsRatingInEveryProductOnListing()
                                .refreshPage()
                                .checkFiveStarsRatingInEveryProductOnListing()
                                .clickFiveRatingStarsInFilter()
                                .waitUntilPreloaderDisappear()
                                .checkTwoUniqueRatingOnListing();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks that rating filter is not present on search route")
    public void testRatingFilterPresenceOnSearchRoute() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "search6"));
        new Listing_page_Logic().checkRatingfilterIsNotPresent();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
