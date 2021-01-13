package ATD.LKW_trucks.QC_27_BlockOfBrandsAndModelsOfCarManufacturers;

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

public class QC_30 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    DataBase db = new DataBase("ATD");

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfBrand(String route) throws SQLException {
        openPage(route);
        new LKW_main_page_Logic().checkTransitionAtIconOfTruckBrand();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_categories_maker"));

    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfBrandMakers(String route) throws SQLException {
        openPage(route);
        new LKW_makers_page_Logic()
                .checkTransitionAtIconOfTruckBrand();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_categories_maker"));

    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfBrandCategory(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic()
                .checkTransitionAtIconOfTruckBrand();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_maker5"));

    }


    @DataProvider(name = "routesCategoryBrand", parallel = true)
    Object[] dataProviderCategoryBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand");
    }

    @Test(dataProvider = "routesCategoryBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfBrandCategoryBrand(String route) throws SQLException {
        openPage(route);
        new LKW_Category_brand_page_Logic()
                .checkTransitionAtIconOfTruckBrand();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_maker_brand6"));

    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfModelCategoryMaker(String route) throws SQLException {
        openPage(route);
        new LKW_Category_maker_Logic()
                .checkTransitionAtIconOfTruckModel();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_car_list49"));

    }

    @DataProvider(name = "routesCategoryMakerBrand", parallel = true)
    Object[] dataProviderCategoryMakerBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMakerBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfModelCategoryMakerBrand(String route) throws SQLException {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .checkTransitionAtIconOfTruckModel();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_model_brand2"));

    }


    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition by click at brand/model in TOP model/brand block")
    public void testChecksTransitionByClickAtImageOfModelCategoriesMaker(String route) throws SQLException {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .checkTransitionAtIconOfTruckModel();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_maker_car_list15"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
