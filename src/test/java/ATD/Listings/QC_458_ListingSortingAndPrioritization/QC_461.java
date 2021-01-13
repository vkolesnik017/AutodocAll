package ATD.Listings.QC_458_ListingSortingAndPrioritization;

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

public class QC_461 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Comparing products between listing modes and check presence elements for OEN listing")
    public void testCompareProductsBetweenListingViewModesOnOEN(String route) {
        openPage(route);
        new Listing_page_Logic().checksImportantElementsOnOenListing()
                .compareProductsOrderBetweenListModeAndTileMode()
                .checksImportantElementsOnOenListing();
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
