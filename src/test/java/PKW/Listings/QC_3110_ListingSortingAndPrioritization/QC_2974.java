package PKW.Listings.QC_3110_ListingSortingAndPrioritization;

import PKW.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2974 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "criteria_parts_group2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chebanenko")
    @Description(value = "Comparing products between listing modes and check presence elements on listing synthetics & criteria")
    public void testCompareProductsBetweenListingViewModesOnTecDoc(String route) {
        openPage(route);
        new Listing_page_Logic().checksElementsOnListing()
                .compareProductsOrderBetweenListModeAndTableMode(10)
                .checksElementsOnListing();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}