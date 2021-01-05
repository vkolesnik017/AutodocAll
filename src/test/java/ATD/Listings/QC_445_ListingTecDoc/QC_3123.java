package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Category_car_list_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import AWS.PriceProductDescription_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3123 {
    LKW_Category_car_list_page_Logic lkwCarListPage = new LKW_Category_car_list_page_Logic();
    Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list47");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlock(String route) {
        openPage(route);
        List<String> idOfProduct = lkwCarListPage.getIdOfProduct();
        List<String> priceTitle = lkwCarListPage.getPriceTitle();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list58,listing_instruments8,category_oen25,category_oen26");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlockCarList(String route) {
        openPage(route);
        List<String> idOfProduct = carListPage.getIdOfProduct();
        List<String> priceTitle = carListPage.getPriceTitle();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @DataProvider(name = "routesCarListTable", parallel = true)
    Object[] dataProviderCarListTable() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list59,listing_instruments9");
    }

    @Test(dataProvider = "routesCarListTable")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlockCarListTable(String route) {
        openPage(route);
        List<String> idOfProduct = carListPage.getIdOfProductTableView();
        List<String> priceTitle = carListPage.getPriceTitleTableView();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
