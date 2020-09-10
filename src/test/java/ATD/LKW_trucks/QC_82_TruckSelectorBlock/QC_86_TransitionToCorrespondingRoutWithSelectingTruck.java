package ATD.LKW_trucks.QC_82_TruckSelectorBlock;

import ATD.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_86_TransitionToCorrespondingRoutWithSelectingTruck {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckParentCategory(String route) throws SQLException {
        openPage(route);
        new LKW_Parent_Category_page_Logic()
                .selectTruckInSelector("24", "714", "1004434");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_maker_car_list4"));
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategory(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .selectTruckInSelector("24", "714", "1004434")
                .checkSuccessfullyLKWCategoryCarListPageLoading("ersatzteile/olfilter-200157/daf/45?car_id=1004434");
    }

    @DataProvider(name = "routesCategoryCarList", parallel = true)
    Object[] dataProviderCategoryCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "routesCategoryCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategoryCarList(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .selectTruckInSelector("24", "714", "1004434")
                .checkSuccessfullyLKWCategoryCarListPageLoading("ersatzteile/olfilter-200157/daf/45?car_id=1004434");
    }

    @DataProvider(name = "routesCategoryBrand", parallel = true)
    Object[] dataProviderCategoryBrand() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategoryBrand(String route) {
        openPage(route);
        new LKW_Category_brand_page_Logic()
                .selectTruckInSelector("24", "714", "1004434")
                .checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/daf/45?car_id=1004434&supplier%5B%5D=4")
                .visibilityOfSelectedBrand()
                .checkTitleOfProductWithSelectedBrand("MANN-FILTER");
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_maker_car_list2,lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategories(String route) throws SQLException {
        openPage(route);
        new LKW_Categories_page_Logic()
                .selectTruckInSelector("24", "714", "1004434");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_maker_car_list4"));
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_makers,404");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckMakers(String route) {
        openPage(route);
        new LKW_makers_page_Logic()
                .selectTruckInSelector("74", "4665", "1006478")
                .checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/unimog?car_id=1006478");

    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategoriesMaker(String route) throws SQLException {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .selectTruckInSelector("74", "1587", "1000784");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_category_car_list11"));
    }


    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding route with selecting truck")
    public void testChecksTransitionToCorrespondingRoutWithSelectingTruckCategoriesMain(String route) throws SQLException {
        openPage(route);
        new LKW_main_page_Logic()
                .selectTruckInSelector("2242", "8959", "1012748");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_maker_car_list6"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
