package LKW_trucks.QC_73_TopProductsBlock;

import ATD.*;
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

public class QC_74_HeadlineOfTopProductsBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker2,lkw_category_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsBlock(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoryMakerBrand", parallel = true)
    Object[] dataProviderCategoryMakerBrand() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMakerBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsBlockMakerBrand(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11,lkw_maker_car_list2,lkw_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsBlockCategories(String route) {
        openPage(route);
        new LKW_Categories_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsParentCategory(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of TOP products block")
    public void testChecksHeadlineOfTopProductsMain(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .visibilityOfHeadlineOfTopProductsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
