package ATD.Listings.QC_2412_ListingOfProductListing;

import ATD.Maker_car_list_page_Logic;
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

public class QC_2674 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    private List<String> searchValues = Arrays.asList("Kupplung", "agr ventil", "Zahnriemensatz mit waserpumpe");


    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list16");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "test check product sorting by price netto in search listing with selected car")
    public void testCheckProductSortingByPriceNettoInSearchListingWithSelectedCar(String route) throws SQLException {
        openPage(route);
        new Maker_car_list_page_Logic().checkProductSortingByPriceNetto(searchValues);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
