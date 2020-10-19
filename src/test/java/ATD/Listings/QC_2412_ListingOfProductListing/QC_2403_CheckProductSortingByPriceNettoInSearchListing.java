package ATD.Listings.QC_2412_ListingOfProductListing;

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
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2403_CheckProductSortingByPriceNettoInSearchListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    private Main_page_Logic mainPage = new Main_page_Logic();
   private List<String> searchValues = Arrays.asList("BOSCH", "Scheibenwischer", "Bremsscheiben", "Motoröl", "agr ventil");


    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "test check product sorting by price netto in search listing")
    public void testCheckProductSortingByPriceNettoInSearchListing(String route) throws SQLException {
        openPage(route);
        mainPage.checkProductSortingByPriceNetto(searchValues);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
