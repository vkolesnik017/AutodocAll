package ATD.ACC.QC_1383_BlockFilterByBrandOnListingAccessories;

import ATD.Listing_accessories_page_Logic;
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

public class QC_1385 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks that selected brand become not active after clicking on it, checks that Selected brands displayed active and entered at top of list after they selected")
    public void testCheckSortingProductsByBrands(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checkingLocationAndActivityBrandsAfterTheySelected()
                .clickFirstActiveBrand()
                .checkResetSelectedFilterByBrand();
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProviders() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks the sorting of Products with one brand selected then with two")
    public void testCheckSortingsProductsByBrands(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checksSortingProductsWithOneBrandThenWithTwo();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
