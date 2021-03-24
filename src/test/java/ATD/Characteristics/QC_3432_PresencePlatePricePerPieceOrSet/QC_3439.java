package ATD.Characteristics.QC_3432_PresencePlatePricePerPieceOrSet;

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


public class QC_3439 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false); }


    @DataProvider(name = "routeOem", parallel = true)
    Object[] dataProviderOem() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE","main", "category_oen28");
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the display of the item (Price per set) on tecdoc and OEM listing")
    public void testOemChecksDisplayItemPricePerSetOnTecdocAndOemListing(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceLeastOneElementPricePerSetOrPiece("set")
                .checkingChangeDisplayProductsAsListAndGrid()
                .checkPresenceLeastOneElementPricePerSetOrPiece("set");
    }


    @DataProvider(name = "routeLkw", parallel = true)
    Object[] dataProviderLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE","lkw_main", "lkw_category_car_list51");
    }

    @Test(dataProvider = "routeLkw")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the display of the item (Price per set) on tecdoc and OEM listing")
    public void testLkwChecksDisplayItemPricePerSetOnTecdocAndOemListing(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceLeastOneElementPricePerSetOrPiece("set")
                .checkingChangeDisplayProductsAsListAndGrid()
                .checkPresenceLeastOneElementPricePerSetOrPiece("set");
    }


    @DataProvider(name = "routeMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE","moto_main", "moto_category_car_list_model8");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the display of the item (Price per set) on tecdoc and OEM listing")
    public void testMotoChecksDisplayItemPricePerSetOnTecdocAndOemListing(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceLeastOneElementPricePerSetOrPiece("set");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
