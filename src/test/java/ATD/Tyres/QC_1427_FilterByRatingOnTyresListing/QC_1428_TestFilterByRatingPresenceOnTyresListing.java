package ATD.Tyres.QC_1427_FilterByRatingOnTyresListing;


import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1428_TestFilterByRatingPresenceOnTyresListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_season6,tyres_season7,tyres_dimension6," +
                "tyres_season,tyres_season8,tyres_season9,tyres_dimension7,tyres_season5,tyres_season10,tyres_season11,tyres_dimension8,tyres_season12,tyres_season4,tyres_dimension4" +
                "tyres_brand_dimension2,tyres_brand_dimension3,tyres_brand_dimension4,tyres_brand_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Filter By Rating Presence On Tyres Listing")
    public void testFilterByRatingPresenceOnTyresListing(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkRatingFilterVisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
