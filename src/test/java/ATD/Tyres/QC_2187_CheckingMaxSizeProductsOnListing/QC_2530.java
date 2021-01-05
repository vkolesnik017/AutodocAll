package ATD.Tyres.QC_2187_CheckingMaxSizeProductsOnListing;

import ATD.Tyre_form_page_Logic;
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

public class QC_2530 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension10,tyres_size2,tyres_season_dimension8,tyres_dimension12,tyres_dimension13");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking maximum product count in listing with selected brand")
    public void testCheckMaxSizeOfProductsInListingWithSelectedBrand(String route) {
        open(route);

        new Tyres_dimension_page_Logic().presenceOfListingBlock().selectAnyBrand(0).checkMaxCountOfProductInListing(700);
    }

    @DataProvider(name = "routesForm", parallel = true)
    Object[] dataProviderForm() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form8");
    }

    @Test(dataProvider = "routesForm")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking maximum product count in listing with selected brand")
    public void testCheckMaxSizeOfProductsInListingWithSelectedBrandForm(String route) {
        open(route);

        new Tyre_form_page_Logic().presenceOfListingBlock().selectAnyBrand(0).checkMaxCountOfProductInListing(700);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
