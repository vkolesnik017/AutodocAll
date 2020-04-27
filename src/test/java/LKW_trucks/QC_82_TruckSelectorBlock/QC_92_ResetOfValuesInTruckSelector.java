package LKW_trucks.QC_82_TruckSelectorBlock;

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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_92_ResetOfValuesInTruckSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main,lkw_parent_category,lkw_category2,lkw_category_brand,lkw_makers");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelector(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfCarBrandFieldInVerticalSelector();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategories(String route) {
        openPage(route);
        new LKW_Categories_page_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfCarBrandFieldInVerticalSelector()
                .checkSuccessfullyLKWCategoriesPageLoading();
    }

    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector at Product route")
    public void testChecksResetOfValuesInTruckSelectorProduct(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .selectBrandOfCarInHorizontalSelector("131")
                .resetOfCarBrandFieldInVerticalSelector();
    }
    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategoryMaker(String route) {
        openPage(route);
        new LKW_Category_maker_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfVerticalSelector()
                .checkSuccessfullyChildCategoryPageLoading();
    }

    @DataProvider(name = "routesCategoryCarListSecond", parallel = true)
    Object[] dataProviderCategoryCarListSecond() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "routesCategoryCarListSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategoryCarListSecond(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfOpenVerticalSelector()
                .checkSuccessfullyChildCategoryPageLoading();
    }
    @DataProvider(name = "routesCategoryMakerBrand", parallel = true)
    Object[] dataProviderCategoryMakerBrand() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMakerBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategoryMakerBrand(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfVerticalSelector()
                .checkSuccessfullyCategoryBrandPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mf-mann-filter");
    }

    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderMakerCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorMakerCarList(String route) {
        open(route);
        new LKW_maker_car_list_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfVerticalSelector()
                .checkSuccessfullyLKWCategoriesPageLoading();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker2");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfVerticalSelector()
                .checkSuccessfullyLKWCategoriesPageLoading();
    }

    @DataProvider(name = "routesCategoryCarList", parallel = true)
    Object[] dataProviderCategoryCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCategoryCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in truck selector")
    public void testChecksResetOfValuesInTruckSelectorCategoryCarList(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .openVerticalSelector()
                .selectBrandOfCarInVerticalSelector("131")
                .resetOfVerticalSelector()
                .checkSuccessfullyLKWCategoriesPageLoading();
    }
    @AfterMethod
    private void tearDown() {
        close();
    }
}
