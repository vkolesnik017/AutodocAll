package ATD.Listings.QC_2187_TyresListing;

import ATD.Tyres_dimension_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2188 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season4,tyres_brand9,tyres_group_season_brand4,tyres_size2,tyres_season_size,tyres_season_dimension9,tyres_brand_size3,tyres_season_brand_dimension,tyres_brand_dimension9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking maximize count of product in Tyres listing")
    public void testCheckMaxCountOfProductInTyresListing(String route) {
        open(route);

        new Tyres_dimension_page_Logic().presenceOfListingBlock().checkMaxCountOfProductInListing(700).checkMaxCountOfPagesInListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
