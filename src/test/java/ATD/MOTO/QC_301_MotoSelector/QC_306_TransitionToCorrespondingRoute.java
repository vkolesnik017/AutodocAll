package ATD.MOTO.QC_301_MotoSelector;

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
import static com.codeborne.selenide.Selenide.*;

public class QC_306_TransitionToCorrespondingRoute {

    private Moto_Category_car_list_page_Logic carListPage = new Moto_Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRoute(String route) throws SQLException {
        openPage(route);

        new Moto_Category_page_Logic()
                .selectMotoInSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_car_list3"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .selectMotoModel("12008").selectMotoMotor("135713").clickOnSearchButton();
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_car_list3"));
    }


    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2,moto_category_car_list_model2");
    }


    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteCarList(String route) throws SQLException {
        openPage(route);

        String headline = carListPage.getTextFromHeadline();
        carListPage.selectMotoModel("12008").selectMotoMotor("135713").clickOnSearchButton()
                .visibilityOfTecDocListing(headline)
                .checkCurrentUrl("moto_category_car_list3");

    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteParentCategory(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .selectMotoInSelector("4081", "12008", "135713")
                .checkCurrentUrl("moto_catalog");
    }

    @DataProvider(name = "routesParentCategoryMaker", parallel = true)
    Object[] dataProviderParentCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesParentCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteParentCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .selectMotoModel("12008").selectMotoMotor("135713").clickOnSearchButton()
                .checkCurrentUrl("moto_catalog");
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers,moto_categories_maker2");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteCategoriesMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .selectMotoInSelector("4081", "12008", "135713").visibilityOfTecDocCatalog();
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
    }


    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_categories");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .selectMotoInSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteCatalogModel(String route) throws SQLException {
        open(route);

        new Moto_Catalog_model_page_Logic()
                .selectMotoInSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to corresponding rout with selected moto ")
    public void testChecksTransitionToCorrespondingRouteMain(String route) throws SQLException {
        open(route);

        new Moto_main_page_Logic()
                .selectMotoInHorizontalMotoSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
