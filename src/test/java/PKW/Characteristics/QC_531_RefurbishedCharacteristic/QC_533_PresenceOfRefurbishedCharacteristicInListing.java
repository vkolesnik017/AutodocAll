package PKW.Characteristics.QC_531_RefurbishedCharacteristic;


import Common.SetUp;
import PKW.Car_parts_Logic;
import PKW.Product_page_Logic;
import PKW.Search_page_Logic;
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

public class QC_533_PresenceOfRefurbishedCharacteristicInListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListing(String route) {
        openPage(route);

        new Search_page_Logic().presenceRefurbishedCharacteristic("Zustand:  Wiederaufbereitet");
    }

    @DataProvider(name = "routesCarParts", parallel = true)
    Object[] dataProviderCarParts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts3,oe_number2");
    }

    @Test(dataProvider = "routesCarParts")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListingCarParts(String route) {
        openPage(route);

        new Car_parts_Logic().presenceRefurbishedCharacteristic("Zustand:  Wiederaufbereitet");
    }

    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product6");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListingProduct(String route) {
        openPage(route);

        new Product_page_Logic().presenceRefurbishedCharacteristic("Zustand  Wiederaufbereitet");
    }

    @DataProvider(name = "routesWithArticle", parallel = true)
    Object[] dataProviderWithArticle() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "search2");
    }

    @Test(dataProvider = "routesWithArticle")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListingWithArticle(String route) {
        openPage(route);

        new Search_page_Logic().presenceRefurbishedCharacteristicInListingWithArticle("Zustand:  Wiederaufbereitet", "R");
    }

    @DataProvider(name = "routesWithArticleCart", parallel = true)
    Object[] dataProviderWithArticleCart() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts4,oe_number3");
    }

    @Test(dataProvider = "routesWithArticleCart")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListingWithArticleCart(String route) {
        openPage(route);

        new Car_parts_Logic().presenceRefurbishedCharacteristicInListingProductWithArticle("Zustand:  Wiederaufbereitet", "R");
    }

    @DataProvider(name = "routesProductArtNum", parallel = true)
    Object[] dataProviderProductArtNum() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product7");
    }

    @Test(dataProvider = "routesProductArtNum")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic at listing and product page")
    public void testChecksPresenceOfRefurbishedCharacteristicInListingProductArtNum(String route) {
        openPage(route);

        new Product_page_Logic().presenceRefurbishedCharacteristicWithArticle("Henkel Parts", "Zustand  Wiederaufbereitet", "R");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
