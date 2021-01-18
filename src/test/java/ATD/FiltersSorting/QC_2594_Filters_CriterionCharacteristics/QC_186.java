package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;


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

public class QC_186 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list54");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list8");
    }

    //TODO проверка на данном руте отключена, так как фильтр по рейтенгу отключен.
    @Test(dataProvider = "routes", enabled = false)
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
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search6"));
        new Listing_page_Logic().checkRatingfilterIsNotPresent();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
