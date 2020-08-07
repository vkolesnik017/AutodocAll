package LKW_trucks.QC_73_TopProductsBlock;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_75_ProductsCountInTopBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_maker_car_list2,lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability and count of products in TOP block")
    public void testChecksAvailabilityOfTopProducts(String route) {
        openPage(route);
        new LKW_Categories_page_Logic()
                .availabilityOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker2,lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability and count of products in TOP block")
    public void testChecksAvailabilityOfTopProductsCategory(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .availabilityOfTopProductsBlock();
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability and count of products in TOP block")
    public void testChecksAvailabilityOfTopProductsParentCategory(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic()
                .availabilityOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderParentCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability and count of products in TOP block")
    public void testChecksAvailabilityOfTopProductsCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .availabilityOfTopProductsBlock()
                .availabilityOfForwardBackLinksOfTopBLock();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability and count of products in TOP block")
    public void testChecksAvailabilityOfTopProductsMain(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .availabilityOfTopProductsBlock()
                .availabilityOfForwardBackLinksOfTopBLock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
