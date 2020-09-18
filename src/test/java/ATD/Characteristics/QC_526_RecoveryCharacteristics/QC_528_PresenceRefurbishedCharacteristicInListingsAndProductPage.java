package ATD.Characteristics.QC_526_RecoveryCharacteristics;

import ATD.Category_car_list_page_Logic;
import ATD.Product_page_Logic;
import ATD.Search_page_Logic;
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

public class QC_528_PresenceRefurbishedCharacteristicInListingsAndProductPage {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPage(String route) {
        openPage(route);
        new Search_page_Logic().presenceRefurbishedCharacteristic("Zustand  Wiederaufbereitet");
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list13,category_oen5");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageCarList(String route) {
        openPage(route);
        new Category_car_list_page_Logic().presenceRefurbishedCharacteristic("Zustand  Wiederaufbereitet");
    }
    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product41");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageProduct(String route) {
        openPage(route);
        new Product_page_Logic().presenceRefurbishedCharacteristic("Zustand  Wiederaufbereitet");
    }

    @DataProvider(name = "routesWithArticle", parallel = true)
    Object[] dataProviderWithArticle() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search33");
    }

    @Test(dataProvider = "routesWithArticle")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageWithArticle(String route) {
        openPage(route);
        new Search_page_Logic().presenceRefurbishedCharacteristicInListingWithArticle("Zustand Wiederaufbereitet", "R");
    }

    @DataProvider(name = "routesCarListWithArticle", parallel = true)
    Object[] dataProviderCarListWithArticle() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list43,category_oen16");
    }

    @Test(dataProvider = "routesCarListWithArticle")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageCarListWithArticle(String route) {
        openPage(route);
        new Category_car_list_page_Logic().presenceRefurbishedCharacteristicInListingProductWithArticle("Zustand Wiederaufbereitet", "R");
    }

    @DataProvider(name = "routesProductWithArticle", parallel = true)
    Object[] dataProviderProductWithArticle() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product40");
    }

    @Test(dataProvider = "routesProductWithArticle")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
    public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageProductWithArticle(String route) {
        openPage(route);
        new Product_page_Logic().presenceRefurbishedCharacteristicWithArticle("Henkel Parts", "Zustand  Wiederaufbereitet", "R");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
