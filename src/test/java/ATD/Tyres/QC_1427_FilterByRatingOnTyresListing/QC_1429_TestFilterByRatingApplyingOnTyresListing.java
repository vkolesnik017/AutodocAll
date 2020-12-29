package ATD.Tyres.QC_1427_FilterByRatingOnTyresListing;

import ATD.Listing_page_Logic;
import Common.SetUp;
import ATD.TyresListing_page_Logic;
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

public class QC_1429_TestFilterByRatingApplyingOnTyresListing {

    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_season6,tyres_season7,tyres_dimension6," +
                "tyres_season,tyres_season8,tyres_season9,tyres_dimension7,tyres_season5,tyres_season10,tyres_season11,tyres_dimension8,tyres_season12,tyres_season4,tyres_dimension4" +
                "tyres_brand_dimension2,tyres_brand_dimension3,tyres_brand_dimension4,tyres_brand_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Filter By Rating Applying On Tyres Listing")
    public void testFilterByRatingApplyingOnTyresListing(String route) {
        openPage(route);
        listingPageLogic.clickThreeRatingStarsInFilter()
                        .waitUntilPreloaderDisappear();
        tyresListingPageLogic.checkThreeStarsRatingInEveryProductOnListingTyres();
        listingPageLogic.clickFiveRatingStarsInFilter()
                        .waitUntilPreloaderDisappear();
        tyresListingPageLogic.checkFiveStarsRatingInEveryProductOnListingTyres();
        listingPageLogic.clickFiveRatingStarsInFilter()
                        .waitUntilPreloaderDisappear();
        tyresListingPageLogic.checkTwoUniqueRatingOnTyresListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
