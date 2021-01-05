package ATD.Search.QC_536_SearchBasicFunctionality;

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

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_541 {

    private String oenNumber = "zzmf18861";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "The test verifies that at the listing have only products with OEN number ZZMF18861 after search by text ZZMF18861")
    public void testSearchByOenNumber(String route) {
        open(route);
        new Main_page_Logic().useSearch(oenNumber);
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(oenNumber);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products with OEN number ZZMF18861 after search by text ZZMF18861")
    public void testSearchByOenNumberLKW(String route) {
        open(route);
        new Main_page_Logic().useSearch(oenNumber);
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(oenNumber);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
